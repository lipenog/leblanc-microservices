import { IPost } from "../../interfaces/Post/IPost";
import Header from "../Header";
import Posts from "../Posts";
import ProfileHeader from "../ProfileHeader";

function Profile() {
    const posts : IPost[] = [
        { id: 1, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString()},
        { id: 2, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString()},
        { id: 3, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString()}
    ]
    const loggedUser = {id: 1, identifier: '@gpv', name: 'piva', image: './bagre.jpeg'}
    return ( 
    <>
        <Header/>
        <Posts posts={posts} header={<ProfileHeader user={loggedUser}/>}/>
    </> 
    );
}

export default Profile