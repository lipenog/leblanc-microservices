import { useState } from 'react';
import styles from '../Login.module.css'
import PasswordInput from "../PasswordInput";
import { ILogin } from '../../../interfaces/Login/ILogin';

interface Props {
    setTrigger: () => void
}

function LoginForm({setTrigger} : Props) {
    const [loginInfo] = useState<ILogin>({username: '', password: ''});

    const setUser = (username : string) => {
        loginInfo.username = username;
    }

    const setPassword = (password : string) => {
        loginInfo.password = password;
    }

    return (     
        <>
            <div className={styles.inputBox}>
                <input placeholder='username' type='input' className={styles.normalInput} onChange={(e) => setUser(e.target.value)}/>
                <PasswordInput placeholder='password' value={loginInfo.password} onChange={setPassword}/>                
            </div>      
            <div className={styles.actionButton}>
                <button className={styles.actionButtonClick} onClick={() => console.log(loginInfo)}>Login</button>
            </div>
            <div className={styles.footer}>
                <span>new to Leblanc?&nbsp;</span>                
                <span className={styles.footerButton} onClick={setTrigger}>create an account.</span>
            </div>  
        </>
    );
}

export default LoginForm;