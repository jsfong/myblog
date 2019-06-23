package com.jsfong.myblog;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jsfong.myblog.entities.Blogpost;
import com.jsfong.myblog.repos.BlogRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoTests {

	@Autowired
	private BlogRepository blogRepository;

	@Before
	public void testSetup() {

		// Add one entry for testing
		String title = "Sample title";
		String body = "Sample body";
		Blogpost blog = new Blogpost();
		blog.setTitle(title);
		blog.setBody(body);
		blogRepository.save(blog);
	}

	@After
	public void testCleanup() {

		// Clean database
		blogRepository.deleteAll();
	}

	@Test
	public void testRepoCreateBlog() {

		String title = "Sample title";
		String body = "Sample body";
		Blogpost blog = new Blogpost();
		blog.setTitle(title);
		blog.setBody(body);
		blogRepository.save(blog);

	}

	@Test
	public void testRepoViewAllBlog() {
		blogRepository.findAll();
	}

	@Test
	public void testRepoViewBlogById() {

		List<Blogpost> blogposts = blogRepository.findAll();
		Blogpost firstPost = blogposts.get(0);
		blogRepository.findById(firstPost.getId());
	}

	@Test
	public void testRepoUpdateBlogpost() {

		List<Blogpost> blogposts = blogRepository.findAll();
		Blogpost post = blogposts.get(0);
		post.setTitle("Modified title");
		post.setBody("Modified body");
		blogRepository.save(post);

	}

	@Test
	public void testRepoDeleteBlogpostById() {
		List<Blogpost> blogposts = blogRepository.findAll();
		Blogpost post = blogposts.get(0);
		blogRepository.delete(post);

	}

}
