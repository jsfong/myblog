Feature: Blog entry can be create
Scenario: client makes a call to POST /blog
	When the client makes a call to POST /blog
	Then the client receives response status code of 200
	And the client receives back the blog entered
