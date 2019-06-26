package com.jsfong.myblog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
		System.out.println("Post " + createdBlogEntry.getTitle() + createdBlogEntry.getBody() + "with id: "
				+ createdBlogEntry.getId());
		modelMap.addAttribute("msg", msg);
		return "createBlog";

	}

	@RequestMapping("/displayBlogs")
	public String displayBlogs(ModelMap modelMap) {
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		modelMap.addAttribute("blogs", allBlogEntry);
		return "displayBlogs";

	}

	@RequestMapping("/showEditBlog")
	public String showEditBlog(@Param("id") int id, ModelMap modelMap) {
		Blogpost post = service.getBlogEntryById(id);
		modelMap.addAttribute("blog", post);
		return "showEditBlog";

	}

	@RequestMapping("/updateBlog")
	public String editBlog(@ModelAttribute("blog") Blogpost post, ModelMap modelMap) {
		service.updateBlogEntry(post.getId(), post);
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		modelMap.addAttribute("blogs", allBlogEntry);
		return "displayBlogs";

	}

	@RequestMapping("/deleteBlog")
	public String deleteBlog(@Param("id") int id, ModelMap modelMap) {

		service.deleteBlogEntryById(id);
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		modelMap.addAttribute("blogs", allBlogEntry);
		return "displayBlogs";

	}
}
