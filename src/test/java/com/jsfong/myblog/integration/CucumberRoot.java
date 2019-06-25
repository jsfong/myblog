package com.jsfong.myblog.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.jsfong.myblog.MyblogApplication;

@SpringBootTest(classes = MyblogApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("INTEGRATION_TEST")
@ContextConfiguration
public class CucumberRoot {
	
	@Autowired
	protected TestRestTemplate template;

}
