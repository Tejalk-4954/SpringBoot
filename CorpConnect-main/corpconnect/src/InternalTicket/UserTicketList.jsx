// import React, { useEffect, useState } from "react";
// import { useNavigate } from "react-router-dom";
// import { getMyTickets } from "./services/TicketServices";
// import { getUserById } from "../User/services/UserProfile"; // <-- new function

// const UserTicketList = ({ onChatStart }) => {
//   const navigate = useNavigate();
//   const [tickets, setTickets] = useState([]);

//   useEffect(() => {
//     const token = localStorage.getItem("accessToken");

//     const fetchTickets = async () => {
//       try {
//         const res = await getMyTickets(token);
//         setTickets(res.data);

//         // Fetch all assigned users
//         const assignedIds = res.data
//           .map((t) => t.assignedTo)
//           .filter((id) => id); // remove null/undefined

//         const uniqueIds = [...new Set(assignedIds)];

      
//       } catch (err) {
//         console.error("Error fetching tickets:", err);
//       }
//     };

//     fetchTickets();
//   }, []);

//     const handleChatClick = (ticketId) => {
//     if (onChatStart) onChatStart(ticketId);
//   };

//   return (
//     <div className="max-w-5xl mx-auto bg-white p-6 rounded-lg shadow-lg mt-6">
//       <h1 className="text-center text-2xl font-semibold text-gray-800 mb-6">
//         My Tickets
//       </h1>

//       <table className="w-full border-collapse">
//         <thead>
//           <tr className="bg-blue-600 text-white">
//             <th className="border border-gray-300 p-3 text-left">Ticket No</th>
//             <th className="border border-gray-300 p-3 text-left">Title</th>
//             <th className="border border-gray-300 p-3 text-left">
//               Description
//             </th>
//             <th className="border border-gray-300 p-3 text-left">Priority</th>
//             <th className="border border-gray-300 p-3 text-left">
//               Department
//             </th>
//             <th className="border border-gray-300 p-3 text-left">
//               Assigned To
//             </th>
//             <th className="border border-gray-300 p-3 text-left">Action</th>
//           </tr>
//         </thead>

//         <tbody>
//           {tickets.map((t, index) => (
//             // const idToUse = t.serialNo || t.id;
//             <tr
//               key={t.id}
//               className={index % 2 === 0 ? "bg-gray-50" : "bg-white"}
//             >
//               <td className="border border-gray-300 p-3">{t.ticketNumber}</td>
//               <td className="border border-gray-300 p-3">{t.title}</td>
//               <td className="border border-gray-300 p-3">{t.description}</td>
//               <td className="border border-gray-300 p-3">{t.priority}</td>
//               <td className="border border-gray-300 p-3">{t.departmentId}</td>
//               <td className="border border-gray-300 p-3">{t.assignedTo}
//                 {/* {t.assignedTo ? userMap[t.assignedTo] || "Loading..." : "Not Assigned"} */}
//               </td>
//               <td className="border border-gray-300 p-3">
//                 <div className="flex gap-3">
//                    <button
//                         onClick={() => handleChatClick(t.id)}
//                         className="text-white bg-indigo-600 hover:bg-indigo-700 focus:ring-4 focus:ring-indigo-500 focus:ring-opacity-50 font-medium rounded-lg text-sm px-4 py-2"
//                       >
//                         💬 Chat
//                       </button>
//                 </div>
//               </td>
//             </tr>
//           ))}
//         </tbody>
//       </table>
//     </div>
//   );
// };

// export default UserTicketList;




import React, { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { getMyTickets } from "./services/TicketServices";

// ⭐ import ChatContext
import { ChatContext } from "../Chat/context/ChatContext";

const UserTicketList = ({ onChatStart }) => {
  const navigate = useNavigate();
  const [tickets, setTickets] = useState([]);

  // ⭐ GET openChat from context
  const { openChat } = useContext(ChatContext);

  useEffect(() => {
    const load = async () => {
      const res = await getMyTickets(); // token automatically added in Axios
      setTickets(res.data);
    };
    load();
  }, []);

  const handleChatClick = (ticketId) => {
    if (onChatStart) onChatStart(ticketId);

    const token = localStorage.getItem("accessToken");
    console.log("🔥 UserTicketList → openChat:", ticketId, token);

    openChat(ticketId, token); // ⭐ IMPORTANT FIX
  };

  return (
    <div className="max-w-5xl mx-auto bg-white p-6 rounded shadow mt-6">
      <h1 className="text-2xl font-semibold mb-4 text-center">My Tickets</h1>

      <table className="w-full border">
        <thead className="bg-blue-600 text-white">
          <tr>
            <th className="p-3">Ticket No</th>
            <th className="p-3">Title</th>
            <th className="p-3">Description</th>
            <th className="p-3">Priority</th>
            <th className="p-3">Department</th>
            <th className="p-3">Assigned To</th>
            <th className="p-3">Action</th>
          </tr>
        </thead>

        <tbody>
          {tickets.map((t, i) => (
            <tr key={t.id} className={i % 2 === 0 ? "bg-gray-50" : "bg-white"}>
              <td className="p-3">{t.ticketNumber}</td>
              <td className="p-3">{t.title}</td>
              <td className="p-3">{t.description}</td>
              <td className="p-3">{t.priority}</td>
              <td className="p-3">{t.departmentId}</td>
              <td className="p-3">{t.assignedTo || "Not Assigned"}</td>

              <td className="p-3">
                <button
                  onClick={() => handleChatClick(t.id)}
                  className="bg-indigo-600 text-white px-4 py-2 rounded"
                >
                  💬 Chat
                </button>
              </td>
            </tr>
          ))}
        </tbody>

      </table>
    </div>
  );
};

export default UserTicketList;
