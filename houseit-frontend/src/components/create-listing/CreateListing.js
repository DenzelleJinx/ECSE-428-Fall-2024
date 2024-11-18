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
        const checkAuth = () => {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            if (!user || user.accountType !== 'landlord') {
                // Redirect to home page if not authenticated as landlord
                navigate('/');
            }
        };
        
        checkAuth();
    })

    // listing variables
    const [title, setTitle] = React.useState('test');
    const [description, setDescription] = React.useState('test desc');
    const [price, setPrice] = React.useState('1');
    const [bedrooms, setBedrooms] = React.useState('1');
    const [bathrooms, setBathrooms] = React.useState('1');
    const [propertyType, setPropertyType] = React.useState('HOUSE');
    const [squareFootage, setSquareFootage] = React.useState('1');
    const [wheelchairAccessible, setWheelchairAccessible] = React.useState(false);
    const [smokingAllowed, setSmokingAllowed] = React.useState(false);

    // address variables
    const [city, setCity] = React.useState('test city');
    const [postalCode, setPostalCode] = React.useState('A1A1A1');
    const [street, setStreet] = React.useState('test street');
    const [streetNumber, setStreetNumber] = React.useState('1');
    const [apartmentNumber, setApartmentNumber] = React.useState('1');

    // amenities variables
    const [gym, setGym] = React.useState(false);
    const [laundry, setLaundry] = React.useState(false);
    const [petsAllowed, setPetsAllowed] = React.useState(false);
    const [parking, setParking] = React.useState(false);
    const [internetIncluded, setInternetIncluded] = React.useState(false);

    // utilities variables
    const [waterCost, setWaterCost] = React.useState('1');
    const [electricityCost, setElectricityCost] = React.useState('1');
    const [heatingCost, setHeatingCost] = React.useState('1');

    // image variables
    const [image1, setImage1] = React.useState('https://liveatencore.com/wp-content/uploads/2018/12/14-dec-2018-UNIT-2.png');
    const [image2, setImage2] = React.useState('https://images1.apartments.com/i2/waLNySi3DU4Z-hW66noKuBfuS1SgQEozHk5sIrcJbBo/117/4346-46-39th-pl-unit-ph-1-queens-ny-building-photo.jpg?p=1');
    const [image3, setImage3] = React.useState('https://images.rentals.ca/property-pictures/medium/montreal-qc/809658/apartment-220293668.jpg');
    const [image4, setImage4] = React.useState('');
    const [image5, setImage5] = React.useState('');
    const [image6, setImage6] = React.useState('');
    const [image7, setImage7] = React.useState('');
    const [image8, setImage8] = React.useState('');
    const [image9, setImage9] = React.useState('');
    const [image10, setImage10] = React.useState('');

    // error variables
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
        let isValid = true;
        
        if (!title || title.length < 1) {
            setTitleError(true);
            setTitleErrorMessage('Title is required.');
            isValid = false;
        } else {
            setTitleError(false);
            setTitleErrorMessage('');
        }
        
        if (!description || description.length >= 256 || description.length < 1) {
            setDescriptionError(true);
            setDescriptionErrorMessage('A description needs to be between 1 and 256 characters.');
            isValid = false;
        } else {
            setDescriptionError(false);
            setDescriptionErrorMessage('');
        }
        
        if (!bedrooms || bedrooms < 1) {
            setBedroomsError(true);
            setBedroomsErrorMessage('There must be at least 1 bedroom.');
            isValid = false;
        } else if (!isNumeric(bedrooms)){
            setBedroomsError(true);
            setBedroomsErrorMessage('The number of bedrooms has to be an integer.');
            isValid = false;
        } else {
            setBedroomsError(false);
            setBedroomsErrorMessage('');
        }
        
        if (!bathrooms || bathrooms < 1) {
            setBathroomsError(true);
            setBathroomsErrorMessage('There must be at least 1 bathroom.');
            isValid = false;
        } else if (!isNumeric(bathrooms)){
            setBathroomsError(true);
            setBathroomsErrorMessage('The number of bathrooms has to be an integer.');
            isValid = false;
        } else {
            setBathroomsError(false);
            setBathroomsErrorMessage('');
        }

        if (!price || price < 1) {
            setPriceError(true);
            setPriceErrorMessage('The minimum price must be at least 1$/month.');
            isValid = false;
        } else if (!isNumeric(price)){
            setPriceError(true);
            setPriceErrorMessage('The price has to be an integer.');
            isValid = false;
        } else {
            setPriceError(false);
            setPriceErrorMessage('');
        }

        if (!squareFootage || squareFootage < 1) {
            setSquareFootageError(true);
            setSquareFootageErrorMessage('The minimum square footage must be at least 1 ft^2.');
            isValid = false;
        } else if (!isNumeric(squareFootage)){
            setSquareFootageError(true);
            setSquareFootageErrorMessage('The square footage has to be an integer.');
            isValid = false;
        } else {
            setSquareFootageError(false);
            setSquareFootageErrorMessage('');
        }

        if (!propertyType) {
            setPropertyTypeError(true);
            setPropertyTypeErrorMessage('Listing Type is required.');
            isValid = false;
        } else {
            setPropertyTypeError(false);
            setPropertyTypeErrorMessage('');
        }

        if (!apartmentNumber || apartmentNumber < 1) {
            setApartmentError(true);
            setApartmentErrorMessage('The address must have a apartment number greater than 1');
            isValid = false;
        } else if (!isNumeric(apartmentNumber)){
            setApartmentError(true);
            setApartmentErrorMessage('The apartment number has to be an integer.');
            isValid = false;
        } else {
            setApartmentError(false);
            setApartmentErrorMessage('');
        }

        if (!streetNumber || streetNumber < 1) {
            setStreetNumberError(true);
            setStreetNumberErrorMessage('The address must have a street number greater than 1');
            isValid = false;
        } else if (!isNumeric(streetNumber)){
            setStreetNumberError(true);
            setStreetNumberErrorMessage('The street number has to be an integer.');
            isValid = false;
        } else {
            setStreetNumberError(false);
            setStreetNumberErrorMessage('');
        }

        if (!street || street.length >= 256 || street.length < 1) {
            setStreetError(true);
            setStreetErrorMessage('A street name needs to be between 1 and 256 characters.');
            isValid = false;
        } else {
            setStreetError(false);
            setStreetErrorMessage('');
        }

        if (!city || city.length >= 256 || city.length < 1) {
            setCityError(true);
            setCityErrorMessage('A city name needs to be between 1 and 256 characters.');
            isValid = false;
        } else {
            setCityError(false);
            setCityErrorMessage('');
        }

        if (!postalCode || postalCode.length != 6) {
            setPostalCodeError(true);
            setPostalCodeErrorMessage('A postal code name needs to have 6 characters.');
            isValid = false;
        } else {
            setPostalCodeError(false);
            setPostalCodeErrorMessage('');
        }

        if (waterCost || electricityCost || heatingCost) {
            if (!waterCost || !isNumeric(waterCost) ||  waterCost < 1) {
                setWaterCostError(true);
                setWaterCostErrorMessage('The water cost must be a positive number.');
                isValid = false;
            } else {
                setWaterCostError(false);
                setWaterCostErrorMessage('');
            }

            if (!electricityCost || !isNumeric(electricityCost) || electricityCost < 1) {
                setElectricityCostError(true);
                setElectricityCostErrorMessage('The electricity cost must be a positive number.');
                isValid = false;
            } else {
                setElectricityCostError(false);
                setElectricityCostErrorMessage('');
            }

            if (!heatingCost || !isNumeric(heatingCost) || heatingCost < 1) {
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
        
        // Validate inputs
        if (!validateInputs()) {
            return;
        }
        
        // Check whether to include utilities or leave utilities as null
        const includeUtilities = waterCost && electricityCost && heatingCost;
        
        // Get property images
        const propertyImages = [];
        for (const img of [image1, image2, image3, image4, image5, image6, image7, image8, image9, image10]) {
            if (img) {
                propertyImages.push({ url: img });
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
            title: title,
            description: description,
            propertyType: propertyType,
            bedrooms: bedrooms,
            bathrooms: bathrooms,
            monthlyPrice: price,
            squareFootage: squareFootage,
            wheelchairAccessible: wheelchairAccessible,
            smokingAllowed: smokingAllowed,
            address: {
                apartment: apartmentNumber,
                streetNumber: streetNumber,
                street: street,
                city: city,
                postalCode: postalCode
            },
            amenitiesOffered: {
                gym: gym,
                laundry: laundry,
                petsAllowed: petsAllowed,
                parking: parking,
                internetIncluded: internetIncluded
            },
            utilitiesCosts: includeUtilities ? {
                waterCost: waterCost,
                electricityCost: electricityCost,
                heatingCost: heatingCost
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
            setServerErrorMessage(errorMessage ? errorMessage : 'An error occurred. Please try again.');
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
                                    value={title}
                                    onChange={(e) => setTitle(e.target.value)}
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
                                    value={description}
                                    onChange={(e) => setDescription(e.target.value)}
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
                                    onChange={(e) => setPropertyType(e.target.value)}
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
                                    value={bedrooms}
                                    onChange={(e) => setBedrooms(e.target.value)}
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
                                    value={bathrooms}
                                    onChange={(e) => setBathrooms(e.target.value)}
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
                                    value={price}
                                    onChange={(e) => setPrice(e.target.value)}
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
                                    value={squareFootage}
                                    onChange={(e) => setSquareFootage(e.target.value)}
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
                                    // border: "1px solid #000",
                                    padding: "2em",
                                    maxwidth: "500px",
                                }}
                            >
                                <FormControlLabel control={<Checkbox id="wheelchair-accessible" checked={wheelchairAccessible} onChange={(e) => setWheelchairAccessible(e.target.checked)}/>} label="Wheelchair Accessible" />
                                <FormControlLabel control={<Checkbox id="smoking-allowed" checked={smokingAllowed} onChange={(e) => setSmokingAllowed(e.target.checked)}/>} label="Smoking Allowed" />
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
                                    value={apartmentNumber}
                                    onChange={(e) => setApartmentNumber(e.target.value)}
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
                                    value={streetNumber}
                                    onChange={(e) => setStreetNumber(e.target.value)}
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
                                    value={street}
                                    onChange={(e) => setStreet(e.target.value)}
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
                                    value={city}
                                    onChange={(e) => setCity(e.target.value)}
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
                                    value={postalCode}
                                    onChange={(e) => setPostalCode(e.target.value)}
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
                            <FormControlLabel control={<Checkbox id="gym" checked={gym} onChange={(e) => setGym(e.target.checked)} />} label="Gym" />
                            <FormControlLabel control={<Checkbox id="laundry" checked={laundry} onChange={(e) => setLaundry(e.target.checked)}/>} label="Laundry" />
                            <FormControlLabel control={<Checkbox id="pets-allowed" checked={petsAllowed} onChange={(e) => setPetsAllowed(e.target.checked)}/>} label="Pets Allowed" />
                            <FormControlLabel control={<Checkbox id="parking" checked={parking} onChange={(e) => setParking(e.target.checked)}/>} label="Parking" />
                            <FormControlLabel control={<Checkbox id="internet-included" checked={internetIncluded} onChange={(e) => setInternetIncluded(e.target.checked)}/>} label="Internet Included" />
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
                                    value={waterCost}
                                    onChange={(e) => setWaterCost(e.target.value)}
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
                                    value={electricityCost}
                                    onChange={(e) => setElectricityCost(e.target.value)}
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
                                    value={heatingCost}
                                    onChange={(e) => setHeatingCost(e.target.value)}
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
                                    value={image1}
                                    onChange={(e) => setImage1(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-2">Image 2</FormLabel>
                                <TextField
                                    name="image-2"
                                    fullWidth
                                    id="image-2"
                                    value={image2}
                                    onChange={(e) => setImage2(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-3">Image 3</FormLabel>
                                <TextField
                                    name="image-3"
                                    fullWidth
                                    id="image-3"
                                    value={image3}
                                    onChange={(e) => setImage3(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-4">Image 4</FormLabel>
                                <TextField
                                    name="image-4"
                                    fullWidth
                                    id="image-4"
                                    value={image4}
                                    onChange={(e) => setImage4(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-5">Image 5</FormLabel>
                                <TextField
                                    name="image-5"
                                    fullWidth
                                    id="image-5"
                                    value={image5}
                                    onChange={(e) => setImage5(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-6">Image 6</FormLabel>
                                <TextField
                                    name="image-6"
                                    fullWidth
                                    id="image-6"
                                    value={image6}
                                    onChange={(e) => setImage6(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-7">Image 7</FormLabel>
                                <TextField
                                    name="image-7"
                                    fullWidth
                                    id="image-7"
                                    value={image7}
                                    onChange={(e) => setImage7(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-8">Image 8</FormLabel>
                                <TextField
                                    name="image-8"
                                    fullWidth
                                    id="image-8"
                                    value={image8}
                                    onChange={(e) => setImage8(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-9">Image 9</FormLabel>
                                <TextField
                                    name="image-9"
                                    fullWidth
                                    id="image-9"
                                    value={image9}
                                    onChange={(e) => setImage9(e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-10">Image 10</FormLabel>
                                <TextField
                                    name="image-10"
                                    fullWidth
                                    id="image-10"
                                    value={image10}
                                    onChange={(e) => setImage10(e.target.value)}
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