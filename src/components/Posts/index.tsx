import { IPost } from "../../interfaces/Post/IPost";
import styles from "./Posts.module.css"
import Post from "./Post";
import { IMedia } from "../../interfaces/Post/media";

const media : IMedia = {
    mediaType: "jpg",
    mediaPath: "./joker.jpg"
}

const video : IMedia = {
    mediaType: "mp4",
    mediaPath: "./pitoco.mp4"
}

const posts : IPost[] = [
    { id: 1, name: 'botcity', identifier: 'gpv', content: 'Uma vez amei, julguei que me amariam, Mas não fui amado. Não fui amado pela única grande razão —Porque não tinha que ser. Consolei-me voltando ao sol e à chuva, E sentando-me outra vez à porta de casa. Os campos, afinal, não são tão verdes para os que são amados Como para os que o não são. Sentir é estar distraído.', image: './bagre.jpeg', publishedAt: (new Date()).toISOString(), media: media},
    { id: 2, name: 'francis', identifier: 'gabrielflamengos', content: 'Gabigol.', image: null, publishedAt: (new Date()).toISOString()},
    { id: 3, name: 'megaVE', identifier: 'megaVE', content: 'Guten morgen', image: './bagre.jpeg', publishedAt: (new Date()).toISOString(), media: video}
];

function Posts() {
    return ( 
        <ul className={styles.posts}>
            {posts.map(post => <Post key={post.id} post={post}/>)}
        </ul>
    );
}

export default Posts;