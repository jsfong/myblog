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

import com.jsfong.myblog.entities.BlogRequireFieldNotFoundException;
import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.service.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogRestController {

	@Autowired
	BlogService service;

	// Create
	@PostMapping
	public Blogpost createBlog(@RequestBody Blogpost post) {
		
		if(post.getTitle() == null) {
			throw new BlogRequireFieldNotFoundException("Blog title should not be empty");
		}
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
	@PutMapping("{id}")
	public Blogpost updateBlog(@PathVariable("id") int id, @RequestBody Blogpost post) {	

		Blogpost blog2Update = service.getBlogEntryById(id);
	
		//Update Title if not null
		if(post.getTitle() != null) {
			blog2Update.setTitle(post.getTitle());
		}
		
		//Update Body if not null
		if(post.getBody() != null) {
			blog2Update.setBody(post.getBody());
		}
		return service.updateBlogEntry(blog2Update);
	}

	// Delete
	@DeleteMapping("/{id}")
	public void deleteBlog(@PathVariable("id") int id) {
		service.deleteBlogEntryById(id);
	}
}
