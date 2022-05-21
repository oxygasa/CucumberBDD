@alltests
Feature: As a user I want to get ability to to use menu section

  Scenario: Docs section should contains appropriate columns
    Given the user opens "URL"
    When the user type "Hello EPAM" text in the "Search" text box
    And the user clicks the "searchbox" button
    Then page with results "Hello EPAM" is displayed