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
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import mcgillLogo from '../../assets/mcgill-logo.png';
import { useNavigate } from 'react-router-dom';

const Navbar = (props) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isLandlord, setIsLandlord] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);
    const [loggedInUsername, setLoggedInUsername] = useState('');
    const [notifications, setNotifications] = useState([]);
    const [anchorEl, setAnchorEl] = useState(null);
    const [accountMenuAnchor, setAccountMenuAnchor] = useState(null);
    const [drawerOpen, setDrawerOpen] = useState(false); // For mobile menu

    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";
    const navigate = useNavigate();
    const theme = useTheme();
    const isMobile = useMediaQuery(theme.breakpoints.down('sm')); // Detect small screens

    useEffect(() => {
        const checkAuth = () => {
            try {
                const user = JSON.parse(localStorage.getItem('currentUser'));
                if (user) {
                    setIsLandlord(user.accountType === 'landlord');
                    setIsAdmin(user.accountType === 'admin');
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
                const response = await Axios.get(
                    `http://localhost:8080/users/${loggedInUsername}/notifications`
                );
                setNotifications(response.data.notifications);
            } catch (error) {
                console.error("Error fetching notifications:", error);
                setNotifications([]);
            }
        };

        checkAuth();
        if (loggedInUsername) {
            fetchNotifications();
        }
    }, [loggedInUsername]);

    const handleNavigation = (path) => {
        navigate(path);
        setDrawerOpen(false); // Close drawer if used
    };

    const handleNotificationClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleNotificationClose = () => {
        setAnchorEl(null);
    };

    const handleAccountMenuOpen = (event) => {
        setAccountMenuAnchor(event.currentTarget);
    };

    const handleAccountMenuClose = () => {
        setAccountMenuAnchor(null);
    };

    const handleLogoutClick = () => {
        localStorage.removeItem('currentUser');
        setIsLoggedIn(false);
        setIsLandlord(false);
        setIsAdmin(false);
        setLoggedInUsername('');
        navigate('/login');
        setDrawerOpen(false); // Close drawer if used
    };

    const navLinks = [
        { label: 'View Listings', path: '/viewListings' },
        { label: 'Saved Listings', path: '/saved-listings' },
        ...(isAdmin ? [{ label: 'Approve Landlords', path: '/approvelandlord' }] : []),
        ...(isLoggedIn
            ? [
                { label: 'Account', path: '/view-account' },
                { label: 'Log Out', path: '/logout', onClick: handleLogoutClick },
            ]
            : [
                { label: 'Login', path: '/login' },
                { label: 'Sign Up', path: '/signup' },
            ]),
    ];

    const renderNavLinks = () => (
        navLinks.map((link, index) => (
            <Button
                key={index}
                color="inherit"
                sx={{ color: secondaryColor }}
                onClick={link.onClick || (() => handleNavigation(link.path))}
            >
                {link.label}
            </Button>
        ))
    );

    const generateNotificationMessage = (notification) => {
        switch (notification.type) {
            case 'CONTACT':
                return `You have a new contact request from ${notification.senderUsername}.`;
            case 'REVIEW':
                return notification.message || 'You have a new notification.';
            default:
                return 'You have a new notification.';
        }
    };

    return (
        <AppTheme {...props}>
            <CssBaseline enableColorScheme />
            <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem', marginTop: "4rem" }} />
            <AppBar position="fixed" sx={{ backgroundColor: primaryColor }}>
                <Toolbar sx={{ justifyContent: 'space-between' }}>
                    {/* Logo and Title */}
                    <div
                        onClick={() => handleNavigation('/')}
                        style={{
                            cursor: 'pointer',
                            display: 'flex',
                            alignItems: 'center',
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
                            sx={{ color: secondaryColor, fontSize: 'clamp(1rem, 2vw, 1.5rem)' }}
                        >
                            House It - Housing Service for McGill University Students
                        </Typography>
                    </div>

                    {/* Desktop Navigation */}
                    {!isMobile && (
                        <div style={{ display: 'flex', gap: '10px', alignItems: 'center' }}>
                            {renderNavLinks()}
                            {isLoggedIn && (
                                <IconButton color="inherit" onClick={handleNotificationClick}>
                                    <Badge badgeContent={notifications.length} color="secondary">
                                        <NotificationsIcon />
                                    </Badge>
                                </IconButton>
                            )}
                        </div>
                    )}

                    {/* Mobile Navigation */}
                    {isMobile && (
                        <>
                            <IconButton
                                edge="end"
                                color="inherit"
                                onClick={() => setDrawerOpen(true)}
                            >
                                <MenuIcon />
                            </IconButton>
                            <Drawer
                                anchor="right"
                                open={drawerOpen}
                                onClose={() => setDrawerOpen(false)}
                            >
                                <List>
                                    {navLinks.map((link, index) => (
                                        <ListItem
                                            button
                                            key={index}
                                            onClick={link.onClick || (() => handleNavigation(link.path))}
                                        >
                                            <ListItemText primary={link.label} />
                                        </ListItem>
                                    ))}
                                    {isLoggedIn && notifications.length > 0 && (
                                        <ListItem>
                                            <ListItemText
                                                primary="Notifications"
                                                secondary={notifications.map((n) => generateNotificationMessage(n)).join(', ')}
                                            />
                                        </ListItem>
                                    )}
                                </List>
                            </Drawer>
                        </>
                    )}
                </Toolbar>
            </AppBar>
        </AppTheme>
    );
};

export default Navbar;
