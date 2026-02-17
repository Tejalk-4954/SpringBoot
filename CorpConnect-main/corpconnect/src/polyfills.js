// Fix for SockJS requiring global in modern bundlers
window.global = window;

// Optional but helps prevent other SockJS errors
window.process = window.process || {
  env: { NODE_ENV: 'development' }
};
