import { useState } from 'react';
import styles from './Login.module.css'
import LoginForm from './LoginForm';
import { GiCoffeeCup } from 'react-icons/gi';

function LoginPage() {
    const [currentForm, setCurrentForm] = useState('login');

    const updateCurrentForm = () => {
        if (currentForm === 'login') {
            setCurrentForm('register')
        } else {
            setCurrentForm('login')
        }
    }

    return ( 
        <div className={styles.loginBackground}>        
            <div className={styles.loginBox}>
                <div className={styles.loginHeader}>
                    <h1>Leblanc</h1>
                    <GiCoffeeCup size={35}/>
                </div> 
                {currentForm === 'login' ? <LoginForm setTrigger={updateCurrentForm}/> : <p>umm</p>}
                
            </div>
        </div>
    );
}

export default LoginPage;