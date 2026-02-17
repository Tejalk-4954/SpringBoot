if(typeof window !== "undefined" && typeof window.global === "undefined"){
    window.global = window;
  }
import "./polyfills";   // <-- add this line FIRSTn
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import {BrowserRouter} from 'react-router-dom'
import { Provider } from 'react-redux';
import { store } from './state-management/store.jsx'




createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Provider store={store} >
    <BrowserRouter>

    <App />

    </BrowserRouter>
    </Provider>
  </StrictMode>,
)
