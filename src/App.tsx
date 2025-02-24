import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Pages/Home';
import Profile from './components/Pages/Profile';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/:userIdentifier" element={<Profile />} />
      </Routes>
    </Router>
  );
}

export default App
