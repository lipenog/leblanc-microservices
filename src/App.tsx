import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Pages/Home';
import Profile from './components/Pages/Profile';
import SearchResult from './components/Pages/SearchResult';

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
