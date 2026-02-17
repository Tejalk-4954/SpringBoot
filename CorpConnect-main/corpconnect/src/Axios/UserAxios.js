import axios from "axios";

const UserAxios = axios.create({
    baseURL : "http://localhost:8082/api/users",
});

UserAxios.interceptors.request.use((config) => {
  const isFormData = config.data instanceof FormData;

  // Only set Content-Type if not FormData
  if (!isFormData) {
    config.headers['Content-Type'] = 'application/json';
  }
  const token = localStorage.getItem('accessToken');
  console.log(token);
  if (token) {
    console.log(token);
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
});

export default UserAxios;