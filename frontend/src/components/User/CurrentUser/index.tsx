import UserImage from "../UserImage/UserImage";
import styles from './CurrentUser.module.css'
import { useNavigate } from "react-router-dom";
import { getLoggedUser } from "../../../http/Cookies";

function CurrentUser() {
    const navigate = useNavigate();
    
    const navigateToMyProfile = () => {
        navigate(`/${getLoggedUser().identifier}`);
    }

    return ( 
            <div className={styles.userBox} onClick={navigateToMyProfile}>
                <UserImage user={getLoggedUser()}></UserImage>
                <span className={styles.profileName}>{getLoggedUser().name}</span>
                <span className={styles.profileIdentidier}>@{getLoggedUser().identifier}</span>                
            </div>
        );
}

export default CurrentUser;