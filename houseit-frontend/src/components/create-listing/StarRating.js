import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Star, StarHalf } from 'lucide-react';
import './StarRating.css'; 

const StarRating = ({ rating, listingId, landlordId }) => {
  const [propertyHoverRating, setPropertyHoverRating] = useState(null); 
  const [landlordHoverRating, setLandlordHoverRating] = useState(null); 
  const [currentRating, setCurrentRating] = useState(
    Math.round(rating * 2) / 2 
  );
  const [landlordRating, setLandlordRating] = useState(null); 
  const [errorMessage, setErrorMessage] = useState(''); 

  
  useEffect(() => {
    const fetchLandlordRating = async () => {
      if (landlordId) {
        try {
          const response = await axios.get(`http://localhost:8080/landlord/${landlordId}`);
          if (response.status === 200 && response.data?.rating != null) {
            setLandlordRating(Math.round(response.data.rating * 2) / 2); 
          }
        } catch (error) {
          console.error('Error fetching landlord rating:', error);
          setErrorMessage('Unable to fetch landlord rating. Please try again later.');
        }
      }
    };

    fetchLandlordRating();
  }, [landlordId]);

  const handleStarHover = (index, type) => {
    if (type === 'property') {
      setPropertyHoverRating(index);
    } else if (type === 'landlord') {
      setLandlordHoverRating(index);
    }
  };

  const handleStarLeave = (type) => {
    if (type === 'property') {
      setPropertyHoverRating(null);
    } else if (type === 'landlord') {
      setLandlordHoverRating(null);
    }
  };

  const handleStarClick = async (index, type) => {
    try {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));

      if (!currentUser) {
        setErrorMessage('You must be logged in to rate.');
        return;
      }

      if (type === 'property') {
        const previousRating = currentUser?.listingRatings?.[listingId];
        if (!previousRating || previousRating === null) {
          const response = await axios.put(
            `http://localhost:8080/listing/${listingId}/rate/${index}`
          );
          if (response.status === 200) {
            currentUser.listingRatings = currentUser.listingRatings || {};
            currentUser.listingRatings[listingId] = index;
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
            setCurrentRating(index);
            setErrorMessage('');
            window.location.reload();
          }
        } else {
          setErrorMessage("You can't rate a property you've already rated.");
        }
      } else if (type === 'landlord') {
        const previousRating = currentUser?.userRatings?.[landlordId];
        if (!previousRating || previousRating === null) {
          const response = await axios.put(
            `http://localhost:8080/landlord/${landlordId}/rate/${index}`
          );
          if (response.status === 200) {
            currentUser.userRatings = currentUser.userRatings || {};
            currentUser.userRatings[landlordId] = index;
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
            setLandlordRating(index);
            setErrorMessage('');
            window.location.reload();
          }
        } else {
          setErrorMessage("You can't rate a landlord you've already rated.");
        }
      }
    } catch (error) {
      console.error('Error updating rating:', error);
      setErrorMessage('An error occurred. Please try again.');
    }
  };

  const renderStars = (ratingToRender, type) => {
    const hoverRating = type === 'property' ? propertyHoverRating : landlordHoverRating;
    const activeRating = hoverRating !== null ? hoverRating : ratingToRender;

    const stars = [];
    for (let i = 1; i <= 5; i++) {
      const isHalfStar = activeRating >= i - 0.5 && activeRating < i;

      stars.push(
        isHalfStar ? (
          <StarHalf
            key={`half-${type}-${i}`}
            className="star star-half"
            onMouseEnter={() => handleStarHover(i - 0.5, type)}
            onMouseLeave={() => handleStarLeave(type)}
            onClick={() => handleStarClick(i - 0.5, type)}
          />
        ) : (
          <Star
            key={`${type}-${i}`}
            className={`star ${i <= activeRating ? 'star-filled' : 'star-empty'}`}
            onMouseEnter={() => handleStarHover(i, type)}
            onMouseLeave={() => handleStarLeave(type)}
            onClick={() => handleStarClick(i, type)}
          />
        )
      );
    }

    return stars;
  };

  return (
    <div className="rating-container">
      <div className="rating-header">
        <div className="rating-label">Property Rating:</div>
        <div className="stars-container">{renderStars(currentRating, 'property')}</div>
      </div>
      {landlordId && (
        <div className="rating-header">
          <div className="rating-label">Landlord Rating:</div>
          {landlordRating !== null ? (
            <div className="stars-container">{renderStars(landlordRating, 'landlord')}</div>
          ) : (
            <div className="loading-message">Loading landlord rating...</div>
          )}
        </div>
      )}
      {errorMessage && <div className="error-message">{errorMessage}</div>}
    </div>
  );
};

export default StarRating;
