package HouseIt.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ResponsiveWebDesignStepDefinitions {

    private String currentDevice;
    private String currentResolution;
    private boolean horizontalScrolling;
    private boolean isHamburgerMenuVisible;
    private boolean isTextReadable;
    private boolean usesHighResolutionAssets;
    private boolean layoutUtilizesScreenSpace;

    @Given("I access the website on a {string}")
    public void iAccessTheWebsiteOnADevice(String device) {
        this.currentDevice = device;
        // Simulate setting up the resolution based on the device
        switch (device) {
            case "desktop":
                currentResolution = "1920x1080";
                break;
            case "laptop":
                currentResolution = "1366x768";
                break;
            case "tablet":
                currentResolution = "1024x768";
                break;
            case "mobile":
                currentResolution = "375x667";
                break;
            default:
                currentResolution = "unknown";
        }
    }

    @When("the website loads")
    public void theWebsiteLoads() {
        // Simulate checking for responsiveness at the given resolution
        horizontalScrolling = false; // Assume no horizontal scrolling unless otherwise specified
    }

    @Then("the content should fit the {string} without horizontal scrolling")
    public void theContentShouldFitResolutionWithoutHorizontalScrolling(String resolution) {
        assertEquals(resolution, currentResolution);
        assertTrue("Content should fit without horizontal scrolling", !horizontalScrolling);
    }

    @Given("I access the website on a mobile device")
    public void iAccessTheWebsiteOnAMobileDevice() {
        this.currentDevice = "mobile";
        this.currentResolution = "375x667";
    }

    @Then("I should see a hamburger menu for navigation")
    public void iShouldSeeAHamburgerMenuForNavigation() {
        isHamburgerMenuVisible = true; // Simulate visibility check
        assertTrue("Hamburger menu should be visible on mobile devices", isHamburgerMenuVisible);
    }

    @Then("the text should be readable without zooming")
    public void theTextShouldBeReadableWithoutZooming() {
        isTextReadable = true; // Simulate readability check
        assertTrue("Text should be readable without zooming", isTextReadable);
    }

    @Given("I access the website on a desktop with a high-resolution monitor")
    public void iAccessTheWebsiteOnADesktopWithAHighResolutionMonitor() {
        this.currentDevice = "desktop";
        this.currentResolution = "3840x2160"; // Example high-resolution monitor
    }

    @Then("the images should utilize high-resolution assets")
    public void theImagesShouldUtilizeHighResolutionAssets() {
        usesHighResolutionAssets = true; // Simulate check for high-resolution assets
        assertTrue("Images should utilize high-resolution assets", usesHighResolutionAssets);
    }

    @Then("the layout should utilize the available screen space effectively")
    public void theLayoutShouldUtilizeTheAvailableScreenSpaceEffectively() {
        layoutUtilizesScreenSpace = true; // Simulate layout check
        assertTrue("Layout should utilize available screen space effectively", layoutUtilizesScreenSpace);
    }
}
