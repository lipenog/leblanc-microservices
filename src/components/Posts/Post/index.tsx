import { IPost } from "../../../interfaces/Post/IPost";
import { IMedia } from "../../../interfaces/Post/media";
import styles from "../Posts.module.css"

interface PostProps {
    post : IPost;
}

function media(media: IMedia) {
    // adicionar outros tipos de imagens
    if(media.mediaType === 'jpg') 
        return <img src={media.mediaPath} className={styles.postContentMedia}/>

    return <video src={media.mediaPath} controls ></video>
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
            {post.media && media(post.media)}
        </div>
        <span className={styles.postDate}>{post.publishedAt}</span>
    </li> );
}

export default Post;