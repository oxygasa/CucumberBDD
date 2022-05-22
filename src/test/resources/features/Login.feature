@alltests
Feature: TC ID TRE003 Incorrect credentials

  Scenario: Providing the not registered credentials is a trigger for the "not registered" error message
    Given the user opens "https://trello.com/login"
    When the user type the "3123412@af.com" in the "login" text box
    And the user does not type in the "password" text box
    And the user click "submit" button
    Then result item is the "error text message" with the value "not registered"

  Scenario: Providing the registered but not approved by email credentials is a trigger for sending the email for the approve
    Given the user opens "https://trello.com/login"
    When the user type the "wename9713@shackvine.com" in the "login" text box
    And the user does not type in the "password" text box
    And the user click "submit" button
    Then the "approve email" module contains current email

  Scenario: Providing the registered and approved credentials but within the clear password
  text box is a trigger for "password is required" message
    Given the user opens "https://trello.com/login"
    When the user type the "trellou0@gmail.com" in the "login" text box
    And the user does not type in the "password" text box
    And the user click "submit" button
    Then result item is the "error text message" with the value "password is required"

@alltests
Feature: TC ID TRE004 Correct credentials

  Scenario: Providing correct login and password get an access to the web application
    Given the user opens "https://trello.com/login"
    When the user type the "trellou0@gmail.com" in the "login" text box
    And the user type the "Trellouser999Te!42" in the "password" text box
    And the user click "submit" button
    Then the "Boards" page is displayed

@alltests
Feature: TC ID TRE005 Social media login by Google

  Scenario: Access granted when linked social media account is provided.