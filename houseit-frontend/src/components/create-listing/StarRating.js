import React, { useState } from 'react';
import axios from 'axios';
import { Star, StarHalf } from 'lucide-react';
import './StarRating.css'; // Import the CSS file

const StarRating = ({ rating, listingId }) => {
  const [hoverRating, setHoverRating] = useState(null); // To track hovered rating
  const [currentRating, setCurrentRating] = useState(
    Math.round(rating * 2) / 2 // Round initial rating to nearest 0.5
  );
  const [errorMessage, setErrorMessage] = useState(''); // To store error messages

  const handleStarHover = (index) => {
    setHoverRating(index);
  };

  const handleStarLeave = () => {
    setHoverRating(null);
  };

  const handleStarClick = async (index) => {
    try {
      let currentUser = JSON.parse(localStorage.getItem('currentUser'));
      const previousRating = currentUser?.ratings?.[listingId];
      if (!currentUser) {
        setErrorMessage('You must be logged in to rate a property.');
        return;
      }
      if (!previousRating || previousRating === null) {
        const response = await axios.put(
          `http://localhost:8080/listing/${listingId}/rate/${index}`
        );
        if (response.status === 200) {
          currentUser.ratings = currentUser.ratings || {};
          currentUser.ratings[listingId] = index;
          localStorage.setItem('currentUser', JSON.stringify(currentUser)); // Save rating to local storage
          setCurrentRating(index); // Update the current rating on successful PUT request
          setErrorMessage(''); // Clear any previous error messages
          window.location.reload();
        }
      } else {
        setErrorMessage("You can't rate a property you've already rated.");
      }
    } catch (error) {
      console.error('Error updating rating:', error);
      setErrorMessage('An error occurred. Please try again.');
    }
  };

  const renderStars = () => {
    const stars = [];
    const activeRating = hoverRating !== null ? hoverRating : currentRating;

    for (let i = 1; i <= 5; i++) {
      const isHalfStar = activeRating >= i - 0.5 && activeRating < i;

      stars.push(
        isHalfStar ? (
          <StarHalf
            key={`half-${i}`}
            className="star star-half"
            onMouseEnter={() => handleStarHover(i - 0.5)}
            onMouseLeave={handleStarLeave}
            onClick={() => handleStarClick(i - 0.5)}
          />
        ) : (
          <Star
            key={i}
            className={`star ${i <= activeRating ? 'star-filled' : 'star-empty'}`}
            onMouseEnter={() => handleStarHover(i)}
            onMouseLeave={handleStarLeave}
            onClick={() => handleStarClick(i)}
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
        <div className="stars-container">{renderStars()}</div>
      </div>
      {errorMessage && <div className="error-message">{errorMessage}</div>}
    </div>
  );
};

export default StarRating;
