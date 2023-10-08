import './App.css';
import Header from  './components/Header/header';
import Chatbody from './components/Chatbody/chatbody';
import Sidenav from './components/Sidenav/sidenav';

// import TypeIt from "typeit-react";
import React from 'react';
import { Route, Routes} from 'react-router-dom';
import Login from  './components/pages/Login';
import SignUp from  './components/pages/SignUp';

function Home() {
  return (
    <div className="App">
      {/* <Header></Header> */}
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/" element={<Header />} />
      </Routes> 
      <Sidenav></Sidenav>
      <Chatbody></Chatbody>
    </div>
  );
}

export default Home;