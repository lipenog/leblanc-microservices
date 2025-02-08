import { useState } from "react";
import { IMedia } from "../../../../interfaces/Post/IMedia";
import { FiArrowLeftCircle } from "react-icons/fi";
import { FiArrowRightCircle } from "react-icons/fi";
import Media from "../Media";
import styles from "../Media.module.css"


interface Props {
    trigger: boolean,
    startPosition: number,
    mediaArray : IMedia[],
    setTrigger: () => void ;
}

function MediaPopup({trigger, startPosition, mediaArray, setTrigger} : Props) {
    const [position, setPosition] = useState(startPosition);
    const [media, setMedia] = useState(mediaArray[startPosition]);

    const handleArrowClickBackward = () => {
        setPosition(position - 1 == -1 ? 0 : position - 1);
        setMedia(mediaArray[position]);
    }

    const handleArrowClickFoward = () => {
        setPosition(position + 1 == mediaArray.length ? 0 : position + 1);
        setMedia(mediaArray[position]);
    }
    
    return trigger && (
        <div className={styles.postMediaPopup}>
            <header className={styles.postMidiaPopupHeader}>
                <button onClick={setTrigger}>close</button>
            </header>
            <div className={styles.postMediaPopupBody}>
                <FiArrowLeftCircle onClick={handleArrowClickBackward} size={20}/>
                <div className={styles.postMediaPopupMedia}>
                    <Media media={media}/>
                </div>                                
                <FiArrowRightCircle onClick={handleArrowClickFoward} size={20}/>
            </div>
        </div>        
    );
}

export default MediaPopup;