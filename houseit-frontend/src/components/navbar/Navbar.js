import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import {
    AppBar,
    Toolbar,
    Typography,
    Button,
    CssBaseline,
    Menu,
    MenuItem,
    Badge,
    IconButton,
    Drawer,
    List,
    ListItem,
    ListItemText,
    useMediaQuery,
    useTheme,
} from '@mui/material';
import NotificationsIcon from '@mui/icons-material/Notifications';
import MenuIcon from '@mui/icons-material/Menu';
import mcgillLogo from '../../assets/mcgill-logo.png';
import { useNavigate } from 'react-router-dom';

const Navbar = (props) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isLandlord, setIsLandlord] = useState(false);
    const [isStudent, setIsStudent] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);
    const [loggedInUsername, setLoggedInUsername] = useState('');

    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";

    const [notifications, setNotifications] = useState([]);
    const [anchorEl, setAnchorEl] = useState(null);
    const [accountMenuAnchor, setAccountMenuAnchor] = useState(null);
    const [drawerOpen, setDrawerOpen] = useState(false);

    const [openDialog, setOpenDialog] = useState(false); // State for dialog visibility
    const [dialogMessage, setDialogMessage] = useState(''); // Message to display in the dialog
    const [dialogSeverity, setDialogSeverity] = useState('error'); // Severity of the message: 'success' or 'error'

    const navigate = useNavigate();
    const theme = useTheme();
    const isMobile = useMediaQuery(theme.breakpoints.down('md')); // Check if screen width is below 'md'

    // Fetch notifications when the component mounts
    useEffect(() => {
        const checkAuth = () => {
            try {
                const user = JSON.parse(localStorage.getItem('currentUser'));
                console.log(user.username);
                if (user) {
                    setIsLandlord(user && user.accountType === 'landlord');
                    setIsStudent(user && user.accountType === 'student');
                    setIsAdmin(user && user.accountType === 'admin');
                    setIsLoggedIn(true);
                    setLoggedInUsername(user.username);
                } else {
                    setIsLoggedIn(false);
                }
            } catch (error) {
                console.error("Error fetching user:", error);
            }
        };

        const fetchNotifications = async () => {
            try {
                // Make a GET request to fetch notifications
                const response = await Axios.get(`http://localhost:8080/users/${loggedInUsername}/notifications`);
                setNotifications(response.data.notifications); // Update state with the notifications data
            } catch (error) {
                console.error("Error fetching notifications:", error);
                setNotifications([]); // Fallback to an empty array if there's an error
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
    const toggleDrawer = (open) => () => setDrawerOpen(open);

    const handlelLogoutClick = () => {
        localStorage.removeItem('currentUser');

        setIsLoggedIn(false);
        setIsLandlord(false);
        setIsStudent(false);
        setIsAdmin(false);
        setLoggedInUsername('');

        navigate('/login');
    };

    // Function to generate a notification message based on its type
    const generateNotificationMessage = (notification) => {
        switch (notification.type) {
            case 'CONTACT':
                return `You have a new contact request from ${notification.senderUsername}.`;
            case 'REVIEW':
                return (notification.senderUsername + ": " + notification.message) || 'You have a new notification.';
            case 'OTHER':
                return (notification.senderUsername + ": " + notification.message) || 'You have a new notification.';
            default:
                return 'You have a new notification.';
        }
    };

    const renderDesktopNavbar = () => (
        <>
            {isAdmin && (
                <Button color="inherit" sx={{ color: secondaryColor }} onClick={() => navigate('/approvelandlord')}>
                    Approve Landlords
                </Button>
            )}
            <Button color="inherit" sx={{ color: secondaryColor }} onClick={() => navigate('/viewListings')}>
                View Listings
            </Button>
            {isLoggedIn ? (
                <>
                    <Button color="inherit" sx={{ color: secondaryColor }} onClick={() => navigate('/saved-listings')}>
                        View Saved Listings
                    </Button>
                    <Button color="inherit" sx={{ color: secondaryColor }} onClick={handleAccountMenuOpen}>
                        Account
                    </Button>
                    <Menu
                        anchorEl={accountMenuAnchor}
                        open={Boolean(accountMenuAnchor)}
                        onClose={handleAccountMenuClose}
                    >
                        <MenuItem onClick={() => navigate('/update-account')}>Update Account</MenuItem>
                        <MenuItem onClick={() => navigate('/view-account')}>View Account</MenuItem>
                    </Menu>
                    <IconButton color="inherit" onClick={handleNotificationClick}>
                        <Badge badgeContent={notifications.length} color="secondary">
                            <NotificationsIcon />
                        </Badge>
                    </IconButton>
                    <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleNotificationClose}>
                        {notifications.length === 0 ? (
                            <MenuItem>No notifications</MenuItem>
                        ) : (
                            notifications.map((notification, index) => (
                                <MenuItem key={index}>{generateNotificationMessage(notification)}</MenuItem>
                            ))
                        )}
                    </Menu>
                    <Button color="inherit" sx={{ color: secondaryColor }} onClick={() => navigate('/logout')}>
                        Log Out
                    </Button>
                </>
            ) : (
                <>
                    <Button color="inherit" sx={{ color: secondaryColor }} onClick={() => navigate('/login')}>
                        Login
                    </Button>
                    <Button color="inherit" sx={{ color: secondaryColor }} onClick={() => navigate('/signup')}>
                        Sign Up
                    </Button>
                </>
            )}
        </>
    );

    const renderMobileDrawer = () => (
        <Drawer anchor="right" open={drawerOpen} onClose={toggleDrawer(false)}>
            <List>
                {isAdmin && (
                    <ListItem button onClick={() => navigate('/approvelandlord')}>
                        <ListItemText primary="Approve Landlords" />
                    </ListItem>
                )}
                <ListItem button onClick={() => navigate('/viewListings')}>
                    <ListItemText primary="View Listings" />
                </ListItem>
                {isLoggedIn ? (
                    <>
                        <ListItem button onClick={() => navigate('/saved-listings')}>
                            <ListItemText primary="View Saved Listings" />
                        </ListItem>
                        <ListItem button onClick={handleAccountMenuOpen}>
                            <ListItemText primary="Account" />
                            <Menu
                            anchorEl={accountMenuAnchor}
                            open={Boolean(accountMenuAnchor)}
                            onClose={handleAccountMenuClose}
                        >
                            <MenuItem onClick={() => navigate('/update-account')}>Update Account</MenuItem>
                            <MenuItem onClick={() => navigate('/view-account')}>View Account</MenuItem>
                        </Menu>
                        </ListItem>
                        <ListItem>
                            <IconButton
                                color="inherit"
                                onClick={handleNotificationClick}
                            >
                                <Badge
                                    badgeContent={notifications.length}
                                    color="secondary"
                                >
                                    <NotificationsIcon />
                                </Badge>
                            </IconButton>
                            <Menu
                                anchorEl={anchorEl}
                                open={Boolean(anchorEl)}
                                onClose={handleNotificationClose}
                            >
                                {notifications.length === 0 ? (
                                    <MenuItem>No notifications</MenuItem>
                                ) : (
                                    notifications.map((notification, index) => (
                                        <MenuItem key={index}>
                                            {generateNotificationMessage(notification)}
                                        </MenuItem>
                                    ))
                                )}
                            </Menu>
                        </ListItem>
                        <ListItem button onClick={() => navigate('/logout')}>
                            <ListItemText primary="Log Out" />
                        </ListItem>
                    </>
                ) : (
                    <>
                        <ListItem button onClick={() => navigate('/login')}>
                            <ListItemText primary="Login" />
                        </ListItem>
                        <ListItem button onClick={() => navigate('/signup')}>
                            <ListItemText primary="Sign Up" />
                        </ListItem>
                    </>
                )}
            </List>
        </Drawer>
    );

    return (
        <>
            <CssBaseline />
            <AppBar position="fixed" sx={{ backgroundColor: primaryColor }}>
                <Toolbar sx={{ justifyContent: 'space-between' }}>
                    <div style={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }} onClick={() => navigate('/')}>
                        <img src={mcgillLogo} alt="McGill Logo" style={{ width: '40px', marginRight: '10px' }} />
                        <Typography variant="h6" sx={{ color: secondaryColor }}>
                            House It - Housing Service for McGill University Students
                        </Typography>
                    </div>
                    {isMobile ? (
                        <>
                            <IconButton color="inherit" onClick={toggleDrawer(true)}>
                                <MenuIcon />
                            </IconButton>
                            {renderMobileDrawer()}
                        </>
                    ) : (
                        renderDesktopNavbar()
                    )}
                </Toolbar>
            </AppBar>
        </>
    );
};

export default Navbar;
