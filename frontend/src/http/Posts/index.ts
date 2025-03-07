import { IPost } from '../../interfaces/Post/IPost';
import { http } from '../AxiosConfig/index'

const POSTS_URL = 'post-service/posts';
const POSTS_SEARCH_URL = 'post-service/search'

const getPosts = async( content : string ) => {
    try {
        const response = await http.get<IPost[]> (POSTS_SEARCH_URL, {
            params: {
                content: content
            }
        });
        return response;
    } catch (e) {
        console.log(e);
    }
} 

const getPostsByUser = async ( userIdentidier : string ) => {
    try {
        const response = await http.get<IPost[]> (`${POSTS_URL}/${userIdentidier}`, {
        })
        return response;
    } catch (e) {
        console.log(e)
    }
}

const postPosts = async ( content: string, files : File[]) => {
    try {
        const formData = new FormData();

        formData.append('content', content);
        
        files.forEach(file => formData.append('media', file));
        const response = await http.request({
            url: POSTS_URL,
            method: 'POST',
            headers : {
                'Content-Type': 'multipart/form-data',
            },
            data: formData
        })

        return response;
    } catch (e) {
        console.log(e);
    }
}

export {postPosts, getPosts, getPostsByUser}