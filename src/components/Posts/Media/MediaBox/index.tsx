import styles from "../Media.module.css"
import { IMedia } from "../../../../interfaces/Post/IMedia";
import Media from "../Media";
import { useState } from "react";
import MediaPopup from "../MediaPopup";

interface Props {
    mediaArray : IMedia[]
}

function MediaBox({mediaArray} : Props) {
    const [mediaModal, setMediaModal] = useState(false);
    const [mediaClicked, setMediaClicked] = useState(1);

    const toggleMediaModal = () => {
        setMediaModal(!mediaModal)
    }
    

    return (
        <>
            <ul className={styles.postMediaBox}>
                {mediaArray.map(media => <li key={media.id} onClick={() => {
                    toggleMediaModal();
                    setMediaClicked(media.id);
                }}>{<Media media={media}/>}</li>)}
            </ul>
            <MediaPopup 
             trigger={mediaModal}
             mediaArray={mediaArray}
             startPosition={mediaClicked} 
             setTrigger={toggleMediaModal}
             />
        </>
    )
}

export default MediaBox;