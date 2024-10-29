import './App.css';
import SignUp from './components/sign-up/SignUp';
import ImageUpload from './components/image-upload/ImageUpload';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import LandingPage from "./components/landing-page/LandingPage";
console.log(SignUp); // Should be a function
console.log(ImageUpload); // Should be a function

function App() {
  return (

      <Router>
          <Routes>
              <Route path="/" element={<LandingPage />} />
              <Route path="/signup" element={<SignUp />} />
          </Routes>
      </Router>
      // <Router>
      //   <div>
      //     <h1>HouseIt</h1>
      //
      //     {/* Navigation Links */}
      //     <nav>
      //       <Link to="/listing">Create Listing</Link>
      //     </nav>
      //
      //     {/* Define Routes */}
      //     <Routes>
      //       <Route path="/" element={<SignUp />} />
      //       <Route path="/listing" element={<ImageUpload />} />
      //     </Routes>
      //   </div>
      // </Router>
  );
}

export default App;
