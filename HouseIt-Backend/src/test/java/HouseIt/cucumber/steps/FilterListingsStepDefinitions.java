package HouseIt.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import HouseIt.service.ListingService;
import HouseIt.model.Listing;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilteredSearchStepDefinitions {

    @Autowired
    private ListingService listingService;

    private List<Listing> filteredListings;
    private String notificationMessage;

    @Given("the user is on the listings search page")
    public void theUserIsOnTheListingsSearchPage() {
        // Assume user is on the search page
    }

    @When("the user selects a minimum price of {int} and a maximum price of {int}")
    public void theUserSelectsPriceRange(int minPrice, int maxPrice) {
        filteredListings = listingService.filterByPrice(minPrice, maxPrice);
    }

    @Then("the user sees a list of available listings with prices between {int} and {int}")
    public void theUserSeesListingsWithinPriceRange(int minPrice, int maxPrice) {
        assertTrue(filteredListings.stream().allMatch(listing -> 
            listing.getPrice() >= minPrice && listing.getPrice() <= maxPrice
        ));
    }

    @When("the user selects {string} as the desired property type")
    public void theUserSelectsPropertyType(String propertyType) {
        filteredListings = listingService.filterByPropertyType(propertyType);
    }

    @Then("the user sees a list of available listings with the property type of {string}")
    public void theUserSeesListingsWithPropertyType(String propertyType) {
        assertTrue(filteredListings.stream().allMatch(listing -> 
            listing.getPropertyType().equalsIgnoreCase(propertyType)
        ));
    }

    @When("the user selects {int} as the minimum number of bedrooms")
    public void theUserSelectsMinimumBedrooms(int bedrooms) {
        filteredListings = listingService.filterByBedrooms(bedrooms);
    }

    @When("the user selects {int} as the minimum number of bathrooms")
    public void theUserSelectsMinimumBathrooms(int bathrooms) {
        filteredListings = listingService.filterByBathrooms(bathrooms);
    }

    @Then("the user sees a list of available listings with at least {int} bedrooms and {int} bathrooms")
    public void theUserSeesListingsWithMinimumBedroomsAndBathrooms(int bedrooms, int bathrooms) {
        assertTrue(filteredListings.stream().allMatch(listing -> 
            listing.getBedrooms() >= bedrooms && listing.getBathrooms() >= bathrooms
        ));
    }

    @When("the user selects the option {string} as true")
    public void theUserSelectsWheelchairAccessible(String accessibility) {
        boolean isAccessible = Boolean.parseBoolean(accessibility);
        filteredListings = listingService.filterByWheelchairAccessibility(isAccessible);
    }

    @Then("the user sees a list of available listings that are wheelchair accessible")
    public void theUserSeesWheelchairAccessibleListings() {
        assertTrue(filteredListings.stream().allMatch(Listing::isWheelchairAccessible));
    }

    @When("the user selects the option {string} as true or false")
    public void theUserSelectsSmokingPolicy(String smokingAllowed) {
        boolean isSmokingAllowed = Boolean.parseBoolean(smokingAllowed);
        filteredListings = listingService.filterBySmokingPolicy(isSmokingAllowed);
    }

    @Then("the user sees a list of available listings based on the smoking policy selected")
    public void theUserSeesListingsWithSmokingPolicy() {
        assertTrue(filteredListings.stream().allMatch(listing -> 
            listing.isSmokingAllowed() == Boolean.parseBoolean(smokingAllowed)
        ));
    }

    @When("the user selects a minimum price of {int} and a maximum price of {int}")
    public void theUserSelectsPriceRangeAgain(int minPrice, int maxPrice) {
        filteredListings = listingService.filterByPrice(minPrice, maxPrice);
    }

    @When("the user selects {string} as the desired property type")
    public void theUserSelectsPropertyTypeAgain(String propertyType) {
        filteredListings = listingService.filterByPropertyType(propertyType);
    }

    @When("the user selects {int} as the minimum number of bedrooms")
    public void theUserSelectsMinimumBedroomsAgain(int bedrooms) {
        filteredListings = listingService.filterByBedrooms(bedrooms);
    }

    @When("the user selects {int} as the minimum number of bathrooms")
    public void theUserSelectsMinimumBathroomsAgain(int bathrooms) {
        filteredListings = listingService.filterByBathrooms(bathrooms);
    }

    @When("the user clicks the Apply Filters button")
    public void theUserClicksApplyFilters() {
        // Filters applied
    }

    @Then("the user sees a list of available listings that match all the selected criteria")
    public void theUserSeesListingsMatchingCriteria() {
        assertTrue(!filteredListings.isEmpty());
    }

    @When("no listings match the selected filters")
    public void noListingsMatchFilters() {
        if (filteredListings.isEmpty()) {
            notificationMessage = "No listings match the selected criteria";
        }
    }

    @Then("the user is notified that no listings match the selected criteria")
    public void theUserIsNotifiedNoListingsMatchCriteria() {
        assertEquals("No listings match the selected criteria", notificationMessage);
    }

    @When("the user clicks the Reset Filters button")
    public void theUserClicksResetFilters() {
        filteredListings = listingService.getAllListings();
    }

    @Then("all filters are cleared and the user sees a list of all available listings without any filters applied")
    public void theUserSeesAllListingsWithoutFilters() {
        List<Listing> allListings = listingService.getAllListings();
        assertEquals(allListings.size(), filteredListings.size());
    }
}
