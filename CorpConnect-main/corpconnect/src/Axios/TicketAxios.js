import axios from "axios";

const TicketAxios = axios.create({
    baseURL: "http://localhost:8090/api/tickets",
});

TicketAxios.interceptors.request.use((config) => {
    const isFormData = config.data instanceof FormData;

    if (!isFormData) {
        config.headers["Content-Type"] = "application/json";
    }

    const token = localStorage.getItem("accessToken");
    if (token) {
        config.headers["Authorization"] = `Bearer ${token}`;
    }

    return config;
});

export default TicketAxios;
