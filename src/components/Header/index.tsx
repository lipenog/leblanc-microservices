import styles from './Header.module.css'
import { GiCoffeeCup } from "react-icons/gi";
import { CiSearch } from "react-icons/ci";
import Input from '../Input/Input';

import { useNavigate } from 'react-router-dom';

function Header() {
    const navigate = useNavigate();

    const navigateToHome = () => {
        navigate('/');
        window.scrollTo(0, 0);
    }

    const navigateAfterSearch = () => {
        
    }

    return (
        <header className={styles.header}>
            <div className={styles.home} onClick={navigateToHome}>
                <h1>Leblanc</h1>
                <GiCoffeeCup size={35}/>
            </div>            
            <div className={styles.searchBar}>
                <Input placeholder='search' icon={CiSearch}/>
            </div>
        </header>    
    ) 
};

export default Header;