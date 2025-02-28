import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Pages/Home';
import Profile from './Pages/Profile';
import SearchResult from './Pages/SearchResult';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/:userIdentifier" element={<Profile />} />
        <Route path="/search" element={<SearchResult/>} />
      </Routes>
    </Router>
  );
}

export default App
