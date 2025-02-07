import styles from "../Posts.module.css"
import { IMedia } from "../../../interfaces/Post/IMedia";

interface Props {
    mediaArray : IMedia[]
}


function treatMedia(media: IMedia) {
    // adicionar outros tipos de imagens
    if(media.mediaType === 'jpg' || media.mediaType === 'png' || media.mediaType === 'jpeg') 
        return <img src={media.mediaPath} className={styles.postContentMedia}/>

    return (
    <video className={styles.postContentMedia} controls>
        <source src={media.mediaPath} type="video/mp4"></source>
    </video>)
}

function MediaBox({mediaArray} : Props) {
    return (
        <ul className={styles.postMediaBox}>
            {mediaArray.map(media => <li key={media.id}>{treatMedia(media)}</li>)}
        </ul>
    )
}

export default MediaBox;