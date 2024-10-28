import React, { useState } from 'react';
import { Button } from '@mui/material';

function ImageUpload() {
  const [image, setImage] = useState(null);
  const [error, setError] = useState('');

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const fileType = file.type;
      if (fileType === 'image/png' || fileType === 'image/jpeg') {
        setImage(URL.createObjectURL(file));
        setError(''); // Clear any previous error
      } else {
        setError('Please upload a PNG or JPEG image.');
      }
    }
  };

  return (
    <div>
      <input
        accept="image/*"
        style={{ display: 'none' }}
        id="upload-button-file"
        type="file"
        onChange={handleImageChange}
      />
      <label htmlFor="upload-button-file">
        <Button variant="contained" component="span">
          Upload Image
        </Button>
      </label>

      {error && <p style={{ color: 'red' }}>{error}</p>}
      {image && <img src={image} alt="Uploaded Preview" style={{ marginTop: '20px', maxWidth: '100%' }} />}
    </div>
  );
}

export default ImageUpload;
