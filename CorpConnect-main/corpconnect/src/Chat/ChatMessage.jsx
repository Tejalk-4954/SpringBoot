// // ChatMessage.js
// import React from "react";

// const ChatMessage = ({ message }) => {
//   // message fields: id, ticketId, senderId, message, attachments, createdAt, internal
//   const myId = localStorage.getItem("userId");
//   const isSelf = String(message.senderId) === String(myId);

//   return (
//     <div style={{ display: "flex", justifyContent: isSelf ? "flex-end" : "flex-start", margin: "6px 0" }}>
//       <div style={{
//         maxWidth: "80%",
//         padding: "8px 12px",
//         borderRadius: 12,
//         boxShadow: "rgba(0,0,0,0.06) 0 1px 2px",
//         background: isSelf ? "#4f46e5" : "#e5e7eb",
//         color: isSelf ? "#fff" : "#111827",
//         fontSize: 14,
//       }}>
//         <div style={{ whiteSpace: "pre-wrap" }}>{message.message}</div>
//         {message.attachments && message.attachments.length > 0 && (
//           <div style={{ marginTop: 8 }}>
//             {message.attachments.map((a) => (
//               <a
//                 key={a}
//                 href={`http://localhost:8090/api/tickets/attachments/download-url?objectKey=${encodeURIComponent(a)}`}
//                 target="_blank"
//                 rel="noreferrer"
//                 style={{ display: "inline-block", marginRight: 8, fontSize: 12 }}
//               >
//                 Attachment
//               </a>
//             ))}
//           </div>
//         )}
//         <div style={{ fontSize: 11, opacity: 0.7, marginTop: 6 }}>
//           {new Date(message.createdAt).toLocaleString()}
//         </div>
//       </div>
//     </div>
//   );
// };

export default function ChatMessage({ msg }) {
  return (
    <div className="chat-msg">
      <b>{msg.senderId}</b>: {msg.message}
      {msg.attachments && msg.attachments.length > 0 && (
        <div>
          {msg.attachments.map((att) => (
            <a key={att} href={`/api/attachments/download-url?key=${att}`}>
              📎 Attachment
            </a>
          ))}
        </div>
      )}
    </div>
  );
}
