import { useEffect, useState } from "react"
import Header from "../components/Header"
import Posts from "../components/Posts"
import ProfileHeader from "../components/ProfileHeader"
import { IPost } from "../interfaces/Post/IPost"
import { getPostsByUser } from "../http/Posts"
import { useParams } from "react-router-dom"
import { handleGetUserByIdentifier } from "../http/Users"
import { IUser } from "../interfaces/User/IUser"
import CurrentUser from "../components/User/CurrentUser"

function Profile() {
    const params = useParams();
    const [user, setUser] = useState<IUser>();
    const [posts, setPosts] = useState<IPost[]>([]);
    
    useEffect(() => {
        if(params.userIdentifier) {
            // searchs the user and posts
            const responseUser = handleGetUserByIdentifier(params.userIdentifier);
            const responsePosts = getPostsByUser(params.userIdentifier);
            responsePosts.then(res => res?.data && setPosts(res?.data));        
            responseUser?.then(res => res?.data && setUser(res.data));
        }        
    }, [params]);


    return ( 
    <>
        <Header/>
        <Posts posts={posts} header={<ProfileHeader user={user}/>}/>
        <CurrentUser/>
    </> 
    );
}

export default Profile