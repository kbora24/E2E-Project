Feature: HomePage Validation

Scenario: HomePage is displayed on accessing the URL

Given User open the Browser
When pass the link "10.197.8.17/hmis/"
Then Home page is displayed
And close browser

Scenario Outline: User is able to login successfully

Given User open the Browser
When pass the link "10.197.8.17/hmis/"
And clicks loin button after enter the username "<username>" and password "<password>"
Then User should be able to login successful
And close browser

Examples:
|username| password|
|reg     |admin    |
|angelad |admin    |
|kamal@  |admin    |