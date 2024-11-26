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
import StatusDialog from '../status-dialog/StatusDialog';


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
  const navigate = useNavigate();

  const [passwordError, setPasswordError] = React.useState(false);
  const [passwordErrorMessage, setPasswordErrorMessage] = React.useState('');
  const [emailError, setEmailError] = React.useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = React.useState('');
  const [serverErrorMessage, setServerErrorMessage] = React.useState('');

  const [openDialog, setOpenDialog] = React.useState(false);
  const [dialogMessage, setDialogMessage] = React.useState('');
  const [dialogSeverity, setDialogSeverity] = React.useState('error');

  const validateInputs = () => {
    const email = document.getElementById('email');
    const password = document.getElementById('password');

    let isValid = true;

    if (!password || !password.value || password.value.length < 6) {
      setPasswordError(true);
      setPasswordErrorMessage('Password needs to be at least 6 characters.');
      isValid = false;
    } else {
      setPasswordError(false);
      setPasswordErrorMessage('');
    }

    if (!email || !email.value || email.value.length < 1) {
      setEmailError(true);
      setEmailErrorMessage('Email, email, or phone number is required.');
      isValid = false;
    } else {
      setEmailError(false);
      setEmailErrorMessage('');
    }

    return isValid;
  };

  const handleDialogClose = () => {
    setOpenDialog(false);
  };


  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!validateInputs()) {
      return;
    }

    const data = new FormData(event.currentTarget);
    const payload = {
      email: data.get('email'),
      password: data.get('password'),
    };

    try {
      const axiosClient = axios.create({
        baseURL: "http://localhost:8080",
      });
      const response = await axiosClient.post('/authentication/login', payload);
      
      console.log('login successful:', response.data);
      setServerErrorMessage('');
      const userResponse = await axiosClient.get('/users/' + data.get('email'), payload);
      let { password, ...userWithoutPassword } = userResponse.data;
      userWithoutPassword['ratings'] = {};
      localStorage.setItem('currentUser', JSON.stringify(userWithoutPassword));
      navigate('/');
    } catch (error) {
      if (error.response && error.response.data) {
        const errorMessage =
          typeof error.response.data === 'string'
            ? error.response.data.split(": ").slice(1).join(": ")
            : JSON.stringify(error.response.data); // Fallback for non-string responses
        
        setServerErrorMessage(errorMessage);
        setDialogMessage('Invalid credentials, please try again.');
        setDialogSeverity('error');
      } else {
        setServerErrorMessage('An error occurred during login. Please try again.');
        setDialogMessage('An error occurred while logging in.');
        setDialogSeverity('error');
      }
      setOpenDialog(true);  // Open the dialog to show the error message
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
          Login
        </Typography>
        <Box
          component="form"
          onSubmit={handleSubmit}
          sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}
        >
          <FormControl required fullWidth>
            <FormLabel htmlFor="email">Email</FormLabel>
            <TextField
              autoComplete="email"
              name="email"
              required
              fullWidth
              id="email"
              placeholder="email"
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
            Login
          </Button>
          <Typography sx={{ textAlign: 'center' }}>
            Don't have an account yet?{' '}
            <Link href="/signup" variant="body2">
              Sign up
            </Link>
          </Typography>
          <Typography sx={{ textAlign: 'center' }}>
            Forgotten your Password?{' '}
            <Link href="/resetpassword" variant="body2">
              Reset Password
            </Link>
          </Typography>
        </Box>
      </Card>
    </SignUpContainer>
    <StatusDialog
        open={openDialog}
        onClose={handleDialogClose}
        severity={dialogSeverity}
        title={dialogSeverity === 'success' ? 'Success' : 'An Error Occurred'}
        message={dialogMessage}
    />
  </AppTheme>
)
}
