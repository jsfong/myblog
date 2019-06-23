package com.jsfong.myblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.repos.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository repository;
	
	@Override
	public Blogpost createBlogEntry(Blogpost blogpost) {
		return repository.save(blogpost);
	}

	@Override
	public List<Blogpost> getAllBlogEntry() {
		return repository.findAll();
	}

	@Override
	public Blogpost getBlogEntryById(int id) {
		return  repository.findById(id).get();
	}

	@Override
	public Blogpost updateBlogEntry(Blogpost blogpost) {
		return repository.save(blogpost);
	}

	@Override
	public void deleteBlogEntryById(int id) {
		repository.deleteById(id);

	}

	@Override
	public void deleteAllBlogEntry() {
		repository.deleteAll();
		
	}

}
