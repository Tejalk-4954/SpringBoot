import axios from "axios";
 
const ChatAxios = axios.create({
  baseURL: "http://localhost:8090/api", // main API root
});
 
ChatAxios.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (token) {
    config.headers["Authorization"] = `Bearer ${token}`;
  }
  return config;
});
 
export default ChatAxios;