Feature: Error Validation
@errorValidation
Scenario Outline:

Given I landed on the Ecommerce Page
When I logged in with username <userName> and password <password>
Then <errorMsg> error message is displayed

Examples:
|userName  				 	|password   |errorMsg|
|atlantaraja@gmail.com		|Allahu12	|Incorrect email or password.|