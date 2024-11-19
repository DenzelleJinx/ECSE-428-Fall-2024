import React, { useState } from 'react';
import BedIcon from '@mui/icons-material/Bed';
import BathtubIcon from '@mui/icons-material/Bathtub';
import HomeIcon from '@mui/icons-material/Home';
import SmokingRoomsIcon from '@mui/icons-material/SmokingRooms';
import AccessibleIcon from '@mui/icons-material/Accessible';
import SquareFootIcon from '@mui/icons-material/SquareFoot'
import { FitnessCenter, LocalLaundryService, Pets, LocalParking, Wifi } from '@mui/icons-material';
import WaterIcon from '@mui/icons-material/Opacity';
import BoltIcon from '@mui/icons-material/Bolt';
import FireIcon from '@mui/icons-material/Whatshot';
import { Button, Modal, Box, Typography } from '@mui/material';
import axios from 'axios';


import apartmentImage from '../../assets/sample-bedroom.png';
import PropertyListing from "../view-listings/ImagePopup";

function ListingCard({ listing}) {
    const [isHovered, setIsHovered] = useState(false);
    const [showPhoneNumber, setShowPhoneNumber] = useState(false);
    const [contactErrorMessage, setContactErrorMessage] = useState('');

    const cardStyles = {
        border: '1px solid #ddd',
        borderRadius: '8px',
        overflow: 'hidden',
        backgroundColor: isHovered ? '#ffe5e5' : '#fff',
        transition: 'box-shadow 0.3s, background-color 0.3s',
        display: 'flex',
        flexDirection: 'column',
        maxWidth: '400px',
        boxShadow: isHovered ? '0 4px 12px rgba(255, 0, 0, 0.3)' : '0 4px 8px rgba(0, 0, 0, 0.1)',
    };


    const infoStyles = {
        padding: '10px',
        display: 'flex',
        flexDirection: 'column',
        gap: '2px',
        fontFamily: "'Roboto', sans-serif", // Material-UI default font

    };

    const priceStyles = {
        fontWeight: 'bold',
        fontSize: '1.8em',
        margin: '0px',
        color: '#3A3B3C',
        fontFamily: "'Roboto', sans-serif", // Material-UI default font
    };

    const priceButtonContainerStyles = {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        marginBottom: '10px',
    };
    const modalStyles = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: '80%',
        maxWidth: '400px',
        bgcolor: 'background.paper',
        boxShadow: 24,
        p: 3,
        borderRadius: 2,
    };

    const phoneStyles = {
        fontSize: '1.25em',
        color: 'white',
        backgroundColor: 'black',
        borderWidth: '1px',
        padding: '4px',
        textAlign: 'center',
        borderRadius: '4px',
        margin: '2px'
    }

    const openUtilitiesModal = () => setShowUtilitiesModal(true);
    const closeUtilitiesModal = () => setShowUtilitiesModal(false);
    const [showUtilitiesModal, setShowUtilitiesModal] = useState(false);

    var callString = `Call ${listing.lister.phoneNumber}`;
    if (listing.lister == null) {
        callString = "Number Unavailable";
    }

    const handleToggle = () => {
        const user = JSON.parse(localStorage.getItem('currentUser'));
        // Don't allow contact button if not logged in
        if (user == null) {
            setContactErrorMessage("Log in to contact lister.")
            return;
        }
        setShowPhoneNumber(!showPhoneNumber);
    };

    // Sends notifications
    const handleNotify = async () => {
        try {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            if (user == null) {
                console.log("User is not logged in.");
                return;
            }
            const body = {
                type: "CONTACT",
                senderUsername: user.username
            };
            const response = await axios.post(`http://localhost:8080/users/${listing.lister.username}/notifications`, body);
            console.log('Notification successful:', response.data);
            
        } catch (error) {
            console.error('Error sending notification:', error);
        }
    };

    return (
        <div
            style={cardStyles}
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
            <div>
                <PropertyListing key={listing.id} property={listing}/>
            </div>
            <div style={infoStyles}>
                <div style={priceButtonContainerStyles}>
                    <p style={priceStyles}>${listing.monthlyPrice} monthly</p>
                    {!showPhoneNumber ?
                        (<div><Button
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
                                fontSize: '1.1em',
                                textTransform: "none"
                            }}
                        >
                        Contact
                        </Button>
                        <p style={{color: 'var(--template-palette-error-main)'}}>{contactErrorMessage}</p></div>) :
                        (<div>
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
                                    fontSize: '1.1em',
                                    textTransform: "none",
                                    margin: '2px'
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
                                    fontSize: '1.1em',
                                    margin: '2px'
                                }}
                            >
                            Nevermind
                            </Button>
                        </div>)
                    }
                </div>
                <p style={priceStyles}>{listing.title}</p>
                <p><em>{listing.description}</em></p>
                <div>
                    <p>
                        {listing.address.apartmentNumber} {listing.address.streetNumber} {listing.address.street}, {listing.address.city}, {listing.address.postalCode}
                    </p>
                </div>
                <div style={{
                    display: 'flex',
                    flexWrap: 'wrap',
                    gap: '10px',
                    padding: '10px',
                    backgroundColor: '#f9f9f9',
                    borderRadius: '8px',
                }}>
                    <span><BedIcon fontSize="small"/> {listing.bedrooms}</span>
                    <span><BathtubIcon fontSize="small"/> {listing.bathrooms}</span>
                    <span><HomeIcon fontSize="small"/> {listing.propertyType}</span>
                </div>
                {(
                    (listing.smokingAllowed ||
                        listing.wheelchairAccessible
                    ) && (
                        <div style={{
                            display: 'flex',
                            flexWrap: 'wrap',
                            gap: '10px',
                            padding: '10px',
                            backgroundColor: '#f9f9f9',
                            borderRadius: '8px',
                        }}>
                            {listing.smokingAllowed && (
                                <span><SmokingRoomsIcon fontSize="small"/> Allowed</span>
                            )}
                            {listing.wheelchairAccessible && (
                                <span><AccessibleIcon fontSize="small"/>  Accessible</span>
                            )}
                        </div>
                    )
                )}

                {(
                    (listing.amenitiesOffered.gym ||
                        listing.amenitiesOffered.laundry ||
                        listing.amenitiesOffered.petsAllowed ||
                        listing.amenitiesOffered.parking ||
                        listing.amenitiesOffered.internetIncluded)
                ) && (

                    <div
                        style={{
                            display: 'flex',
                            flexWrap: 'wrap',
                            gap: '10px',
                            padding: '10px',
                            backgroundColor: '#f9f9f9',
                            borderRadius: '8px',
                        }}
                    >
                        {/* Gym */}
                        {listing.amenitiesOffered.gym && (
                            <div style={{display: 'flex', alignItems: 'center', gap: '5px'}}>
                                <FitnessCenter fontSize="small"/>
                                <span>Gym</span>
                            </div>
                        )}

                        {/* Laundry */}
                        {listing.amenitiesOffered.laundry && (
                            <div style={{display: 'flex', alignItems: 'center', gap: '5px'}}>
                                <LocalLaundryService fontSize="small"/>
                                <span>Laundry</span>
                            </div>
                        )}

                        {/* Pets Allowed */}
                        {listing.amenitiesOffered.petsAllowed && (
                            <div style={{display: 'flex', alignItems: 'center', gap: '5px'}}>
                                <Pets fontSize="small"/>
                                <span>Pets Allowed</span>
                            </div>
                        )}

                        {/* Parking */}
                        {listing.amenitiesOffered.parking && (
                            <div style={{display: 'flex', alignItems: 'center', gap: '5px'}}>
                                <LocalParking fontSize="small"/>
                                <span>Parking</span>
                            </div>
                        )}

                        {/* Internet Included */}
                        {listing.amenitiesOffered.internetIncluded && (
                            <div style={{display: 'flex', alignItems: 'center', gap: '5px'}}>
                                <Wifi fontSize="small"/>
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
                        margin: '10px 0',
                        backgroundColor: '#3A3B3C',
                        color: 'white',
                        textTransform: 'none',
                    }}
                    fullWidth
                    onClick={openUtilitiesModal}
                >
                    Additional Information
                </Button>

                {/* Utilities Modal */}
                <Modal open={showUtilitiesModal} onClose={closeUtilitiesModal}>
                    <Box sx={modalStyles}>
                        <Typography variant="h6" gutterBottom>
                            Additional Information
                        </Typography>
                        <div style={{display: 'flex', flexDirection: 'column', gap: '10px'}}>
                            <div style={{display: 'flex', alignItems: 'center', gap: '10px'}}>
                                <WaterIcon color="primary"/>
                                <Typography variant="body1">
                                    Water: {listing.utilitiesCosts != null ? `$${listing.utilitiesCosts.waterCost}` : 'N/A'}
                                </Typography>
                            </div>
                            <div style={{display: 'flex', alignItems: 'center', gap: '10px'}}>
                                <BoltIcon color="primary"/>
                                <Typography variant="body1">
                                    Electricity: {listing.utilitiesCosts != null ? `$${listing.utilitiesCosts.electricityCost}` : 'N/A'}
                                </Typography>
                            </div>
                            <div style={{display: 'flex', alignItems: 'center', gap: '10px'}}>
                                <FireIcon color="primary"/>
                                <Typography variant="body1">
                                    Heating: {listing.utilitiesCosts != null ? `$${listing.utilitiesCosts.heatingCost}` : 'N/A'}
                                </Typography>
                            </div>

                        <div style={{display: 'flex', alignItems: 'center', gap: '10px'}}>
                            <SquareFootIcon color="primary"/>
                            <Typography variant="body1">
                                Square Footage: {listing.squareFootage} square ft
                            </Typography>
                        </div>
                        <Button
                            variant="contained"
                            color="secondary"
                            style={{
                                margin: '10px 0',
                                backgroundColor: '#3A3B3C',
                                color: 'white',
                                textTransform: 'none',
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
)
};

export default ListingCard;
