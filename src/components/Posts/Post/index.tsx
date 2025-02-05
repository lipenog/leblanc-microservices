import { IPost } from "../../../interfaces/Post/IPost";
import styles from "../Posts.module.css"

interface PostProps {
    post : IPost;
}

function Media(post: IPost) {
    if(!post.media) return null;

    // adicionar outros tipos de imagens
    if(post.media.mediaType === 'jpg') 
        return <img src={post.media.mediaPath} className={styles.postContentMedia}/>

    return <video src={post.media.mediaPath} controls ></video>
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
        <div className={styles.postContent}>
            <span className={styles.postContentText}>{post.content}</span>
            {Media(post)}
        </div>
        <span className={styles.postDate}>{post.publishedAt}</span>
    </li> );
}

export default Post;