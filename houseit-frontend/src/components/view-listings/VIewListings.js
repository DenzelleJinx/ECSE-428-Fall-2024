import React, { useState, useEffect } from 'react';
import { useListings } from '../../hooks/useListings';
import ListingCard from "../create-listing/ListingCard";
import '../create-listing/Listing.css';
import Navbar from '../navbar/Navbar';
import Axios from 'axios';
import StatusDialog from '../status-dialog/StatusDialog';

export default function Listing() {
    // Use the custom hook to fetch listings
    const { listings: fetchedListings, loading, error } = useListings();

    // Maintain local state for active listings
    const [listings, setListings] = useState([]);
    const [openDialog, setOpenDialog] = useState(false);
    const [dialogMessage, setDialogMessage] = useState('');
    const [dialogSeverity, setDialogSeverity] = useState('error');

    // Populate local state when fetchedListings changes
    useEffect(() => {
        if (fetchedListings) {
            const activeListings = fetchedListings.filter(listing => !listing.completed);
            setListings(activeListings);
        }
    }, [fetchedListings]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleRentOut = async (listingId) => {
        try {
            const response = await Axios.put(`http://localhost:8080/listing/${listingId}/complete`);
            if (response.status === 200) {
                setDialogMessage('Your listing has been rented out. A notification has been sent to the landlord.');
                setDialogSeverity('success');
                setOpenDialog(true);
            }

            setListings(prevListings => prevListings.filter(listing => listing.id !== listingId));
        } catch (error) {
            console.error("Error renting out listing:", error);
            setDialogMessage('An error occurred while renting out the listing. Please try again later.');
            setDialogSeverity('error');
            setOpenDialog(true);
        }
    };

    return (
        <div className="dashboard">
            <Navbar />
            <header className="dashboard-header">
                <h2>Listings</h2>
            </header>
            <div className="listing-grid">
                {listings.map(listing => (
                    <ListingCard
                        key={listing.id}
                        listing={listing}
                        onRentOut={handleRentOut}
                        />
                ))}
            </div>
            {/* Status Dialog */}
            <StatusDialog
                open={openDialog}
                onClose={() => setOpenDialog(false)}
                severity={dialogSeverity}
                message={dialogMessage}
            />
        </div>
    );
}
