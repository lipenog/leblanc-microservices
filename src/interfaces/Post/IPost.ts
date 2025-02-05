import { IMedia } from "./media";

interface IPost {
    id: number;
    identifier: string;
    name: string;
    content: string;
    image: string | null;
    media?: IMedia;
    publishedAt: string;
}

export type {IPost}