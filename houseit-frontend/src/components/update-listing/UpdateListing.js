import React from 'react';
import {
    Box,
    Button,
    Container,
    CssBaseline,
    FormLabel,
    FormControl,
    Link,
    TextField,
    Typography,
    Stack,
    Card as MuiCard,
    MenuItem,
    Select,
    FormControlLabel,
    Checkbox,
    AppBar,
    Toolbar,
} from '@mui/material';
import { styled } from '@mui/material/styles';
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import ImageUpload from '../../components/image-upload/ImageUpload';
import { useNavigate } from 'react-router-dom';
import mcgillLogo from '../../assets/mcgill-logo.png';
import Navbar from '../navbar/Navbar';

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


export default function UpdateListing(props) {
    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";

    const [lisitingType, setListingType] = React.useState('');

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
    const [lisitingTypeError, setListingTypeError] = React.useState(false);
    const [listingTypeErrorMessage, setListingTypeErrorMessage] = React.useState('');
    const [priceError, setPriceError] = React.useState(false);
    const [priceErrorMessage, setPriceErrorMessage] = React.useState('');
    const [squareFootageError, setSquareFootageError] = React.useState(false);
    const [squareFootageErrorMessage, setSquareFootageErrorMessage] = React.useState('');

    const validateInputs = () => {
        const description = document.getElementById('Description');
        const bedrooms = document.getElementById('Bedrooms');
        const bathrooms = document.getElementById('Bathrooms');
        const title = document.getElementById('Title');
        const price = document.getElementById('Price');
        const squareFootage = document.getElementById('SquareFootage');

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

        if (!lisitingType || lisitingType.value === '') {
            setListingTypeError(true);
            setListingTypeErrorMessage('Listing Type is required.');
            isValid = false;
        } else {
            setListingTypeError(false);
            setListingTypeErrorMessage('');
        }

        return isValid;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!validateInputs()) {
            return;
        }

        const data = new FormData(event.currentTarget);
        const payload = {
            name: data.get('username'),
            email: data.get('email'),
            password: data.get('password'),
            lisitingType: lisitingType,
        };

        try {
            const response = await fetch('/authentication/signup', {
                method: 'POST',
                body: JSON.stringify(payload),
            });

            if (response.ok) {
                const result = await response.json();
                console.log('Signup successful:', result);
            } else {
                const errorData = await response.json();
                console.error('Signup error:', errorData);
                // TODO: handle backend errors
            }
        } catch (error) {
            console.error('Network error:', error);
        }
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
                <Card variant="outlined">
                    <Typography
                        component="h1"
                        variant="h4"
                        sx={{ width: '100%', fontSize: 'clamp(2rem, 10vw, 2.15rem)' }}
                    >
                        Create a Listing
                    </Typography>
                    <Box
                        component="form"
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
                                value={lisitingType}
                                error={lisitingTypeError}
                                // helperText={lisitingTypeErrorMessage}
                                color={lisitingTypeError ? 'error' : 'primary'}
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
                        <FormControlLabel control={<Checkbox />} label="Wheelchair Accessible" />
                        <FormControlLabel control={<Checkbox />} label="Smoking Allowed" />
                        <ImageUpload fullWidth/>
                        <Button
                            type="createListing"
                            fullWidth
                            variant="contained"
                            onClick={validateInputs}
                            sx={{ marginBottom: 2 }} // Adjust the spacing here
                        >
                            Create Listing
                        </Button>
                    </Box>
                </Card>
            </CreateListingContainer>
        </AppTheme>
    );
}