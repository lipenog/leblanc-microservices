import axios from "axios";
import { getBearer } from "../Cookies";

const BACK_URL = 'http://localhost:8765';

const http = axios.create({
	baseURL: BACK_URL,
	headers: {
		Accept: "application/json",
		Content: "application/json"
	}
});

http.interceptors.request.use(function (config) {
	const bearer = getBearer();
	if(bearer) {
		console.log("entrou")
		config.headers.Authorization = bearer;
	}
	return config;
})

export { http }