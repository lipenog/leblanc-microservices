import { useCookies } from "react-cookie";
import { IUser } from "../../../interfaces/User/IUser";
import UserImage from "../UserImage/UserImage";
import styles from './CurrentUser.module.css'
import { useNavigate } from "react-router-dom";

function CurrentUser() {
    const navigate = useNavigate();
    const [cookies] = useCookies(['loggedUser']); 
    
    const user = cookies.loggedUser;


    const navigateToMyProfile = () => {
        navigate(`/${user.identifier}`);
    }

    return ( 
            <div className={styles.userBox} onClick={navigateToMyProfile}>
                <UserImage user={user}></UserImage>
                <span className={styles.profileName}>{user.name}</span>
                <span className={styles.profileIdentidier}>@{user.identifier}</span>                
            </div>
        );
}

export default CurrentUser;