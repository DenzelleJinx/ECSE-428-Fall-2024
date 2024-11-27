package HouseIt.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilterListingsStepDefinitions {

    @Given("the user is on the listings search page")
    public void theUserIsOnTheListingsSearchPage() {
        // Code to navigate to the listings search page
    }

    @When("the user selects a minimum price of {int} and a maximum price of {int}")
    public void theUserSelectsAPriceRange(int minPrice, int maxPrice) {
        // Code to apply the price range filter
    }

    @When("the user selects {string} as the desired property type")
    public void theUserSelectsPropertyType(String propertyType) {
        // Code to apply the property type filter
    }

    @When("the user selects {int} as the minimum number of bedrooms")
    public void theUserSelectsMinimumBedrooms(int bedrooms) {
        // Code to apply the minimum bedrooms filter
    }

    @When("the user selects {int} as the minimum number of bathrooms")
    public void theUserSelectsMinimumBathrooms(int bathrooms) {
        // Code to apply the minimum bathrooms filter
    }

    @When("the user selects the option {string} as true")
    public void theUserSelectsWheelchairAccessible(String option) {
        // Code to apply the wheelchair accessibility filter
    }

    @When("the user selects the option {string} as true or false")
    public void theUserSelectsSmokingPolicy(String option) {
        // Code to apply the smoking policy filter
    }

    @When("the user clicks the {string} button")
    public void theUserClicksButton(String button) {
        // Code to simulate clicking the specified button (e.g., "Apply Filters" or "Reset Filters")
    }

    @Then("the user sees a list of available listings with prices between {int} and {int}")
    public void theUserSeesListingsWithinPriceRange(int minPrice, int maxPrice) {
        // Code to verify listings with prices within the specified range are displayed
    }

    @Then("the user sees a list of available listings with the property type of {string}")
    public void theUserSeesListingsWithPropertyType(String propertyType) {
        // Code to verify listings with the specified property type are displayed
    }

    @Then("the user sees a list of available listings with at least {int} bedrooms and {int} bathrooms")
    public void theUserSeesListingsWithBedroomsAndBathrooms(int bedrooms, int bathrooms) {
        // Code to verify listings with the specified bedrooms and bathrooms are displayed
    }

    @Then("the user sees a list of available listings that are wheelchair accessible")
    public void theUserSeesListingsThatAreWheelchairAccessible() {
        // Code to verify wheelchair-accessible listings are displayed
    }

    @Then("the user sees a list of available listings based on the smoking policy selected")
    public void theUserSeesListingsBasedOnSmokingPolicy() {
        // Code to verify listings based on the smoking policy filter are displayed
    }

    @Then("the user sees a list of available listings that match all the selected criteria")
    public void theUserSeesListingsMatchingAllCriteria() {
        // Code to verify listings that match all applied filters are displayed
    }

    @Then("the user is notified that no listings match the selected criteria")
    public void theUserIsNotifiedNoListingsMatchCriteria() {
        // Code to display a message that no listings match the selected filters
    }

    @Then("all filters are cleared and the user sees a list of all available listings without any filters applied")
    public void allFiltersAreClearedAndUserSeesAllListings() {
        // Code to clear all filters and display all available listings
    }

    @When("clicks the {string} button")
    public void clicks_the_button(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("the user selects {string} as the minimum number of bedrooms")
    public void the_user_selects_as_the_minimum_number_of_bedrooms(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("selects {string} as the minimum number of bathrooms")
    public void selects_as_the_minimum_number_of_bathrooms(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the user sees a list of available listings with at least {string} bedrooms and {string} bathrooms")
    public void the_user_sees_a_list_of_available_listings_with_at_least_bedrooms_and_bathrooms(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("selects {string} as the desired property type")
    public void selects_as_the_desired_property_type(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("selects {string} as the minimum number of bedrooms")
    public void selects_as_the_minimum_number_of_bedrooms(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("selects the option {string} as true")
    public void selects_the_option_as_true(String string) {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("selects the option {string} as false")
    public void selects_the_option_as_false(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    
    @Given("the user has applied filters for price, property type, bedrooms, and bathrooms")
    public void the_user_has_applied_filters_for_price_property_type_bedrooms_and_bathrooms() {
        // Write code here that turns the phrase above into concrete actions
    }

}
