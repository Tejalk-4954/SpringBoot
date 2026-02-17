// // useChatSocket.js
// import { useEffect, useRef, useState, useCallback } from "react";
// import { createStompClient } from "../lib/stompClient";

// /**
//  * Hook to manage STOMP socket per ticketId.
//  *
//  * onMessage: (messageObject) => void
//  * onAuthError: () => void  // called when server rejects connection or token missing
//  */
// export const useChatSocket = ({ ticketId, onMessage, onAuthError }) => {
//   const clientRef = useRef(null);
//   const subscriptionRef = useRef(null);
//   const [connected, setConnected] = useState(false);

//   const connect = useCallback(() => {
//     if (!ticketId) return;

//     const token = localStorage.getItem("accessToken") || localStorage.getItem("token");
//     if (!token) {
//       if (onAuthError) onAuthError();
//       return;
//     }

//     // create new client for each connect attempt to avoid weird states
//     const client = createStompClient();
//     clientRef.current = client;

//     client.onConnect = (frame) => {
//       setConnected(true);
//       // Subscribe to the ticket topic. Backend must publish to this.
//       const topic = `/topic/tickets/${ticketId}`;
//       subscriptionRef.current = client.subscribe(topic, (msg) => {
//         try {
//           const body = JSON.parse(msg.body);
//           if (onMessage) onMessage(body);
//         } catch (e) {
//           console.error("Failed to parse message body", e);
//         }
//       });
//     };

//     client.onStompError = (frame) => {
//       console.error("STOMP error", frame);
//       // If server rejects due to auth it may return a specific message
//       if (frame && frame.headers && frame.headers["message"] && /Auth|Unauthorized|Forbidden/i.test(frame.headers["message"])) {
//         if (onAuthError) onAuthError();
//         client.deactivate();
//       }
//     };

//     client.onWebSocketClose = (evt) => {
//       setConnected(false);
//     };

//     client.onWebSocketError = (evt) => {
//       console.error("WebSocket error", evt);
//     };

//     client.activate();
//   }, [ticketId, onMessage, onAuthError]);

//   // connect when ticketId changes
//   useEffect(() => {
//     connect();
//     return () => {
//       try {
//         subscriptionRef.current?.unsubscribe();
//       } catch (e) {}
//       try {
//         clientRef.current?.deactivate();
//       } catch (e) {}
//       subscriptionRef.current = null;
//       clientRef.current = null;
//       setConnected(false);
//     };
//   }, [connect]);

//   const send = useCallback(
//     (payload) => {
//       const client = clientRef.current;
//       if (!client || !client.connected) {
//         console.warn("STOMP client not connected. Falling back to REST POST is recommended.");
//         return Promise.reject(new Error("not-connected"));
//       }
//       const destination = `/app/chat.send.${ticketId}`;
//       return new Promise((resolve, reject) => {
//         try {
//           client.publish({
//             destination,
//             body: JSON.stringify(payload),
//             // optionally set headers per-message
//           });
//           resolve(true);
//         } catch (err) {
//           reject(err);
//         }
//       });
//     },
//     [ticketId]
//   );

//   return {
//     connected,
//     send,
//     disconnect: () => {
//       try {
//         subscriptionRef.current?.unsubscribe();
//       } catch (e) {}
//       try {
//         clientRef.current?.deactivate();
//       } catch (e) {}
//     },
//   };
// };// ⭐ Your original imports
import { useEffect, useState } from "react";
import {
  connectWebSocket,
  disconnectWebSocket,
} from "../services/StompClient";

// ⭐ Custom WebSocket Hook for Chat
export default function useChatWebSocket(jwt, ticketId) {
  
  // ⭐ Your original message state
  const [messages, setMessages] = useState([]);

  // ⭐ NEW: Track WebSocket connection status
  const [connected, setConnected] = useState(false);

  useEffect(() => {
    // ⭐ If required values are missing, stop connection
    if (!jwt || !ticketId) {
      console.warn("❌ WebSocket not started — missing jwt or ticketId");
      return;
    }

    console.log("🔄 Connecting WebSocket for Ticket:", ticketId);

    // ⭐ Establish WebSocket connection
    connectWebSocket(jwt, ticketId, (msg) => {
      setConnected(true); // ⭐ Mark connection as active
      setMessages((prev) => [...prev, msg]); // ⭐ Your original message update
    });

    // ⭐ Cleanup function when component unmounts or ticketId changes
    return () => {
      console.log("🔌 Disconnecting WebSocket");
      disconnectWebSocket();
      setConnected(false); // ⭐ Mark as disconnected
    };
  }, [jwt, ticketId]); // ⭐ Reconnect when jwt or ticket changes

  // ⭐ Return same original data + NEW connected state
  return { messages, setMessages, connected };
}
