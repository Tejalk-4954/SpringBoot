// // import React, { useEffect, useState } from 'react';
// // import { fetchAssignedTickets, closeTicket } from './services/TicketServices';

// // const getPriorityStyles = (priority) => {
// //   switch (priority) {
// //     case 'Urgent':
// //       return 'bg-red-100 text-red-800 border-red-400';
// //     case 'High':
// //       return 'bg-yellow-100 text-yellow-800 border-yellow-400';
// //     case 'Medium':
// //       return 'bg-blue-100 text-blue-800 border-blue-400';
// //     case 'Low':
// //     default:
// //       return 'bg-gray-100 text-gray-800 border-gray-400';
// //   }
// // };

// // const TicketList = ({ onChatStart }) => {
// //   const [tickets, setTickets] = useState([]);

// //   useEffect(() => {
// //     const loadTickets = async () => {
// //       const data = await fetchAssignedTickets();
// //       setTickets(data);
// //     };
// //     loadTickets();
// //   }, []);

// //   const handleChatClick = (ticketId) => {
// //     if (onChatStart) onChatStart(ticketId);
// //   };

// //   return (
// //     <div className="p-4 sm:p-6 bg-white shadow-lg rounded-xl">
// //       <h2 className="text-2xl font-semibold text-gray-800 mb-4">Departmental Tickets</h2>
// //       <div className="overflow-x-auto">
// //         <table className="min-w-full divide-y divide-gray-200">
// //           <thead className="bg-gray-50">
// //             <tr>
// //               <th className="px-3 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">S/N</th>
// //               <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-3/12">Title</th>
// //               <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-4/12">Description</th>
// //               <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Priority</th>
// //               <th className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Actions</th>
// //             </tr>
// //           </thead>
// //           <tbody className="bg-white divide-y divide-gray-200">
// //             {tickets.length > 0 ? (
// //               tickets.map((ticket) => {
// //                 const idToUse = ticket.serialNo || ticket.id;
// //                 return (
// //                   <tr key={ticket.id} className="hover:bg-gray-50 transition duration-150 ease-in-out">
// //                     <td className="px-3 py-4 whitespace-nowrap text-sm font-medium text-gray-900">{idToUse}</td>
// //                     <td className="px-6 py-4 whitespace-normal text-sm font-medium text-blue-600">{ticket.title}</td>
// //                     <td className="px-6 py-4 text-sm text-gray-500 max-w-sm">
// //                       <div className="truncate ...">{ticket.description}</div>
// //                     </td>
// //                     <td className="px-6 py-4 whitespace-nowrap">
// //                       <span className={`px-3 py-1 inline-flex text-xs leading-5 font-semibold rounded-full border ${getPriorityStyles(ticket.priority)}`}>
// //                         {ticket.priority}
// //                       </span>
// //                     </td>
// //                     <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium space-x-2">

// //                       {/* Chat button */}
// //                       <button
// //                         onClick={() => handleChatClick(idToUse)}
// //                         className="text-white bg-indigo-600 hover:bg-indigo-700 focus:ring-4 focus:ring-indigo-500 focus:ring-opacity-50 font-medium rounded-lg text-sm px-4 py-2"
// //                       >
// //                         💬 Chat
// //                       </button>

// //                       {/* Close button */}

// //                     </td>
// //                   </tr>
// //                 );
// //               })
// //             ) : (
// //               <tr>
// //                 <td colSpan="5" className="text-center py-8 text-gray-500">
// //                   No tickets found for your department.
// //                 </td>
// //               </tr>
// //             )}
// //           </tbody>
// //         </table>
// //       </div>
// //     </div>
// //   );
// // };

// // export default TicketList;






// import React, { useEffect, useState, useContext } from 'react';
// import { fetchAssignedTickets, closeTicket } from './services/TicketServices';

// // ⭐ Import ChatContext
// import { ChatContext } from "../Chat/context/ChatContext.jsx";

// const getPriorityStyles = (priority) => {
//   switch (priority) {
//     case 'Urgent':
//       return 'bg-red-100 text-red-800 border-red-400';
//     case 'High':
//       return 'bg-yellow-100 text-yellow-800 border-yellow-400';
//     case 'Medium':
//       return 'bg-blue-100 text-blue-800 border-blue-400';
//     case 'Low':
//     default:
//       return 'bg-gray-100 text-gray-800 border-gray-400';
//   }
// };

// const TicketList = ({ onChatStart }) => {
//   const [tickets, setTickets] = useState([]);

//   // ⭐ GET openChat function from context
//   const { openChat } = useContext(ChatContext);

//   useEffect(() => {
//     const loadTickets = async () => {
//       const data = await fetchAssignedTickets();
//       setTickets(data);
//     };
//     loadTickets();
//   }, []);

//   // ⭐ FIXED — Now calling openChat()
//   const handleChatClick = (ticketId) => {
//     if (onChatStart) onChatStart(ticketId);

//     const token = localStorage.getItem("accessToken");
//     console.log("🔥 TicketList calling openChat:", ticketId, token); // debug

//     openChat(ticketId, token);
//   };

//   return (
//     <div className="p-4 sm:p-6 bg-white shadow-lg rounded-xl">
//       <h2 className="text-2xl font-semibold text-gray-800 mb-4">Departmental Tickets</h2>
//       <div className="overflow-x-auto">
//         <table className="min-w-full divide-y divide-gray-200">
//           <thead className="bg-gray-50">
//             <tr>
//               <th className="px-3 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-1/12">S/N</th>
//               <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-3/12">Title</th>
//               <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-4/12">Description</th>
//               <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Priority</th>
//               <th className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider w-2/12">Actions</th>
//             </tr>
//           </thead>
//           <tbody className="bg-white divide-y divide-gray-200">
//             {tickets.length > 0 ? (
//               tickets.map((ticket) => {
//                 const idToUse = ticket.serialNo || ticket.id;
//                 return (
//                   <tr key={ticket.id} className="hover:bg-gray-50 transition duration-150 ease-in-out">
//                     <td className="px-3 py-4">{idToUse}</td>
//                     <td className="px-6 py-4 text-blue-600">{ticket.title}</td>
//                     <td className="px-6 py-4">{ticket.description}</td>
//                     <td className="px-6 py-4">
//                       <span className={`px-3 py-1 rounded-full border ${getPriorityStyles(ticket.priority)}`}>
//                         {ticket.priority}
//                       </span>
//                     </td>
//                     <td className="px-6 py-4 text-right">
//                       <button
//                         onClick={() => handleChatClick(idToUse)}
//                         className="text-white bg-indigo-600 hover:bg-indigo-700 rounded-lg px-4 py-2"
//                       >
//                         💬 Chat
//                       </button>
//                     </td>
//                   </tr>
//                 );
//               })
//             ) : (
//               <tr>
//                 <td colSpan="5" className="text-center py-8 text-gray-500">
//                   No tickets found for your department.
//                 </td>
//               </tr>
//             )}
//           </tbody>
//         </table>
//       </div>
//     </div>
//   );
// };

// export default TicketList;




import React, { useEffect, useState, useContext } from 'react';
import { fetchAssignedTickets } from './services/TicketServices';

// ⭐ Import ChatContext
import { ChatContext } from "../Chat/context/ChatContext.jsx";

const getPriorityStyles = (priority) => {
  switch (priority) {
    case 'Urgent': return 'bg-red-100 text-red-800 border-red-400';
    case 'High': return 'bg-yellow-100 text-yellow-800 border-yellow-400';
    case 'Medium': return 'bg-blue-100 text-blue-800 border-blue-400';
    default: return 'bg-gray-100 text-gray-800 border-gray-400';
  }
};

const TicketList = ({ onChatStart }) => {
  const [tickets, setTickets] = useState([]);

  // ⭐ GET openChat from context
  const { openChat } = useContext(ChatContext);

  useEffect(() => {
    const load = async () => {
      const data = await fetchAssignedTickets();
      setTickets(data);
    };
    load();
  }, []);

  const handleChatClick = (ticketId) => {
    if (onChatStart) onChatStart(ticketId);

    const token = localStorage.getItem("accessToken");
    console.log("🔥 TicketList → openChat:", ticketId, token);

    openChat(ticketId, token);
  };

  return (
    <div className="p-5 bg-white shadow rounded-xl">
      <h2 className="text-xl font-semibold mb-4">Departmental Tickets</h2>

      <table className="min-w-full border">
        <thead className="bg-gray-100">
          <tr>
            <th className="p-2">S/N</th>
            <th className="p-2">Title</th>
            <th className="p-2">Description</th>
            <th className="p-2">Priority</th>
            <th className="p-2">Action</th>
          </tr>
        </thead>

        <tbody>
          {tickets.map((t) => {
            const idToUse = t.serialNo || t.id;

            return (
              <tr key={t.id}>
                <td className="p-2">{idToUse}</td>
                <td className="p-2 text-blue-600">{t.title}</td>
                <td className="p-2">{t.description}</td>

                <td className="p-2">
                  <span className={`px-3 py-1 rounded ${getPriorityStyles(t.priority)}`}>
                    {t.priority}
                  </span>
                </td>

                <td className="p-2">
                  <button
                    onClick={() => handleChatClick(idToUse)}
                    className="bg-indigo-600 text-white px-4 py-2 rounded"
                  >
                    💬 Chat
                  </button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default TicketList;
