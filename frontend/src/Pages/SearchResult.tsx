import { useLocation } from "react-router-dom";
import { IPost } from "../interfaces/Post/IPost";
import Header from "../components/Header";
import Posts from "../components/Posts";
import CurrentUser from "../components/User/CurrentUser";
import SearchInfo from "../components/Posts/SearchInfo";
import { getPosts } from "../http/Posts";
import { useEffect, useState } from "react";


function SearchResult( ) {
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const query = searchParams.get('q');
    
    const loggedUser = {id: 1, identifier: '@gpv', name: 'piva', image: null}

    const [posts, setPosts] = useState<IPost[]>([]);

    useEffect(() => {
        if(query) {
            const response = getPosts(query);
            response.then(res => res?.data && setPosts(res?.data));
        }
    }, [query]);

    return ( 
    <>
        <Header/>
        <Posts posts={posts} header={<SearchInfo query={query}/>}/>
        <CurrentUser/>
    </>
 );
}

export default SearchResult;