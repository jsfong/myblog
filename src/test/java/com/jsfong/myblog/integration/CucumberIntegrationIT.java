package com.jsfong.myblog.integration;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.jsfong.myblog.service.BlogService;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/features",
		plugin= {"pretty", "html:target/cucumber"})
public class CucumberIntegrationIT {

}
