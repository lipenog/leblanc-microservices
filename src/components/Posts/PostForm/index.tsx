import { IUser } from "../../../interfaces/User/IUser";
import { CiImageOn } from "react-icons/ci";
import UserImage from "../../User/UserImage/UserImage";
import styles from "./PostForm.module.css"
import { useState } from "react";
import { IMedia } from "../../../interfaces/Post/IMedia";
import MediaBox from "../Media/MediaBox";
import { postPosts } from "../../../http/Posts";

interface Props {
    user: IUser;
}

let imageCount = 0;

function PostForm({user} : Props) {
    const [postContent, setPostContent] = useState('');
    const [imagesArray, setImagesArray] = useState<IMedia[]>([]);
    const [originalFilesArray, setOriginalFilesArray] = useState<File[]>([]);
    
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
            setOriginalFilesArray((old) => [...old, ...fileArray]);
        }
    }

    const handleSubmit = () => {
        const response = postPosts(postContent, originalFilesArray);
        console.log(response);
        setPostContent('');
        setImagesArray([]);
        setOriginalFilesArray([])
    }

    return (
    <div className={styles.postForm}>
        <UserImage user={user}/>
        <div className={styles.postFormBody}>
            <input placeholder="What is happening?!" className={styles.postFromText} value={postContent} onChange={e => setPostContent(e.target.value)}/>
            {imageCount > 0 && <MediaBox mediaArray={imagesArray}/>}
            <div className={styles.postFormFooter}>
                <input type="file" multiple onChange={handleImageInput}></input>
                <CiImageOn className={styles.postImageButton} size={30}/>
                <button className={styles.postSubmitButton} onClick={handleSubmit}>Post</button>    
            </div>
        </div>
    </div>
);
}

export default PostForm;