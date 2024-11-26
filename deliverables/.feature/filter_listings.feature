Feature: Filtered Search for Accommodation Listings

  As a user,
  I want to conduct a filtered search on the platform
  So that I can view available accommodation listings that fit my needs before deciding to contact the user who posted the ad.

  Scenario: Successfully filter listings by price range (Normal Flow)
    Given the user is on the listings search page
    When the user selects a minimum price of <minPrice> and a maximum price of <maxPrice>
    And clicks the "Apply Filters" button
    Then the user sees a list of available listings with prices between <minPrice> and <maxPrice>

    Examples:
      | minPrice | maxPrice |
      | 500      | 1000     |
      | 800      | 1500     |
      | 1200     | 2000     |

  Scenario: Successfully filter listings by property type (Normal Flow)
    Given the user is on the listings search page
    When the user selects "<propertyType>" as the desired property type
    And clicks the "Apply Filters" button
    Then the user sees a list of available listings with the property type of "<propertyType>"

    Examples:
      | propertyType   |
      | STUDIO         |
      | DORM           |
      | CONDO          |
      | APARTMENT      |
      | HOUSE          |

  Scenario: Successfully filter listings by number of bedrooms and bathrooms (Normal Flow)
    Given the user is on the listings search page
    When the user selects "<bedrooms>" as the minimum number of bedrooms
    And selects "<bathrooms>" as the minimum number of bathrooms
    And clicks the "Apply Filters" button
    Then the user sees a list of available listings with at least "<bedrooms>" bedrooms and "<bathrooms>" bathrooms

    Examples:
      | bedrooms | bathrooms |
      | 1        | 1         |
      | 2        | 2         |
      | 3        | 2         |

  Scenario: Successfully filter listings by wheelchair accessibility (Normal Flow)
    Given the user is on the listings search page
    When the user selects the option "Wheelchair Accessible" as true
    And clicks the "Apply Filters" button
    Then the user sees a list of available listings that are wheelchair accessible

  Scenario: Successfully filter listings by smoking policy (Normal Flow)
    Given the user is on the listings search page
    When the user selects the option "Smoking Allowed" as true or false
    And clicks the "Apply Filters" button
    Then the user sees a list of available listings based on the smoking policy selected

    Examples:
      | smokingAllowed |
      | true           |
      | false          |

  Scenario: Successfully filter listings by multiple criteria (Normal Flow)
    Given the user is on the listings search page
    When the user selects a minimum price of <minPrice> and a maximum price of <maxPrice>
    And selects "<propertyType>" as the desired property type
    And selects "<bedrooms>" as the minimum number of bedrooms
    And selects "<bathrooms>" as the minimum number of bathrooms
    And selects the option "Wheelchair Accessible" as true
    And selects the option "Smoking Allowed" as false
    And clicks the "Apply Filters" button
    Then the user sees a list of available listings that match all the selected criteria

    Examples:
      | minPrice | maxPrice | propertyType | bedrooms | bathrooms |
      | 500      | 1000     | STUDIO       | 1        | 1         |
      | 800      | 1500     | HOUSE        | 2        | 2         | 
      | 1200     | 2000     | CONDO        | 3        | 2         | 
      | 1200     | 2000     | APARTMENT    | 1        | 1         | 

  Scenario: No listings match the selected filters (Error Flow)
    Given the user is on the listings search page
    When the user selects a minimum price of <minPrice> and a maximum price of <maxPrice>
    And selects "<propertyType>" as the desired property type
    And selects "<bedrooms>" as the minimum number of bedrooms
    And selects "<bathrooms>" as the minimum number of bathrooms
    And clicks the "Apply Filters" button
    Then the user is notified that no listings match the selected criteria

    Examples:
      | minPrice | maxPrice | propertyType | bedrooms | bathrooms |
      | 5000     | 10000    | HOUSE        | 10       | 10        |  # No house listings with this high price
      | 0        | 500      | STUDIO       | 1        | 1         |  # No studio listings this cheap

  Scenario: Reset filters and view all listings (Normal Flow)
    Given the user has applied filters for price, property type, bedrooms, and bathrooms
    When the user clicks the "Reset Filters" button
    Then all filters are cleared and the user sees a list of all available listings without any filters applied
