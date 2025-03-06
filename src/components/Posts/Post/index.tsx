import { IPost } from "../../../interfaces/Post/IPost";
import styles from "../Posts.module.css"

interface PostProps {
    post : IPost;
}

function Post({post} : PostProps) {
    return ( 
    <li className={styles.post}>
        <header className={styles.postHeader}>
            <img src={post.image ? post.image : './user.jpg'} className={styles.profileImage}/>
            <div className={styles.profileInfo}>
                <span className={styles.profileName}>{post.name}</span>
                <span className={styles.profileIdentidier}>@{post.identifier}</span>
            </div>
        </header>
        <span className={styles.postContent}>{post.content}</span>
    </li> );
}

export default Post;