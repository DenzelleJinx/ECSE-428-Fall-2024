import React, { useState, useEffect } from "react";
import Axios from "axios";
import ListingCard from "../create-listing/ListingCard";

export default function MyListings({ landlordId }) {
  const [listings, setListings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchListings = async () => {
      try {
        const response = await Axios.get(`http://localhost:8080/landlord/${landlordId}/my-listings`);
        setListings(response.data);
      } catch (error) {
        console.error("Error fetching listings:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchListings();
  }, [landlordId]);

  if (loading) return <p>Loading...</p>;

  return (
    <div className="listing-grid">
      {listings.map((listing) => (
        <ListingCard key={listing.id} listing={listing} />
      ))}
    </div>
  );
}
