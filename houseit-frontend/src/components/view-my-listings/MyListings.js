import React, { useState, useEffect } from 'react';
import { useListings } from '../../hooks/useListings';
import ListingCard from "../create-listing/ListingCard";
import '../create-listing/Listing.css';
import Navbar from '../navbar/Navbar';
import Axios from 'axios';
import StatusDialog from '../status-dialog/StatusDialog';
import FilterModal from '../filter-modal/FilterModal';
import { Box, Button, TextField, InputAdornment, IconButton,} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import ClearIcon from '@mui/icons-material/Clear';
import FilterAltIcon from '@mui/icons-material/FilterAlt'; 

export default function MyListings({ landlordId }) {
    const [listings, setListings] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [searchQuery, setSearchQuery] = useState('');
    const [filteredListings, setFilteredListings] = useState([]);
    const [openDialog, setOpenDialog] = useState(false);
    const [dialogMessage, setDialogMessage] = useState('');
    const [dialogSeverity, setDialogSeverity] = useState('error');
    const [isFilterModalOpen, setIsFilterModalOpen] = useState(false);
    const [filters, setFilters] = useState({
        address: '',
        bedrooms: { min: '', max: '' },
        bathrooms: { min: '', max: '' },
        propertyType: '',
        smokingAllowed: null,
        wheelchairAccessible: null,
        amenities: {
            gym: null,
            laundry: null,
            petsAllowed: null,
            parking: null,
            internetIncluded: null,
        },
        utilities: {
            waterCost: { min: '', max: '' },
            electricityCost: { min: '', max: '' },
            heatingCost: { min: '', max: '' },
        },
        squareFootage: { min: '', max: '' },
        propertyRating: { min: '', max: '' },
    });


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

    const handleClear = () => setSearchQuery('');
    const handleFilterChange = (newFilters) => {
        setFilters(newFilters);
    };

    return (
        <div className="dashboard">
            <Navbar />
            <header className="dashboard-header">
                <h2>My Listings</h2>
                <div className="actions">
                    {/*<input
                        type="text"
                        className="search-bar"
                        placeholder="Search"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                    /> */}
                    <Box sx={{ display: 'flex', justifyContent: 'center', gap: '10px' }}>
                        <TextField
                            variant="outlined"
                            placeholder="Search..."
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                            slotProps= {{
                                input: {
                                    startAdornment: (
                                        <InputAdornment position="start">
                                            <SearchIcon />
                                        </InputAdornment>
                                    ),
                                    endAdornment: searchQuery && (
                                        <InputAdornment position="end">
                                            <IconButton onClick={handleClear}>
                                                <ClearIcon />
                                            </IconButton>
                                        </InputAdornment>
                                    )
                                }
                            }}
                            sx={{
                                height: '36px',
                                '& .MuiOutlinedInput-root': {
                                  height: '100%',
                                },
                                '& .MuiInputBase-input': {
                                  padding: '8px',
                                },
                                flexGrow: 0.25,
                              }}
                        />
                        <IconButton
                            sx={{ height: '36px', width: '36px' }}
                            onClick={() => setIsFilterModalOpen(true)}
                        >
                            <FilterAltIcon />
                        </IconButton>
                    </Box>
                </div>
            </header>
            <div className="listing-grid">
                {filteredListings.map(listing => (
                    <ListingCard
                        key={listing.id}
                        listing={listing}
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

            {/* Filter Modal */}
            {isFilterModalOpen && (
                <FilterModal
                    filters={filters}
                    onChange={setFilters}
                    onClose={() => setIsFilterModalOpen(false)}
                    onApply={handleFilterChange}
                />
            )}
        </div>
    );
}
