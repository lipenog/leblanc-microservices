import { getLoggedUser } from "../../http/Cookies";
import { IUser } from "../../interfaces/User/IUser";
import styles from "./ProfileHeader.module.css"
import ProfileHeaderImage from "./profileHeaderImage";

interface Props {
    user : IUser | undefined;
}

function ProfileHeader({user} : Props) {
    return ( 
    user &&
    <div className={styles.profileHeader}>
        {user.identifier == getLoggedUser().identifier ? <ProfileHeaderImage user={user}/> : <img src={user.image ? user.image : './user.jpg'} className={styles.profileImage}/>}
        <div className={styles.profileInfo}>
            <div className={styles.profileName}>
                <span>{user.name}</span>
                <span className={styles.profileIdentifier}>@{user.identifier}</span>
            </div>
            <div className={styles.profileBio}>
                <span>Bem vindos ao meu perfil! (•‿•)</span>
            </div>
        </div>
    </div>
 );
}

export default ProfileHeader;