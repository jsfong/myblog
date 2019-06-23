package com.jsfong.myblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	BlogService service;

	@RequestMapping("/showCreateBlog")
	public String showCreateBlog() {
		return "createBlog";
	}

	@RequestMapping("/saveBlog")
	public String saveBlog(@ModelAttribute("blogpost") Blogpost post, ModelMap modelMap) {
		
		System.out.println("Creating Post " + post.getTitle() + post.getBody());
		Blogpost createdBlogEntry = service.createBlogEntry(post);
		String msg = "Blog post with id: " + createdBlogEntry.getId() + " created";
		System.out.println("Post " + createdBlogEntry.getTitle() + createdBlogEntry.getBody()+ "with id: " + createdBlogEntry.getId());
		modelMap.addAttribute("msg", msg);
		return "createBlog";

	}
}
