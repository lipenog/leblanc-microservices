import { FaRegEye } from "react-icons/fa";
import { FaRegEyeSlash } from "react-icons/fa";
import styles from './LoginForm.module.css'
import { useState } from 'react';

interface Props {
    setTrigger: () => void
}

function LoginForm({setTrigger} : Props) {
    const [togglePasswordView, setTogglePasswordView] = useState(false);

    const iconClick = () => {
        setTogglePasswordView(!togglePasswordView);
    }

    return (     
        <>
            <div className={styles.loginInput}>
                <input placeholder='username' type='input' className={styles.usernameInput}/>
                <div className={styles.passwordInput}>
                    <input placeholder='password' type={togglePasswordView ? 'input' : 'password'}/>
                    {togglePasswordView ? <FaRegEye size={20} onClick={iconClick}/> : <FaRegEyeSlash size={20} onClick={iconClick}/>}
                </div>                    
            </div>      
            <div className={styles.loginButton}>
                <button className={styles.loginButtonClick}>Login</button>
            </div>
            <div className={styles.loginFooter}>
                <span>new to Leblanc?&nbsp;</span>                
                <span className={styles.createAccount} onClick={setTrigger}>create an account.</span>
            </div>  
        </>
    );
}

export default LoginForm;