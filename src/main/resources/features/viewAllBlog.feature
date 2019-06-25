Feature: Able to view all entered blogs post
Scenario: client makes a call to GET /blog
	Given there are blog created
	When the client makes a call to GET /blog
	Then the client receives responses status code of 200
	And the client receives back all the blog entered