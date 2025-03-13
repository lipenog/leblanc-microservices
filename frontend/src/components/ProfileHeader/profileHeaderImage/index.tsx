import { useState } from "react";
import { IUser } from "../../../interfaces/User/IUser";
import styles from "../ProfileHeader.module.css"
import { FaPen } from "react-icons/fa";
import { useRef } from "react";
import { handleUpdateImage } from "../../../http/Users";


interface Props {
    user : IUser | undefined;
}

function ProfileHeaderImage({user} : Props) {
    const [hover, setHover] = useState(false);
    const fileInputRef = useRef<HTMLInputElement | null>(null);

    const updateHover = () => {
        setHover(!hover);
    }
    
    const handlePenClick = () => {
        fileInputRef.current?.click();
    };

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];
        if (!file) return;
        
        handleUpdateImage(file)
    };

    return ( 
        <div onMouseEnter={updateHover} onMouseLeave={updateHover} onClick={handlePenClick} className={styles.profileImageOnHover}>
            {hover && 
            <div className={styles.profileUpdateImage}>
                <FaPen size={30}/>
            </div>}            
            <img src={user?.image ? user.image : './user.jpg'}/>
            <input
                type="file"
                ref={fileInputRef}
                style={{ display: 'none' }}
                onChange={handleFileChange}
            />
        </div>
    );
}

export default ProfileHeaderImage;