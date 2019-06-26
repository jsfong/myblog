package com.jsfong.myblog.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jsfong.myblog.entities.Blogpost;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateBlogStep extends CucumberRoot {

	final String uri = "/blog";
	private Blogpost samplePost;
	private Blogpost updatedPost;
	private String sampleTitle = "Sample title";
	private String sampleBody = "Sample body";
	private String updateTitle = "Update title";
	private String updateBody = "Update body";

	private ResponseEntity<Blogpost> response; // output

	/**
	 * GIVEN
	 */
	// Given there is a blog created
	@Given("^there is a blog created$")
	public void there_is_a_blog_created() {
		samplePost = new Blogpost();
		samplePost.setTitle(sampleTitle);
		samplePost.setBody(sampleBody);
		response = template.postForEntity(uri, samplePost, Blogpost.class);
		samplePost = response.getBody();
	}

	/**
	 * WHEN
	 */
	// when client makes a call to POST /blog
	@When("^the client makes a call to POST /blog$")
	public void the_client_makes_a_call_to_POST() throws Throwable {
		Blogpost newBlogpost = new Blogpost();
		newBlogpost.setTitle(sampleTitle);
		newBlogpost.setBody(sampleBody);
		response = template.postForEntity(uri, newBlogpost, Blogpost.class);

		System.out.println(response.getBody());
	}

	// When the client makes a call to GET /blog with id
	@When("^the client makes a call to GET /blog with id$")
	public void the_client_makes_a_call_to_GE_with_id() {
		String id = String.valueOf(samplePost.getId());
		response = template.getForEntity(uri + "/" + id, Blogpost.class);
	}

	// When the client makes a call to PUT /blog to update the blog created by id
	@When("^the client makes a call to PUT /blog to update the blog created by id$")
	public void the_client_makes_a_call_to_PUT_to_update_the_blog_created_by_id() {
		updatedPost = new Blogpost();
		updatedPost.setId(samplePost.getId());
		updatedPost.setTitle(updateTitle);
		updatedPost.setBody(updateBody);
		String id = String.valueOf(samplePost.getId());
		HttpEntity<Blogpost> requestEntity = new HttpEntity<>(updatedPost);
		response = template.exchange(uri+"/"+id, HttpMethod.PUT, requestEntity, Blogpost.class);

	}
	
	//When the client makes a call to DELETE /blog by id
	@When("^the client makes a call to DELETE /blog by id$")
	public void the_client_makes_a_call_to_DELETE_by_id() {
		String id = String.valueOf(samplePost.getId());
		response = template.exchange(uri+"/"+id, HttpMethod.DELETE, null, Blogpost.class);
	}

	/**
	 * THEN
	 */
	// Then the client receives response status code of 200
	@Then("^the client receives response status code of (\\d+)$")
	public void the_client_receives_response_status_code_of(int statusCode) throws Throwable {
		int currentStatusCode = response.getStatusCodeValue();
		assertEquals(currentStatusCode, statusCode);
	}

	/**
	 * AND
	 */
	// And the client receives back the blog entered
	@And("^the client receives back the blog entered$")
	public void the_client_receives_back_the_blog_entered() {
		assertEquals(response.getBody().getTitle(), sampleTitle);
		assertEquals(response.getBody().getBody(), sampleBody);
	}

	// And the client receives back the blog with the specific id
	@And("^the client receives back the blog with the specific id$")
	public void the_client_receives_back_the_blog_with_the_specific_id() {
		assertEquals(response.getBody().getId(), samplePost.getId());
	}
	
	//And the client receives back the updated blog with the specific id
	@And("^the client receives back the updated blog with the specific id$")
	public void the_client_receives_back_the_updated_blog_with_the_specific_id() {
		assertEquals(response.getBody().getId(), updatedPost.getId());
		assertEquals(response.getBody().getTitle(), updateTitle);
		assertEquals(response.getBody().getBody(), updateBody);
	}
}
