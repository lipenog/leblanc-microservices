import axios, { AxiosError } from "axios";
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

http.interceptors.response.use(function (response) {
    return response;
  }, function (error : AxiosError) {
	console.log(error);
	if (error.response?.status === 401) {
		window.location.href = '/login'
	}
    return Promise.reject(error);
  })

export { http }