import { IPost } from "../../../interfaces/Post/IPost";
import UserImage from "../../User/UserImage";
import MediaBox from "../Media";
import styles from "../Posts.module.css"

interface PostProps {
    post : IPost;
}

function Post({post} : PostProps) {
    return ( 
    <li className={styles.post}>
        <header className={styles.postHeader}>
            <UserImage user={post.user}/>
            <div className={styles.profileInfo}>
                <span className={styles.profileName}>{post.user.name}</span>
                <span className={styles.profileIdentidier}>@{post.user.identifier}</span>
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