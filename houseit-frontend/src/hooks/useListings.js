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
                setListings(response.data);
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