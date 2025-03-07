import { useNavigate } from "react-router-dom";
import { IPost } from "../../../interfaces/Post/IPost";
import UserImage from "../../User/UserImage/UserImage";
import MediaBox from "../Media/MediaBox";
import styles from "../Posts.module.css"

interface PostProps {
    post : IPost;
}

function Post({post} : PostProps) {
    const navigate = useNavigate();

    const handleHeaderClick = () => {
        navigate(`/${post.usersDTO.identifier}`);
    }

    return ( 
    <li className={styles.post}>
        <header className={styles.postHeader} onClick={handleHeaderClick}>
            <UserImage user={post.usersDTO}/>
            <div className={styles.profileInfo}>
                <span className={styles.profileName}>{post.usersDTO.name}</span>
                <span className={styles.profileIdentidier}>@{post.usersDTO.identifier}</span>
            </div>
        </header>
        <div className={styles.postContent}>
            <span className={styles.postContentText}>{post.content}</span>
            {post.media && <MediaBox mediaArray={post.media}/>}
        </div>
        <span className={styles.postDate}>{post.publishedAt}</span>
    </li> );
}

export default Post;