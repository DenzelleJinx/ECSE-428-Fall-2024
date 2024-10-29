import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import mcgillLogo from '../../assets/mcgill-logo.png';
import { useNavigate } from 'react-router-dom';



const Navbar = (props) => {
    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";
    const navigate = useNavigate();

    const handleSignUpClick = () => {
        navigate('/signup');
    };

    const handleLogoClick = () => {
        navigate('/');
    };
    return (
        <AppTheme {...props}>
            <CssBaseline enableColorScheme />
            <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem' , marginTop: "4rem"}} />
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
                        <div
                            onClick={handleLogoClick}
                            style={{
                                cursor: 'pointer',
                                display: 'flex',       // Arrange items side by side
                                alignItems: 'center',  // Center items vertically
                                flexGrow: 1
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
                                    borderRadius: '5px'
                                }}
                            />
                            <Typography variant="h6" component="div" sx={{ flexGrow: 1, color: secondaryColor }}>
                                House It - Housing Service for McGill University Students
                            </Typography>
                        </div>
                        <Button color="inherit" sx={{ color: secondaryColor }}>
                            Login
                        </Button>
                        <Button color="inherit" sx={{ color: secondaryColor }} onClick={handleSignUpClick}>
                            Sign Up
                        </Button>
                    </Toolbar>
                </AppBar>
            </div>
        </AppTheme>
    );
};

export default Navbar;