import React, { useState, useEffect } from "react";
import { useListings } from "../../hooks/useListings";
import ListingCard from "../create-listing/ListingCard";
import "../create-listing/Listing.css";
import Navbar from "../navbar/Navbar";
import Axios from "axios";
import StatusDialog from "../status-dialog/StatusDialog";
import FilterModal from "../filter-modal/FilterModal";
import {
  Box,
  Button,
  TextField,
  InputAdornment,
  IconButton,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import ClearIcon from "@mui/icons-material/Clear";
import FilterAltIcon from "@mui/icons-material/FilterAlt";

export default function Listing() {
  // Use the custom hook to fetch listings
  const { listings: fetchedListings, loading, error } = useListings();

  // Maintain local state for active listings
  const [listings, setListings] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [dialogMessage, setDialogMessage] = useState("");
  const [dialogSeverity, setDialogSeverity] = useState("error");
  const [filteredListings, setFilteredListings] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");

  // Define the filter modal state and filter state
  const [isFilterModalOpen, setIsFilterModalOpen] = useState(false);
  const [filters, setFilters] = useState({
    address: "",
    bedrooms: { min: "", max: "" },
    bathrooms: { min: "", max: "" },
    propertyType: "",
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
      waterCost: { min: "", max: "" },
      electricityCost: { min: "", max: "" },
      heatingCost: { min: "", max: "" },
    },
    squareFootage: { min: "", max: "" },
    propertyRating: { min: "", max: "" },
  });

  const [deleteListingId, setDeleteListingId] = useState(null);

  useEffect(() => {
    if (fetchedListings) {
      const activeListings = fetchedListings.filter(
        (listing) => !listing.completed
      );
      setListings(activeListings);
      setFilteredListings(activeListings);
    }
  }, [fetchedListings]);

  // Update filtered listings when search or filters change
  useEffect(() => {
    const lowercasedQuery = searchQuery.toLowerCase();
    const filtered = listings.filter((listing) => {
      const matchesSearch =
        listing.title.toLowerCase().includes(lowercasedQuery) ||
        listing.description.toLowerCase().includes(lowercasedQuery);
      const matchesFilters =
        (filters.address
          ? listing.address.street
              .toLowerCase()
              .includes(filters.address.toLowerCase())
          : true) &&
        (filters.bedrooms.min
          ? listing.bedrooms >= filters.bedrooms.min
          : true) &&
        (filters.bedrooms.max
          ? listing.bedrooms <= filters.bedrooms.max
          : true) &&
        (filters.bathrooms.min
          ? listing.bathrooms >= filters.bathrooms.min
          : true) &&
        (filters.bathrooms.max
          ? listing.bathrooms <= filters.bathrooms.max
          : true) &&
        (filters.propertyType
          ? listing.propertyType === filters.propertyType
          : true);
      return matchesSearch && matchesFilters;
    });
    setFilteredListings(filtered);
  }, [searchQuery, filters, listings]);

  const handleFilterChange = (newFilters) => {
    setFilters(newFilters);
  };

  const handleClear = () => {
    setSearchQuery("");
  };

  const handleDeleteListing = (listingId) => {
    // Show the confirmation dialog
    setDeleteListingId(listingId);
  };

  const confirmDelete = async () => {
    try {
      //your API or delete the listing here
      await Axios.delete(`http://localhost:8080/listing/${deleteListingId}`);
      setListings((prevListings) =>
        prevListings.filter((listing) => listing.id !== deleteListingId)
      );
      setDialogMessage("Listing deleted successfully!");
      setDialogSeverity("success");
      setOpenDialog(true);
    } catch (error) {
      setDialogMessage("Error deleting listing. Please try again later.");
      setDialogSeverity("error");
      setOpenDialog(true);
    }
    setDeleteListingId(null); // Reset deleteListingId
  };

  const cancelDelete = () => {
    setDeleteListingId(null); // Reset deleteListingId
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="dashboard">
      <Navbar />
      <header className="dashboard-header">
        <h2>Listings</h2>
        <div className="actions">
          <Box sx={{ display: "flex", justifyContent: "center", gap: "10px" }}>
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
                  ),
                },
              }}
              sx={{
                height: "36px",
                "& .MuiOutlinedInput-root": {
                  height: "100%",
                },
                "& .MuiInputBase-input": {
                  padding: "8px",
                },
                flexGrow: 0.25,
              }}
            />
            <IconButton
              sx={{ height: "36px", width: "36px" }}
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
          onChange={handleFilterChange}
          onClose={() => setIsFilterModalOpen(false)}
          onApply={handleFilterChange}
        />
      )}

      {/* Confirmation Dialog for Deleting Listing */}
      <Dialog open={deleteListingId !== null} onClose={cancelDelete}>
        <DialogTitle>Delete Listing</DialogTitle>
        <DialogContent>
          <p>Are you sure you want to delete this listing?</p>
        </DialogContent>
        <DialogActions>
          <Button onClick={cancelDelete} color="primary">
            Cancel
          </Button>
          <Button onClick={confirmDelete} color="error">
            Delete
          </Button>
        </DialogActions>
      </Dialog>

      {/* Floating "Delete Listing" Button */}
      <Button
        variant="contained"
        color="error"
        sx={{
          position: "fixed",
          bottom: "20px",
          right: "20px",
          zIndex: 1000,
          width: "150px",
          height: "45px",
          textTransform: "none",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          borderRadius: "4px",
        }}
        onClick={() => handleDeleteListing(123)} // Replace 123 with actual listing ID
      >
        Delete Listing
      </Button>
    </div>
  );
}
