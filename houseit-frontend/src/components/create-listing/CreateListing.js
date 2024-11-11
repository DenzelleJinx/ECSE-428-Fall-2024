import React, { useRef } from 'react';
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
import ImageUpload from '../../components/image-upload/ImageUpload';
import { useNavigate } from 'react-router-dom';
import mcgillLogo from '../../assets/mcgill-logo.png';
import Navbar from '../navbar/Navbar';
import StatusDialog from '../../components/status-dialog/StatusDialog';

// const Card = styled(MuiCard)(({ theme }) => ({
//     display: 'flex',
//     flexDirection: 'column',
//     alignSelf: 'center',
//     width: '100%',
//     padding: theme.spacing(4),
//     gap: theme.spacing(2),
//     margin: 'auto',
//     boxShadow:
//         'hsla(220, 30%, 5%, 0.05) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.05) 0px 15px 35px -5px',
//     [theme.breakpoints.up('sm')]: {
//         width: '450px',
//     },
//     ...theme.applyStyles('dark', {
//         boxShadow:
//             'hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px',
//     }),
// }));

const isNumeric = (string) => /^[+-]?\d+(\.\d+)?$/.test(string)


export default function CreateListing(props) {
    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";
    const formRef = useRef(null);
    const [listingType, setListingType] = React.useState('');

    const handleLisitingTypeChange = (event) => {
        setListingType(event.target.value);
    };

    const [descriptionError, setDescriptionError] = React.useState(false);
    const [descriptionErrorMessage, setDescriptionErrorMessage] = React.useState('');
    const [bedroomsError, setBedroomsError] = React.useState(false);
    const [bedroomsErrorMessage, setBedroomsErrorMessage] = React.useState('');
    const [bathroomsError, setBathroomsError] = React.useState(false);
    const [bathroomsErrorMessage, setBathroomsErrorMessage] = React.useState('');
    const [titleError, setTitleError] = React.useState(false);
    const [titleErrorMessage, setTitleErrorMessage] = React.useState('');
    const [listingTypeError, setListingTypeError] = React.useState(false);
    const [listingTypeErrorMessage, setListingTypeErrorMessage] = React.useState('');
    const [priceError, setPriceError] = React.useState(false);
    const [priceErrorMessage, setPriceErrorMessage] = React.useState('');
    const [squareFootageError, setSquareFootageError] = React.useState(false);
    const [squareFootageErrorMessage, setSquareFootageErrorMessage] = React.useState('');

    const [apartmentError, setApartmentError] = React.useState(false);
    const [apartmentErrorMessage, setApartmentErrorMessage] = React.useState('');
    const [streetNumberError, setStreetNumberError] = React.useState(false);
    const [streetNumberErrorMessage, setStreetNumberErrorMessage] = React.useState('');
    const [streetError, setStreetError] = React.useState(false);
    const [streetErrorMessage, setStreetErrorMessage] = React.useState('');
    const [cityError, setCityError] = React.useState(false);
    const [cityErrorMessage, setCityErrorMessage] = React.useState('');
    const [postalCodeError, setPostalCodeError] = React.useState(false);
    const [postalCodeErrorMessage, setPostalCodeErrorMessage] = React.useState('');

    const [openDialog, setOpenDialog] = React.useState(false); // State for dialog visibility
    const [dialogMessage, setDialogMessage] = React.useState(''); // Message to display in the dialog
    const [dialogSeverity, setDialogSeverity] = React.useState('error'); // Severity of the message: 'success' or 'error'

    const validateInputs = () => {
        const description = document.getElementById('Description');
        const bedrooms = document.getElementById('Bedrooms');
        const bathrooms = document.getElementById('Bathrooms');
        const title = document.getElementById('Title');
        const price = document.getElementById('Price');
        const squareFootage = document.getElementById('SquareFootage');
        const apartment = document.getElementById('ApartmentNumber');
        const streetNumber = document.getElementById('StreetNumber');
        const street = document.getElementById('Street');
        const city = document.getElementById('City');
        const postalCode = document.getElementById('PostalCode');

        let isValid = true;

        if (!description || !description.value || description.value >= 256 || description.value < 1) {
            setDescriptionError(true);
            setDescriptionErrorMessage('A description needs to be between 1 and 256 characters.');
            isValid = false;
        } else {
            setDescriptionError(false);
            setDescriptionErrorMessage('');
        }

        if (!bedrooms || !bedrooms.value || bedrooms.value < 1) {
            setBedroomsError(true);
            setBedroomsErrorMessage('There must be at least 1 bedroom.');
            isValid = false;
        } else if (!isNumeric(bedrooms.value)){
            setBedroomsError(true);
            setBedroomsErrorMessage('The number of bedrooms has to be an integer.');
            isValid = false;
        } else {
            setBedroomsError(false);
            setBedroomsErrorMessage('');
        }

        if (!bathrooms || !bathrooms.value || bathrooms.value < 1) {
            setBathroomsError(true);
            setBathroomsErrorMessage('There must be at least 1 bathroom.');
            isValid = false;
        } else if (!isNumeric(bathrooms.value)){
            setBathroomsError(true);
            setBathroomsErrorMessage('The number of bathrooms has to be an integer.');
            isValid = false;
        } else {
            setBathroomsError(false);
            setBathroomsErrorMessage('');
        }

        if (!price || !price.value || price.value < 1) {
            setPriceError(true);
            setPriceErrorMessage('The minimum price must be at least 1$/month.');
            isValid = false;
        } else if (!isNumeric(price.value)){
            setPriceError(true);
            setPriceErrorMessage('The price has to be an integer.');
            isValid = false;
        } else {
            setPriceError(false);
            setPriceErrorMessage('');
        }

        if (!squareFootage || !squareFootage.value || squareFootage.value < 1) {
            setSquareFootageError(true);
            setSquareFootageErrorMessage('The minimum square footage must be at least 1 ft^2.');
            isValid = false;
        } else if (!isNumeric(squareFootage.value)){
            setSquareFootageError(true);
            setSquareFootageErrorMessage('The square footage has to be an integer.');
            isValid = false;
        } else {
            setSquareFootageError(false);
            setSquareFootageErrorMessage('');
        }

        if (!title || !title.value || title.value.length < 1) {
            setTitleError(true);
            setTitleErrorMessage('Title is required.');
            isValid = false;
        } else {
            setTitleError(false);
            setTitleErrorMessage('');
        }

        if (!listingType || listingType.value === '') {
            setListingTypeError(true);
            setListingTypeErrorMessage('Listing Type is required.');
            isValid = false;
        } else {
            setListingTypeError(false);
            setListingTypeErrorMessage('');
        }

        if (!apartment || !apartment.value || apartment.value < 1) {
            setApartmentError(true);
            setApartmentErrorMessage('The address must have a apartment number greater than 1');
            isValid = false;
        } else if (!isNumeric(apartment.value)){
            setApartmentError(true);
            setApartmentErrorMessage('The apartment number has to be an integer.');
            isValid = false;
        } else {
            setApartmentError(false);
            setApartmentErrorMessage('');
        }

        if (!streetNumber || !streetNumber.value || streetNumber.value < 1) {
            setStreetNumberError(true);
            setStreetNumberErrorMessage('The address must have a street number greater than 1');
            isValid = false;
        } else if (!isNumeric(streetNumber.value)){
            setStreetNumberError(true);
            setStreetNumberErrorMessage('The street number has to be an integer.');
            isValid = false;
        } else {
            setStreetNumberError(false);
            setStreetNumberErrorMessage('');
        }

        if (!street || !street.value || street.value >= 256 || street.value < 1) {
            setStreetError(true);
            setStreetErrorMessage('A street name needs to be between 1 and 256 characters.');
            isValid = false;
        } else {
            setStreetError(false);
            setStreetErrorMessage('');
        }

        if (!city || !city.value || city.value >= 256 || city.value < 1) {
            setCityError(true);
            setCityErrorMessage('A city name needs to be between 1 and 256 characters.');
            isValid = false;
        } else {
            setCityError(false);
            setCityErrorMessage('');
        }

        if (!postalCode || !postalCode.value || postalCode.value > 6 || postalCode.value <= 5) {
            setPostalCodeError(true);
            setPostalCodeErrorMessage('A postal code name needs to have 6 characters.');
            isValid = false;
        } else {
            setPostalCodeError(false);
            setPostalCodeErrorMessage('');
        }

        return isValid;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const isValid = validateInputs();

        if (!isValid) {
            setDialogMessage('Please fill in all required fields.');
            setDialogSeverity('error');
            setOpenDialog(true);
            console.log("hey");
            return;
        }

        const data = new FormData(formRef.current);
        const payload = {
            name: data.get('username'),
            email: data.get('email'),
            password: data.get('password'),
            listingType: listingType,
        };
        console.log(payload);

        try {
            const response = await fetch('/authentication/signup', {
                method: 'POST',
                body: JSON.stringify(payload),
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Signup successful:', result);
                setDialogMessage('Listing created successfully!');
                setDialogSeverity('success');
            } else {
                const errorData = await response.json();
                console.error('Signup error:', errorData);
                setDialogMessage(`Error: ${errorData.message}`);
                setDialogSeverity('error');
                // TODO: handle backend errors
            }
        } catch (error) {
            console.error('Network error:', error);
            setDialogMessage('Network error occurred. Please try again later.');
            setDialogSeverity('error');
        }

        setOpenDialog(true); // Open the dialog after submission attempt
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
        ...theme.applyStyles('dark', {
            boxShadow:
                'hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px',
        }),
    }));

        const CreateListingContainer = styled(Stack)(({ theme }) => ({
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
        navigate('/createlisting');
    };
    const handleLogoClick = () => {
        navigate('/');
    };

    return (
        <AppTheme {...props}>
            <CssBaseline enableColorScheme />
            <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem' , marginTop: "4rem"}} />
            <Navbar />
            <CreateListingContainer direction="column" justifyContent="space-between">
                <Box textAlign='center'>
                    <Typography
                        component="h1"
                        variant="h4"
                        sx={{ width: '100%', fontSize: 'clamp(2rem, 10vw, 2.15rem)' }}
                    >
                        Create a Listing
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
                            ref={formRef} // Assign the ref to the form element
                            onSubmit={handleSubmit}
                            sx={{ display: 'flex', flexDirection: 'column', gap: 2, }} // Adjusted marginBottom
                        >
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Title">Title</FormLabel>
                                <TextField
                                    name="Title"
                                    required
                                    fullWidth
                                    id="Title"
                                    placeholder="Title"
                                    error={titleError}
                                    helperText={titleErrorMessage}
                                    color={titleError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Description">Description</FormLabel>
                                <TextField
                                    required
                                    fullWidth
                                    id="Description"
                                    placeholder="Enter description here"
                                    name="Description"
                                    autoComplete="Description"
                                    variant="outlined"
                                    error={descriptionError}
                                    helperText={descriptionErrorMessage}
                                    color={descriptionError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="listing-type">Listing Type</FormLabel>
                                <Select
                                    id="lisiting-type"
                                    value={listingType}
                                    error={listingTypeError}
                                    helperText={listingTypeErrorMessage}
                                    color={listingTypeError ? 'error' : 'primary'}
                                    onChange={handleLisitingTypeChange}
                                >
                                    <MenuItem value={"lease"}>Original Lease</MenuItem>
                                    <MenuItem value={"sublet"}>Sublet</MenuItem>
                                </Select>
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Bedrooms">Bedrooms</FormLabel>
                                <TextField
                                    required
                                    fullWidth
                                    name="Bedrooms"
                                    placeholder="Number of Bedrooms"
                                    type="Bedrooms"
                                    id="Bedrooms"
                                    autoComplete="Bedrooms"
                                    variant="outlined"
                                    error={bedroomsError}
                                    helperText={bedroomsErrorMessage}
                                    color={bedroomsError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Bathrooms">Bathrooms</FormLabel>
                                <TextField
                                    required
                                    fullWidth
                                    name="Bathrooms"
                                    placeholder="Number of Bathrooms"
                                    type="Bathrooms"
                                    id="Bathrooms"
                                    autoComplete="Bathrooms"
                                    variant="outlined"
                                    error={bathroomsError}
                                    helperText={bathroomsErrorMessage}
                                    color={bathroomsError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Price">Price</FormLabel>
                                <TextField
                                    required
                                    fullWidth
                                    name="Price"
                                    placeholder="Monthly Price"
                                    type="Price"
                                    id="Price"
                                    autoComplete="Price"
                                    variant="outlined"
                                    error={priceError}
                                    helperText={priceErrorMessage}
                                    color={priceError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="squareFootage">SquareFootage</FormLabel>
                                <TextField
                                    required
                                    fullWidth
                                    name="Square Footage"
                                    placeholder="Square Footage (ft^2)"
                                    type="SquareFootage"
                                    id="SquareFootage"
                                    autoComplete="SquareFootage"
                                    variant="outlined"
                                    error={squareFootageError}
                                    helperText={squareFootageErrorMessage}
                                    color={squareFootageError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <div
                                style={{
                                    margin: "0 25%",
                                    display: "flex",
                                    flexDirection: "row",
                                    justifyContent: "center",
                                    border: "1px solid #000",
                                    padding: "2em",
                                    maxwidth: "500px",
                                }}
                            >
                                <FormControlLabel control={<Checkbox />} label="Wheelchair Accessible" />
                                <FormControlLabel control={<Checkbox />} label="Smoking Allowed" />
                            </div>
                        </Box>
                    </Card>
                    <hr />
                    <Card variant="outlined">
                        <Typography
                            component="h2"
                            variant="h5"
                            sx={{ width: '100%', fontSize: 'clamp(1rem, 5vw, 1rem)' }}
                        >
                            Address Info
                        </Typography>
                        <Box
                            component="form"
                            onSubmit={handleSubmit}
                            sx={{ display: 'flex', flexDirection: 'column', gap: 2, }} // Adjusted marginBottom
                        >
                            <FormControl fullWidth>
                                <FormLabel htmlFor="ApartmentNumber">Apartment Number</FormLabel>
                                <TextField
                                    name="ApartmentNumber"
                                    fullWidth
                                    id="ApartmentNumber"
                                    placeholder="Apartment Number"
                                    error={apartmentError}
                                    helperText={apartmentErrorMessage}
                                    color={apartmentError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="StreetNumber">Street Number</FormLabel>
                                <TextField
                                    name="StreetNumber"
                                    required
                                    fullWidth
                                    id="StreetNumber"
                                    placeholder="Street Number"
                                    error={streetNumberError}
                                    helperText={streetNumberErrorMessage}
                                    color={streetNumberError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="Street">Street</FormLabel>
                                <TextField
                                    name="Street"
                                    required
                                    fullWidth
                                    id="Street"
                                    placeholder="Street"
                                    error={streetError}
                                    helperText={streetErrorMessage}
                                    color={streetError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="City">City</FormLabel>
                                <TextField
                                    name="City"
                                    required
                                    fullWidth
                                    id="City"
                                    placeholder="City"
                                    error={cityError}
                                    helperText={cityErrorMessage}
                                    color={cityError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="PostalCode">Postal Code</FormLabel>
                                <TextField
                                    name="PostalCode"
                                    required
                                    fullWidth
                                    id="PostalCode"
                                    placeholder="Postal Code"
                                    error={postalCodeError}
                                    helperText={postalCodeErrorMessage}
                                    color={postalCodeError ? 'error' : 'primary'}
                                />
                            </FormControl>
                        </Box>
                    </Card>
                </div>
                <Box textAlign='center'>
                    <ImageUpload fullWidth/>
                    <Button
                        type="createListing"
                        fullWidth={false}
                        variant="contained"
                        onClick={handleSubmit}
                        sx={{ marginBottom: 2 }} // Adjust the spacing here
                    >
                        Create Listing
                    </Button>
                </Box>
            </CreateListingContainer>
            <StatusDialog
                open={openDialog}
                onClose={handleDialogClose}
                severity={dialogSeverity}
                title={dialogSeverity === 'success' ? 'Success' : 'An Error Occurred'}
                message={dialogMessage}
            />
        </AppTheme>
    );
}