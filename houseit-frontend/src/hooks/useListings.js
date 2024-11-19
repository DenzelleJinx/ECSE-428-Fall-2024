import { useState, useEffect } from 'react';
import axios from 'axios';

export function useListings() {
    const [listings, setListings] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchListings = async () => {
            setLoading(true);
            try {
                const response = await axios.get('http://localhost:8080/listing');
                // console.log(response.data)

                // Fetch lister details for each listing
                const listingsWithListers = await Promise.all(
                    response.data.map(async (listing) => {
                        try {
                            const listerResponse = await axios.get(`http://localhost:8080/listing/${listing.id}/lister`);
                            // Add lister to listing
                            return { ...listing, lister: listerResponse.data };
                        } catch (error) {
                            console.error(`Error fetching lister for listing ${listing.id}:`, error);
                            return { ...listing, lister: null };
                        }
                    })
                );
                // console.log(listingsWithListers);
                setListings(listingsWithListers);
                setError(null);
            } catch (err) {
                setError('Error fetching listings');
            } finally {
                setLoading(false);
            }
        };
        fetchListings(); // Call fetchListings here
    }, []);
    return { listings, loading, error };
}
