package com.jsfong.myblog.unittest;

import static org.junit.Assert.*;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.exception.BlogNotFoundException;
import com.jsfong.myblog.exception.BlogRequireFieldNotFoundException;
import com.jsfong.myblog.service.BlogService;
import com.jsfong.myblog.service.BlogServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
//TOOD update
	@Autowired
	private BlogService service;

	private Blogpost samplePost = new Blogpost();
	private String title = "Sample title";
	private String body = "Sample body";

	private void testSetup() {

		// Add one entry for testing
		samplePost = new Blogpost();
		samplePost.setTitle(title);
		samplePost.setBody(body);
		service.createBlogEntry(samplePost);
	}

	@After
	public void testCleanup() {

		// Clean database
		service.deleteAllBlogEntry();
	}

	@Test
	public void testCreateBlog() {

		String title = "Sample title2";
		String body = "Sample body2";
		Blogpost blog = new Blogpost();
		blog.setTitle(title);
		blog.setBody(body);

		// Create an blog entry
		service.createBlogEntry(blog);

	}

	@Test
	public void testServiceViewAllBlog() {

		testSetup();
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		assertEquals(1, allBlogEntry.size());
	}

	@Test
	public void testServiceViewBlogById() {

		testSetup();
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		Blogpost post2Retrive = allBlogEntry.get(0);
		Blogpost postRetrieved = service.getBlogEntryById(post2Retrive.getId());

		assertEquals(post2Retrive.getId(), postRetrieved.getId());
		assertEquals(post2Retrive.getTitle(), postRetrieved.getTitle());
		assertEquals(post2Retrive.getBody(), postRetrieved.getBody());

	}

	@Test
	public void testServiceUpdateBlogpost() {

		testSetup();
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		Blogpost post2Update = allBlogEntry.get(0);
		post2Update.setTitle("Updated");
		Blogpost postUpdated = service.updateBlogEntry(post2Update.getId(), post2Update);

		assertEquals(post2Update.getTitle(), postUpdated.getTitle());

	}

	@Test
	public void testServiceDeleteBlogpostById() {
		testSetup();
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		Blogpost post2Delete = allBlogEntry.get(0);
		service.deleteBlogEntryById(post2Delete.getId());

	}

	@Test(expected = BlogNotFoundException.class)
	public void testServiceUpdateBlogpostWrongId() {
		testSetup();
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		Blogpost post2Update = allBlogEntry.get(0);
		post2Update.setTitle("Updated");
		service.updateBlogEntry(post2Update.getId() - 1, post2Update);

	}

	@Test(expected = BlogNotFoundException.class)
	public void testServiceGetBlogpostWrongId() {
		testSetup();
		List<Blogpost> allBlogEntry = service.getAllBlogEntry();
		Blogpost firstBlog = allBlogEntry.get(0);
		service.getBlogEntryById(firstBlog.getId() - 1);
	}

	@Test(expected = BlogRequireFieldNotFoundException.class)
	public void testServiceCreateBlogMissingTitle() {

		String body = "Sample body2";
		Blogpost blog = new Blogpost();
		blog.setBody(body);

		// Create an blog entry
		service.createBlogEntry(blog);
	}

}
