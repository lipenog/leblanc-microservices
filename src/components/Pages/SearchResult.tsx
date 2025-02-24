import Header from "../Header";
import Posts from "../Posts";
import CurrentUser from "../User/CurrentUser";

function SearchResult( ) {
    const loggedUser = {id: 1, identifier: '@gpv', name: 'piva', image: null}
    return ( 
    <>
        <Header/>
        <Posts posts={[]} header={undefined}/>
        <CurrentUser user={loggedUser}/>
    </>
 );
}

export default SearchResult;