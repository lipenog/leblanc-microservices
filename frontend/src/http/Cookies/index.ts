import { Cookies } from "react-cookie";


const getBearer = () => {
    const cookies = new Cookies();
    return cookies.get('bearer');
}


export {getBearer}