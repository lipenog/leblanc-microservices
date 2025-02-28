import Header from "../components/Header"
import Posts from "../components/Posts"
import ProfileHeader from "../components/ProfileHeader"
import { IPost } from "../interfaces/Post/IPost"

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