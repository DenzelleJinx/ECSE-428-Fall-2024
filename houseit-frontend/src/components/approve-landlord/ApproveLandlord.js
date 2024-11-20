import React, { useState, useEffect } from 'react';
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
} from '@mui/material';
import { styled } from '@mui/material/styles';
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import { useNavigate } from 'react-router-dom';
import Navbar from '../navbar/Navbar';
import axios from 'axios';
import StatusDialog from '../status-dialog/StatusDialog';

const isNumeric = (string) => /^[+-]?\d+(\.\d+)?$/.test(string);

export default function ApproveLandlord(props) {
    const [userNameError, setUserNameError] = useState(false);
    const [userNameErrorMessage, setUserNameErrorMessage] = useState('');
    const [emailError, setEmailError] = useState(false);
    const [emailErrorMessage, setEmailErrorMessage] = useState('');
    const [reasonError, setReasonError] = useState(false);
    const [reasonErrorMessage, setReasonErrorMessage] = useState('');
    const [phoneNumberError, setPhoneNumberError] = useState(false);
    const [phoneNumberErrorMessage, setPhoneNumberErrorMessage] = useState('');

    const [openDialog, setOpenDialog] = useState(false); // State for dialog visibility
    const [dialogMessage, setDialogMessage] = useState(''); // Message to display in the dialog
    const [dialogSeverity, setDialogSeverity] = useState('error'); // Severity of the message: 'success' or 'error'

    const navigate = useNavigate();

    useEffect(() => {
        const checkAuth = () => {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            if (!user || user.accountType !== 'admin') {
                // Redirect to home page if not authenticated as admin
                navigate('/');
            }
        };

        checkAuth();
    }, []);

    const validateInputs = () => {
        const userName = document.getElementById('Username');
        const email = document.getElementById('Email');
        const reason = document.getElementById('Reason');
        const phoneNumber = document.getElementById('phone-number');

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

        if (!phoneNumber || !phoneNumber.value || phoneNumber.length < 1) {
            setPhoneNumberError(true);
            setPhoneNumberErrorMessage('Phone number is required for landlords.');
            isValid = false;
        } else if (!isNumeric(phoneNumber.value)) {
            setPhoneNumberError(true);
            setPhoneNumberErrorMessage('Phone number must be a number');
            isValid = false;
        } else {
            setPhoneNumberError(false);
            setPhoneNumberErrorMessage('');
        }

        return isValid;
    };

    const handleAction = async (actionType) => {
        // actionType: 'approve' or 'decline'
        const email = document.getElementById('Email').value;

        try {
            const axiosClient = axios.create({
                baseURL: 'http://localhost:8080',
            });

            const userResponse = await axiosClient.get(`/users/${email}`);
            if (userResponse.status === 200) {
                const userId = userResponse.data.id;

                let actionResponse;
                if (actionType === 'approve') {
                    actionResponse = await axiosClient.put(`/admin/approve-landlord/${userId}`);
                } else if (actionType === 'decline') {
                    actionResponse = await axiosClient.put(`/admin/reject-landlord/${userId}`);
                }

                if (actionResponse.status === 200) {
                    setDialogMessage(
                        actionType === 'approve'
                            ? 'Landlord approved successfully.'
                            : 'Landlord rejected successfully.'
                    );
                    setDialogSeverity('success');
                    setOpenDialog(true);
                }
            } else {
                setDialogMessage('Landlord User not found');
                setDialogSeverity('error');
                setOpenDialog(true);
            }
        } catch (error) {
            if (error.response && error.response.data) {
                console.error('error:', error.response.data);
            }
        }
    };

    const handleDialogClose = () => {
        setOpenDialog(false);
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
    }));

    const ApproveLandlordContainer = styled(Stack)(({ theme }) => ({
        marginTop: theme.spacing(12),
        height: 'auto',
        padding: theme.spacing(2),
        [theme.breakpoints.up('sm')]: {
            padding: theme.spacing(4),
        },
    }));

    return (
        <AppTheme {...props}>
            <CssBaseline enableColorScheme />
            <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem', marginTop: '4rem' }} />
            <Navbar />
            <ApproveLandlordContainer direction="column" justifyContent="space-between">
                <Box textAlign="center">
                    <Typography component="h1" variant="h4" sx={{ width: '100%', fontSize: 'clamp(2rem, 10vw, 2.15rem)' }}>
                        Approve a Landlord
                    </Typography>
                </Box>
                <div
                    style={{
                        margin: '0 25%',
                        display: 'flex',
                        flexDirection: 'row',
                        justifyContent: 'center',
                        padding: '2em',
                    }}
                >
                    <Card variant="outlined">
                        <Typography component="h2" variant="h5" sx={{ width: '100%', fontSize: 'clamp(1rem, 5vw, 1rem)' }}>
                            Listing Info
                        </Typography>
                        <Box component="form" onSubmit={(e) => e.preventDefault()} sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="Username">Username</FormLabel>
                                <TextField
                                    name="Username"
                                    fullWidth
                                    id="Username"
                                    placeholder="Username"
                                    error={userNameError}
                                    helperText={userNameErrorMessage}
                                    color={userNameError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl fullWidth>
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
                            <FormControl fullWidth>
                                <FormLabel htmlFor="phone-number">Phone Number</FormLabel>
                                <TextField
                                    fullWidth
                                    name="phoneNumber"
                                    placeholder="Enter phone number"
                                    type="phoneNumber"
                                    id="phone-number"
                                    variant="outlined"
                                    error={phoneNumberError}
                                    helperText={phoneNumberErrorMessage}
                                    color={phoneNumberError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Reason">Reason</FormLabel>
                                <TextField
                                    required
                                    fullWidth
                                    name="Reason"
                                    placeholder="Reason"
                                    id="Reason"
                                    variant="outlined"
                                    error={reasonError}
                                    helperText={reasonErrorMessage}
                                    color={reasonError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <Box sx={{ marginTop: 3, display: 'flex', justifyContent: 'space-between' }}>
                                <Button
                                    color="success"
                                    variant="contained"
                                    onClick={() => {
                                        if (validateInputs()) {
                                            handleAction('approve');
                                        }
                                    }}
                                >
                                    Approve
                                </Button>
                                <Button
                                    color="error"
                                    variant="contained"
                                    onClick={() => {
                                        if (validateInputs()) {
                                            handleAction('decline');
                                        }
                                    }}
                                >
                                    Decline
                                </Button>
                            </Box>
                        </Box>
                    </Card>
                </div>
            </ApproveLandlordContainer>
            <StatusDialog
                open={openDialog}
                onClose={handleDialogClose}
                message={dialogMessage}
                severity={dialogSeverity}
            />
        </AppTheme>
    );
}
