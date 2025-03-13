import { IUser } from "../../interfaces/User/IUser";
import { http } from "../AxiosConfig";

const USER_PATH = '/user-service/users/identifier'
const USER_PHOTO_PATH = '/user-service/users/profile-image'

const handleGetUserByIdentifier = (userIdentidier : string) => {
    try {
        const response = http.get<IUser>(`${USER_PATH}/${userIdentidier}`, {});
        return response;
    } catch (e) {
        console.log(e);
    }
}

const handleUpdateImage = async (file : File) => {
    try {
        const formData = new FormData();

        formData.append('image', file);
        
        const response = await http.request({
            url: USER_PHOTO_PATH,
            method: 'PUT',
            headers : {
                'Content-Type': 'multipart/form-data',
            },
            data: formData
        })

        return response;
    } catch (e) {
        console.log(e);
    }
}

export {handleGetUserByIdentifier, handleUpdateImage}