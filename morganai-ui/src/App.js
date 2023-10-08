import './App.css';
import Header from  './components/Header/header';
import Chatbody from  './components/Chatbody/chatbody';
import Sidenav from  './components/Sidenav/sidenav';
import ChatBox from  './components/Chatbody/ChatBox';
import TypeIt from "typeit-react";



function App() {
  return (
    <div className="App">
        <Header></Header>
        <Sidenav></Sidenav>
        <Chatbody></Chatbody>
        {/* <ChatBox></ChatBox> */}
        {/* <TypeIt
        options={{
          strings: ["This will be typed!"],
          speed: 10,
          waitUntilVisible: true,
        }}
      /> */}
    </div>
  );
}

export default App;
