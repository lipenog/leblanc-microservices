import { Cookies } from "react-cookie";


const getBearer = () => {
    const cookies = new Cookies();
    return cookies.get('bearer');
}

const getLoggedUser = () => {
    const cookie = new Cookies();
    const user = cookie.get('loggedUser');
    if (user === undefined || user === null) {
        window.location.href = '/login';
    }
    return user;
}

export {getBearer, getLoggedUser}