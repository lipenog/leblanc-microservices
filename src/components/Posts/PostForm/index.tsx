import { IUser } from "../../../interfaces/User/IUser";
import { CiImageOn } from "react-icons/ci";
import UserImage from "../../User/UserImage";
import styles from "./PostForm.module.css"
import { useState } from "react";
import { IMedia } from "../../../interfaces/Post/IMedia";
import MediaBox from "../Media";

interface Props {
    user: IUser;
}

let imageCount = 0;

function PostForm({user} : Props) {
    const [imagesArray, setImagesArray] = useState<IMedia[]>([]);
    
    const handleImageInput = (e: React.ChangeEvent<HTMLInputElement>) => {
        const files = e.target.files;
        if(files) {
            const fileArray = Array.from(files);
            console.log(fileArray)
            const imagesArray : IMedia[] = fileArray.map(file => ({
                id: imageCount++,
                mediaPath: URL.createObjectURL(file),
                mediaType: file.type.split('/')[1]
            }
            ));
            setImagesArray((old) => [...old, ...imagesArray]);
        }
    }

    return (
    <div className={styles.postForm}>
        <UserImage user={user}/>
        <div className={styles.postFormBody}>
            <input placeholder="What is happening?!" className={styles.postFromText}/>
            {imageCount > 0 && <MediaBox mediaArray={imagesArray}/>}
            <div className={styles.postFormFooter}>
                <input type="file" multiple onChange={handleImageInput}></input>
                <CiImageOn className={styles.postImageButton} size={30}/>
                <button className={styles.postSubmitButton}>Post</button>    
            </div>
        </div>
    </div>
);
}

export default PostForm;