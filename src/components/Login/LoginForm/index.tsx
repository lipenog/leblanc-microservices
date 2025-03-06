import styles from '../Login.module.css'
import PasswordInput from "../PasswordInput";

interface Props {
    setTrigger: () => void
}

function LoginForm({setTrigger} : Props) {
    return (     
        <>
            <div className={styles.inputBox}>
                <input placeholder='username' type='input' className={styles.normalInput}/>
                <PasswordInput placeholder='password'/>                
            </div>      
            <div className={styles.actionButton}>
                <button className={styles.actionButtonClick}>Login</button>
            </div>
            <div className={styles.footer}>
                <span>new to Leblanc?&nbsp;</span>                
                <span className={styles.footerButton} onClick={setTrigger}>create an account.</span>
            </div>  
        </>
    );
}

export default LoginForm;