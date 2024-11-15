package HouseIt.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import HouseIt.dao.LandlordDAO;
import HouseIt.dao.ListingDAO;
import HouseIt.model.Landlord;
import HouseIt.model.Address;
import HouseIt.model.Amenities;
import HouseIt.model.Listing;
import HouseIt.model.Utilities;
import HouseIt.model.Image;
import HouseIt.dto.ListingDTO;


@Service
public class ListingService {

    
    
    @Autowired
    private ListingDAO listingDAO;

    @Autowired
    private LandlordDAO landlordDAO;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AmenitiesService amenitiesService;

    @Autowired
    private UtilitiesService utilitiesService;

    @Autowired
    private ImageService imageService;


    public Listing completeListing(int id) {
        Listing listing = listingDAO.findListingById(id);
        if (listing == null) {
            throw new IllegalArgumentException("Listing not found");
        }
        listing.setCompleted(true);
        return listingDAO.save(listing);
    }


    @Transactional
    public Listing createListing(int landlordId,String title, String description, int monthlyPrice, float propertyRating, int bedrooms, int bathrooms, 
                                 Listing.PropertyType propertyType, int squareFootage, boolean wheelchairAccessible, boolean hidden, 
                                 boolean smokingAllowed, Address address,  Amenities amenitiesOffered, List<Image> propertyImages, Utilities utilitiesCosts ) {

        // Validation of individual parameters
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        
        if (monthlyPrice <= 0) {
            throw new IllegalArgumentException("Monthly price must be greater than zero.");
        }

        // Creating a new Listing object
        Listing newListing = new Listing();
        newListing.setTitle(title);
        newListing.setDescription(description);
        newListing.setMonthlyPrice(monthlyPrice);
        newListing.setPropertyRating(propertyRating);
        newListing.setBedrooms(bedrooms);
        newListing.setBathrooms(bathrooms);
        newListing.setPropertyType(propertyType);
        newListing.setSquareFootage(squareFootage);
        newListing.setWheelchairAccessible(wheelchairAccessible);
        newListing.setHidden(hidden);
        newListing.setSmokingAllowed(smokingAllowed);

        address = addressService.createAddress(address);
        newListing.setAddress(address);

        amenitiesOffered = amenitiesService.createAmenities(amenitiesOffered);
        newListing.setAmenitiesOffered(amenitiesOffered);

        utilitiesCosts = utilitiesService.createUtilities(utilitiesCosts);
        newListing.setUtilitiesCosts(utilitiesCosts);

        List<Image> newPropertyImages = new ArrayList<>();
        for(Image img : propertyImages) {
            Image image = imageService.createImage(img);
            newPropertyImages.add(image);
        }
        Image[] imageArray = new Image[newPropertyImages.size()];     // Create an array of the same size as the list
        imageArray = newPropertyImages.toArray(imageArray);           // Convert the list to an array
        newListing.setPropertyImages(imageArray);

        Landlord landlord = landlordDAO.findLandlordById(landlordId);
        if (landlord == null) {
            throw new IllegalArgumentException("No such landlord with id: " + landlordId);
        }

        // Save listing and assign to landlord
        newListing = listingDAO.save(newListing);
        landlord.addProperty(newListing);
        landlordDAO.save(landlord);

        return newListing;
    }
    
    public Listing updateListing(int id, String title, String description, int monthlyPrice, float propertyRating, int bedrooms, int bathrooms, 
    Listing.PropertyType propertyType, int squareFootage, boolean wheelchairAccessible, boolean hidden, 
    boolean smokingAllowed, Address address,  Amenities amenitiesOffered, List<Image> propertyImages, Utilities utilitiesCosts ) {

        // Validation of individual parameters
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        
        if (monthlyPrice <= 0) {
            throw new IllegalArgumentException("Monthly price must be greater than zero.");
        }
        
        // Get and update the existing Listing object
        Listing existingListing = listingDAO.findListingById(id);
        if (existingListing == null) {
            throw new IllegalArgumentException("No such listing with id: " + id);
        }

        existingListing.setTitle(title);
        existingListing.setDescription(description);
        existingListing.setMonthlyPrice(monthlyPrice);
        existingListing.setPropertyRating(propertyRating);
        existingListing.setBedrooms(bedrooms);
        existingListing.setBathrooms(bathrooms);
        existingListing.setPropertyType(propertyType);
        existingListing.setSquareFootage(squareFootage);
        existingListing.setWheelchairAccessible(wheelchairAccessible);
        existingListing.setHidden(hidden);
        existingListing.setSmokingAllowed(smokingAllowed);

        address = addressService.updateAddress(address);
        existingListing.setAddress(address);

        amenitiesOffered = amenitiesService.updateAmenities(amenitiesOffered);
        existingListing.setAmenitiesOffered(amenitiesOffered);

        utilitiesCosts = utilitiesService.updateUtilities(utilitiesCosts);
        existingListing.setUtilitiesCosts(utilitiesCosts);

        List<Image> newPropertyImages = new ArrayList<>();
        for(Image img : propertyImages) {
            Image image = imageService.updateImage(img);
            newPropertyImages.add(image);
        }
        Image[] imageArray = new Image[newPropertyImages.size()];     // Create an array of the same size as the list
        imageArray = newPropertyImages.toArray(imageArray);           // Convert the list to an array
        existingListing.setPropertyImages(imageArray);
        
        return listingDAO.save(existingListing);
    }

    public Listing getListing(int id) {

        Listing listing = listingDAO.findListingById(id);
        if (listing == null) {
            throw new IllegalArgumentException("No such listing with id: " + id);
        }

        return listing;
    }

    public List<Listing> getAllListings() {
        return listingDAO.findAll();
    }

    public ListingDTO convertToDTO(Listing listing) {

        ListingDTO dto = new ListingDTO();
        dto.setId(listing.getId());
        dto.setTitle(listing.getTitle());
        dto.setDescription(listing.getDescription());
        dto.setMonthlyPrice(listing.getMonthlyPrice());
        dto.setPropertyRating(listing.getPropertyRating());
        dto.setBedrooms(listing.getBedrooms());
        dto.setBathrooms(listing.getBathrooms());
        dto.setPropertyType(listing.getPropertyType());
        dto.setSquareFootage(listing.getSquareFootage());
        dto.setWheelchairAccessible(listing.getWheelchairAccessible());
        dto.setHidden(listing.getHidden());
        dto.setSmokingAllowed(listing.getSmokingAllowed());
        dto.setAddress(listing.getAddress());
        dto.setAmenitiesOffered(listing.getAmenitiesOffered());
        dto.setUtilitiesCosts(listing.getUtilitiesCosts());

        List<Image> imageList = listing.getPropertyImages();  // Get the list of images
        Image[] imageArray = new Image[imageList.size()];     // Create an array of the same size as the list
        imageArray = imageList.toArray(imageArray);           // Convert the list to an array
        dto.setPropertyImages(imageArray);                    // Pass the array to the setPropertyImages method

        return dto;
    }
}
