// import { Client } from "@stomp/stompjs";
// import SockJS from "sockjs-client";

// /**
//  * Create a STOMP client configured to use SockJS and JWT via connectHeaders.
//  * - tokenKey: string key in localStorage (e.g. "accessToken" or "token")
//  */
// export const createStompClient = (tokenKey = "accessToken") => {
//   const token = localStorage.getItem(tokenKey);

//   const client = new Client({
//     // Use SockJS; backend endpoint: /ws
//     webSocketFactory: () => new SockJS("http://localhost:8090/ws?token=${token}"),
//     reconnectDelay: 5000,
//     heartbeatIncoming: 0,
//     heartbeatOutgoing: 20000,
//     debug: (msg) => {
//       // comment this out in production
//       // console.log("[STOMP DEBUG]", msg);
//     },
//     // do not auto-connect here; caller calls .activate()
//   });

//   // We'll attach connectHeaders when activating so token can be refreshed between reconnects
//   client.buildConnectHeaders = () => {
//     const t = localStorage.getItem(tokenKey);
//     return t ? { Authorization: `Bearer ${t}` } : {};
//   };

//   // patch client to include connect headers on connect
//   const originalActivate = client.activate.bind(client);
//   client.activate = (opts = {}) => {
//     // Add connectHeaders using latest token
//     const connOpts = {
//       ...opts,
//       connectHeaders: {
//         ...(opts.connectHeaders || {}),
//         ...(client.buildConnectHeaders ? client.buildConnectHeaders() : {}),
//       },
//     };
//     return originalActivate(connOpts);
//   };

//   return client;
// };

// import SockJS from "sockjs-client";
// import { over } from "stompjs";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

let stompClient = null;
let subscription = null;

export const connectWebSocket = (token, ticketId, onMessage) => {
  return new Promise((resolve, reject) => {
    
    // ⭐ Always connect to backend, not React (5173)
    const token = localStorage.getItem("accessToken");
    const socket = new SockJS(`http://localhost:8090/ws?token=${token}`);

    stompClient = Stomp.over(socket);
    stompClient.debug = null;

    stompClient.connect(
      {},
      () => {
        console.log("🟢 WebSocket Connected for:", ticketId);

        subscription = stompClient.subscribe(
          `/topic/tickets/${ticketId}`,
          (msg) => onMessage(JSON.parse(msg.body))
        );

        resolve(true);
      },
      (err) => {
        console.error("❌ WebSocket error:", err);
        reject(err);
      }
    );
  });
};

export const sendMessage = (ticketId, payload) => {
  if (!stompClient || !stompClient.connected) return;

  stompClient.send(`/app/chat.send.${ticketId}`, {}, JSON.stringify(payload));
};

export const disconnectWebSocket = () => {
  if (subscription) subscription.unsubscribe();
  if (stompClient) stompClient.disconnect();
  console.log("🔴 WebSocket disconnected");
};
