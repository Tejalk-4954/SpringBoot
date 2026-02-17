// // src/Hiring/VideoCall.jsx
// import React, { useEffect, useRef, useState } from "react";
// import { useParams, useNavigate } from "react-router-dom";
// import { getInterviewById } from "../InternalTicket/services/InterviewServices";

// export default function VideoCall() {
//   const { id } = useParams();
//   const containerRef = useRef(null);
//   const apiRef = useRef(null);
//   const navigate = useNavigate();
//   const [info, setInfo] = useState(null);

//   useEffect(() => {
//     getInterviewById(id).then(r => setInfo(r.data)).catch(() => {});
//   }, [id]);

//   useEffect(() => {
//     const start = () => {
//       if (!window.JitsiMeetExternalAPI) {
//         console.error("Jitsi script not loaded");
//         return;
//       }

//       const domain = "meet.jit.si";
//       const options = {
//         roomName: `interview-${id}`, // unique
//         parentNode: containerRef.current,
//         userInfo: {
//           displayName: localStorage.getItem("userName") || "User",
//           email: localStorage.getItem("userEmail") || ""
//         },
//         configOverwrite: { enableWelcomePage: false },
//         interfaceConfigOverwrite: { DEFAULT_REMOTE_DISPLAY_NAME: "Participant" },
//       };

//       apiRef.current = new window.JitsiMeetExternalAPI(domain, options);

//       apiRef.current.addEventListener("videoConferenceLeft", () => {
//         navigate(-1);
//       });
//     };

//     const t = setTimeout(start, 300);
//     return () => {
//       clearTimeout(t);
//       if (apiRef.current) {
//         try { apiRef.current.dispose(); } catch (e) {}
//         apiRef.current = null;
//       }
//     };
//   }, [id, navigate]);

//   return (
//     <div className="min-h-screen bg-blue-50 p-6">
//       <div className="max-w-5xl mx-auto bg-white rounded-lg shadow p-4">
//         <div className="flex items-center justify-between mb-4">
//           <div>
//             <h2 className="text-xl font-semibold text-blue-700">Interview Call</h2>
//             <div className="text-sm text-gray-600">Interview: {id} {info && `• ${info.interviewDate}`}</div>
//           </div>
//           <div>
//             <button onClick={() => navigate(-1)} className="px-3 py-2 border rounded">Close</button>
//           </div>
//         </div>

//         <div className="w-full h-[640px] bg-black rounded overflow-hidden">
//           <div ref={containerRef} style={{ height: "100%" }} />
//         </div>
//       </div>
//     </div>
//   );
// }



import React, { useEffect, useRef } from "react";

export default function VideoCall() {
  const containerRef = useRef(null);

  useEffect(() => {
    const domain = "meet.jit.si";
    const options = {
      roomName: "CorpConnectInterviewRoom",
      width: "100%",
      height: 600,
      parentNode: containerRef.current,
    };
    new window.JitsiMeetExternalAPI(domain, options);
  }, []);

  return (
    <div className="p-6 max-w-5xl mx-auto">
      <h2 className="text-3xl font-bold text-blue-700 mb-4">Video Interview</h2>
      <div ref={containerRef}></div>
    </div>
  );
}
