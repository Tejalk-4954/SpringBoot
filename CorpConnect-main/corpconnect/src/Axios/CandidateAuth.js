import axios from "axios";

const CandidateAxios = axios.create({
    baseURL : 'http://localhost:8082/api/candidates'
});

CandidateAxios.interceptors.request.use((config) => {
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

export default CandidateAxios;