import styles from './Header.module.css'
import { GiCoffeeCup } from "react-icons/gi";

function Header() {
    return (
        <header className={styles.header}>
            <div className={styles.home}>
                <h1>Leblanc</h1>
                <GiCoffeeCup/>
            </div>
            <button>user</button>
        </header>    
    ) 
};

export default Header;