import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import ListingCard from "../create-listing/ListingCard";
import Navbar from '../navbar/Navbar';
import { Box, TextField, InputAdornment, IconButton, Typography } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import ClearIcon from '@mui/icons-material/Clear';
import FilterAltIcon from '@mui/icons-material/FilterAlt';

export default function MyListings({ landlordId }) {
    const [listings, setListings] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [searchQuery, setSearchQuery] = useState('');
    const [filteredListings, setFilteredListings] = useState([]);

    useEffect(() => {
        const fetchListings = async () => {
            try {
                const response = await Axios.get(`http://localhost:8080/landlord/${landlordId}/listings`);
                setListings(response.data);
                setFilteredListings(response.data);
                setLoading(false);
            } catch (error) {
                console.error("Error fetching listings:", error);
                setError("Failed to fetch listings.");
                setLoading(false);
            }
        };

        fetchListings();
    }, [landlordId]);

    useEffect(() => {
        const lowercasedQuery = searchQuery.toLowerCase();
        const filtered = listings.filter(listing =>
            listing.title.toLowerCase().includes(lowercasedQuery) ||
            listing.description.toLowerCase().includes(lowercasedQuery)
        );
        setFilteredListings(filtered);
    }, [searchQuery, listings]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;
    if (!listings.length) return <p>No listings found.</p>;

    return (
        <div className="dashboard">
            <Navbar />
            <header className="dashboard-header">
                <Typography variant="h2" gutterBottom>
                    My Listings
                </Typography>
                <Box sx={{ display: 'flex', justifyContent: 'center', gap: '10px' }}>
                    <TextField
                        variant="outlined"
                        placeholder="Search..."
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        InputProps={{
                            startAdornment: (
                                <InputAdornment position="start">
                                    <SearchIcon />
                                </InputAdornment>
                            ),
                            endAdornment: (
                                <InputAdornment position="end">
                                    {searchQuery && (
                                        <IconButton onClick={() => setSearchQuery('')}>
                                            <ClearIcon />
                                        </IconButton>
                                    )}
                                </InputAdornment>
                            ),
                        }}
                        sx={{
                            width: '100%',
                            maxWidth: '500px',
                        }}
                    />
                </Box>
            </header>
            <div className="listing-grid">
                {filteredListings.map(listing => (
                    <ListingCard key={listing.id} listing={listing} />
                ))}
            </div>
        </div>
    );
}
