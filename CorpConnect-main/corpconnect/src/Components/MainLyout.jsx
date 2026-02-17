import React, { useState } from 'react'
import Navbar from '../utils/Navbar'
import { Route, Routes } from 'react-router-dom'
import Home from './Home'
import About from './About'
import Services from './Services'
import TicketHome from '../InternalTicket/TicketHome'
import { Footer } from './Footer'
import FacingIssueForm from '../InternalTicket/FacingIssueForm'
import Profile from '../User/Profile'
import HiringPostForm from '../HiringTicket/HiringpOstForm'
import HiringPostsList from '../HiringTicket/HiringPostsList'
import TicketList from '../InternalTicket/TicketList'
import ChatBox from '../Chat/ChatBox'
import ServiceChatFaq from '../InternalTicket/ServiceChatFaq'
import Register from '../utils/Register'
import Login from '../utils/Login'
import { UserList } from '../admin/UserList'
import { AdminHome } from '../admin/AdminHome'
import { UserOTPPage } from '../utils/UserOTPPage'
import CandidateRegister from '../Candidate/CandidateRegister'
import { CandidateHome } from '../Candidate/CandidateHome'
import UserTicketList from '../InternalTicket/UserTicketList'
import AllTicketList from '../InternalTicket/AllTicketList'
import AssignTicket from '../InternalTicket/AssignTicket'
import { ChatProvider } from "../Chat/context/ChatContext.jsx";
import InterviewSchedule from '../HiringTicket/InterviewSchedule.jsx'
import InterviewCard from '../HiringTicket/components/InterviewCard.jsx'
import InterviewDetails from '../HiringTicket/InterviewDetails.jsx';
import InterviewList from '../HiringTicket/InterviewList.jsx';
import VideoCall from '../HiringTicket/VideoCall.jsx'

const MainLyout = () => {
  window.global = window;

  const [openChatId, setOpenChatId] = useState(null);
 
const handleChatStart = (id) => setOpenChatId(id);
const closeChat = () => setOpenChatId(null);
 
  return (
    <ChatProvider>
    <div className='min-h-screen  flex flex-col'>
      <Navbar/>
      <main className='flex-grow' >
         <Routes>
        <Route path='/'  element={<Home/>}  />
        <Route path='/register'  element={<Register/>}  />
        <Route path='/candidate/register'  element={<CandidateRegister/>}  />
        <Route path='/login'  element={<Login/>}  />
        <Route path='/userotp'  element={<UserOTPPage/>}  />
        <Route path='/admin/user/list'  element={<UserList/>}  />
        <Route path='/admin/home'  element={<AdminHome/>}  />
        <Route path='/candidate/home'  element={<CandidateHome/>}  />

        <Route path='/about'  element={<About/>}  />
        <Route path='/services'  element={<Services/>}  />
        <Route path='/user/profile'  element={<Profile/>}  />
        <Route path='/internal/ticket'  element={<TicketHome/>}  />
        <Route path='/internal/ticket/listAll/ticket'  element={<AllTicketList/>}  />
        <Route path='/internal/ticket/assign'  element={<AssignTicket/>}  />
        <Route path='/internal/ticket/service'  element={<ServiceChatFaq/>}  />
        <Route path='/internal/ticket/list'  element={<TicketList  onChatStart={handleChatStart} />}  />
        <Route path='/internal/ticket/user/list'  element={<UserTicketList onChatStart={handleChatStart} />}  />
        
        <Route path='/internal/ticket/facingIssue/form'  element={<FacingIssueForm/>}  />
        <Route path='/hiring/ticket/post'  element={<HiringPostForm/>}  />
        <Route path='/hiring/ticket/post/list'  element={<HiringPostsList/>}  />
      <Route path="/hiring/interviews/schedule" element={<InterviewSchedule/>}/>
<Route path="/hiring/interviews/list" element={<InterviewList/>}/>
<Route path="/hiring/interviews/:id" element={<InterviewDetails />} />
<Route path="/hiring/interviews/:id/call" element={<VideoCall />} />
      
      
      </Routes>
     
     
      </main>
      {/* {isChatOpen && activeTicketSerialNo && (
        <ChatBox 
          ticketId={activeTicketSerialNo} 
          onClose={handleChatClose} 
        />
      )} */}
      {/* {openChatId && <ChatBox ticketId={openChatId} onClose={closeChat} />} */}
     <ChatBox />
        
      <Footer/>
    </div>
    </ChatProvider>
  )
}

export default MainLyout;
