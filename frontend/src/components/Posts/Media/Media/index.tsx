import { IMedia } from "../../../../interfaces/Post/IMedia";
import styles from "../Media.module.css"

interface Props {
    media: IMedia;
}

function treatMedia(media: IMedia) {
    const path = `http://localhost:9000/media/${media.mediaPath}`
    media.mediaType = media.mediaType.replace('.', '');
    if(media.mediaType === 'jpg' || media.mediaType === 'png' || media.mediaType === 'jpeg') 
        return <img src={path} className={styles.postContentMedia}/>

    return (
    <video className={styles.postContentMedia} controls>
        <source src={path} type="video/mp4"></source>
    </video>)
}

function Media({media} : Props) {
    return (  
        treatMedia(media)    
    );
}

export default Media;