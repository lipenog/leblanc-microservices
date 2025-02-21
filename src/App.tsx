import Header from './components/Header'
import Posts from './components/Posts';
import CurrentUser from './components/User/CurrentUser';

function App() {
  return (
  <>
    <Header/>
    <Posts/>
    <CurrentUser user={{id: 1, identifier: '@gpv', name: 'piva', image: null}}/>
  </>
  );
}

export default App
