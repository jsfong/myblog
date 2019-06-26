Feature: Able to view a blogs post by id
Scenario: client makes a call to GET /blog with id
	Given there is a blog created
	When the client makes a call to GET /blog with id
	Then the client receives response status code of 200
	And the client receives back the blog with the specific id