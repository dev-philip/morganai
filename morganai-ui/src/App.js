import './App.css';
import Header from  './components/Header/header';
import Chatbody from  './components/Chatbody/chatbody';
import ChatBox from  './components/Chatbody/ChatBox';



function App() {
  return (
    <div className="App">
        <Header></Header>
        {/* <Chatbody></Chatbody> */}
        <ChatBox></ChatBox>
    </div>
  );
}

export default App;
