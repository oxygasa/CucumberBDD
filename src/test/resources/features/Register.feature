@alltests
Feature: TC ID TRE001 Register a new user through Trello

  Scenario: Checking an integration within Atlassian account allow the correct user account creation.
    Given the user opens "https://trello.com/signup"
    When the user type the "email" in the "email text field" from "https://temp-mail.io/"
    And the user type the "email" from "https://temp-mail.io/"
    Then result item is Google Recaptcha

@alltests
Feature: TC ID TRE002 Registration with an existing data

  Scenario: Checking if existing user can register at trello repeatedly
    Given the user opens "https://trello.com"
    When the user type the "trellou0@gmail.com" in the "email text field"
    And the user click the "Sign up it's free" button
    Then result item is Google Recaptcha