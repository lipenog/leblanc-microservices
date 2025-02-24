import styles from './Header.module.css'
import { GiCoffeeCup } from "react-icons/gi";
import { useNavigate } from 'react-router-dom';
import SearchInput from '../Input/Input';

function Header() {
    const navigate = useNavigate();

    const navigateToHome = () => {
        navigate('/');
        window.scrollTo(0, 0);
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
        </header>    
    ) 
};

export default Header;