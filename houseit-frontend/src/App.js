import './App.css';
import SignUp from './components/sign-up/SignUp';
import ImageUpload from './components/image-upload/ImageUpload';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import LandingPage from "./components/landing-page/LandingPage";
import CreateListing from "./components/create-listing/CreateListing";
import UpdateListing from "./components/update-listing/UpdateListing";

console.log(SignUp); // Should be a function
console.log(ImageUpload); // Should be a function

function App() {
  return (

      <Router>
          <Routes>
              <Route path="/" element={<LandingPage />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/createlisting" element={<CreateListing />} />
          </Routes>
      </Router>
  );
}

export default App;
