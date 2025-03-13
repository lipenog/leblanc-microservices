import { useEffect } from 'react';
import styles from './Toast.module.css';
import { FaCheck } from "react-icons/fa";
import { MdErrorOutline } from "react-icons/md";

interface Props {
    type : 'success' | 'error';
    message: string;
    trigger: boolean;
    setTrigger: () => void;
}

function Toast({type, message, trigger, setTrigger}: Props) {

    useEffect(() => {
        setInterval(setTrigger, 3500);
    }, [trigger]);

    return (
        trigger && 
        <div className={type == 'success' ? styles.successToast : styles.errorToast} onClick={setTrigger}>
            <div className={styles.toastIcon}>
                {type == 'success' ? <FaCheck size={20}/> : <MdErrorOutline size={20}/>}
            </div>
            <div className={styles.toastMessage}>
                {message}
            </div>            
        </div>
    );
}

export default Toast;