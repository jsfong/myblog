package com.jsfong.myblog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogRestController {

	@Autowired
	BlogService service;

	// Create
	//TODO Validation - Title is mandatory
	@PostMapping
	public Blogpost createBlog(@RequestBody Blogpost post) {
		return service.createBlogEntry(post);

	}

	// Get All
	@GetMapping
	public List<Blogpost> getAllBlogs() {
		return service.getAllBlogEntry();
	}

	// Get
	@GetMapping("/{id}")
	public Blogpost getBlog(@PathVariable("id") int id) {
		return service.getBlogEntryById(id);
	}

	// Update
	//TODO Validation-able to update partial field
	@PutMapping("{id}")
	public Blogpost updateBlog(@PathVariable("id") int id, @RequestBody Blogpost post) {		
		return service.updateBlogEntry(post);
	}

	// Delete
	@DeleteMapping("/{id}")
	public void deleteBlog(@PathVariable("id") int id) {
		service.deleteBlogEntryById(id);
	}
}
