import React, { useState } from 'react';
import './FilterModal.css';

export default function FilterModal({ filters, onChange, onClose, onApply }) {
    const [localFilters, setLocalFilters] = useState(filters);

    // This is to make sure the modal updates to continous input
    const handleInputChange = (key, subKey, value) => {
        setLocalFilters(prevFilters => {
            if (subKey) {
                return { ...prevFilters, [key]: { ...prevFilters[key], [subKey]: value } };
            }
            return { ...prevFilters, [key]: value };
        });
    };

    const handleApply = () => {
        onApply(localFilters);
        onClose();
    };

    const handleReset = () => {
        const resetFilters = {
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
        };

        setLocalFilters(resetFilters); // Reset local state
    };

    return (
        <div className="filter-modal">
            <div className="modal-content">
                <h1 className="modal-header">Filter Listings</h1>
                <div className="filter-section">
                    <label>Street Name: </label>
                    <input
                        type="text"
                        value={localFilters.address}
                        onChange={(e) => handleInputChange('address', null, e.target.value)}
                    />
                </div>
                <div className="filter-section">
                    <label>Bedrooms: </label>
                    <input
                        type="number"
                        placeholder="Min"
                        value={localFilters.bedrooms.min}
                        onChange={(e) => handleInputChange('bedrooms', 'min', e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Max"
                        value={localFilters.bedrooms.max}
                        onChange={(e) => handleInputChange('bedrooms', 'max', e.target.value)}
                    />
                </div>
                <div className="filter-section">
                    <label>Bathrooms: </label>
                    <input
                        type="number"
                        placeholder="Min"
                        value={localFilters.bathrooms.min}
                        onChange={(e) => handleInputChange('bathrooms', 'min', e.target.value)}
                    />
                    <input
                        type="number"
                        placeholder="Max"
                        value={localFilters.bathrooms.max}
                        onChange={(e) => handleInputChange('bathrooms', 'max', e.target.value)}
                    />
                </div>
                <div className="filter-section">
                    <label>Property Type: </label>
                    <select
                        value={localFilters.propertyType}
                        onChange={(e) => handleInputChange('propertyType', null, e.target.value)}
                    >
                        <option value="">Any</option>
                        <option value="Home">Home</option>
                        <option value="Dorm">Dorm</option>
                        <option value="Condo">Condo</option>
                        <option value="Studio">Studio</option>
                        <option value="Apartment">Apartment</option>
                    </select>
                </div>
                <div className="filter-section">
                    <label>
                        <input
                            type="checkbox"
                            checked={localFilters.smokingAllowed || false}
                            onChange={(e) => handleInputChange('smokingAllowed', null, e.target.checked ? true : null)}
                        />
                        Smoking Allowed
                    </label>
                </div>
                <div className="filter-section">
                    <label>
                        <input
                            type="checkbox"
                            checked={localFilters.wheelchairAccessible || false}
                            onChange={(e) => handleInputChange('wheelchairAccessible', null, e.target.checked ? true : null)}
                        />
                        Wheelchair Accessible
                    </label>
                </div>

                {/* Amenities */}
                <h4>Amenities:</h4>
                {Object.keys(localFilters.amenities).map(amenity => {
                    // Add spaces before uppercase letters
                    const formattedAmenity = amenity.replace(/([a-z])([A-Z])/g, '$1 $2');
                    return (
                        <div className="filter-section" key={amenity}>
                            <label>
                                <input
                                    type="checkbox"
                                    checked={localFilters.amenities[amenity] || false}
                                    onChange={(e) => handleInputChange('amenities', amenity, e.target.checked ? true : null)}
                                />
                                {formattedAmenity.charAt(0).toUpperCase() + formattedAmenity.slice(1)}
                            </label>
                        </div>
                    );
                })}

                <div className="modal-actions">
                    <button className="cancel" onClick={onClose}>Cancel</button>
                    <button className="reset" onClick={handleReset}>Reset</button>
                    <button className="apply" onClick={handleApply}>Apply</button>
                </div>
            </div>
        </div>
    );
}