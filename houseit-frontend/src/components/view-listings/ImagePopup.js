import React, { useState } from "react";
import PhotoCamera from "@mui/icons-material/PhotoCamera";
import CloseIcon from "@mui/icons-material/Close";


const PropertyListing = ({ property }) => {
    const [showModal, setShowModal] = useState(false);

    const openModal = () => setShowModal(true);
    const closeModal = () => setShowModal(false);

    return (
        <div style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "20px" }}>
            {/* First Image and Camera Icon */}
            <div style={{ position: "relative" }}>
                <img
                    src={property.propertyImages[0].url}
                    alt={`Property ${property.id}`}
                    style={{ width: "300px", height: "200px", objectFit: "cover", borderRadius: "5px" }}
                />
                <button
                    onClick={openModal}
                    style={{
                        position: "absolute",
                        bottom: "10px",
                        right: "10px",
                        backgroundColor: "rgba(0, 0, 0, 0.5)",
                        color: "white",
                        border: "none",
                        padding: "5px 10px",
                        cursor: "pointer",
                        borderRadius: "50%",
                    }}
                >
                    <PhotoCamera />
                </button>
            </div>

            {/* Modal for Viewing All Images */}
            {showModal && (
                <div
                    style={{
                        position: "fixed",
                        top: "0",
                        left: "0",
                        width: "100vw",
                        height: "100vh",
                        backgroundColor: "rgba(0, 0, 0, 0.7)",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        zIndex: "1000",
                    }}
                >
                    <div
                        style={{
                            position: "relative",
                            backgroundColor: "white",
                            padding: "20px",
                            borderRadius: "10px",
                            maxWidth: "90%",
                            maxHeight: "90%",
                            overflow: "auto",
                        }}
                    >
                        {/* Close Button */}
                        <button
                            onClick={closeModal}
                            style={{
                                position: "absolute",
                                top: "10px",
                                right: "10px",
                                backgroundColor: "red",
                                color: "white",
                                border: "none",
                                padding: "5px 10px",
                                cursor: "pointer",
                                borderRadius: "5px",
                            }}
                        >
                            <CloseIcon/>
                        </button>
                        <h3>Property Images</h3>
                        <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
                            {property.propertyImages.map((image, index) => (
                                <img
                                    key={image.id}
                                    src={image.url}
                                    alt={`Property Image ${index + 1}`}
                                    style={{
                                        width: "200px",
                                        height: "150px",
                                        objectFit: "cover",
                                        borderRadius: "5px",
                                    }}
                                />
                            ))}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default PropertyListing;
