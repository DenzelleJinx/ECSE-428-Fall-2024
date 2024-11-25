Feature: Responsive Web Design
  As a user, I want the website to adapt to any screen size so that I can have a consistent experience across all my devices.

  Scenario Outline: Website adapts to different screen sizes
    Given I access the website on a <device>
    When the website loads
    Then the content should fit the <resolution> without horizontal scrolling

  Examples:
    | device          | resolution      |
    | desktop         | 1920x1080       |
    | laptop          | 1366x768        |
    | tablet          | 1024x768        |
    | mobile          | 375x667         |

  Scenario: View website on a mobile device
    Given I access the website on a mobile device
    When the website loads
    Then I should see a hamburger menu for navigation
    And the text should be readable without zooming

  Scenario: View website on a desktop
    Given I access the website on a desktop with a high-resolution monitor
    When the website loads
    Then the images should utilize high-resolution assets
    And the layout should utilize the available screen space effectively
