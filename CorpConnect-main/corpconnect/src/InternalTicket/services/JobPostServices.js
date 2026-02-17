import axios from "axios";

const BASE_URL = "http://localhost:8083/api/job-posts"; // hiring-service

export const getPublicJobPosts = () => axios.get(`${BASE_URL}/public`);

export const getJobPostById = (id) => axios.get(`${BASE_URL}/${id}`);

export const getApplicationsByJob = (jobId) =>
  axios.get(`http://localhost:8083/api/applications/job/${jobId}`);
