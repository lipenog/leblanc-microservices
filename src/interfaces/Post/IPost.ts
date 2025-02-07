import { IUser } from "../User/IUser";
import { IMedia } from "./IMedia";

interface IPost {
    id: number;
    user: IUser;
    content: string;
    media?: IMedia[];
    publishedAt: string;
}

export type {IPost}