import { useState, useRef, useEffect } from 'react';
import './chatbody.css';
import TypeIt from "typeit-react";
import axios from 'axios';

function Chatbody() {

  const [chatMessage, setChatMessage] = useState('');
  const [chatMessageTrack, setChatMessageTrack] = useState('');
  const [aiMessage, setAiMessage] = useState('');
  const [chatMessageTempDb, setChatMessageTempDb] = useState([]);
  const [chatMessageDb, setChatMessageDb] = useState([
   
  ]);
  const [activateBot, setActivateBot] = useState(0);
  const [activateHuman, setActivateHuman] = useState(0);

  const bottomRef = useRef(null);

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      sendChatMessage();
      event.preventDefault(); // To prevent a newline or form submission
    }
  };

  const handleInputChange = (event) => {
    
   setChatMessageTrack(event.target.value);
  };

  const manipulateDataDb = (chatMessage, aiResponse) => {


    // Create a new message object
  const newMessage = {
    id: (chatMessageDb.length + 1).toString(),  // Incrementing ID based on array length (adjust if needed)
    userInput: chatMessage,
    aiResponse: aiResponse,
    date: new Date().toLocaleDateString(),
    time: new Date().toLocaleTimeString()
  };

  // Append the new message object to the chatMessageDb array
  setChatMessageDb(prevDb => [...prevDb, newMessage]);
  }

  const scrollToBottom = () => {
    bottomRef.current.scrollIntoView({ behavior: 'smooth' });
  };


  useEffect(() => {
    scrollToBottom();
  }, [chatMessageDb]);

  const sendChatMessage = () => {
    // alert(chatMessage);
    setChatMessage(chatMessageTrack);

    axios.get('http://localhost:8080/api/ai/' + chatMessageTrack)
            .then(response => {
              // alert("success");
              console.log(response);
              console.log(response.data);
              manipulateDataDb(chatMessageTrack, response.data);
              setAiMessage(response.data);
            })
            .catch(error => {
                alert("error occurred");
                console.log(error);
            });

    setTimeout(() => {
      setActivateBot(1);
    }, 2000)
    
    setActivateHuman(1);
    setChatMessageTrack("");
    
  }
  return (
    <div className="Chatbody">
      <div className='chatbody-container'>
      {chatMessageDb.map((dataItem) => (
        <>
          <div className='chat-container' key={dataItem.id}>
              <div className='chat-user-text'>
                <div className='werey-avater'>
                  <div className="avatar-circle">
                    <img src="https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg?t=st=1696722213~exp=1696722813~hmac=6fb296d005f9834f2ef6f3010e74fdf0522d0282facb30f9beb1165284eb2402" alt="User Avatar" />
                  </div>
                </div>
                 <div className='data'>{dataItem.userInput}</div>
              </div>
              <div className='time'>{dataItem.date + " " + dataItem.time}</div>
            
                
                  <div className='chat-ai-reply'>
                    <div className='werey-avater'>
                      <div className="avatar-circle">
                        <img src="https://img.freepik.com/premium-vector/young-smiling-man-avatar-man-with-brown-beard-mustache-hair-wearing-yellow-sweater-sweatshirt-3d-vector-people-character-illustration-cartoon-minimal-style_365941-860.jpg?w=740" alt="User Avatar" />
                      </div>
                    </div>
                   
                    <div className='data'>
                      {/* {dataItem.aiResponse} */}
                      <TypeIt
                        options={{
                          strings: [`${dataItem.aiResponse}`],
                          speed: 10,
                          waitUntilVisible: true,
                          afterComplete: function (instance) {
                            instance.destroy();
                          }
                        }}
                      />
                      </div>
                  </div>
                <hr />
          </div>
          </>
        ))}


              {/* important break line */}
    
          <div ref={bottomRef}></div>
      </div>



                
      

      <div className="input-container">
          <form>
              <div className="file-container">
                  {/* <input type="file" id="fileInput" hidden /> */}
                  <label for="fileInput">
                  <i className="fa fa-keyboard-o" aria-hidden="true"></i>

                  </label>
              </div>
              <input className="input-text" value={chatMessageTrack} onChange={handleInputChange} onKeyDown={handleKeyDown} type="text" placeholder="Send a Message" />
              <button type="button" className="input-button" title='Send a Message' onClick={() => sendChatMessage()}>
                  <i className="fa fa-paper-plane-o" aria-hidden="true"></i>
              </button>
          </form>
        </div>
    </div>
  );
}

export default Chatbody;
