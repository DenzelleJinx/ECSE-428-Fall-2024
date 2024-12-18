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
import StatusDialog from '../status-dialog/StatusDialog';

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
    const [isLandlordApproved, setIsLandlordApproved] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [openDialog, setOpenDialog] = React.useState(false); // State for dialog visibility
    const [dialogMessage, setDialogMessage] = React.useState(''); // Message to display in the dialog
    const [dialogSeverity, setDialogSeverity] = React.useState('error'); // Severity of the message: 'success' or 'error'

    useEffect(() => {
        const checkAuth = () => {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            setIsLandlord(user && user.accountType === 'landlord');
            if (isLandlord) {
                setIsLandlordApproved(user && user.accountStatus === 'ACTIVE');
            }
        };
        
        checkAuth();
    },);

    const navigate = useNavigate();

    const handleSignUpClick = () => {
        navigate('/signup');
    };

    const handleListingClick = () => {
        if (!isLandlordApproved) {
            setDialogMessage('Your landlord account is pending approval. Please wait for an admin to approve your account.');
            setDialogSeverity('error');
            setOpenDialog(true);
        } else {
            navigate('/createlisting');
        }
    };

    const handleViewListingsClick = () => {
        navigate('/viewlistings')
    }

    const handleLogoClick = () => {
        navigate('/');
    };

    const handleDialogClose = () => {
        setOpenDialog(false);
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
                        {isLoggedIn && (
                        <Button variant="outlined" size="large" sx={{ m: 1, color: primaryColor, borderColor: primaryColor }} onClick={handleSignUpClick}>
                            Sign Up Now
                        </Button>
                        )}
                    </Box>
                </Container>
                <StatusDialog
                    open={openDialog}
                    onClose={handleDialogClose}
                    severity={dialogSeverity}
                    title={dialogSeverity === 'success' ? 'Success' : 'An Error Occurred'}
                    message={dialogMessage}
                />
            </div>
        </AppTheme>
    );
}

export default LandingPage;
