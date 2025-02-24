import { IUser } from "../../interfaces/User/IUser";
import styles from "./ProfileHeader.module.css"

interface Props {
    user : IUser;
}

function ProfileHeader({user} : Props) {
    return ( 
    <div className={styles.profileHeader}>
        <img src={user.image ? user.image : './user.jpg'} className={styles.profileImage}/>
        <div className={styles.profileInfo}>
            <div className={styles.profileName}>
                <span>{user.name}</span>
                <span>{user.identifier}</span>
            </div>
            <div className={styles.profileBio}>
                <span>Bem vindos ao meu perfil! (•‿•)</span>
            </div>
        </div>
    </div>
 );
}

export default ProfileHeader;