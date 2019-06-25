package com.jsfong.myblog.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jsfong.myblog.entities.Blogpost;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateBlogStep extends CucumberRoot {

	final String uri = "/blog";
	private String sampleTitle = "Sample title";
	private String sampleBody = "Sample body";
	private ResponseEntity<Blogpost> response; // output
	
	//When the client makes a call /blog
	@When("^the client makes a call /blog$")
	public void the_client_makes_a_call_blog() throws Throwable {
		Blogpost newBlogpost = new Blogpost();
		newBlogpost.setTitle(sampleTitle);
		newBlogpost.setBody(sampleBody);
		response = template.postForEntity(uri, newBlogpost, Blogpost.class);
		
		System.out.println(response.getBody());
	}
	
	//Then the client receives response status code of 200
	@Then("^the client receives response status code of (\\d+)$")
	public void the_client_receives_response_status_code_of(int statusCode) throws Throwable {
		int currentStatusCode = response.getStatusCodeValue();
		assertEquals(currentStatusCode, statusCode);
	}
	
	//And the client receives back the blog entered
	@And("^the client receives back the blog entered$")
	public void the_client_receives_back_the_blog_entered() {
		assertEquals(response.getBody().getTitle(), sampleTitle);
		assertEquals(response.getBody().getBody(), sampleBody);
	}
}
