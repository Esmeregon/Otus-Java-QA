Feature: Title of the personal account page

  @accountTests
  Scenario: Checking the title of the personal account page
    Given The user went to the website
    When The user go through authorization on the site
    And go to the personal account page
    Then The system should display the page title

