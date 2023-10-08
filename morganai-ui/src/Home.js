import './App.css';
import Header from  './components/Header/header';
import Chatbody from './components/Chatbody/chatbody';
import Sidenav from './components/Sidenav/sidenav';

// import TypeIt from "typeit-react";
import React from 'react';

function Home() {
  return (
    <div className="App">
      <Header></Header>
      <Sidenav></Sidenav>
      <Chatbody></Chatbody>
    </div>
  );
}

export default Home;