import React, { useState, useEffect } from "react";
import Axios from "axios";
import ListingCard from "../create-listing/ListingCard";

export default function SavedListings({ studentId }) {
  const [listings, setListings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSavedListings = async () => {
      try {
        const response = await Axios.get(`http://localhost:8080/student/${studentId}/saved-listings`);
        setListings(response.data);
      } catch (error) {
        console.error("Error fetching saved listings:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchSavedListings();
  }, [studentId]);

  if (loading) return <p>Loading...</p>;

  return (
    <div className="listing-grid">
      {listings.map((listing) => (
        <ListingCard key={listing.id} listing={listing} />
      ))}
    </div>
  );
}
