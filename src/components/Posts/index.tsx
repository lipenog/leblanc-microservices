import { IPost } from "../../interfaces/Post/IPost";
import styles from "./Posts.module.css"
import Post from "./Post";
import { IMedia } from "../../interfaces/Post/IMedia";
import PostForm from "./PostForm";

const media : IMedia = {
    id: 1,
    mediaType: "jpg",
    mediaPath: "./joker.jpg"
}

const media1 : IMedia = {
    id: 2,
    mediaType: "jpg",
    mediaPath: "./joker.jpg"
}

const media2 : IMedia = {
    id: 3,
    mediaType: "jpg",
    mediaPath: "./joker.jpg"
}


const video : IMedia = {
    id: 4,
    mediaType: "mp4",
    mediaPath: "./pitoco.mp4"
}

const posts : IPost[] = [
    { id: 1, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', publishedAt: (new Date()).toISOString(), media: [media, media1, media2, video]},
    { id: 2, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Gabigol.', publishedAt: (new Date()).toISOString(), media: [media, media1, media2]},
    { id: 3, user : {id: 1, name: 'botcity', identifier: 'gpv', image: null}, content: 'Guten morgen', publishedAt: (new Date()).toISOString(), media: [media, video]},
    { id: 4, user : {id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}, content: 'Ameinda.', publishedAt: (new Date()).toISOString(), media: [media]}
];

function Posts() {
    return ( 
        <ul className={styles.posts}>
            <PostForm key={-1} user={{id: 1, name: 'botcity', identifier: 'gpv', image: './bagre.jpeg'}}></PostForm>
            {posts.map(post => <Post key={post.id} post={post}/>)}
        </ul>
    );
}

export default Posts;