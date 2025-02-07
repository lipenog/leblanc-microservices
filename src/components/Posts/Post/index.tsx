import { IPost } from "../../../interfaces/Post/IPost";
import { IMedia } from "../../../interfaces/Post/media";
import UserImage from "../../User/UserImage";
import styles from "../Posts.module.css"

interface PostProps {
    post : IPost;
}

function treatMedia(media: IMedia) {
    // adicionar outros tipos de imagens
    if(media.mediaType === 'jpg') 
        return <img src={media.mediaPath} className={styles.postContentMedia}/>

    return (
    <video className={styles.postContentMedia} controls>
        <source src={media.mediaPath} type="video/mp4"></source>
    </video>)
}

function mediaBox(mediaArray: IMedia[]) {
    return (
        <ul className={styles.postMediaBox}>
            {mediaArray.map(media => <li key={media.id}>{treatMedia(media)}</li>)}
        </ul>
    )
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
            {post.media && mediaBox(post.media)}
        </div>
        <span className={styles.postDate}>{post.publishedAt}</span>
    </li> );
}

export default Post;