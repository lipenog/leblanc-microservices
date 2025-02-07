import { IUser } from "../../../interfaces/User/IUser";
import { CiImageOn } from "react-icons/ci";
import UserImage from "../../User/UserImage";
import styles from "./PostForm.module.css"

interface Props {
    user: IUser;
}

function PostForm({user} : Props) {
    return (
    <div className={styles.postForm}>
        <UserImage user={user}/>
        <div className={styles.postFormBody}>
            <input placeholder="What is happening?!" className={styles.postFromText}/>
            <div className={styles.postFormFooter}>
                <CiImageOn className={styles.postImageButton} size={30}/>
                <button className={styles.postSubmitButton}>Post</button>    
            </div>
        </div>
    </div>
);
}

export default PostForm;