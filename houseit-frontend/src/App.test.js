import { render, screen, fireEvent } from '@testing-library/react';
import { act } from 'react';
import SignUp from './components/sign-up/SignUp';
import LandingPage from './components/landing-page/LandingPage';
import { MemoryRouter, Route, Routes } from 'react-router-dom';
import CreateListing from "./components/create-listing/CreateListing";

// Suppress specific console.error warnings
beforeAll(() => {
  jest.spyOn(console, 'error').mockImplementation((message) => {
    if (message.includes('ReactDOMTestUtils.act is deprecated')) {
      return; // Ignore this specific warning
    }
    console.error(message); // Log other errors as usual
  });
});

afterAll(() => {
  console.error.mockRestore(); // Restore original console.error after tests
});

test('renders sign in form', () => {
  render(<SignUp />);

  // Check if email and password fields, and sign-in button are present
  const emailField = screen.getByLabelText(/email/i);
  const passwordField = screen.getByLabelText(/password/i);
  const signInButton = screen.getByRole('button', { name: /sign up/i });

  expect(emailField).toBeInTheDocument();
  expect(passwordField).toBeInTheDocument();
  expect(signInButton).toBeInTheDocument();
});

test('submits form with invalid data', () => {
  render(<SignUp />);

  act(() => {
    // Attempt to submit form with empty fields
    fireEvent.click(screen.getByRole('button', { name: /sign up/i }));
  });

  act(() => {
    // Enter invalid email format and a short password
    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'invalid-email' } });
    fireEvent.change(screen.getByLabelText(/password/i), { target: { value: '123' } });
  });

  act(() => {
    // Click sign-in button
    fireEvent.click(screen.getByRole('button', { name: /sign up/i }));
  });

  // Assert that the specific error messages are displayed for invalid email and password
  expect(screen.getByText(/please enter a valid email address/i)).toBeInTheDocument();
  expect(screen.getByText(/password must be at least 6 characters/i)).toBeInTheDocument();
});

test('navigates to Sign Up page when clicking "Sign Up Now" button', () => {
  render(
      <MemoryRouter initialEntries={['/']}>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/signup" element={<SignUp />} />
        </Routes>
      </MemoryRouter>
  );

  act(() => {
    // Find and click the "Sign Up Now" button
    const signUpNowButton = screen.getByRole('button', { name: /sign up now/i });
    fireEvent.click(signUpNowButton);
  });

  // Assert that the SignUpPage component is displayed
  expect(screen.getByText(/username/i)).toBeInTheDocument();
});

test('navigates to Create Listing page when clicking "Create Listing" button', () => {
  render(
      <MemoryRouter initialEntries={['/']}>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/createlisting" element={<CreateListing />} />
        </Routes>
      </MemoryRouter>
  );

  act(() => {
    // Find and click the "Create Listing" button
    const createListingButton = screen.getByRole('button', { name: /create listing/i });
    fireEvent.click(createListingButton);
  });

  // Assert that the CreateListing component is displayed
  expect(screen.getByText(/create a listing/i)).toBeInTheDocument();
});

test('renders create listing form', () => {
  render(<CreateListing />);

  // Check that all required fields are present
  const titleField = screen.getByLabelText(/title/i);
  const descriptionField = screen.getByLabelText(/description/i);
  const priceField = screen.getByLabelText(/price/i);
  const submitButton = screen.getByRole('button', { name: /create listing/i });

  expect(titleField).toBeInTheDocument();
  expect(descriptionField).toBeInTheDocument();
  expect(priceField).toBeInTheDocument();
  expect(submitButton).toBeInTheDocument();
});

test('submits form with invalid data', () => {
  render(<CreateListing />);

  act(() => {
    // Attempt to submit form without filling in required fields
    fireEvent.click(screen.getByRole('button', { name: /create listing/i }));
  });

  act(() => {
    // Enter an invalid price (e.g., a non-numeric value or negative number)
    fireEvent.change(screen.getByLabelText(/title/i), { target: { value: 'Test Listing' } });
    fireEvent.change(screen.getByLabelText(/description/i), { target: { value: 'This is a test listing.' } });
    fireEvent.change(screen.getByLabelText(/price/i), { target: { value: '-50' } });
  });

  act(() => {
    // Submit the form again
    fireEvent.click(screen.getByRole('button', { name: /create listing/i }));
  });

  // Assert that the correct error message appears for invalid price
  expect(screen.getByText(/The minimum price must be at least 1/i)).toBeInTheDocument();
});
