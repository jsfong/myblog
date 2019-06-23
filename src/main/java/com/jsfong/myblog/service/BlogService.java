package com.jsfong.myblog.service;

import java.util.List;

import com.jsfong.myblog.entities.Blogpost;

public interface BlogService {

	public Blogpost createBlogEntry(Blogpost blogpost);

	public List<Blogpost> getAllBlogEntry();

	public Blogpost getBlogEntryById(int id);
	
	public Blogpost updateBlogEntry(Blogpost blogpost);
	
	public void deleteBlogEntryById(int id);
	
	public void deleteAllBlogEntry();

}
