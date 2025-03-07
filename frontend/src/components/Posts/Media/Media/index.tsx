import { IMedia } from "../../../../interfaces/Post/IMedia";
import styles from "../Media.module.css"

interface Props {
    media: IMedia;
}

function treatMedia(media: IMedia) {
    if(media.mediaType === '.jpg' || media.mediaType === '.png' || media.mediaType === '.jpeg') 
        return <img src={media.mediaPath} className={styles.postContentMedia}/>

    return (
    <video className={styles.postContentMedia} controls>
        <source src={media.mediaPath} type="video/mp4"></source>
    </video>)
}

function Media({media} : Props) {
    return (  
        treatMedia(media)    
    );
}

export default Media;