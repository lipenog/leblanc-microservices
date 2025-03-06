import { useState } from 'react';
import styles from '../Login.module.css'
import PasswordInput from '../PasswordInput';
import { IRegister } from '../../../interfaces/Login/IRegister';
import { handleRegister } from "../../../http/Login";

interface Props {
    setTrigger : () => void
}

function RegisterForm({setTrigger} : Props) {
    const [registerInfo] = useState<IRegister>({name:'', username:'', password:'', confirm_password:''});

    const setName = (name : string) => registerInfo.name = name;
    const setUsername = (username : string) => registerInfo.username = username;
    const setPassword = (password : string) => registerInfo.password = password;
    const setConfirmPassword = (password : string) => registerInfo.confirm_password = password;

    const handleSubmit = () => {
        // verify the form
        if (registerInfo.password !== registerInfo.confirm_password) {
            alert("The passwords must be equal");
            return;
        }

        if (registerInfo.name === null || registerInfo.name === '') {
            alert("The name must not be null");
            return
        }

        if (registerInfo.username === null || registerInfo.username === '') {
            alert("The username must not be null");
            return
        }

        // register the account calling axios
        const response = handleRegister(registerInfo);
        console.log(response);
    }

    return ( 
        <>  
            <div className={styles.inputBox}>
                <input placeholder='name' type='input' className={styles.normalInput} onChange={(e) => setName(e.target.value)}/>
                <input placeholder='username' type='input' className={styles.normalInput} onChange={(e) => setUsername(e.target.value)}/>
                <PasswordInput placeholder='password' onChange={setPassword}/>    
                <PasswordInput placeholder='confirm password' onChange={setConfirmPassword}/>                        
            </div>      
            <div className={styles.actionButton}>
                <button className={styles.actionButtonClick} onClick={handleSubmit}>Register</button>
            </div>
            <div className={styles.footer}>
                <span>Already have an account?&nbsp;</span>                
                <span className={styles.footerButton} onClick={setTrigger}>Sign in.</span>
            </div>  
        </>
    );
}

export default RegisterForm;

