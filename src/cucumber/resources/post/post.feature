Feature: If a post is loaded?

  Scenario: Load first post
    Given post title is "HelloWorld-07-06-2018.md"
    And post text is "hello"
    When I ask to give me a post
    Then Name must be "HelloWorld"
    Then Full name must be "HelloWorld-07-06-2018.md"
    Then Date must be "2018-06-07"
    Then Text must be "hello"
