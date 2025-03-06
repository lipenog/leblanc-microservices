import { useState } from 'react';
import styles from '../Login.module.css'
import PasswordInput from '../PasswordInput';
import { IRegister } from '../../../interfaces/Login/IRegister';

interface Props {
    setTrigger : () => void
}

function RegisterForm({setTrigger} : Props) {
    const [registerInfo] = useState<IRegister>({name:'', username:'', password:'', confirm_password:''});

    const setName = (name : string) => registerInfo.name = name;
    const setUsername = (username : string) => registerInfo.username = username;
    const setPassword = (password : string) => registerInfo.password = password;
    const setConfirmPassword = (password : string) => registerInfo.confirm_password = password;

    // todo add password and confirm password validation after submit

    return ( 
        <>  
            <div className={styles.inputBox}>
                <input placeholder='name' type='input' className={styles.normalInput} onChange={(e) => setName(e.target.value)}/>
                <input placeholder='username' type='input' className={styles.normalInput} onChange={(e) => setUsername(e.target.value)}/>
                <PasswordInput placeholder='password' onChange={setPassword}/>    
                <PasswordInput placeholder='confirm password' onChange={setConfirmPassword}/>                        
            </div>      
            <div className={styles.actionButton}>
                <button className={styles.actionButtonClick} onClick={() => console.log(registerInfo)}>Register</button>
            </div>
            <div className={styles.footer}>
                <span>Already have an account?&nbsp;</span>                
                <span className={styles.footerButton} onClick={setTrigger}>Sign in.</span>
            </div>  
        </>
    );
}

export default RegisterForm;