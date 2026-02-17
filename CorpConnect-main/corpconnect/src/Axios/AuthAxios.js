import axios from "axios";

const AuthAxios = axios.create({
    baseURL : 'http://localhost:8082/api/auth'
});

AuthAxios.interceptors.request.use((config) => {
    const isFormData = config.data instanceof FormData;

    if(!isFormData){
        config.headers['Content-Type'] = 'application/json';
    }
    const token = localStorage.getItem('token');
    console.log(token);
    if(token){
        console.log(token);
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
});

export default AuthAxios;