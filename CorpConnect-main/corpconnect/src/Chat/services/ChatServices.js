// import ChatAxios from "../../Axios/ChatAxios";

// export const getChatHistory = (ticketId) =>
//   ChatAxios.get(`/tickets/${ticketId}/chat`);

// export const sendChatMessage = (ticketId, payload) =>
//   ChatAxios.post(`/tickets/${ticketId}/chat`, payload);
import axios from "../../Axios/ChatAxios";

export const fetchChatHistory = (ticketId) => {
  return axios.get(`/tickets/${ticketId}/chat`);
};
