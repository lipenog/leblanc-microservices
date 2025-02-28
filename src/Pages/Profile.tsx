import { useEffect, useState } from "react"
import Header from "../components/Header"
import Posts from "../components/Posts"
import ProfileHeader from "../components/ProfileHeader"
import { IPost } from "../interfaces/Post/IPost"
import { getPostsByUser } from "../http/Posts"
import { useParams } from "react-router-dom"

function Profile() {
    const loggedUser = {id: 1, identifier: '@gpv____', name: 'piva', image: './bagre.jpeg'}
    const params = useParams();
    const [posts, setPosts] = useState<IPost[]>([]);
    
    useEffect(() => {
        if(params.userIdentifier) {
            const response = getPostsByUser(params.userIdentifier);
            response.then(res => res?.data && setPosts(res?.data));        
        }        
    }, [params]);


    return ( 
    <>
        <Header/>
        <Posts posts={posts} header={<ProfileHeader user={loggedUser}/>}/>
    </> 
    );
}

export default Profile