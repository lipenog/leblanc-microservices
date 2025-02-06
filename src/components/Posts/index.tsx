import { IPost } from "../../interfaces/Post/IPost";
import styles from "./Posts.module.css"
import Post from "./Post";
import { IMedia } from "../../interfaces/Post/media";

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
    { id: 1, name: 'botcity', identifier: 'gpv', content: 'Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. Senhor. ', image: './bagre.jpeg', publishedAt: (new Date()).toISOString(), media: [media, media1, media2, video]},
    { id: 2, name: 'francis', identifier: 'gabrielflamengos', content: 'Gabigol.', image: null, publishedAt: (new Date()).toISOString(), media: [media, media1, media2]},
    { id: 3, name: 'megaVE', identifier: 'megaVE', content: 'Guten morgen', image: './bagre.jpeg', publishedAt: (new Date()).toISOString(), media: [media, video]},
    { id: 4, name: 'luquetadocroquete', identifier: 'lucaamaferro', content: 'Ameinda.', image: './bagre.jpeg', publishedAt: (new Date()).toISOString(), media: [media]}
];

function Posts() {
    return ( 
        <ul className={styles.posts}>
            {posts.map(post => <Post key={post.id} post={post}/>)}
        </ul>
    );
}

export default Posts;