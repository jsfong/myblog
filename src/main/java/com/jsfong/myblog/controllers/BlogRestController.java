package com.jsfong.myblog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.exception.BlogNotFoundException;
import com.jsfong.myblog.exception.BlogRequireFieldNotFoundException;
import com.jsfong.myblog.service.BlogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "/blog", description = "Operations with blog", produces = "application/json")
@RequestMapping("/blog")
public class BlogRestController {

	@Autowired
	BlogService service;

	// Create
	@ApiOperation(value = "create blog", response = Blogpost.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Blog created", response = Blogpost.class),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public Blogpost createBlog(@RequestBody Blogpost post) {
		return service.createBlogEntry(post);
	}

	// Get All
	@ApiOperation(value = "Retrieve all blog", response = List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Blog retrieved", response = List.class),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Blogpost> getAllBlogs() {
		return service.getAllBlogEntry();
	}

	// Get
	@ApiOperation(value = "Retrieve blog with ID", response = Blogpost.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Blog retrieved", response = Blogpost.class),
			@ApiResponse(code = 404, message = "Blog not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	public Blogpost getBlog(@PathVariable("id") int id) {
		return service.getBlogEntryById(id);
	}

	// Update
	@ApiOperation(value = "Update blog with ID", response = Blogpost.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Blog updated", response = Blogpost.class),
			@ApiResponse(code = 404, message = "Blog not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping(path="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public Blogpost updateBlog(@PathVariable("id") int id, @RequestBody Blogpost post) {		
		return service.updateBlogEntry(id, post);
	}

	// Delete
	@ApiOperation(value = "Delete blog with ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Blog deleted"),
			@ApiResponse(code = 500, message = "Error deleting") })
	@DeleteMapping("/{id}")
	public void deleteBlog(@PathVariable("id") int id) {
		service.deleteBlogEntryById(id);
	}
}
