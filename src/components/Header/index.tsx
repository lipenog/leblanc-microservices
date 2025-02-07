import styles from './Header.module.css'
import { GiCoffeeCup } from "react-icons/gi";
import { CiSearch } from "react-icons/ci";
import Input from '../Input/Input';

function Header() {
    return (
        <header className={styles.header}>
            <div className={styles.home} onClick={() => console.log('homes')}>
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