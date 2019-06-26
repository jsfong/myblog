Feature: Able to update a blogs post by id
Scenario: client makes a call to PUT /blog by id
	Given there is a blog created
	When the client makes a call to PUT /blog to update the blog created by id
	Then the client receives response status code of 200
	And the client receives back the updated blog with the specific id