Feature: If PostHeader is created in right way?

  Scenario: Transform File to PostHeader
    Given title is "test-07-06-2018.md"
    When I ask to give me a PostHeader
    Then Name must be "test"
    Then Full name must be "test-07-06-2018.md"
    Then Date must be "2018-06-07"

  Scenario: Transform File to PostHeader
    Given title is "test.md"
    Then a failure is expected
    When I ask to give me a PostHeader
    And Parsing is fail

  Scenario: Transform File to PostHeader
    Given title is "not-md-07-06-2018.txt"
    Then a failure is expected
    When I ask to give me a PostHeader
    And Parsing is fail