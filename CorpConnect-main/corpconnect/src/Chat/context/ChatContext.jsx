

// import { createContext, useState } from "react";

// export const ChatContext = createContext();

// export const ChatProvider = ({ children }) => {
//   const [jwt, setJwt] = useState("");
//   const [ticketId, setTicketId] = useState(null);
//   const [isChatOpen, setIsChatOpen] = useState(false);

//   const openChat = (ticket, token) => {
//     setTicketId(ticket);
//     setJwt(token);
//     setIsChatOpen(true);
//   };

//   const closeChat = () => setIsChatOpen(false);

//   return (
//     <ChatContext.Provider
//       value={{ jwt, ticketId, isChatOpen, openChat, closeChat }}
//     >
//       {children}
//     </ChatContext.Provider>
//   );
// };





// src/Chat/context/ChatContext.jsx
import { createContext, useState } from "react";

export const ChatContext = createContext();

export const ChatProvider = ({ children }) => {
  const [jwt, setJwt] = useState("");
  const [ticketId, setTicketId] = useState(null);
  const [isChatOpen, setIsChatOpen] = useState(false);

  const openChat = (ticket, token) => {
    console.log("🎯 openChat called:", ticket, token);
    setTicketId(ticket);
    setJwt(token);
    setIsChatOpen(true);
  };

  const closeChat = () => setIsChatOpen(false);

  return (
    <ChatContext.Provider
      value={{ jwt, ticketId, isChatOpen, openChat, closeChat }}
    >
      {children}
    </ChatContext.Provider>
  );
};
