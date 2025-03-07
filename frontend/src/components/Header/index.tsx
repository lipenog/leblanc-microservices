import styles from './Header.module.css'
import { GiCoffeeCup } from "react-icons/gi";
import { useNavigate } from 'react-router-dom';
import SearchInput from '../Input/Input';
import { IoExitOutline } from "react-icons/io5";
import { useCookies } from 'react-cookie';

function Header() {
    const navigate = useNavigate();
    const [cookies, setCookie, removeCookie] = useCookies(['bearer', 'loggedUser']);

    const navigateToHome = () => {
        navigate('/');
        window.scrollTo(0, 0);
    }

    const handleLogout = () => {
        // delete all cookies related to login
        removeCookie('bearer');
        removeCookie('loggedUser');
        // navigate to /login
        navigate('/login');
    }

    return (
        <header className={styles.header}>
            <div className={styles.home} onClick={navigateToHome}>
                <h1>Leblanc</h1>
                <GiCoffeeCup size={35}/>
            </div>            
            <div className={styles.searchBar}>
                <SearchInput/>
            </div>
            <div className={styles.exitButton}>
                <IoExitOutline size={35} onClick={handleLogout}/>
            </div>
        </header>    
    ) 
};

export default Header;