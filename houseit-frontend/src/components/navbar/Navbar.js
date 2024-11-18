import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import Badge from '@mui/material/Badge';
import IconButton from '@mui/material/IconButton';
import NotificationsIcon from '@mui/icons-material/Notifications';
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import mcgillLogo from '../../assets/mcgill-logo.png';
import { useNavigate } from 'react-router-dom';

const Navbar = (props) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isLandlord, setIsLandlord] = useState(false);
    const [isStudent, setIsStudent] = useState(false);
    const [loggedInUsername, setLoggedInUsername] = useState('');

    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";
    const navigate = useNavigate();

    //const loggedInUsername = "john"
    //const isLoggedIn = false; // Replace with actual login state
    const [notifications, setNotifications] = useState([]);
    const [anchorEl, setAnchorEl] = useState(null);
    const [accountMenuAnchor, setAccountMenuAnchor] = useState(null);

    // Fetch notifications when the component mounts
    useEffect(() => {
        const checkAuth = () => {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            if (user) {
                setIsLandlord(user && user.accountType === 'landlord');
                setIsStudent(user && user.accountType === 'student')
                setIsLoggedIn(true);
                setLoggedInUsername(user.username);
            } else {
                setIsLoggedIn(false);
            }
        };

        // Define the API URL for notifications
        const fetchNotifications = async () => {
            try {
                // Make a GET request to fetch notifications
                const response = await Axios.get(`http://localhost:8080/users/${loggedInUsername}/notifications`);
                setNotifications(response.data); // Update state with the notifications data
            } catch (error) {
                console.error("Error fetching notifications:", error);
            }
        };
    
        fetchNotifications(); // Call the function
        checkAuth();
    }, [loggedInUsername]); // Run the effect when the component mounts or when loggedInUsername changes

    const handleNavigation = (path) => {
        navigate(path);
    };

    const handleNotificationClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleApproveLandlordClick = () => {
        navigate('/approvelandlord');
    }
    const handleNotificationClose = () => {
        setAnchorEl(null);
    };

    const handleAccountMenuOpen = (event) => {
        setAccountMenuAnchor(event.currentTarget);
    };

    const handleAccountMenuClose = () => {
        setAccountMenuAnchor(null);
    };

    const handleUpdateAccountClick = () => {
        navigate('/update-account');
        handleAccountMenuClose();
    };

    const handleViewAccountClick = () => {
        navigate('/view-account');
        handleAccountMenuClose();
    };

    // Function to generate a notification message based on its type
    const generateNotificationMessage = (notification) => {
        switch (notification.type) {
            case 'CONTACT':
                return `You have a new contact request from ${notification.senderUsername}.`;
            case 'REVIEW':
                return notification.message || 'You have a new notification.';
            case 'OTHER':
                return notification.message || 'You have a new notification.';
            default:
                return 'You have a new notification.';
        }
    };

    return (
        <AppTheme {...props}>
            <CssBaseline enableColorScheme />
            <ColorModeSelect
                sx={{
                    position: 'fixed',
                    top: '1rem',
                    right: '1rem',
                    marginTop: "4rem",
                }}
            />
            <div
                style={{
                    backgroundPosition: 'center',
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                    justifyContent: 'center',
                }}
            >
                <AppBar position="fixed" sx={{ backgroundColor: primaryColor }}>
                    <Toolbar sx={{ justifyContent: 'space-between' }}>
                        {/* Logo and Title */}
                        <div
                            onClick={() => handleNavigation('/')}
                            style={{
                                cursor: 'pointer',
                                display: 'flex',
                                alignItems: 'center',
                                flexGrow: 1,
                            }}
                        >
                            <img
                                src={mcgillLogo}
                                alt="McGill Logo"
                                style={{
                                    width: '40px',
                                    marginRight: '10px',
                                    backgroundColor: secondaryColor,
                                    padding: '5px',
                                    borderRadius: '5px',
                                }}
                            />
                            <Typography
                                variant="h6"
                                component="div"
                                sx={{ flexGrow: 1, color: secondaryColor }}
                            >
                                House It - Housing Service for McGill University Students
                            </Typography>
                        </div>

                        {/* Navigation Buttons */}
                        <div style={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
                            {isLandlord && (
                            <Button color="inherit" sx={{ color: secondaryColor }} onClick={handleApproveLandlordClick}>
                                Approve Landlords
                            </Button>
                        )}
                        <Button
                                color="inherit"
                                sx={{ color: secondaryColor }}
                                onClick={() => handleNavigation('/viewListings')}
                            >
                                View Listings
                            </Button>
                            {isLoggedIn && (
                                <>
                                    <Button
                                        color="inherit"
                                        sx={{ color: secondaryColor }}
                                        onClick={() => navigate('/saved-listings')}
                                    >
                                        View Saved Listings
                                    </Button>
                                    <Button
                                        color="inherit"
                                        sx={{ color: secondaryColor }}
                                        onClick={handleAccountMenuOpen}
                                    >
                                        Account
                                    </Button>
                                    <Menu
                                        anchorEl={accountMenuAnchor}
                                        open={Boolean(accountMenuAnchor)}
                                        onClose={handleAccountMenuClose}
                                    >
                                        <MenuItem onClick={handleUpdateAccountClick}>Update Account</MenuItem>
                                        <MenuItem onClick={handleViewAccountClick}>View Account</MenuItem>
                                    </Menu>
                                    {/* Notifications Dropdown */}
                                    <IconButton
                                        color="inherit"
                                        onClick={handleNotificationClick}
                                    >
                                        <Badge badgeContent={notifications.length} color="secondary">
                                            <NotificationsIcon />
                                        </Badge>
                                    </IconButton>
                                    {/* Notifications Dropdown */}
                                    <Menu
                                        anchorEl={anchorEl}
                                        open={Boolean(anchorEl)}
                                        onClose={handleNotificationClose}
                                    >
                                        {notifications.length === 0 ? (
                                            <MenuItem>No notifications</MenuItem>
                                        ) : (
                                            notifications.map((notification, index) => (
                                                <MenuItem key={index} onClick={handleNotificationClose}>
                                                    {generateNotificationMessage(notification)} {/* Assuming notification has a "message" field */}
                                                </MenuItem>
                                            ))
                                        )}
                                    </Menu>
                                    <Button
                                        color="inherit"
                                        sx={{ color: secondaryColor }}
                                        onClick={() => {
                                            //Add logout logic
                                            navigate('/login'); // Redirect to login page
                                        }}
                                    >
                                        Log Out
                                    </Button>
                                </>
                            )}
                            {!isLoggedIn && (
                                <>
                                    <Button
                                        color="inherit"
                                        sx={{ color: secondaryColor }}
                                        onClick={() => handleNavigation('/login')}
                                    >
                                        Login
                                    </Button>
                                    <Button
                                        color="inherit"
                                        sx={{ color: secondaryColor }}
                                        onClick={() => handleNavigation('/signup')}
                                    >
                                        Sign Up
                                    </Button>
                                </>
                            )}
                        </div>
                    </Toolbar>
                </AppBar>
            </div>
        </AppTheme>
    );
};

export default Navbar;
