Feature: If a post is loaded?

  Scenario: Load first post
    Given post title is "HelloWorld-07-06-2018.md"
    And post text is "hello"
    When I ask to give me a post
    Then 2 Name must be "HelloWorld"
    Then 2 Full name must be "HelloWorld-07-06-2018.md"
    Then 2 Date must be "2018-06-07"
    Then 2 Text must be "hello"
