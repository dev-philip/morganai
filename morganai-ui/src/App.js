import './App.css';
import Header from  './components/Header/header';
import Chatbody from './components/Chatbody/chatbody';
import Sidenav from './components/Sidenav/sidenav';

// import TypeIt from "typeit-react";
import React from 'react';
import { Link, Route, Routes} from 'react-router-dom';
import Login from  './components/pages/Login';
import SignUp from  './components/pages/SignUp';
import Home from  './Home';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/" element={<Home />} />
      </Routes> 
    </div>
  );
}

export default App;
