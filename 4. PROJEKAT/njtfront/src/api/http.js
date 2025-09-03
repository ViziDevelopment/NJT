import axios from "axios";

// Koristi Vite proxy (preporuka): u vite.config.js podesiti // server.proxy = { '/api': { target: 'http://localhost:8080', changeOrigin: true } } 
const http = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: { "Content-Type": "application/json" },
});


export default http;