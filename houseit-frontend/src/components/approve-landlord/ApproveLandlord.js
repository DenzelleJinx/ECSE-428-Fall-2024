import React from 'react';
import {
    Box,
    Button,
    CssBaseline,
    FormLabel,
    FormControl,
    TextField,
    Typography,
    Stack,
    Card as MuiCard,
    MenuItem,
    Select,
    FormControlLabel,
    Checkbox,
    Card,
} from '@mui/material';
import { styled } from '@mui/material/styles';
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import { useNavigate } from 'react-router-dom';
import Navbar from '../navbar/Navbar';

const isNumeric = (string) => /^[+-]?\d+(\.\d+)?$/.test(string)


export default function ApproveLandlord(props) {
    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";


    const [userNameError, setUserNameError] = React.useState(false);
    const [userNameErrorMessage, setUserNameErrorMessage] = React.useState('');
    const [emailError, setEmailError] = React.useState(false);
    const [emailErrorMessage, setEmailErrorMessage] = React.useState('');
    const [reasonError, setReasonError] = React.useState(false);
    const [reasonErrorMessage, setReasonErrorMessage] = React.useState('');


    const validateInputs = () => {
        const userName = document.getElementById('UserName');
        const email = document.getElementById('Email');
        const reason = document.getElementById('Reason');

        let isValid = true;

        if (!reason || !reason.value || reason.value >= 256 || reason.value < 1) {
            setReasonError(true);
            setReasonErrorMessage('A reason needs to be between 1 and 256 characters.');
            isValid = false;
        } else {
            setReasonError(false);
            setReasonErrorMessage('');
        }


        if (!email || !email.value || !/\S+@\S+\.\S+/.test(email.value)) {
            setEmailError(true);
            setEmailErrorMessage('Please enter a valid email address.');
            isValid = false;
        } else {
            setEmailError(false);
            setEmailErrorMessage('');
        }


        if (!userName || !userName.value || userName.value.length < 1) {
            setUserNameError(true);
            setUserNameErrorMessage('Username is required.');
            isValid = false;
        } else {
            setUserNameError(false);
            setUserNameErrorMessage('');
        }


        return isValid;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!validateInputs()) {
            return;
        }

        //TODO !!!
    };

    const Card = styled(MuiCard)(({ theme }) => ({
        display: 'flex',
        flexDirection: 'column',
        alignSelf: 'center',
        width: '1000px',
        maxHeight: '1500px', // Set a max height for the card
        padding: theme.spacing(4),
        gap: theme.spacing(2),
        boxShadow:
            'hsla(220, 30%, 5%, 0.05) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.05) 0px 15px 35px -5px',
        [theme.breakpoints.up('sm')]: {
            width: '450px',
        },
        ...theme.applyStyles('dark', {
            boxShadow:
                'hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px',
        }),
    }));

    const ApproveLandlordContainer = styled(Stack)(({ theme }) => ({
        marginTop: theme.spacing(12), // Adds space at the top
        height: 'auto',
        padding: theme.spacing(2),
        [theme.breakpoints.up('sm')]: {
            padding: theme.spacing(4),
        },
        '&::before': {
            content: '""',
            display: 'block',
            position: 'absolute',
            zIndex: -1,
            inset: 0,
            backgroundImage:
                'radial-gradient(ellipse at 50% 50%, hsl(210, 100%, 97%), hsl(0, 0%, 100%))',
            backgroundRepeat: 'no-repeat',
            ...theme.applyStyles('dark', {
                backgroundImage:
                    'radial-gradient(at 50% 50%, hsla(210, 100%, 16%, 0.5), hsl(220, 30%, 5%))',
            }),
        },
    }));

    const navigate = useNavigate();

    const handleSignUpClick = () => {
        navigate('/signup');
    };

    const handleListingClick = () => {
        navigate('/approvelandlord');
    };
    const handleLogoClick = () => {
        navigate('/');
    };

    return (
        <AppTheme {...props}>
            <CssBaseline enableColorScheme />
            <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem' , marginTop: "4rem"}} />
            <Navbar />
            <ApproveLandlordContainer direction="column" justifyContent="space-between">
                <Box textAlign='center'>
                    <Typography
                        component="h1"
                        variant="h4"
                        sx={{ width: '100%', fontSize: 'clamp(2rem, 10vw, 2.15rem)' }}
                    >
                        Approve a Landlord
                    </Typography>
                </Box>
                <div
                    style={{
                        margin: "0 25%",
                        display: "flex",
                        flexDirection: "row",
                        justifyContent: "center",
                        padding: "2em",
                    }}
                >
                    <Card variant="outlined">
                        <Typography
                            component="h2"
                            variant="h5"
                            sx={{ width: '100%', fontSize: 'clamp(1rem, 5vw, 1rem)' }}
                        >
                            Listing Info
                        </Typography>
                        <Box
                            component="form"
                            onSubmit={handleSubmit}
                            sx={{ display: 'flex', flexDirection: 'column', gap: 2, }} // Adjusted marginBottom
                        >
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Username">Username</FormLabel>
                                <TextField
                                    name="Username"
                                    fullWidth
                                    id="Username"
                                    placeholder="Username"
                                    inputProps={{ readOnly: true }}
                                    error={userNameError}
                                    helperText={userNameErrorMessage}
                                    color={userNameError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Email">Email</FormLabel>
                                <TextField
                                    fullWidth
                                    name="Email"
                                    placeholder="Email"
                                    type="Email"
                                    id="Email"
                                    autoComplete="Email"
                                    variant="outlined"
                                    error={emailError}
                                    helperText={emailErrorMessage}
                                    color={emailError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Reason">Reason</FormLabel>
                                <TextField
                                    required
                                    fullWidth
                                    name="Reason"
                                    placeholder="Reason"
                                    type="Reason"
                                    id="Reason"
                                    autoComplete="Reason"
                                    variant="outlined"
                                    error={reasonError}
                                    helperText={reasonErrorMessage}
                                    color={reasonError ? 'error' : 'primary'}
                                />
                            </FormControl>
                        </Box>
                    </Card>
                </div>
                <Box textAlign='center'>
                    <Button
                        type="approveLandlord"
                        fullWidth={false}
                        variant="contained"
                        onClick={validateInputs}
                        sx={{ marginBottom: 2 }} // Adjust the spacing here
                    >
                        Approve
                    </Button>
                    <Button
                        type="declineLandlord"
                        fullWidth={false}
                        variant="contained"
                        onClick={validateInputs}
                        sx={{ marginBottom: 2 }} // Adjust the spacing here
                    >
                        Decline
                    </Button>
                </Box>
            </ApproveLandlordContainer>
        </AppTheme>
    );
}