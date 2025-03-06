import { IPost } from "../../interfaces/Post/IPost";
import styles from "./Posts.module.css"
import Post from "./Post";

const posts : IPost[] = [
    { id: 1, name: 'botcity', identifier: 'gpv', content: 'Senhor.', image: './bagre.jpeg'},
    { id: 2, name: 'francis', identifier: 'gabrielflamengos', content: 'Gabigol.', image: null}
];

function Posts() {
    return ( 
        <ul className={styles.posts}>
            {posts.map(post => <Post key={post.id} post={post}/>)}
        </ul>
    );
}

export default Posts;