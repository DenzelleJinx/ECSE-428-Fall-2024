import * as React from 'react';
import { Container, Typography, Button, Box, AppBar, Toolbar } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import FormLabel from '@mui/material/FormLabel';
import FormControl from '@mui/material/FormControl';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import MuiCard from '@mui/material/Card';
import { styled } from '@mui/material/styles';
import AppTheme from '../../shared-theme/AppTheme';
import ColorModeSelect from '../../shared-theme/ColorModeSelect';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import mcgillLogo from '../../assets/mcgill-logo.png';
import { useNavigate } from 'react-router-dom';
import Navbar from '../navbar/Navbar';
import axios from 'axios';


const Card = styled(MuiCard)(({ theme }) => ({
  display: 'flex',
  flexDirection: 'column',
  alignSelf: 'center',
  width: '100%',
  padding: theme.spacing(4),
  gap: theme.spacing(2),
  margin: 'auto',
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

const SignUpContainer = styled(Stack)(({ theme }) => ({
  height: 'calc((1 - var(--template-frame-height, 0)) * 100dvh)',
  minHeight: '100%',
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

export default function SignUp(props) {
  const primaryColor = "#D50032";
  const secondaryColor = "#FFFFFF";

  const [accountType, setAccountType] = React.useState('');
  const [phoneNumber, setPhoneNumber] = React.useState('');
  const [phoneNumberError, setPhoneNumberError] = React.useState(false);
  const [phoneNumberErrorMessage, setPhoneNumberErrorMessage] = React.useState('');
  const [emailError, setEmailError] = React.useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = React.useState('');
  const [passwordError, setPasswordError] = React.useState(false);
  const [passwordErrorMessage, setPasswordErrorMessage] = React.useState('');
  const [usernameError, setUsernameError] = React.useState(false);
  const [usernameErrorMessage, setUsernameErrorMessage] = React.useState('');
  const [accountTypeError, setAccountTypeError] = React.useState(false);
  const [accountTypeErrorMessage, setAccountTypeErrorMessage] = React.useState('');
  const [serverErrorMessage, setServerErrorMessage] = React.useState('');

  const handleAccountTypeChange = (event) => {
    setAccountType(event.target.value);
  };

  const validateInputs = () => {
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const username = document.getElementById('username');

    let isValid = true;

    if (!email || !email.value || !/\S+@\S+\.\S+/.test(email.value)) {
      setEmailError(true);
      setEmailErrorMessage('Please enter a valid email address.');
      isValid = false;
    } else {
      setEmailError(false);
      setEmailErrorMessage('');
    }

    if (!password || !password.value || password.value.length < 6) {
      setPasswordError(true);
      setPasswordErrorMessage('Password must be at least 6 characters long.');
      isValid = false;
    } else {
      setPasswordError(false);
      setPasswordErrorMessage('');
    }

    if (!username || !username.value || username.value.length < 1) {
      setUsernameError(true);
      setUsernameErrorMessage('Username is required.');
      isValid = false;
    } else {
      setUsernameError(false);
      setUsernameErrorMessage('');
    }

    if (!accountType || accountType.value === '') {
      setAccountTypeError(true);
      setAccountTypeErrorMessage('Account Type is required.');
      isValid = false;
    } else {
      setAccountTypeError(false);
      setAccountTypeErrorMessage('');
    }

    if (!accountType || (accountType.value === 'landlord' && (!phoneNumber || phoneNumber.length < 1))) {
      setPhoneNumberError(true);
      setPhoneNumberErrorMessage('Phone number is required for landlords.');
      isValid = false;
    } else {
      setPhoneNumberError(false);
      setPhoneNumberErrorMessage('');
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
      username: data.get('username'),
      email: data.get('email'),
      password: data.get('password'),
      accountType: accountType,
      ...(accountType === 'landlord' && { phoneNumber: phoneNumber })
    };

    try {
      const axiosClient = axios.create({
        baseURL: "http://localhost:8080",
      });
      const response = await axiosClient.post('/authentication/signup', payload);
      
      console.log('Signup successful:', response.data);
      setServerErrorMessage('');
    } catch (error) {
      setServerErrorMessage(error.response ? error.response.data.split(": ").slice(1).join(": ") : 'An error occurred during signup. Please try again.');
    }
};

return (
  <AppTheme {...props}>
    <CssBaseline enableColorScheme />
    <ColorModeSelect sx={{ position: 'fixed', top: '1rem', right: '1rem', marginTop: "4rem" }} />
    <Navbar />
    <SignUpContainer direction="column" justifyContent="space-between">
      <Card variant="outlined">
        <Typography
          component="h1"
          variant="h4"
          sx={{ width: '100%', fontSize: 'clamp(2rem, 10vw, 2.15rem)' }}
        >
          Sign up
        </Typography>
        <Box
          component="form"
          onSubmit={handleSubmit}
          sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}
        >
          <FormControl required fullWidth>
            <FormLabel htmlFor="username">Username</FormLabel>
            <TextField
              autoComplete="username"
              name="username"
              required
              fullWidth
              id="username"
              placeholder="username"
              error={usernameError}
              helperText={usernameErrorMessage}
              color={usernameError ? 'error' : 'primary'}
            />
          </FormControl>
          <FormControl required fullWidth>
            <FormLabel htmlFor="email">Email</FormLabel>
            <TextField
              required
              fullWidth
              id="email"
              placeholder="your@email.com"
              name="email"
              autoComplete="email"
              variant="outlined"
              error={emailError}
              helperText={emailErrorMessage}
              color={emailError ? 'error' : 'primary'}
            />
          </FormControl>
          <FormControl required fullWidth>
            <FormLabel htmlFor="password">Password</FormLabel>
            <TextField
              required
              fullWidth
              name="password"
              placeholder="••••••"
              type="password"
              id="password"
              autoComplete="new-password"
              variant="outlined"
              error={passwordError}
              helperText={passwordErrorMessage}
              color={passwordError ? 'error' : 'primary'}
            />
          </FormControl>
          <FormControl required fullWidth>
            <FormLabel htmlFor="account-type">Account Type</FormLabel>
            <Select
              id="account-type"
              value={accountType}
              error={accountTypeError}
              onChange={handleAccountTypeChange}
              color={accountTypeError ? 'error' : 'primary'}
            >
              <MenuItem value={"student"}>Student</MenuItem>
              <MenuItem value={"landlord"}>Landlord</MenuItem>
            </Select>
          </FormControl>
          {accountType === 'landlord' && (
            <FormControl required fullWidth>
              <FormLabel htmlFor="phone-number">Phone Number</FormLabel>
              <TextField
                fullWidth
                id="phone-number"
                name="phoneNumber"
                placeholder="Enter phone number"
                value={phoneNumber}
                onChange={(e) => setPhoneNumber(e.target.value)}
                error={phoneNumberError}
                helperText={phoneNumberErrorMessage}
              />
            </FormControl>
          )}
          {serverErrorMessage && (
            <Typography color="error" sx={{ textAlign: 'center', marginBottom: 1 }}>
              {serverErrorMessage}
            </Typography>
          )}
          <Button
            type="submit"
            fullWidth
            variant="contained"
            onClick={validateInputs}
          >
            Sign up
          </Button>
          <Typography sx={{ textAlign: 'center' }}>
            Already have an account?{' '}
            <Link href="/material-ui/getting-started/templates/sign-in/" variant="body2">
              Sign in
            </Link>
          </Typography>
        </Box>
      </Card>
    </SignUpContainer>
  </AppTheme>
)
}
