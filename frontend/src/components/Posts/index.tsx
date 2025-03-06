import { IPost } from "../../interfaces/Post/IPost";
import styles from "./Posts.module.css"
import Post from "./Post";
import { ReactNode } from "react";

interface Props {
    posts : IPost[];
    header: ReactNode;
}

function Posts({posts, header} : Props) {
    return ( 
        <div style={{marginTop : "4.8rem"}}>
            <ul className={styles.posts}>
                {header}
                {posts.map(post => <Post key={post.id} post={post}/>)}
            </ul>
        </div>
    );
}

export default Posts;