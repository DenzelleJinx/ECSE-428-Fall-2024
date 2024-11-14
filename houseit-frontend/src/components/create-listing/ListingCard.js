import React, { useState } from 'react';
import BedIcon from '@mui/icons-material/Bed';
import axios from 'axios';
import BathtubIcon from '@mui/icons-material/Bathtub';
import HomeIcon from '@mui/icons-material/Home';
import SmokingRoomsIcon from '@mui/icons-material/SmokingRooms';
import AccessibleIcon from '@mui/icons-material/Accessible';
import apartmentImage from '../../assets/sample-bedroom.png';

function ListingCard({ listing }) {
    const [isHovered, setIsHovered] = useState(false);
    const [isButtonHovered, setIsButtonHovered] = useState(false);
    const [showPhoneNumber, setShowPhoneNumber] = useState(false);

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

    const imageStyles = {
        width: '100%',
        height: '200px',
        objectFit: 'cover',
    };

    const infoStyles = {
        padding: '10px',
        display: 'flex',
        flexDirection: 'column',
        gap: '2px',
    };

    const priceStyles = {
        fontWeight: 'bold',
        fontSize: '1.2em',
        color: '#333',
    };

    const detailsStyles = {
        display: 'flex',
        gap: '16px',
        alignItems: 'center',
        fontSize: '0.9em',
        color: '#555',
    };

    const buttonStyles = {
        backgroundColor: isButtonHovered ? '#b71c1c' : '#d50032',
        fontSize: '1.25em',
        color: 'white',
        border: 'none',
        padding: '5px',
        borderRadius: '5px',
        cursor: 'pointer',
    };

    const phoneStyles = {
        fontSize: '1.25em',
        color: 'black',
        borderWidth: '1px',
        padding: '4px',
        borderRadius: '5px',
    };

    // Shows and hides phone number only
    const handleToggle = () => {
        setShowPhoneNumber(!showPhoneNumber);
    };

    // Sends notifications
    const handleContactButtonClick = async () => {
        try {
            const axiosClient = axios.create({
                baseURL: "http://localhost:8080",
            });

            // NotificationDTO
            const notificationData = {
                message: `Someone contacted you about the listing: ${listing.title}`,
                localDateTime: new Date().toISOString(),
                type: 'Contact Request',
                senderId: 1, // Get the actual sender instead of just 1 TODO
            };

            const landlordId = listing.landlordId;

            const response = await axiosClient.post(`/users/${landlordId}/notifications`, notificationData, {params: {id: landlordId}});
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
            <img src={apartmentImage} alt={listing.title} style={imageStyles} />
            <div style={infoStyles}>
                <div style={priceStyles}>${listing.monthlyPrice} monthly</div>
                <h4>{listing.title}</h4>
                <p><em>{listing.description}</em></p>
                <p>{listing.address}</p>
                <div style={detailsStyles}>
                    <span><BedIcon fontSize="small" /> {listing.bedrooms}</span>
                    <span><BathtubIcon fontSize="small" /> {listing.bathrooms}</span>
                    <span><HomeIcon fontSize="small" /> {listing.propertyType}</span>
                    {listing.smokingAllowed && (
                        <span><SmokingRoomsIcon fontSize="small" /> Smoking Allowed</span>
                    )}
                    {listing.wheelchairAccessible && (
                        <span><AccessibleIcon fontSize="small" /> Wheelchair Accessible</span>
                    )}
                </div>
                {showPhoneNumber ? (
                    <div style={phoneStyles}>
                        Call {listing.landlordPhone} <button onClick={handleContactButtonClick}>Notify</button>
                        <button onClick={handleToggle}>Nevermind</button>
                    </div>
                ) : (
                    <button style={buttonStyles} onClick={handleToggle} onMouseEnter={() => setIsButtonHovered(true)} onMouseLeave={() => setIsButtonHovered(false)}>
                        Contact
                    </button>
                )}
            </div>            
        </div>
    );
}

export default ListingCard;
