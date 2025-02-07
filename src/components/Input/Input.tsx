import styles from './Input.module.css'
import { IconType } from "react-icons";

interface props {
    placeholder: string;
    icon? : IconType;
}

function Input({placeholder, icon: Icon} : props) {
    return ( 
        <div className={styles.inputShape}>
            <input placeholder={placeholder} className={styles.input}></input> 
            {Icon && <Icon/>}
        </div>
     );
}

export default Input;