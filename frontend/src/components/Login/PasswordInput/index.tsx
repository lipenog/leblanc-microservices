import { useState } from 'react';
import styles from '../Login.module.css'
import { FaRegEye, FaRegEyeSlash } from 'react-icons/fa';

interface Props {
    placeholder : string
    onChange : (arg0 : string) => void
}

function passwordInput({placeholder, onChange} : Props) {
    const [togglePasswordView, setTogglePasswordView] = useState(false);

    const iconClick = () => {
        setTogglePasswordView(!togglePasswordView);
    }

    return ( 
        <div className={styles.passwordInput}>
            <input placeholder={placeholder} type={togglePasswordView ? 'input' : 'password'} onChange={(e) => onChange(e.target.value)}/>
            {togglePasswordView ? <FaRegEye size={20} onClick={iconClick}/> : <FaRegEyeSlash size={20} onClick={iconClick}/>}
        </div>        
    );
}

export default passwordInput;