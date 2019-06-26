Feature: Able to delete a blogs post by id
Scenario: client makes a call to DELETE /blog by id
	Given there is a blog created
	When the client makes a call to DELETE /blog by id
	Then the client receives response status code of 200