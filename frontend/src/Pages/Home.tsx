import { useEffect, useState } from "react"
import Header from "../components/Header"
import Posts from "../components/Posts"
import PostForm from "../components/Posts/PostForm"
import CurrentUser from "../components/User/CurrentUser"
import { getPosts } from "../http/Posts"
import { IPost } from "../interfaces/Post/IPost"

function Home() {
    const loggedUser = {id: 1, identifier: 'gpv____', name: 'piva', image: null}
    
    const [posts, setPosts] = useState<IPost[]>([]);
    
    useEffect(() => {
        const response = getPosts("");
        response.then(res => res?.data && setPosts(res?.data));
    }, []);

    return (
    <>
        <Header/>
        <Posts posts={posts} header={<PostForm key={-1} user={loggedUser}></PostForm>}/>
        <CurrentUser/>
    </>
    );
}

export default Home
