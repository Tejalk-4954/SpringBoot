// 


import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";
import Register from "./pages/Register";
import CandidateDashboard from "./pages/CandidateDashboard";
import AllJobs from "./pages/AllJobs";
import ApplyJob from "./pages/ApplyJob";
import AppliedJobs from "./pages/AppliedJobs";
import ManagerDashboard from "./pages/ManagerDashboard";
import CreateJobPost from "./pages/CreateJobPost";
import ViewTickets from "./pages/ViewTickets";
import ViewApplicants from "./pages/ViewApplicants";
import ScheduleInterview from "./pages/ScheduleInterview";
import VideoCall from "./pages/VideoCall";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div className="mt-16">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<CandidateDashboard />} />
          <Route path="/jobs" element={<AllJobs />} />
          <Route path="/apply/:jobId" element={<ApplyJob />} />
          <Route path="/applied-jobs" element={<AppliedJobs />} /> 
          <Route path="/manager" element={<ManagerDashboard />} />
          <Route path="/create-jobpost" element={<CreateJobPost />} />
          <Route path="/view-tickets" element={<ViewTickets />} />
          <Route path="/view-applicants/:ticketId" element={<ViewApplicants />} />
          <Route path="/schedule-interview/:appId" element={<ScheduleInterview />} />
          <Route path="/videocall/:roomId" element={<VideoCall />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
