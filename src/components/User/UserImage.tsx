import styles from './User.module.css'
import { IUser } from "../../interfaces/User/IUser";

interface Props {
    user: IUser
}

function UserImage({user} : Props) {
    return ( 
        <img src={user.image ? user.image : './user.jpg'} className={styles.profileImage}/>
    );
}

export default UserImage;