import { IUser } from "../../../interfaces/User/IUser";
import UserImage from "../UserImage/UserImage";
import styles from './CurrentUser.module.css'

interface Props {
    user : IUser
} 

function CurrentUser({user} : Props) {
    return ( 
            <div className={styles.userBox}>
                <UserImage user={user}></UserImage>
                <span className={styles.profileName}>{user.name}</span>
                <span className={styles.profileIdentidier}>{user.identifier}</span>                
            </div>
        );
}

export default CurrentUser;