import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Pages/Home';
import Profile from './Pages/Profile';
import SearchResult from './Pages/SearchResult';
import LoginBox from './components/LoginBox';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/:userIdentifier" element={<Profile />} />
        <Route path="/search" element={<SearchResult/>} />
        <Route path="/login" element={<LoginBox/>} />
      </Routes>
    </Router>
  );
}

export default App
