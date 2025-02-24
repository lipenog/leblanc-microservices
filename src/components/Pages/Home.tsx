import { IMedia } from "../../interfaces/Post/IMedia";
import { IPost } from "../../interfaces/Post/IPost";
import Header from "../Header";
import Posts from "../Posts";
import PostForm from "../Posts/PostForm";
import CurrentUser from "../User/CurrentUser";

function Home() {
    const media : IMedia = {
        id: 1,
        mediaType: "jpg",
        mediaPath: "./joker.jpg"
    }
    
    const media1 : IMedia = {
        id: 2,
        mediaType: "jpg",
        mediaPath: "./akechi.jpg"
    }
    
    const media2 : IMedia = {
        id: 3,
        mediaType: "png",
        mediaPath: "./morgana.png"
    }
    
    const media3 : IMedia = {
        id: 1,
        mediaType: "jpeg",
        mediaPath: "./ameinda.jpeg"
    }
    
    
    const video : IMedia = {
        id: 4,
        mediaType: "mp4",
        mediaPath: "./pitoco.mp4"
    }
    
    const posts : IPost[] = [
        { id: 1, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString(), media: [media, media1, media2]},
        { id: 2, user : {id: 2, name: 'gabrielflamengos', identifier: 'francis', image: './bagre.jpeg'}, content: 'Gabigol.', publishedAt: (new Date()).toISOString()},
        { id: 3, user : {id: 1, name: 'megaVE', identifier: 'megaVE', image: null}, content: 'Guten morgen', publishedAt: (new Date()).toISOString()},
        { id: 4, user : {id: 1, name: 'luquetadocrocs', identifier: 'lucaadoraferro10', image: './bagre.jpeg'}, content: 'Ameinda.', publishedAt: (new Date()).toISOString(), media: [media3]}
    ];
    
    const loggedUser = {id: 1, identifier: '@gpv', name: 'piva', image: null}
  return (
  <>
    <Header/>
    <Posts posts={posts} header={<PostForm key={-1} user={loggedUser}></PostForm>}/>
    <CurrentUser user={loggedUser}/>
  </>
  );
}

export default Home
