// chatApi.js
import axios from "axios";
 
/**
 * Simple REST helper for fetching chat history and sending (fallback).
 * Base URL matches your backend controller: /api/tickets/{ticketId}/chat
 */
const API_BASE = "http://localhost:8090/api";
 
const getAuthHeaders = () => {
  const token = localStorage.getItem("accessToken") || localStorage.getItem("token");
  return token ? { Authorization: `Bearer ${token}` } : {};
};
 
export const fetchChatHistory = async (ticketId) => {
  const res = await axios.get(`${API_BASE}/tickets/${ticketId}/chat`, {
    headers: getAuthHeaders(),
  });
  // backend returns List<ChatMessagePayload>
  return res.data;
};
 
/**
 * Optional REST send fallback (if you post to REST instead of STOMP)
 */
export const sendChatMessageRest = async (ticketId, payload) => {
  const res = await axios.post(`${API_BASE}/tickets/${ticketId}/chat`, payload, {
    headers: getAuthHeaders(),
  });
  return res.data;
};

