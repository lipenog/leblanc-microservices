import styles from './Header.module.css'
import { GiCoffeeCup } from "react-icons/gi";
import { CiSearch } from "react-icons/ci";
import { useState } from 'react';

function Header() {
    const [searchQuery, setSearchQuery] = useState('');

    return (
        <header className={styles.header}>
            <div className={styles.home} onClick={() => console.log('homes')}>
                <h1>Leblanc</h1>
                <GiCoffeeCup size={35}/>
            </div>            
            <div className={styles.searchBar}>
                <input placeholder='search' className={styles.searchBarInput}></input>    
                <CiSearch className={styles.searchIcon} size={30} onClick={() => console.log('search')}></CiSearch>
            </div>
        </header>    
    ) 
};

export default Header;