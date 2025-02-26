import { useLocation } from "react-router-dom";
import Header from "../Header";
import Posts from "../Posts";
import CurrentUser from "../User/CurrentUser";
import SearchInfo from "../Posts/SearchInfo";
import { IPost } from "../../interfaces/Post/IPost";

function SearchResult( ) {
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const query = searchParams.get('q');
    
    const posts : IPost[] = [
        { id: 1, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString()},
        { id: 2, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString()},
        { id: 3, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString()}
    ]

    const loggedUser = {id: 1, identifier: '@gpv', name: 'piva', image: null}

    return ( 
    <>
        <Header/>
        <Posts posts={posts} header={<SearchInfo query={query}/>}/>
        <CurrentUser user={loggedUser}/>
    </>
 );
}

export default SearchResult;