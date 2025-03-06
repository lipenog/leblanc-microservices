import { useState } from 'react';
import styles from '../Login.module.css'
import { FaRegEye, FaRegEyeSlash } from 'react-icons/fa';

interface Props {
    placeholder : string
}

function passwordInput({placeholder} : Props) {
    const [togglePasswordView, setTogglePasswordView] = useState(false);

    const iconClick = () => {
        setTogglePasswordView(!togglePasswordView);
    }

    return ( 
        <div className={styles.passwordInput}>
            <input placeholder={placeholder} type={togglePasswordView ? 'input' : 'password'}/>
            {togglePasswordView ? <FaRegEye size={20} onClick={iconClick}/> : <FaRegEyeSlash size={20} onClick={iconClick}/>}
        </div>        
    );
}

export default passwordInput;