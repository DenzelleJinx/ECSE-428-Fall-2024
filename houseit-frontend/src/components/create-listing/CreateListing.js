import React, { useEffect } from 'react';
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
import axios from 'axios';


const isNumeric = (string) => /^[+-]?\d+(\.\d+)?$/.test(string)


export default function CreateListing(props) {

    useEffect(() => {
        // TODO: Remove this when done testing
        const isTesting = true;
        if (isTesting) {
            // this code block sets test values to make your life easier
            console.log("using test values");
            const title = document.getElementById('Title');
            const description = document.getElementById('Description');
            const bedrooms = document.getElementById('Bedrooms');
            const bathrooms = document.getElementById('Bathrooms');
            const price = document.getElementById('Price');
            const squareFootage = document.getElementById('SquareFootage');
            const wheelchairAccessible = document.getElementById('wheelchair-accessible');
            const smokingAllowed = document.getElementById('smoking-allowed');
            const apartment = document.getElementById('ApartmentNumber');
            const streetNumber = document.getElementById('StreetNumber');
            const street = document.getElementById('Street');
            const city = document.getElementById('City');
            const postalCode = document.getElementById('PostalCode');
            const gym = document.getElementById('gym');
            const laundry = document.getElementById('laundry');
            const petsAllowed = document.getElementById('pets-allowed');
            const parking = document.getElementById('parking');
            const internetIncluded = document.getElementById('internet-included');
            const waterCost = document.getElementById('water-cost');
            const electricityCost = document.getElementById('electricity-cost');
            const heatingCost = document.getElementById('heating-cost');
            title.value = "test";
            description.value = "test desc";
            setPropertyType("HOUSE");
            bedrooms.value = 1;
            bathrooms.value = 1;
            price.value = 1;
            squareFootage.value = 1;
            wheelchairAccessible.checked = false;
            smokingAllowed.checked = false;
            apartment.value = 1;
            streetNumber.value = 1;
            street.value = "test street";
            city.value = "test city";
            postalCode.value = "A1A1A1";
            gym.checked = false;
            laundry.checked = false;
            petsAllowed.checked = false;
            parking.checked = false;
            internetIncluded.checked = false;
            waterCost.value = 1;
            electricityCost.value = 1;
            heatingCost.value = 1;
            document.getElementById('image-1').value = "https://liveatencore.com/wp-content/uploads/2018/12/14-dec-2018-UNIT-2.png";
            document.getElementById('image-3').value = "https://images1.apartments.com/i2/waLNySi3DU4Z-hW66noKuBfuS1SgQEozHk5sIrcJbBo/117/4346-46-39th-pl-unit-ph-1-queens-ny-building-photo.jpg?p=1";
            document.getElementById('image-4').value = "https://images.rentals.ca/property-pictures/medium/montreal-qc/809658/apartment-220293668.jpg";
        }
    })

    const primaryColor = "#D50032";
    const secondaryColor = "#FFFFFF";

    const [propertyType, setPropertyType] = React.useState('');

    const handlePropertyTypeChange = (event) => {
        setPropertyType(event.target.value);
    };

    const [descriptionError, setDescriptionError] = React.useState(false);
    const [descriptionErrorMessage, setDescriptionErrorMessage] = React.useState('');
    const [bedroomsError, setBedroomsError] = React.useState(false);
    const [bedroomsErrorMessage, setBedroomsErrorMessage] = React.useState('');
    const [bathroomsError, setBathroomsError] = React.useState(false);
    const [bathroomsErrorMessage, setBathroomsErrorMessage] = React.useState('');
    const [titleError, setTitleError] = React.useState(false);
    const [titleErrorMessage, setTitleErrorMessage] = React.useState('');
    const [propertyTypeError, setPropertyTypeError] = React.useState(false);
    const [propertyTypeErrorMessage, setPropertyTypeErrorMessage] = React.useState('');
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

    const [waterCostError, setWaterCostError] = React.useState(false);
    const [waterCostErrorMessage, setWaterCostErrorMessage] = React.useState('');
    const [electricityCostError, setElectricityCostError] = React.useState(false);
    const [electricityCostErrorMessage, setElectricityCostErrorMessage] = React.useState('');
    const [heatingCostError, setHeatingCostError] = React.useState(false);
    const [heatingCostErrorMessage, setHeatingCostErrorMessage] = React.useState('');

    const [serverErrorMessage, setServerErrorMessage] = React.useState('');
    const [serverSuccessMessage, setServerSuccessMessage] = React.useState('');

    const validateInputs = () => {        
        const title = document.getElementById('Title');
        const description = document.getElementById('Description');
        const bedrooms = document.getElementById('Bedrooms');
        const bathrooms = document.getElementById('Bathrooms');
        const price = document.getElementById('Price');
        const squareFootage = document.getElementById('SquareFootage');
        const apartment = document.getElementById('ApartmentNumber');
        const streetNumber = document.getElementById('StreetNumber');
        const street = document.getElementById('Street');
        const city = document.getElementById('City');
        const postalCode = document.getElementById('PostalCode');

        const waterCost = document.getElementById('water-cost');
        const electricityCost = document.getElementById('electricity-cost');
        const heatingCost = document.getElementById('heating-cost');

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

        if (!propertyType || propertyType.value === '') {
            setPropertyTypeError(true);
            setPropertyTypeErrorMessage('Listing Type is required.');
            isValid = false;
        } else {
            setPropertyTypeError(false);
            setPropertyTypeErrorMessage('');
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

        if ((waterCost && waterCost.value) || (electricityCost && electricityCost.value) || (heatingCost && heatingCost.value)) {
            if (!waterCost || !waterCost.value || waterCost.value < 0) {
                setWaterCostError(true);
                setWaterCostErrorMessage('The water cost must be a positive number.');
                isValid = false;
            } else {
                setWaterCostError(false);
                setWaterCostErrorMessage('');
            }

            if (!electricityCost || !electricityCost.value || electricityCost.value < 0) {
                setElectricityCostError(true);
                setElectricityCostErrorMessage('The electricity cost must be a positive number.');
                isValid = false;
            } else {
                setElectricityCostError(false);
                setElectricityCostErrorMessage('');
            }

            if (!heatingCost || !heatingCost.value || heatingCost.value < 0) {
                setHeatingCostError(true);
                setHeatingCostErrorMessage('The heating cost must be a positive number.');
                isValid = false;
            } else {
                setHeatingCostError(false);
                setHeatingCostErrorMessage('');
            }
        }

        return isValid;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Clear previous error messages
        setServerErrorMessage('');
        setServerSuccessMessage('');
        
        // Get form values
        const title = document.getElementById('Title');
        const description = document.getElementById('Description');
        const bedrooms = document.getElementById('Bedrooms');
        const bathrooms = document.getElementById('Bathrooms');
        const price = document.getElementById('Price');
        const squareFootage = document.getElementById('SquareFootage');
        const wheelchairAccessible = document.getElementById('wheelchair-accessible');
        const smokingAllowed = document.getElementById('smoking-allowed');
        const apartment = document.getElementById('ApartmentNumber');
        const streetNumber = document.getElementById('StreetNumber');
        const street = document.getElementById('Street');
        const city = document.getElementById('City');
        const postalCode = document.getElementById('PostalCode');
        const gym = document.getElementById('gym');
        const laundry = document.getElementById('laundry');
        const petsAllowed = document.getElementById('pets-allowed');
        const parking = document.getElementById('parking');
        const internetIncluded = document.getElementById('internet-included');
        const waterCost = document.getElementById('water-cost');
        const electricityCost = document.getElementById('electricity-cost');
        const heatingCost = document.getElementById('heating-cost');
        
        // Validate inputs
        if (!validateInputs()) {
            return;
        }
        
        // Check whether to include utilities or leave utilities as null
        const includeUtilities = waterCost && electricityCost && heatingCost && 
        waterCost.value && electricityCost.value && heatingCost.value;
        
        // Get property images
        const propertyImages = [];
        for (let i = 1; i <= 10; i++) {
            const img = document.getElementById(`image-${i}`);
            if (img && img.value) {
                propertyImages.push({ url: img.value });
            }
        }

        // Get lanlord ID
        const user = JSON.parse(localStorage.getItem('currentUser'));
        if (!user || user.accountType !== 'landlord') {
            setServerErrorMessage('You must be logged in as a landlord to create a listing.');
            return;
        }

        // Build the payload
        const payload = {
            landlordId: user.id,
            title: title.value,
            description: description.value,
            propertyType: propertyType,
            bedrooms: bedrooms.value,
            bathrooms: bathrooms.value,
            monthlyPrice: price.value,
            squareFootage: squareFootage.value,
            wheelchairAccessible: wheelchairAccessible.checked,
            smokingAllowed: smokingAllowed.checked,
            address: {
                apartment: apartment.value,
                streetNumber: streetNumber.value,
                street: street.value,
                city: city.value,
                postalCode: postalCode.value
            },
            amenitiesOffered: {
                gym: gym.checked,
                laundry: laundry.checked,
                petsAllowed: petsAllowed.checked,
                parking: parking.checked,
                internetIncluded: internetIncluded.checked
            },
            utilitiesCosts: includeUtilities ? {
                waterCost: waterCost.value,
                electricityCost: electricityCost.value,
                heatingCost: heatingCost.value
            } : null,
            propertyImages: propertyImages
        };

        // Send request and handle response
        try {
            const axiosClient = axios.create({
              baseURL: "http://localhost:8080",
            });
            const response = await axiosClient.post('/listing', payload);
            console.log('Listing created successfully:', response.data); // TODO: Remove this when done testing
            setServerSuccessMessage('Listing created successfully');
          } catch (error) {
            let errorMessage = null
            if (error.response && typeof error.response.data === 'string') {
                errorMessage = error.response.data
            }
            setServerErrorMessage(errorMessage ? errorMessage : 'An error occurred during signup. Please try again.');
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
                        flexDirection: "column",
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
                                <FormLabel htmlFor="property-type">Property Type</FormLabel>
                                <Select
                                    id="property-type"
                                    value={propertyType}
                                    error={propertyTypeError}
                                    color={propertyTypeError ? 'error' : 'primary'}
                                    onChange={handlePropertyTypeChange}
                                >
                                    <MenuItem value={"STUDIO"}>Studio</MenuItem>
                                    <MenuItem value={"DORM"}>Dorm</MenuItem>
                                    <MenuItem value={"CONDO"}>Condo</MenuItem>
                                    <MenuItem value={"APARTMENT"}>Apartment</MenuItem>
                                    <MenuItem value={"HOUSE"}>House</MenuItem>
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
                                <FormControlLabel control={<Checkbox id="wheelchair-accessible" />} label="Wheelchair Accessible" />
                                <FormControlLabel control={<Checkbox id="smoking-allowed"/>} label="Smoking Allowed" />
                            </div>
                        </Box>
                    </Card>
                    <br />
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
                    <br />
                    <Card variant="outlined">
                        <Typography
                            component="h2"
                            variant="h5"
                            sx={{ width: '100%', fontSize: 'clamp(1rem, 5vw, 1rem)' }}
                        >
                            Amenities
                        </Typography>
                        <Box
                            component="form"
                            onSubmit={handleSubmit}
                            sx={{ display: 'flex', flexDirection: 'column', gap: 2, }} // Adjusted marginBottom
                        >
                            <FormControlLabel control={<Checkbox id="gym" />} label="Gym" />
                            <FormControlLabel control={<Checkbox id="laundry"/>} label="Laundry" />
                            <FormControlLabel control={<Checkbox id="pets-allowed"/>} label="Pets Allowed" />
                            <FormControlLabel control={<Checkbox id="parking"/>} label="Parking" />
                            <FormControlLabel control={<Checkbox id="internet-included"/>} label="Internet Included" />
                        </Box>
                    </Card>
                    <br />
                    <Card variant="outlined">
                        <Typography
                            component="h2"
                            variant="h5"
                            sx={{ width: '100%', fontSize: 'clamp(1rem, 5vw, 1rem)' }}
                        >
                            Utilities (leave all fields blank if not applicable)
                        </Typography>
                        <Box
                            component="form"
                            onSubmit={handleSubmit}
                            sx={{ display: 'flex', flexDirection: 'column', gap: 2, }} // Adjusted marginBottom
                        >
                            <FormControl fullWidth>
                                <FormLabel htmlFor="water-cost">Water Cost</FormLabel>
                                <TextField
                                    name="water-cost"
                                    fullWidth
                                    id="water-cost"
                                    error={waterCostError}
                                    helperText={waterCostErrorMessage}
                                    color={waterCostError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="electricity-cost">Electricity Cost</FormLabel>
                                <TextField
                                    name="electricity-cost"
                                    fullWidth
                                    id="electricity-cost"
                                    error={electricityCostError}
                                    helperText={electricityCostErrorMessage}
                                    color={electricityCostError ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="heating-cost">Heating Cost</FormLabel>
                                <TextField
                                    name="heating-cost"
                                    fullWidth
                                    id="heating-cost"
                                    error={heatingCostError}
                                    helperText={heatingCostErrorMessage}
                                    color={heatingCostError ? 'error' : 'primary'}
                                />
                            </FormControl>
                        </Box>
                    </Card>
                    <br />
                    <Card variant="outlined">
                        <Typography
                            component="h2"
                            variant="h5"
                            sx={{ width: '100%', fontSize: 'clamp(1rem, 5vw, 1rem)' }}
                        >
                            Images
                        </Typography>
                        <Box
                            component="form"
                            onSubmit={handleSubmit}
                            sx={{ display: 'flex', flexDirection: 'column', gap: 2, }} // Adjusted marginBottom
                        >
                            {/* TODO: Improve the image upload section below? Maybe make it more dynamic */}
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-1">Image 1</FormLabel>
                                <TextField
                                    name="image-1"
                                    fullWidth
                                    id="image-1"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-2">Image 2</FormLabel>
                                <TextField
                                    name="image-2"
                                    fullWidth
                                    id="image-2"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-3">Image 3</FormLabel>
                                <TextField
                                    name="image-3"
                                    fullWidth
                                    id="image-3"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-4">Image 4</FormLabel>
                                <TextField
                                    name="image-4"
                                    fullWidth
                                    id="image-4"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-5">Image 5</FormLabel>
                                <TextField
                                    name="image-5"
                                    fullWidth
                                    id="image-5"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-6">Image 6</FormLabel>
                                <TextField
                                    name="image-6"
                                    fullWidth
                                    id="image-6"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-7">Image 7</FormLabel>
                                <TextField
                                    name="image-7"
                                    fullWidth
                                    id="image-7"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-8">Image 8</FormLabel>
                                <TextField
                                    name="image-8"
                                    fullWidth
                                    id="image-8"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-9">Image 9</FormLabel>
                                <TextField
                                    name="image-9"
                                    fullWidth
                                    id="image-9"
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-10">Image 10</FormLabel>
                                <TextField
                                    name="image-10"
                                    fullWidth
                                    id="image-10"
                                />
                            </FormControl>
                        </Box>
                    </Card>
                </div>
                {/* TODO: Consider putting all the form fields in a single form, including the box below */}
                <Box textAlign='center'>
                    {serverErrorMessage && (
                        <Typography color="error" sx={{ textAlign: 'center', marginBottom: 1 }}>
                        {serverErrorMessage}
                        </Typography>
                    )}
                    {serverSuccessMessage && (
                        <Typography color="success" sx={{ textAlign: 'center', marginBottom: 1 }}>
                        {serverSuccessMessage}
                        </Typography>
                    )}
                    {/* <ImageUpload fullWidth/> */} {/* TODO: Remove the ImageUpload component? */}
                    <Button
                        type="submit"
                        fullWidth={false}
                        variant="contained"
                        onClick={handleSubmit}
                        sx={{ marginBottom: 2 }} // Adjust the spacing here
                    >
                        Create Listing
                    </Button>
                </Box>
            </CreateListingContainer>
        </AppTheme>
    );
}