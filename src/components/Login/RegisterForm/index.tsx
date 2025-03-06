import styles from '../Login.module.css'
import PasswordInput from '../PasswordInput';

interface Props {
    setTrigger : () => void
}

function RegisterForm({setTrigger} : Props) {
    return ( 
        <>  
            <div className={styles.inputBox}>
                <input placeholder='name' type='input' className={styles.normalInput}/>
                <input placeholder='username' type='input' className={styles.normalInput}/>
                <PasswordInput placeholder='password'/>    
                <PasswordInput placeholder='confirm password'/>                        
            </div>      
            <div className={styles.actionButton}>
                <button className={styles.actionButtonClick}>Register</button>
            </div>
            <div className={styles.footer}>
                <span>Already have an account?&nbsp;</span>                
                <span className={styles.footerButton} onClick={setTrigger}>Sign in.</span>
            </div>  
        </>
    );
}

export default RegisterForm;