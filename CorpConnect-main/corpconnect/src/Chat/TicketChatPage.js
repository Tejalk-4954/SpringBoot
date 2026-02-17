import React, { useState, useEffect } from "react";
import { getChatHistory } from "../api/ChatApi";
import { useChatWebSocket } from "../hooks/useChatWebSocket";
import ChatMessage from "../components/Chat/ChatMessage";
import ChatInput from "../components/Chat/ChatInput";

const TicketChatPage = ({ ticketId }) => {
  const [messages, setMessages] = useState([]);

  const { sendMessage } = useChatWebSocket(ticketId, (newMsg) => {
    setMessages((prev) => [...prev, newMsg]);
  });

  useEffect(() => {
    const fetchHistory = async () => {
      const res = await getChatHistory(ticketId);
      setMessages(res.data);
    };
    fetchHistory();
  }, [ticketId]);

  return (
    <div className="chat-page">
      <div className="messages">
        {messages.map((msg) => (
          <ChatMessage key={msg.id} message={msg} />
        ))}
      </div>
      <ChatInput onSend={(msg) => sendMessage(msg)} ticketId={ticketId} />
    </div>
  );
};

export default TicketChatPage;
