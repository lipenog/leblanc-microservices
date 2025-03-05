import { GiCoffeeCup } from 'react-icons/gi';
import styles from './LoginBox.module.css'

function LoginBox() {
    return ( 
        <>
        <div className={styles.loginBackground}>
            <div className={styles.loginBox}>
                <div className={styles.loginHeader}>
                    <h1>Leblanc</h1>
                    <GiCoffeeCup size={35}/>
                </div>     
                <div className={styles.loginInput}>
                    <input placeholder='username' className={styles.usernameInput}/>
                    <input placeholder='password' className={styles.passwordInput}/>
                </div>      
                <div className={styles.loginFooter}>
                    <span>new to Leblanc?&nbsp;</span>                
                    <span className={styles.createAccount}>create an account.</span>
                </div>  
            </div>
        </div>
        </>
    );
}

export default LoginBox;