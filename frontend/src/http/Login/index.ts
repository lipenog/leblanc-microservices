import { Cookies } from 'react-cookie';
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
                'Authorization': `Basic ${basicAuth}`,
                Accept: "application/json",
		        Content: "application/json"
            }
        }); 
        // set the user object and the session token in the cookies for future use
        const cookies = new Cookies();
        cookies.set('loggedUser', response.data, { path: '/' });
        cookies.set('bearer', response.headers.authorization, { path: '/' });
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