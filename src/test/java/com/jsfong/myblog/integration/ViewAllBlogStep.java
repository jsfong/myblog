package com.jsfong.myblog.integration;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.service.BlogService;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ViewAllBlogStep extends CucumberRoot {
	final String uri = "/blog";
	private String sampleTitle = "Sample title";
	private String sampleBody = "Sample body";
	private String sampleTitle2 = "Sample title 2";
	private String sampleBody2 = "Sample body 2";
	private ResponseEntity<Blogpost[]> response; // output

	@Autowired
	private BlogService service;
//	Feature: Able to view all entered blogs post
//	Scenario: client makes a call to GET /blog

	// Given there are blog created
	@Given("^there are blog created$")
	public void there_are_blog_created() {

		//Clear database
		service.deleteAllBlogEntry();
	
		// Create 2 blog
		Blogpost newBlogpost = new Blogpost();
		newBlogpost.setTitle(sampleTitle);
		newBlogpost.setBody(sampleBody);
		template.postForEntity(uri, newBlogpost, Blogpost.class);

		Blogpost newBlogpost2 = new Blogpost();
		newBlogpost2.setTitle(sampleTitle2);
		newBlogpost2.setBody(sampleBody2);
		template.postForEntity(uri, newBlogpost2, Blogpost.class);

	}

	// When the client makes a call to GET /blog
	@When("^the client makes a call to GET /blog$")
	public void the_client_makes_a_call_to_GET() {
		response = template.getForEntity(uri, Blogpost[].class);
	}

	// Then the client receives responses status code of 200
	@Then("^the client receives responses status code of (\\d+)$")
	public void the_client_receives_responses_status_code_of(int statusCode) throws Throwable {
		int currentStatusCode = response.getStatusCodeValue();
		assertEquals(currentStatusCode, statusCode);
	}

	//	And the client receives back all the blog entered
	@And("^the client receives back all the blog entered$")
	public void the_client_receives_back_all_the_blog_entered() {
		Blogpost[] blogposts = response.getBody();

		System.out.println(blogposts.length);
		assertEquals(blogposts[0].getTitle(), sampleTitle);
		assertEquals(blogposts[0].getBody(), sampleBody);
		assertEquals(blogposts[1].getTitle(), sampleTitle2);
		assertEquals(blogposts[1].getBody(), sampleBody2);

	}
}
