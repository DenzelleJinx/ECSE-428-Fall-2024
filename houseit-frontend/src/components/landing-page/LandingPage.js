import React, { useEffect, useState } from 'react';
import { Container, Typography, Button, Box, AppBar, Toolbar } from '@mui/material';
import { styled } from '@mui/system';
import { useNavigate } from 'react-router-dom';
import mcgillLogo from '../../assets/mcgill-logo.png'; // Adjust path as needed
import backgroundImage from '../../assets/background.jpeg'; // Adjust path as needed
import AppTheme from '../../shared-theme/AppTheme';
import CssBaseline from '@mui/material/CssBaseline';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import Navbar from '../navbar/Navbar';

const primaryColor = "#D50032";
const secondaryColor = "#FFFFFF";

const StyledButton = styled(Button)({
    backgroundColor: primaryColor,
    color: secondaryColor,
    '&:hover': {
        backgroundColor: "#B00029",
    },
});

function LandingPage(props) {
    const [isLandlord, setIsLandlord] = useState(false);

    useEffect(() => {
        const checkAuth = () => {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            setIsLandlord(user && user.accountType === 'landlord');
        };
        
        checkAuth();
    }, []);

    const navigate = useNavigate();

    const handleSignUpClick = () => {
        navigate('/signup');
    };

    const handleListingClick = () => {
        navigate('/createlisting');
    };

    const handleViewListingsClick = () => {
        navigate('/viewlistings')
    }

    const handleLogoClick = () => {
        navigate('/');
    };

    return (
        <AppTheme {...props}>
            <CssBaseline enableColorScheme />
            <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem' , marginTop: "4rem"}} />
            <div
                style={{
                    backgroundImage: `url(${backgroundImage})`,
                    backgroundSize: 'cover',
                    backgroundPosition: 'center',
                    minHeight: '100vh',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    justifyContent: 'center',
                }}
            >
                <Navbar />
                <Container
                    maxWidth="md"
                    sx={{
                        textAlign: 'center',
                        marginTop: 4,
                        padding: 4,
                        backgroundColor: secondaryColor,
                        borderRadius: 2,
                        boxShadow: 3,
                    }}
                >
                    <Box sx={{ padding: 4 }}>
                        <Typography variant="h3" component="h1" gutterBottom sx={{ color: primaryColor }}>
                            Find Your Perfect Rental Unit
                        </Typography>
                        <Typography variant="h6" color="textSecondary" paragraph>
                            Discover listings and connect with landlords effortlessly.
                        </Typography>
                        <StyledButton variant="contained" size="large" sx={{ m: 1 }} onClick={handleViewListingsClick}>
                            View Current Listings
                        </StyledButton>
                        {isLandlord && (
                            <StyledButton variant="contained" size="large" sx={{ m: 1 }} onClick={handleListingClick}>
                                Create Listing
                            </StyledButton>
                        )}
                        <Button variant="outlined" size="large" sx={{ m: 1, color: primaryColor, borderColor: primaryColor }} onClick={handleSignUpClick}>
                            Sign Up Now
                        </Button>
                    </Box>
                </Container>
            </div>
        </AppTheme>
    );
}

export default LandingPage;
