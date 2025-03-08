import { IUser } from "../../interfaces/User/IUser";
import { http } from "../AxiosConfig";

const USER_PATH = '/user-service/users/identifier'

const handleGetUserByIdentifier = (userIdentidier : string) => {
    try {
        const response = http.get<IUser>(`${USER_PATH}/${userIdentidier}`, {});
        return response;
    } catch (e) {
        console.log(e);
    }
}

export {handleGetUserByIdentifier}