package com.jsfong.myblog.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.exception.BlogNotFoundException;
import com.jsfong.myblog.exception.BlogRequireFieldNotFoundException;
import com.jsfong.myblog.repos.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository repository;

	@Override
	public Blogpost createBlogEntry(Blogpost blogpost) {
		if (blogpost.getTitle() == null) {
			throw new BlogRequireFieldNotFoundException("Blog title should not be empty");
		}
		return repository.save(blogpost);
	}

	@Override
	public List<Blogpost> getAllBlogEntry() {
		return repository.findAll();
	}

	@Override
	public Blogpost getBlogEntryById(int id) {
		Blogpost blogpost = null;
		try {
			blogpost = repository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new BlogNotFoundException("Blog not found");
		}
		return blogpost;
	}

	@Override
	public Blogpost updateBlogEntry(Blogpost blogpost) {
		Blogpost blog2Update = null;

		try {
			blog2Update = repository.findById(blogpost.getId()).get();
		} catch (NoSuchElementException e) {
			throw new BlogNotFoundException("Blog not found");
		}

		// Update Title if not null
		if (blogpost.getTitle() != null) {
			blog2Update.setTitle(blogpost.getTitle());
		}

		// Update Body if not null
		if (blogpost.getBody() != null) {
			blog2Update.setBody(blogpost.getBody());
		}

		return repository.save(blog2Update);
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
