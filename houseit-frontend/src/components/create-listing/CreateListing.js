import React, { useEffect , useRef, useState } from 'react';
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
    InputAdornment
} from '@mui/material';
import { styled } from '@mui/material/styles';
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import { useNavigate } from 'react-router-dom';
import Navbar from '../navbar/Navbar';
import StatusDialog from '../../components/status-dialog/StatusDialog';
import axios from 'axios';

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

export default function CreateListing() {
    const emptyForm = {
        title: '',
        description: '',
        propertyType: 'APARTMENT',
        bedrooms: '',
        bathrooms: '',
        price: '',
        squareFootage: '',
        wheelchairAccessible: false,
        smokingAllowed: false,
        address: {
            apartment: '',
            streetNumber: '',
            street: '',
            city: '',
            postalCode: '',
        },
        amenities: {
            gym: false,
            laundry: false,
            petsAllowed: false,
            parking: false,
            internetIncluded: false,
        },
        utilities: {
            waterCost: '',
            electricityCost: '',
            heatingCost: '',
        },
        images: [
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        ]
    }
    const [formData, setFormData] = useState(emptyForm);
    const [errors, setErrors] = useState({});

    const navigate = useNavigate();
    useEffect(() => {
        const checkAuth = () => {
            const user = JSON.parse(localStorage.getItem('currentUser'));
            if (!user || user.accountType !== 'landlord') {
                // Redirect to home page if not authenticated as landlord
                navigate('/');
            }
        };

        checkAuth();
    }, [navigate])

    const handleInputChange = (e, integerInput=false) => {
        const { id, value, type, checked } = e.target;
        // console.log(e.target);
        // console.log(id, value);

        // If it needs to be an integer and it is one
        // Or if it doesn't need to be an integer
        if ((integerInput && /^\d*$/.test(value)) || (!integerInput)) {
            setFormData((prevState) => ({
                ...prevState,
                [id]: type === 'checkbox' ? checked : value,
            }));
            return;
        }
    };

    const handleNestedChange = (e, group, integerInput=false) => {
        const { id, value, type, checked } = e.target;
        // console.log(e.target);
        // console.log(id, value);
        if ((integerInput && /^\d*$/.test(value)) || (!integerInput)) {
            setFormData((prevState) => ({
                ...prevState,
                [group]: {
                    ...prevState[group],
                    [id]: type === 'checkbox' ? checked : value,
                },
            }));
        }
    };

    // For some unknown, evil, malevolently orchestrated reason,
    // Select objects from mui send the obsolete name property as event identifiers
    // instead of the regular id.
    const handlePropertyChange = (e) => {
        const { name, value } = e.target;

        setFormData((prevState) => ({
            ...prevState,
            [name]: value,
        }));
        return;
    }

    const handleImageChange = (index, value) => {
        setFormData((prevFormData) => {
            const updatedImages = [...prevFormData.images];
            updatedImages[index] = value;
            return {
                ...prevFormData,
                images: updatedImages,
            };
        });
    };



    const formRef = useRef(null);


    const handleDialogClose = () => {
        setOpenDialog(false);
    };

    const [serverErrorMessage, setServerErrorMessage] = React.useState('');
    const [serverSuccessMessage, setServerSuccessMessage] = React.useState('');

    const [openDialog, setOpenDialog] = React.useState(false); // State for dialog visibility
    const [dialogMessage, setDialogMessage] = React.useState(''); // Message to display in the dialog
    const [dialogSeverity, setDialogSeverity] = React.useState('error'); // Severity of the message: 'success' or 'error'

    const validateInputs = () => {
        const newErrors = {};
        if (!formData.title) newErrors.title = 'Title is required.';
        if (!formData.description || formData.description.length > 256)
            newErrors.description = 'Description must be between 1 and 256 characters.';
        if (!formData.bedrooms || isNaN(formData.bedrooms) || formData.bedrooms < 1)
            newErrors.bedrooms = 'There must be at least 1 bedroom.';
        if (!formData.bathrooms || isNaN(formData.bathrooms) || formData.bathrooms < 1)
            newErrors.bathrooms = 'There must be at least 1 bathroom.';
        if (!formData.price || isNaN(formData.price) || formData.price < 1)
            newErrors.price = 'Price must be at least $1/month.';
        if (!formData.squareFootage || isNaN(formData.squareFootage) || formData.squareFootage < 1)
            newErrors.squareFootage = 'Square footage must be at least 1 ft<sup>2</sup>.';
        if (!formData.address.apartment) newErrors.apartment = 'The address must have an apartment number.';
        if (!formData.address.streetNumber || isNaN(formData.address.streetNumber) || formData.address.streetNumber < 1)
            newErrors.streetNumber = 'Address must have a street number greater than 1.';
        if (!formData.address.street || formData.address.street.length > 256)
            newErrors.street = 'Street name must be between 1 and 256 characters.';
        if (!formData.address.city || formData.address.city.length > 256)
            newErrors.city = 'City name must be between 1 and 256 characters.';
        if (!formData.address.postalCode || formData.address.postalCode.length !== 6)
            newErrors.postalCode = 'Postal code must be exactly 6 characters.';
        // If at least one of the utilities exists
        if (formData.utilities.waterCost !== "" || formData.utilities.electricityCost !== "" || formData.utilities.heatingCost !== "")
        {
            // But not all of them at the same time
            if (!(!!formData.utilities.waterCost && !!formData.utilities.electricityCost && !!formData.utilities.heatingCost))
            {
                // Set the remaining utilities to 0
                if (!formData.utilities.waterCost) {
                    formData.utilities.waterCost = "0";
                }
                if (!formData.utilities.electricityCost) {
                    formData.utilities.electricityCost = "0";
                }
                if (!formData.utilities.heatingCost) {
                    formData.utilities.heatingCost = "0";
                }
            }
            // Now check if any are negative
            if (formData.utilities.waterCost < 0)
                newErrors.waterCost = 'The water cost cannot be a negative number.';
            if (formData.utilities.electricityCost < 0)
                newErrors.waterCost = 'The electricity cost cannot be a negative number.';
            if (formData.utilities.heatingCost < 0)
                newErrors.waterCost = 'The heating cost cannot be a negative number.';
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Clear previous error messages
        setServerErrorMessage('');
        setServerSuccessMessage('');

        if (!formData.address.apartment && formData.propertyType === "HOUSE") {
            formData.address.apartment = "N/A";
        }
        // Validate inputs
        if (!validateInputs()) {
            setDialogMessage('Please fill in all required fields.');
            setDialogSeverity('error');
            setOpenDialog(true);
            return;
        }

        // Check whether to include utilities or leave utilities as null
        const includeUtilities = formData.utilities.waterCost && formData.utilities.electricityCost && formData.utilities.heatingCost

        // Get property images
        const propertyImages = [];
        for (let i = 0; i < 10; i++) {
            const img = formData.images[i];
            if (img) {
                propertyImages.push({url : img});
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
            title: formData.title,
            description: formData.description,
            propertyType: formData.propertyType,
            bedrooms: parseInt(formData.bedrooms),
            bathrooms: parseInt(formData.bathrooms),
            monthlyPrice: parseInt(formData.price),
            squareFootage: parseInt(formData.squareFootage),
            wheelchairAccessible: formData.wheelchairAccessible,
            smokingAllowed: formData.smokingAllowed,
            address: {
                apartmentNumber: formData.address.apartment,
                streetNumber: formData.address.streetNumber,
                street: formData.address.street,
                city: formData.address.city,
                postalCode: formData.address.postalCode,
            },
            amenitiesOffered: {
                gym: formData.amenities.gym,
                laundry: formData.amenities.laundry,
                petsAllowed: formData.amenities.petsAllowed,
                parking: formData.amenities.parking,
                internetIncluded: formData.amenities.internetIncluded,
            },
            utilitiesCosts: includeUtilities ?
                {
                    waterCost: parseFloat(formData.utilities.waterCost),
                    electricityCost: parseFloat(formData.utilities.electricityCost),
                    heatingCost: parseFloat(formData.utilities.heatingCost),
                } : null,
            propertyImages: propertyImages
        };
        // console.log(payload);

        // Send request and handle response
        try {
            const axiosClient = axios.create({
                baseURL: "http://localhost:8080",
            });
            const response = await axiosClient.post('/listing', payload);
            // console.log('Listing created successfully:', response.data);

            // Changed from using server error message to instead use dialog message
            setDialogMessage('Listing created successfully.');
            setDialogSeverity('success');
            setOpenDialog(true);

            // Clear form
            // setFormData(emptyForm) //TODO : uncomment
        } catch (error) {
            let errorMessage = null
            if (error.response && typeof error.response.data === 'string') {
                errorMessage = error.response.data
            }
            setServerErrorMessage(errorMessage ? errorMessage : 'An error occurred during listing creation. Please try again.');
        }
    };



    return (
        <AppTheme>
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
                            ref={formRef} // Assign the ref to the form element
                            onSubmit={handleSubmit}
                            sx={{ display: 'flex', flexDirection: 'column', gap: 2, }} // Adjusted marginBottom
                        >
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="title">Title</FormLabel>
                                <TextField
                                    fullWidth
                                    id="title"
                                    placeholder="Title"
                                    value={formData.title}
                                    onChange={handleInputChange}
                                    error={!!errors.title}
                                    helperText={errors.title}
                                    color={!!errors.title ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="description">Description</FormLabel>
                                <TextField
                                    fullWidth
                                    id="description"
                                    placeholder="Enter description here"
                                    autoComplete="Description"
                                    variant="outlined"
                                    value={formData.description}
                                    onChange={handleInputChange}
                                    error={!!errors.description}
                                    helperText={errors.description}
                                    color={!!errors.description ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="propertyType">Property Type</FormLabel>
                                <Select
                                    id="propertyType"
                                    name="propertyType"
                                    value={formData.propertyType}
                                    onChange={(e) => handlePropertyChange(e)}
                                >
                                    <MenuItem value={"STUDIO"}>Studio</MenuItem>
                                    <MenuItem value={"DORM"}>Dorm</MenuItem>
                                    <MenuItem value={"CONDO"}>Condo</MenuItem>
                                    <MenuItem value={"APARTMENT"}>Apartment</MenuItem>
                                    <MenuItem value={"HOUSE"}>House</MenuItem>
                                </Select>
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="bedrooms">Bedrooms</FormLabel>
                                <TextField
                                    fullWidth
                                    placeholder="Number of Bedrooms"
                                    /* type="number"
                                    This type allows - + and . to not trigger onChange which is problematic
                                    Just pass true to integerInput in handleInputChange*/
                                    id="bedrooms"
                                    variant="outlined"
                                    value={formData.bedrooms}
                                    onChange={(e) => handleInputChange(e, true)}
                                    error={!!errors.bedrooms}
                                    helperText={errors.bedrooms}
                                    color={!!errors.bedrooms ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="bathrooms">Bathrooms</FormLabel>
                                <TextField
                                    fullWidth
                                    placeholder="Number of Bathrooms"
                                    id="bathrooms"
                                    variant="outlined"
                                    value={formData.bathrooms}
                                    onChange={(e) => handleInputChange(e, true)}
                                    error={!!errors.bathrooms}
                                    helperText={errors.bathrooms}
                                    color={!!errors.bathrooms ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="price">Price</FormLabel>
                                <TextField
                                    fullWidth
                                    placeholder="Monthly Price"
                                    id="price"
                                    variant="outlined"
                                    value={formData.price}
                                    onChange={(e) => handleInputChange(e, true)}
                                    error={!!errors.price}
                                    helperText={errors.price}
                                    color={!!errors.price ? 'error' : 'primary'}
                                    slotProps= {{
                                        input: {
                                            endAdornment: (
                                                <InputAdornment position="end">
                                                    $
                                                </InputAdornment>
                                            )
                                        }
                                    }}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="squareFootage">Square Footage</FormLabel>
                                <TextField
                                    fullWidth
                                    placeholder="Square Footage (ft²)"
                                    id="squareFootage"
                                    autoComplete="SquareFootage"
                                    variant="outlined"
                                    value={formData.squareFootage}
                                    onChange={(e) => handleInputChange(e, true)}
                                    error={!!errors.squareFootage}
                                    helperText={errors.squareFootage}
                                    color={!!errors.squareFootage ? 'error' : 'primary'}
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
                                <FormControlLabel
                                    control={<Checkbox id="wheelchairAccessible" onChange={(e) => handleInputChange(e)}/>}
                                    label="Wheelchair Accessible"
                                />
                                <FormControlLabel
                                    control={<Checkbox id="smokingAllowed" onChange={(e) => handleInputChange(e)}/>}
                                    label="Smoking Allowed"
                                />
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
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="ApartmentNumber">Apartment Number</FormLabel>
                                <TextField
                                    fullWidth
                                    id="apartment"
                                    placeholder="Apartment Number"
                                    value={formData.address.apartment}
                                    onChange={(e) => handleNestedChange(e, 'address')}
                                    error={!!errors.apartment}
                                    helperText={errors.apartment}
                                    color={!!errors.apartment ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="StreetNumber">Street Number</FormLabel>
                                <TextField
                                    fullWidth
                                    id="streetNumber"
                                    placeholder="Street Number"
                                    value={formData.address.streetNumber}
                                    onChange={(e) => handleNestedChange(e, 'address', true)}
                                    error={!!errors.streetNumber}
                                    helperText={errors.streetNumber}
                                    color={!!errors.streetNumber ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="street">Street</FormLabel>
                                <TextField
                                    fullWidth
                                    id="street"
                                    placeholder="Street"
                                    value={formData.address.street}
                                    onChange={(e) => handleNestedChange(e, 'address')}
                                    error={!!errors.street}
                                    helperText={errors.street}
                                    color={!!errors.street ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="City">City</FormLabel>
                                <TextField
                                    fullWidth
                                    id="city"
                                    placeholder="City"
                                    value={formData.address.city}
                                    onChange={(e) => handleNestedChange(e, 'address')}
                                    error={!!errors.city}
                                    helperText={errors.city}
                                    color={!!errors.city ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl required fullWidth>
                                <FormLabel htmlFor="postalCode">Postal Code</FormLabel>
                                <TextField
                                    fullWidth
                                    id="postalCode"
                                    placeholder="Postal Code"
                                    value={formData.address.postalCode}
                                    onChange={(e) => handleNestedChange(e, 'address')}
                                    error={!!errors.postalCode}
                                    helperText={errors.postalCode}
                                    color={!!errors.postalCode ? 'error' : 'primary'}
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
                            <FormControlLabel
                                control={<Checkbox id="gym" onChange={(e) => handleNestedChange(e, "amenities")} />}
                                label="Gym"
                            />
                            <FormControlLabel
                                control={<Checkbox id="laundry" onChange={(e) => handleNestedChange(e, "amenities")} />}
                                label="Laundry"
                            />
                            <FormControlLabel
                                control={<Checkbox id="petsAllowed" onChange={(e) => handleNestedChange(e, "amenities")} />}
                                label="Pets Allowed"
                            />
                            <FormControlLabel
                                control={<Checkbox id="parking" onChange={(e) => handleNestedChange(e, "amenities")} />}
                                label="Parking"
                            />
                            <FormControlLabel
                                control={<Checkbox id="internetIncluded" onChange={(e) => handleNestedChange(e, "amenities")} />}
                                label="Internet Included"
                            />
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
                            {/* Utilities can actually be floats so using type="number" here is fine */}
                            <FormControl fullWidth>
                                <FormLabel htmlFor="waterCost">Water Cost</FormLabel>
                                <TextField
                                    fullWidth
                                    id="waterCost"
                                    type="number"
                                    value={formData.utilities.waterCost}
                                    onChange={(e) => handleNestedChange(e, 'utilities')}
                                    error={!!errors.waterCost}
                                    helperText={errors.waterCost}
                                    color={!!errors.waterCost ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="electricityCost">Electricity Cost</FormLabel>
                                <TextField
                                    fullWidth
                                    id="electricityCost"
                                    type="number"
                                    value={formData.utilities.electricityCost}
                                    onChange={(e) => handleNestedChange(e, 'utilities')}
                                    error={!!errors.electricityCost}
                                    helperText={errors.electricityCost}
                                    color={!!errors.electricityCost ? 'error' : 'primary'}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="heatingCost">Heating Cost</FormLabel>
                                <TextField
                                    fullWidth
                                    id="heatingCost"
                                    type="number"
                                    value={formData.utilities.heatingCost}
                                    onChange={(e) => handleNestedChange(e, 'utilities')}
                                    error={!!errors.heatingCost}
                                    helperText={errors.heatingCost}
                                    color={!!errors.heatingCost ? 'error' : 'primary'}
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
                                    fullWidth
                                    id="image-1"
                                    value={formData.images[0]}
                                    onChange={(e) => handleImageChange(0, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-2">Image 2</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-2"
                                    value={formData.images[1]}
                                    onChange={(e) => handleImageChange(1, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-3">Image 3</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-3"
                                    value={formData.images[2]}
                                    onChange={(e) => handleImageChange(2, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-4">Image 4</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-4"
                                    value={formData.images[3]}
                                    onChange={(e) => handleImageChange(3, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-5">Image 5</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-5"
                                    value={formData.images[4]}
                                    onChange={(e) => handleImageChange(4, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-6">Image 6</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-6"
                                    value={formData.images[5]}
                                    onChange={(e) => handleImageChange(5, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-7">Image 7</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-7"
                                    value={formData.images[6]}
                                    onChange={(e) => handleImageChange(6, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-8">Image 8</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-8"
                                    value={formData.images[7]}
                                    onChange={(e) => handleImageChange(7, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-9">Image 9</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-9"
                                    value={formData.images[8]}
                                    onChange={(e) => handleImageChange(8, e.target.value)}
                                />
                            </FormControl>
                            <FormControl fullWidth>
                                <FormLabel htmlFor="image-10">Image 10</FormLabel>
                                <TextField
                                    fullWidth
                                    id="image-10"
                                    value={formData.images[9]}
                                    onChange={(e) => handleImageChange(9, e.target.value)}
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