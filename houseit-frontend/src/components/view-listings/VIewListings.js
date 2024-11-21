import React from "react";
import { useListings } from "../../hooks/useListings";
import ListingCard from "../create-listing/ListingCard";
import "../create-listing/Listing.css";
import Navbar from "../navbar/Navbar";

// I created mock listings giving that the database implementation had not been merged yet.
// Whoever works on the back-end can uncomment the code such that useListings is called.

const listings = [
  // {
  //     id: 1,
  //     title: "3 1/2 for Rent in the McGill Ghetto",
  //     address: "3021 Rue Milton, Montreal, QC H2X 1W5",
  //     description: "Cozy apartment, hydro included in price!",
  //     monthlyPrice: "1,200.00",
  //     bedrooms: 1,
  //     bathrooms: 1,
  //     squareFootage: 880,
  //     propertyType: 'Lease',
  //     smokingAllowed: false,
  //     wheelchairAccessible: true,
  // },
  // {
  //     id: 2,
  //     title: "4 1/2 to Sublet in the McGill Ghetto",
  //     address: "3628 Rue Lorne Crescent, Montreal, QC H2X 2A8",
  //     description: "Subletting from May to August 2025",
  //     monthlyPrice: "2,000.00",
  //     bedrooms: 2,
  //     bathrooms: 1,
  //     squareFootage: 1000,
  //     propertyType: 'Sublet',
  //     smokingAllowed: true,
  //     wheelchairAccessible: true
  //
  // },
  // {
  //     id: 3,
  //     title: "5 1/2 to Sublet in the McGill Ghetto",
  //     address: "3628 Rue Durocher, Montreal, QC H2X 2W4",
  //     description: "Subletting from January to August 2025",
  //     monthlyPrice: "1,200.00",
  //     bedrooms: 4,
  //     bathrooms: 2,
  //     squareFootage: 1200,
  //     propertyType: 'Sublet',
  //     smokingAllowed: true,
  //
  // },
  // Add more listings as needed
];

export default function Listing() {
  const { listings, loading, error, setListings } = useListings(); // Assuming you have a setter to update the listings state
  console.log(listings);
  if (listings === undefined) return <p>No listings available.</p>;

  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  // State for managing modal visibility
  const [showModal, setShowModal] = useState(false);
  const [currentListingId, setCurrentListingId] = useState(null);

  // Function to handle deletion
  const handleDelete = async (id) => {
    try {
      // Send DELETE request to backend to delete the listing
      const response = await fetch(`/api/listings/${id}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error("Failed to delete the listing");
      }

      // Update the state to remove the deleted listing
      setListings((prevListings) =>
        prevListings.filter((listing) => listing.id !== id)
      );

      // Close the modal after successful deletion
      setShowModal(false);
      alert("Listing deleted successfully");
    } catch (error) {
      console.error("Error deleting listing:", error);
      alert("Failed to delete listing");
    }
  };

  // Function to show the delete confirmation modal
  const showDeleteModal = (id) => {
    setCurrentListingId(id);
    setShowModal(true);
  };

  // Function to hide the delete confirmation modal
  const hideDeleteModal = () => {
    setShowModal(false);
    setCurrentListingId(null);
  };

  return (
    <div className="dashboard">
      <Navbar />
      <header className="dashboard-header">
        <h2>Listings</h2>
      </header>
      <div className="listing-grid">
        {listings.map((listing) => (
          <div key={listing.id} className="listing-card-wrapper">
            <ListingCard listing={listing} />
            {/* Delete Button */}
            <button
              onClick={() => showDeleteModal(listing.id)}
              className="delete-btn"
            >
              Delete
            </button>
          </div>
        ))}
      </div>

      {/* Modal for Delete Confirmation */}
      {showModal && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h3>Are you sure you want to delete this listing?</h3>
            <div>
              <button
                onClick={() => handleDelete(currentListingId)}
                className="confirm-btn"
              >
                Yes, Delete
              </button>
              <button onClick={hideDeleteModal} className="cancel-btn">
                No, Cancel
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
