import React, { useState, useEffect } from "react";
import Axios from "axios";
import ListingCard from "../create-listing/ListingCard";
import { useListings } from '../../hooks/useListings';
import '../create-listing/Listing.css';
import Navbar from '../navbar/Navbar';
import StatusDialog from '../status-dialog/StatusDialog';
import FilterModal from '../filter-modal/FilterModal';
import {Box, Button, TextField, InputAdornment, IconButton, Typography, CssBaseline} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import ClearIcon from '@mui/icons-material/Clear';
import FilterAltIcon from '@mui/icons-material/FilterAlt';
import ColorModeSelect from "../../shared-theme/ColorModeSelect";
import AppTheme from "../../shared-theme/AppTheme";  // Filter icon

export default function SavedListings({ studentId }) {
  const [loading, setLoading] = useState(true);

  // Maintain local state for active listings
  const [listings, setListings] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [dialogMessage, setDialogMessage] = useState('');
  const [dialogSeverity, setDialogSeverity] = useState('error');
  const [filteredListings, setFilteredListings] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  // Populate local state when fetchedListings changes
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

  const [isFilterModalOpen, setIsFilterModalOpen] = useState(false);

  useEffect(() => {
    const fetchSavedListings = async () => {
      try {
        // Get the current user from localStorage
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));

        // Check if currentUser exists and has an id
        if (!currentUser || !currentUser.id) {
          console.error("No valid student ID found.");
          setLoading(false);
          return;
        }

        const studentId = currentUser.id; // Extract student ID from the current user
        const response = await Axios.get(`http://localhost:8080/student/${studentId}/favorites`);
        setListings(response.data);
      } catch (error) {
        console.error("Error fetching saved listings:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchSavedListings();
  }, []); // Dependency array is empty to run the effect once on mount


  // Update filtered listings when search or filters change
  useEffect(() => {
    const lowercasedQuery = searchQuery.toLowerCase();
    const filtered = listings.filter(listing => {
      // Search Query Matching
      const matchesSearch =
        listing.title.toLowerCase().includes(lowercasedQuery) ||
        listing.description.toLowerCase().includes(lowercasedQuery);

      // Filter Matching
      const matchesFilters = (
        (filters.address ? listing.address.street.toLowerCase().includes(filters.address.toLowerCase()) : true) &&
        (filters.bedrooms.min ? listing.bedrooms >= filters.bedrooms.min : true) &&
        (filters.bedrooms.max ? listing.bedrooms <= filters.bedrooms.max : true) &&
        (filters.bathrooms.min ? listing.bathrooms >= filters.bathrooms.min : true) &&
        (filters.bathrooms.max ? listing.bathrooms <= filters.bathrooms.max : true) &&
        (filters.propertyType ? listing.propertyType === filters.propertyType : true) &&
        (filters.smokingAllowed !== null ? listing.smokingAllowed === filters.smokingAllowed : true) &&
        (filters.wheelchairAccessible !== null ? listing.wheelchairAccessible === filters.wheelchairAccessible : true) &&
        (filters.amenities.gym !== null ? listing.amenitiesOffered.gym === filters.amenities.gym : true) &&
        (filters.amenities.laundry !== null ? listing.amenitiesOffered.laundry === filters.amenities.laundry : true) &&
        (filters.amenities.petsAllowed !== null ? listing.amenitiesOffered.petsAllowed === filters.amenities.petsAllowed : true) &&
        (filters.amenities.parking !== null ? listing.amenitiesOffered.parking === filters.amenities.parking : true) &&
        (filters.amenities.internetIncluded !== null ? listing.amenitiesOffered.internetIncluded === filters.amenities.internetIncluded : true) &&
        (filters.utilities.waterCost.min ? listing.utilities.waterCost >= filters.utilities.waterCost.min : true) &&
        (filters.utilities.waterCost.max ? listing.utilities.waterCost <= filters.utilities.waterCost.max : true) &&
        (filters.utilities.electricityCost.min ? listing.utilities.electricityCost >= filters.utilities.electricityCost.min : true) &&
        (filters.utilities.electricityCost.max ? listing.utilities.electricityCost <= filters.utilities.electricityCost.max : true) &&
        (filters.utilities.heatingCost.min ? listing.utilities.heatingCost >= filters.utilities.heatingCost.min : true) &&
        (filters.utilities.heatingCost.max ? listing.utilities.heatingCost <= filters.utilities.heatingCost.max : true) &&
        (filters.squareFootage.min ? listing.squareFootage >= filters.squareFootage.min : true) &&
        (filters.squareFootage.max ? listing.squareFootage <= filters.squareFootage.max : true) &&
        (filters.propertyRating.min ? listing.propertyRating >= filters.propertyRating.min : true) &&
        (filters.propertyRating.max ? listing.propertyRating <= filters.propertyRating.max : true)
      );

      return matchesSearch && matchesFilters;
    });
    setFilteredListings(filtered);
  }, [searchQuery, filters, listings]);

  const handleFilterChange = (newFilters) => {
    setFilters(newFilters);
  };

  const handleClear = () => {
    setSearchQuery('');
  };

  if (loading) return <p>Loading...</p>;

  return (
      <AppTheme >
        <CssBaseline enableColorScheme />
        <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem' , marginTop: "4rem"}} />
        <Navbar />
      <header className="dashboard-header">
        <h2>Listings</h2>
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
              slotProps={{
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
        {filteredListings.map((listing) => (
          <ListingCard key={listing.id} listing={listing} />
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
    </AppTheme>
  );
}
