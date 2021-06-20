Feature: Authorization on the site

  @signInTests
  Scenario Outline: Authorization on the site
    Given The user went to the login page
    When User enters incorrect email "<email>" or password "<password>"
    Then An error message is displayed
    Examples:
      | email                    | password                         |
      | testtesttesttest@test.ru | test                             |
      | test                     | testtesttesttest@test.ru         |
      | test                     | test                             |