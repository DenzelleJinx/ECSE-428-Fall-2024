import React from 'react';
import { useListings } from '../../hooks/useListings';
import ListingCard from "../create-listing/ListingCard";
import '../create-listing/Listing.css';

// I created mock listings giving that the database implementation had not been merged yet.
// Whoever works on the back-end can uncomment the code such that useListings is called.

const listings = [
    {
        id: 1,
        title: "3 1/2 for Rent in the McGill Ghetto",
        address: "3021 Rue Milton, Montreal, QC H2X 1W5",
        description: "Cozy apartment, hydro included in price!",
        monthlyPrice: "1,200.00",
        bedrooms: 1,
        bathrooms: 1,
        squareFootage: 880,
        propertyType: 'Lease',
        smokingAllowed: false,
        wheelchairAccessible: true,
    },
    {
        id: 2,
        title: "4 1/2 to Sublet in the McGill Ghetto",
        address: "3628 Rue Lorne Crescent, Montreal, QC H2X 2A8",
        description: "Subletting from May to August 2025",
        monthlyPrice: "2,000.00",
        bedrooms: 2,
        bathrooms: 1,
        squareFootage: 1000,
        propertyType: 'Sublet',
        smokingAllowed: true,
        wheelchairAccessible: true

    },
    {
        id: 3,
        title: "5 1/2 to Sublet in the McGill Ghetto",
        address: "3628 Rue Durocher, Montreal, QC H2X 2W4",
        description: "Subletting from January to August 2025",
        monthlyPrice: "1,200.00",
        bedrooms: 4,
        bathrooms: 2,
        squareFootage: 1200,
        propertyType: 'Sublet',
        smokingAllowed: true,

    },
    // Add more listings as needed
];

export default function Listing() {
    // Create sample listings because none are currently defined.

    // const { listing, loading, error } = useListings();
    // if (listing ===  undefined) return <p>No listings available.</p>;
    //
    // if (loading) return <p>Loading...</p>;
    // if (error) return <p>{error}</p>;

    // return (
    //     <div>
    //         <h1>"Currently there are no listings, so I cannot extract properties directly from there"</h1>
    //     </div>
    // );

    return (
        <div className="dashboard">
            <header className="dashboard-header">
                <h2>Listings</h2>
            </header>
            <div className="listing-grid">
                {listings.map(listing => (
                    <ListingCard key={listing.id} listing={listing} />
                ))}
            </div>
        </div>
    );
}
