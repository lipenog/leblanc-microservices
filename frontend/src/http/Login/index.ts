import { ILogin } from '../../interfaces/Login/ILogin';
import { IRegister } from '../../interfaces/Login/IRegister';
import { IUser } from '../../interfaces/User/IUser';
import { http } from '../AxiosConfig/index'

const CREDENTIALS_URL = 'user-service/credentials';
const REGISTER_URL = 'user-service/register'

const handleLogin = async(login : ILogin) => {
    const basicAuth = btoa(`${login.username}:${login.password}`);
    try {
        const response = await http.get<IUser> (CREDENTIALS_URL, {
            headers: {
                'Authorization': `Basic ${basicAuth}`
            }
        });
        return response;
    } catch (e) {
        console.log(e);
    }
}


const handleRegister = async(register : IRegister) => {
    try {
        const response = await http.post<IUser> (REGISTER_URL, register, {            
        });
        return response;
    } catch (e) {
        console.log(e);
    }   
}

export {handleLogin, handleRegister}