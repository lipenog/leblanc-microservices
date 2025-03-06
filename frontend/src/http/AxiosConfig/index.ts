import axios, { AxiosError } from "axios";

const BACK_URL = 'http://localhost:8765';

const http = axios.create({
	baseURL: BACK_URL,
	headers: {
		Accept: "application/json",
		Content: "application/json"
	}
});

export { http }