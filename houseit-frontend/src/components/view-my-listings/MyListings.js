import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import ListingCard from "../create-listing/ListingCard";
import Navbar from '../navbar/Navbar';
import { Box, TextField, InputAdornment, IconButton, Typography } from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import ClearIcon from '@mui/icons-material/Clear';

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
                setFilteredListings(response.data); // Assuming no filters are initially applied
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

    const handleClear = () => setSearchQuery('');

    return (
        <div className="dashboard">
            <Navbar />
            <header className="dashboard-header">
                <Typography variant="h4" component="h2" style={{ margin: "20px 0" }}>
                    My Listings
                </Typography>
                <Box sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    width: '50%', // Makes the search bar smaller and more centered
                    margin: 'auto'
                }}>
                    <TextField
                        fullWidth
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
                                    <IconButton onClick={handleClear} edge="end">
                                        <ClearIcon />
                                    </IconButton>
                                </InputAdornment>
                            ),
                        }}
                    />
                </Box>
            </header>
            <div className="listing-grid">
                {loading ? <p>Loading...</p> :
                    filteredListings.length > 0 ? (
                        <Box sx={{
                            display: 'grid',
                            gridTemplateColumns: 'repeat(auto-fill, minmax(240px, 1fr))',
                            gap: '16px',
                            padding: 2
                        }}>
                            {filteredListings.map(listing => (
                                <ListingCard key={listing.id} listing={listing} />
                            ))}
                        </Box>
                    ) : <p>You currently have no listing</p>
                }
                {error && <p>{error}</p>}
            </div>
        </div>
    );
}
