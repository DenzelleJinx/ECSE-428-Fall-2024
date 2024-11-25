import React, { useState, useEffect } from "react";
import BedIcon from "@mui/icons-material/Bed";
import BathtubIcon from "@mui/icons-material/Bathtub";
import HomeIcon from "@mui/icons-material/Home";
import SmokingRoomsIcon from "@mui/icons-material/SmokingRooms";
import AccessibleIcon from "@mui/icons-material/Accessible";
import SquareFootIcon from "@mui/icons-material/SquareFoot";
import {
  FitnessCenter,
  LocalLaundryService,
  Pets,
  LocalParking,
  Wifi,
} from "@mui/icons-material";
import WaterIcon from "@mui/icons-material/Opacity";
import BoltIcon from "@mui/icons-material/Bolt";
import FireIcon from "@mui/icons-material/Whatshot";
import { Button, Modal, Box, Typography } from "@mui/material";
import Axios from "axios";
import StatusDialog from "../status-dialog/StatusDialog";

import apartmentImage from "../../assets/sample-bedroom.png";
import PropertyListing from "../view-listings/ImagePopup";

import { useNavigate } from "react-router-dom";

import axios from "axios";

function ListingCard({ listing, onRentOut }) {
    const [isHovered, setIsHovered] = useState(false);
    const [isSaved, setIsSaved] = useState(false);

  const cardStyles = {
    border: "1px solid #ddd",
    borderRadius: "8px",
    overflow: "hidden",
    backgroundColor: isHovered ? "#ffe5e5" : "#fff",
    transition: "box-shadow 0.3s, background-color 0.3s",
    display: "flex",
    flexDirection: "column",
    maxWidth: "400px",
    boxShadow: isHovered
      ? "0 4px 12px rgba(255, 0, 0, 0.3)"
      : "0 4px 8px rgba(0, 0, 0, 0.1)",
  };

  const infoStyles = {
    padding: "10px",
    display: "flex",
    flexDirection: "column",
    gap: "2px",
    fontFamily: "'Roboto', sans-serif", // Material-UI default font
  };

  const priceStyles = {
    fontWeight: "bold",
    fontSize: "1.8em",
    margin: "0px",
    color: "#3A3B3C",
    fontFamily: "'Roboto', sans-serif", // Material-UI default font
  };

  const priceButtonContainerStyles = {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    marginBottom: "10px",
  };
  const modalStyles = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: "80%",
    maxWidth: "400px",
    bgcolor: "background.paper",
    boxShadow: 24,
    p: 3,
    borderRadius: 2,
  };

  const phoneStyles = {
    fontSize: "1.25em",
    color: "white",
    backgroundColor: "black",
    borderWidth: "1px",
    padding: "4px",
    textAlign: "center",
    borderRadius: "4px",
    margin: "2px",
  };


    const [isLandlord, setIsLandlord] = useState(false);
    const [isStudent, setIsStudent] = useState(false);
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isContactingLandlord, setIsContactingLandlord] = useState(false);
    const [isRentingOut, setIsRentingOut] = useState(false);

    const openUtilitiesModal = () => setShowUtilitiesModal(true);
    const closeUtilitiesModal = () => setShowUtilitiesModal(false);
    const [showUtilitiesModal, setShowUtilitiesModal] = useState(false);
    
    const [showPhoneNumber, setShowPhoneNumber] = useState(false);
    const [callString, setCallString] = useState('Number Unavailable');
    const [currentUserName, setCurrentUserName] = useState(null);
    const [landlordData, setLandLordData] = useState(null);

    const [openDialog, setOpenDialog] = React.useState(false); // State for dialog visibility
    const [dialogMessage, setDialogMessage] = React.useState(''); // Message to display in the dialog
    const [dialogSeverity, setDialogSeverity] = React.useState('error'); // Severity of the message: 'success' or 'error'

    const handleDialogClose = () => {
        setOpenDialog(false);
    };
    const navigate = useNavigate();
    const [serverErrorMessage, setServerErrorMessage] = useState('');
    const [serverSuccessMessage, setServerSuccessMessage] = useState('');

    useEffect(() => {
        const checkAuth = async () => {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            setIsLandlord(user && user.accountType === 'landlord');
            setIsStudent(user && user.accountType === 'student');
            setIsAuthenticated(user != null);
            setCurrentUserName(user && user.username);

            const landlordResponse = await Axios.get(`http://localhost:8080/users/id/${listing.landlordId}`);
            setLandLordData(landlordResponse.data);  // Extract landlord data
            setCallString(`Call ${landlordResponse.data.phoneNumber}`);
            if (callString === 'Call ') {
                setCallString("Number Unavailable");
            }
        };

        checkAuth();
    }, []);

    const handleToggle = () => {
        setShowPhoneNumber(!showPhoneNumber);
    };
    const handleSave = async () => {
        try {
            const response = await Axios.post(
                `http://localhost:8080/users/${currentUserName}/saved-listings`,
                { listingId: listing.id }
            );
            if (response.status === 201) {
                setDialogMessage('Listing has been saved successfully.');
                setDialogSeverity('success');
                setIsSaved(true);
            }
        } catch (error) {
            console.error('Error saving listing:', error);
            setDialogMessage('An error occurred while saving the listing. Please try again.');
            setDialogSeverity('error');
        } finally {
            setOpenDialog(true);
        }
    };

    // Sends notifications
    const handleNotify = async () => {
        try {
            if (currentUserName == null) {
                console.error('User not logged in.');
                return;
            }

            const body = {
                type: "CONTACT",
                senderUsername: currentUserName
            };
            console.log(body);
            console.log(landlordData.username);
            const response = await Axios.post(`http://localhost:8080/users/${landlordData.username}/notifications`, body);
            console.log('Notification successful:', response.data);
            if (response.status === 201) {
                setDialogMessage('Your message has been sent to the landlord.');
                setDialogSeverity('success');
                setOpenDialog(true);
            }

            setShowPhoneNumber(false);

        } catch (error) {
            console.error('Error sending notification:', error);
            setDialogMessage('An error occurred while contacting the landlord. Please try again later.');
            setDialogSeverity('error');
            setOpenDialog(true);
        }
    };

    /* const handleContactLandlord = async () => {
        try {
            const landlordResponse = await Axios.get(`http://localhost:8080/users/id/${listing.landlordId}`);
            const landlord = landlordResponse.data;  // Extract landlord data

            // Now, send a POST request to the landlord's notifications endpoint
            const notificationBody = {
                type: "CONTACT",
                senderUsername: currentUserName
            };

            console.log("Sending notification to landlord:", landlord.username, notificationBody);
            // Make a GET request to fetch notifications
            const response = await Axios.post(`http://localhost:8080/users/${landlord.username}/notifications`, notificationBody);

            if (response.status === 201) {
                setDialogMessage('Your message has been sent to the landlord.');
                setDialogSeverity('success');
                setOpenDialog(true);
            }
        } catch (error) {
            console.error("Error contacting landlord:", error);
            setDialogMessage('An error occurred while contacting the landlord. Please try again later.');
            setDialogSeverity('error');
            setOpenDialog(true);
        }

    }; */



    const handleRentOut = async () => {
        try {
            const landlordResponse = await Axios.get(`http://localhost:8080/users/id/${listing.landlordId}`);
            const landlord = landlordResponse.data;  // Extract landlord data

      // Now, send a POST request to the landlord's notifications endpoint
      const notificationBody = {
        type: "OTHER",
        message: `Your listing has been rented out by ${currentUserName}.`,
        senderUsername: currentUserName,
      };

      console.log(
        "Sending notification to landlord:",
        landlord.username,
        notificationBody,
        "by",
        currentUserName
      );

      // Make a GET request to fetch notifications
      const response = await Axios.post(
        `http://localhost:8080/users/${landlord.username}/notifications`,
        notificationBody
      );

      if (response.status === 201) {
        onRentOut(listing.id);
      }
    } catch (error) {
      console.error("Error contacting landlord:", error);
      setDialogMessage(
        "An error occurred while contacting the landlord. Please try again later."
      );
      setDialogSeverity("error");
      setOpenDialog(true);
    }
  };

  const handleUpdate = () => {
    localStorage.setItem("currentListing", JSON.stringify(listing));
    navigate("/updatelisting");
  };

  const handleDelete = async (event) => {
    localStorage.setItem("currentListing", JSON.stringify(listing));

    event.preventDefault();

    // Clear previous error messages
    setServerErrorMessage("");
    setServerSuccessMessage("");

    // Get listing ID
    const listingID = listing.id;

    try {
      const axiosClient = axios.create({
        baseURL: "http://localhost:8080",
      });
      const listingResponse = await axiosClient.get(`/listing/${listingID}`);
      if (listingResponse.status === 200) {
        const listingID = listingResponse.data.id;

        const response = await axiosClient.delete(`/listing/${listingID}`);
        console.log("Listing deleted successfully:", response.data); // TODO: Remove this when done testing
        setServerSuccessMessage("Listing deleted successfully");
      }
    } catch (error) {
      let errorMessage = null;
      if (error.response && typeof error.response.data === "string") {
        errorMessage = error.response.data;
      }
      setServerErrorMessage(
        errorMessage
          ? errorMessage
          : "An error occurred during signup. Please try again."
      );
    }
    window.location.href = "/viewListings";
  };

  return (
    <div
      style={cardStyles}
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
    >
      <div>
        <PropertyListing key={listing.id} property={listing} />
      </div>
      <div style={infoStyles}>
        <div style={priceButtonContainerStyles}>
          <p style={priceStyles}>${listing.monthlyPrice} monthly</p>
          {isStudent && (
            <div>
              {!showPhoneNumber ? (
                <div>
                  <Button
                    onClick={handleToggle}
                    variant="contained"
                    color="primary"
                    size="small"
                    sx={{
                      backgroundColor: "#d50032",
                      color: "white",
                      "&:hover": {
                        backgroundColor: "#b71c1c", // Hover effect
                      },
                      fontSize: "1.1em",
                      textTransform: "none",
                    }}
                  >
                    Contact
                  </Button>
                </div>
              ) : (
                <div>
                  <p style={phoneStyles}>{callString}</p>
                  <Button
                    onClick={handleNotify}
                    variant="contained"
                    color="primary"
                    size="small"
                    sx={{
                      backgroundColor: "#d50032",
                      "&:hover": {
                        backgroundColor: "#b71c1c", // Hover effect
                      },
                      fontSize: "1.1em",
                      textTransform: "none",
                      margin: "2px",
                    }}
                  >
                    Notify
                  </Button>
                  <Button
                    onClick={handleToggle}
                    variant="contained"
                    color="primary"
                    size="small"
                    sx={{
                      textTransform: "none",
                      fontSize: "1.1em",
                      margin: "2px",
                    }}
                  >
                    Nevermind
                  </Button>
                </div>
              )}
              <Button
                variant="contained"
                color="secondary"
                onClick={handleRentOut}
                disabled={listing.completed}
                style={{
                  marginTop: "10px",
                  backgroundColor: "green",
                  color: "white",
                  textTransform: "none",
                }}
              >
                Rent Out
              </Button>
              <StatusDialog
                open={openDialog}
                onClose={handleDialogClose}
                message={dialogMessage}
                severity={dialogSeverity}
              />
            </div>
          )}
        </div>
        <p style={priceStyles}>{listing.title}</p>
        <p>
          <em>{listing.description}</em>
        </p>
        <div>
          <p>
            {listing.address.apartment} {listing.address.streetNumber}{" "}
            {listing.address.street}, {listing.address.city},{" "}
            {listing.address.postalCode}
          </p>
        </div>
        <div
          style={{
            display: "flex",
            flexWrap: "wrap",
            gap: "10px",
            padding: "10px",
            backgroundColor: "#f9f9f9",
            borderRadius: "8px",
          }}
        >
          <span>
            <BedIcon fontSize="small" /> {listing.bedrooms}
          </span>
          <span>
            <BathtubIcon fontSize="small" /> {listing.bathrooms}
          </span>
          <span>
            <HomeIcon fontSize="small" /> {listing.propertyType}
          </span>
        </div>
        {(listing.smokingAllowed || listing.wheelchairAccessible) && (
          <div
            style={{
              display: "flex",
              flexWrap: "wrap",
              gap: "10px",
              padding: "10px",
              backgroundColor: "#f9f9f9",
              borderRadius: "8px",
            }}
          >
            {listing.smokingAllowed && (
              <span>
                <SmokingRoomsIcon fontSize="small" /> Allowed
              </span>
            )}
            {listing.wheelchairAccessible && (
              <span>
                <AccessibleIcon fontSize="small" /> Accessible
              </span>
            )}
          </div>
        )}
        {(listing.amenitiesOffered.gym ||
          listing.amenitiesOffered.laundry ||
          listing.amenitiesOffered.petsAllowed ||
          listing.amenitiesOffered.parking ||
          listing.amenitiesOffered.internetIncluded) && (
          <div
            style={{
              display: "flex",
              flexWrap: "wrap",
              gap: "10px",
              padding: "10px",
              backgroundColor: "#f9f9f9",
              borderRadius: "8px",
            }}
          >
            {/* Gym */}
            {listing.amenitiesOffered.gym && (
              <div
                style={{ display: "flex", alignItems: "center", gap: "5px" }}
              >
                <FitnessCenter fontSize="small" />
                <span>Gym</span>
              </div>
            )}

            {/* Laundry */}
            {listing.amenitiesOffered.laundry && (
              <div
                style={{ display: "flex", alignItems: "center", gap: "5px" }}
              >
                <LocalLaundryService fontSize="small" />
                <span>Laundry</span>
              </div>
            )}

            {/* Pets Allowed */}
            {listing.amenitiesOffered.petsAllowed && (
              <div
                style={{ display: "flex", alignItems: "center", gap: "5px" }}
              >
                <Pets fontSize="small" />
                <span>Pets Allowed</span>
              </div>
            )}

            {/* Parking */}
            {listing.amenitiesOffered.parking && (
              <div
                style={{ display: "flex", alignItems: "center", gap: "5px" }}
              >
                <LocalParking fontSize="small" />
                <span>Parking</span>
              </div>
            )}

            {/* Internet Included */}
            {listing.amenitiesOffered.internetIncluded && (
              <div
                style={{ display: "flex", alignItems: "center", gap: "5px" }}
              >
                <Wifi fontSize="small" />
                <span>Internet Included</span>
              </div>
            )}
          </div>
        )}
        {/* Utilities Button */}
        <Button
          variant="contained"
          color="primary"
          style={{
            margin: "10px 0",
            backgroundColor: "#3A3B3C",
            color: "white",
            textTransform: "none",
          }}
          fullWidth
          onClick={openUtilitiesModal}
        >
          Additional Information
        </Button>
        {isLandlord && (
          <Button
            variant="contained"
            color="primary"
            style={{
              margin: "10px 0",
              backgroundColor: "#3A3B3C",
              color: "white",
              textTransform: "none",
            }}
            fullWidth
            onClick={handleUpdate}
          >
            Update
          </Button>
        )}
        {isLandlord && (
          <Button
            variant="contained"
            color="secondary"
            style={{
              margin: "10px 0",
              backgroundColor: "#d32f2f",
              color: "white",
              textTransform: "none",
            }}
            fullWidth
            onClick={handleDelete}
          >
            Delete
          </Button>
        )}

        {/* Utilities Modal */}
        <Modal open={showUtilitiesModal} onClose={closeUtilitiesModal}>
          <Box sx={modalStyles}>
            <Typography variant="h6" gutterBottom>
              Additional Information
            </Typography>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                gap: "10px",
              }}
            >
              <div
                style={{ display: "flex", alignItems: "center", gap: "10px" }}
              >
                <WaterIcon color="primary" />
                <Typography variant="body1">
                  Water:{" "}
                  {listing.utilitiesCosts != null
                    ? `$${listing.utilitiesCosts.waterCost}`
                    : "N/A"}
                </Typography>
              </div>
              <div
                style={{ display: "flex", alignItems: "center", gap: "10px" }}
              >
                <BoltIcon color="primary" />
                <Typography variant="body1">
                  Electricity:{" "}
                  {listing.utilitiesCosts != null
                    ? `$${listing.utilitiesCosts.electricityCost}`
                    : "N/A"}
                </Typography>
              </div>
              <div
                style={{ display: "flex", alignItems: "center", gap: "10px" }}
              >
                <FireIcon color="primary" />
                <Typography variant="body1">
                  Heating:{" "}
                  {listing.utilitiesCosts != null
                    ? `$${listing.utilitiesCosts.heatingCost}`
                    : "N/A"}
                </Typography>
              </div>

              <div
                style={{ display: "flex", alignItems: "center", gap: "10px" }}
              >
                <SquareFootIcon color="primary" />
                <Typography variant="body1">
                  Square Footage: {listing.squareFootage} square ft
                </Typography>
              </div>
              <Button
                variant="contained"
                color="secondary"
                style={{
                  margin: "10px 0",
                  backgroundColor: "#3A3B3C",
                  color: "white",
                  textTransform: "none",
                }}
                onClick={closeUtilitiesModal}
              >
                Close
              </Button>
            </div>
          </Box>
        </Modal>
      </div>
    </div>
  );
}

export default ListingCard;
