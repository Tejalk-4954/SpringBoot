// // ChatInput.js
// import React, { useState } from "react";

// const ChatInput = ({ onSend, ticketId }) => {
//   const [text, setText] = useState("");

//   const doSend = async () => {
//     if (!text.trim()) return;
//     const payload = {
//       ticketId,
//       senderId: localStorage.getItem("userId"),
//       message: text.trim(),
//       attachments: null,
//       internal: false,
//     };
//     await onSend(payload);
//     setText("");
//   };

//   const onKeyDown = (e) => {
//     if (e.key === "Enter" && !e.shiftKey) {
//       e.preventDefault();
//       doSend();
//     }
//   };

//   return (
//     <div style={{ display: "flex", gap: 8, marginTop: 12 }}>
//       <textarea
//         value={text}
//         onChange={(e) => setText(e.target.value)}
//         onKeyDown={onKeyDown}
//         placeholder="Type a message..."
//         style={{ flex: 1, padding: 8, borderRadius: 6 }}
//       />
//       <button onClick={doSend} style={{ padding: "8px 12px", borderRadius: 6 }}>Send</button>
//     </div>
//   );
// };

// export default ChatInput;

// // src/components/ChatInput.js
// import React, { useState } from "react";

// const ChatInput = ({ ticketId, onSend }) => {
//   const [text, setText] = useState("");

//   const doSend = async () => {
//     if (!text.trim()) return;
//     const payload = {
//       ticketId,
//       senderId: localStorage.getItem("userId"),
//       message: text.trim(),
//       attachments: null,
//       internal: false,
//     };
//     await onSend(payload);
//     setText("");
//   };

//   const onKeyDown = (e) => {
//     if (e.key === "Enter" && !e.shiftKey) {
//       e.preventDefault();
//       doSend();
//     }
//   };

//   return (
//     <div style={{ display: "flex", gap: 8, marginTop: 12 }}>
//       <textarea
//         value={text}
//         onChange={(e) => setText(e.target.value)}
//         onKeyDown={onKeyDown}
//         placeholder="Type a message..."
//         style={{ flex: 1, padding: 8, borderRadius: 6 }}
//       />
//       <button onClick={doSend} style={{ padding: "8px 12px", borderRadius: 6 }}>Send</button>
//     </div>
//   );
// };

import { useState } from "react";
import { sendMessage } from "./services/StompClient";

export default function ChatInput({ ticketId }) {
  const [text, setText] = useState("");

  const handleSend = () => {
    if (!text.trim()) return;

    sendMessage(ticketId, {
      message: text,
      attachments: [],
      internal: false,
    });

    setText("");
  };

  return (
    <div className="chat-input">
      <input
        placeholder="Type..."
        value={text}
        onChange={(e) => setText(e.target.value)}
      />
      <button onClick={handleSend}>Send</button>
    </div>
  );
}
