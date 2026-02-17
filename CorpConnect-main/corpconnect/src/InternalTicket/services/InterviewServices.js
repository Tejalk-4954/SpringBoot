import axios from "axios";

const BASE = "http://localhost:8083/api/interviews";  

const authHeaders = () => {
  const token = localStorage.getItem("accessToken");
  return {
    headers: {
      Authorization: token ? `Bearer ${token}` : "",
    },
  };
};

export const scheduleInterview = (dto) => {
  return axios.post(`${BASE}/schedule`, dto, authHeaders());
};

export const getInterviewById = (id) => {
  return axios.get(`${BASE}/${id}`, authHeaders());
};

export const getInterviewsByInterviewer = (interviewerId) => {
  return axios.get(`${BASE}/interviewer/${interviewerId}`, authHeaders());
};

export const decideInterview = (id, decision) => {
  return axios.post(`${BASE}/${id}/decision?decision=${decision}`, null, authHeaders());
};
