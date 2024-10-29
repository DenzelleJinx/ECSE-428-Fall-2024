import React from 'react';
import { Container, Typography, Button, Box, AppBar, Toolbar } from '@mui/material';
import { styled } from '@mui/system';
import { useNavigate } from 'react-router-dom';
import mcgillLogo from '../../assets/mcgill-logo.png'; // Adjust path as needed
import backgroundImage from '../../assets/background.jpeg'; // Adjust path as needed

const primaryColor = "#D50032";
const secondaryColor = "#FFFFFF";

const StyledButton = styled(Button)({
    backgroundColor: primaryColor,
    color: secondaryColor,
    '&:hover': {
        backgroundColor: "#B00029",
    },
});

function LandingPage() {
    const navigate = useNavigate();

    const handleSignUpClick = () => {
        navigate('/signup');
    };

    return (
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
            <AppBar position="fixed" sx={{ backgroundColor: primaryColor }}>
                <Toolbar>
                    <img
                        src={mcgillLogo}
                        alt="McGill Logo"
                        style={{
                            width: '40px',
                            marginRight: '10px',
                            backgroundColor: secondaryColor,
                            padding: '5px',
                            borderRadius: '5px'
                        }}
                    />
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1, color: secondaryColor }}>
                        House It - Housing Service for McGill University Students
                    </Typography>
                    <Button color="inherit" sx={{ color: secondaryColor }}>
                        Login
                    </Button>
                    <Button color="inherit" sx={{ color: secondaryColor }} onClick={handleSignUpClick}>
                        Sign Up
                    </Button>
                </Toolbar>
            </AppBar>

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
                    <StyledButton variant="contained" size="large" sx={{ m: 1 }}>
                        Explore Listings
                    </StyledButton>
                    <Button variant="outlined" size="large" sx={{ m: 1, color: primaryColor, borderColor: primaryColor }} onClick={handleSignUpClick}>
                        Sign Up Now
                    </Button>
                </Box>
            </Container>
        </div>
    );
}

export default LandingPage;
