import './App.css';
import SignUp from './components/sign-up/SignUp';
import SignIn from './components/sign-in/SignIn';
import ImageUpload from './components/image-upload/ImageUpload';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import LandingPage from "./components/landing-page/LandingPage";
import CreateListing from "./components/create-listing/CreateListing";
import ApproveLandlord from "./components/approve-landlord/ApproveLandlord";
import UpdateListing from "./components/update-listing/UpdateListing";
import ViewListings from "./components/view-listings/VIewListings";

console.log(SignUp); // Should be a function
console.log(ImageUpload); // Should be a function

function App() {
  return (

      <Router>
          <Routes>
              <Route path="/" element={<LandingPage />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/login" element={<SignIn />} />
              <Route path="/createlisting" element={<CreateListing />} />
              <Route path="/approvelandlord" element={<ApproveLandlord />} />
              <Route path="/viewlistings" element={<ViewListings />} />
              <Route path="/updatelisting" element={<UpdateListing />} /> //TODO remove
          </Routes>
      </Router>
  );
}

export default App;
