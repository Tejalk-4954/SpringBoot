// import { useEffect, useState } from "react";
// import { createStompClient } from "./services/StompClient";
// import { getChatHistory } from "./services/ChatServices";

// const ChatBox = ({ ticketId }) => {
//   const [messages, setMessages] = useState([]);

//   useEffect(() => {
//     const client = createStompClient();

//     // Load chat history
//     getChatHistory(ticketId).then((res) => setMessages(res));

//     client.onConnect = () => {
//       console.log("✅ Connected to WebSocket");

//       // Subscribe to topic
//       client.subscribe(`/topic/ticket/${ticketId}`, (msg) => {
//         const body = JSON.parse(msg.body);
//         setMessages((prev) => [...prev, body]);
//       });
//     };

//     client.activate();

//     return () => {
//       console.log("Cleaning up...");
//       client.deactivate();
//     };
//   }, [ticketId]);

//   return (
//     <div>
//       {messages.map((m, i) => (
//         <div key={i}>{m.text}</div>
//       ))}
//     </div>
//   );
// };

// export default ChatBox;

import { useContext, useEffect } from "react";
import { ChatContext } from "./context/ChatContext";
import ChatInput from "./ChatInput";
import ChatMessage from "./ChatMessage";
import useChatWebSocket from "./hooks/useChatWebSocket";
import { fetchChatHistory } from "./services/ChatServices";
import "./ChatBox.css";


export default function ChatBox() {
  const { jwt, ticketId, isChatOpen, closeChat } = useContext(ChatContext);
  const { messages, setMessages } = useChatWebSocket(jwt, ticketId);

  useEffect(() => {
    if (ticketId) {
      fetchChatHistory(ticketId).then((res) => {
        setMessages(res.data);
      });
    }
  }, [ticketId]);

  if (!isChatOpen) return null;

  return (
    <div className="chat-popup">
      <div className="chat-header">
        <h3>Chat for {ticketId}</h3>
        <button onClick={closeChat}>X</button>
      </div>

      <div className="chat-body">
        {messages.map((msg) => (
          <ChatMessage key={msg.id} msg={msg} />
        ))}
      </div>

      <ChatInput ticketId={ticketId} />
    </div>
  );
}
