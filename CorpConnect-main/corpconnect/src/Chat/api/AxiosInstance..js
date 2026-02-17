import axios from "axios";

const userAxios = axios.create({
  baseURL: "http://localhost:8090/api/tickets", // your backend base URL
});

userAxios.interceptors.request.use(config => {
  const token = localStorage.getItem("token");
  if (token) config.headers["Authorization"] = `Bearer ${token}`;
  return config;
});

export default userAxios;

