Feature:Successfully place an order in eCommerce web site

Background:
Given I landed on the Ecommerce Page
@submitOrder
Scenario Outline:
Given I logged in with username <userName> and password <password>
When I add product <productName> to the cart 
And checkout the <productName> and submit the order
Then <thankUMessage> appears in the confirmation page

Examples:
 |userName					| password  | productName | thankUMessage 			|
 |atlantaraja@gmail.com  	|Allahu123	| ZARA COAT 3 |	Thankyou for the order. |
 