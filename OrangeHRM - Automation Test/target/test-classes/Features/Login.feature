@LoginFeature
Feature: Login Functionality of OrangeHrm Site

  @Login @ValidScenario
  Scenario Outline: Valid Login Scenario
    Given The Login Page is Opened
    And I fill the valid Login Credentials <username> and <password> and clicked Login
    Then The User is logged in

    Examples: 
      | username            | password      |
      #| "Rei Penber"        | "Rei@penber2" |
      | "   Rei Penber    " | "Rei@penber2" |

  @Login @InvalidScenario
  Scenario Outline: Invalid Login Scenario
    Given The Login Page is Opened
    When I fill the invalid Login Credentials <username> and <password> and clicked Login
    Then An error message is shown on <error-location> as <error-message>

    Examples: 
      | username         | password             | error-location | error-message         |
      | "Rei Penber"     | "not Rei pass"       | "homepage"     | "Invalid credentials" |
      | "not Rei user"   | "Rei@penber2"        | "homepage"     | "Invalid credentials" |
      | "not groot user" | "not groot pass"     | "homepage"     | "Invalid credentials" |
      | "Rei Penber"     | "   Rei@penber2    " | "homepage"     | "Invalid credentials" |
      | ""               | ""                   | "both"         | "Required"            |
      | ""               | "Rei@penber2"        | "username"     | "Required"            |
      | "Rei Penber"     | ""                   | "password"     | "Required"            |

  @Logout
  Scenario: The Ability to Logout
    Given The Login Page is Opened
    When I logged in with valid credentials
    When The User is logged in
    When The User clicks log out
    Then The User is logged out

  @StayLoggedInBackBtn
  Scenario: The Ability of staying logged in after clicking back button
    Given The Login Page is Navigated
    When I logged in with valid credentials
    When The User is logged in
    When The Back Button is clicked
    Then The User is still logged in

  @StayLoggedOutBackBtn
  Scenario: The Ability of staying logged out after clicking back button
    Given The Login Page is Navigated
    When I logged in with valid credentials
    And The User is logged in
    And The User clicks log out
    And The Back Button is clicked
    Then The User is logged out

  @StayLoggedIn
  Scenario: The Ability to Stay logged in after closing and reopening browser
    Given The Login Page is Opened
    When I logged in with valid credentials
    When The User is logged in
    When The User closes and reopens the browser
    Then The User is still logged in
