Feature: Login

  As a user I can login

  Scenario: Login as a user
    When I login as "Administrator"
    Then I am really logged in as "Administrator"
