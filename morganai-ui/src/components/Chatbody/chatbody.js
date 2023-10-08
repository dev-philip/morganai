import { useState } from 'react';
import './chatbody.css';
import TypeIt from "typeit-react";

function Chatbody() {

  const [chatMessage, setChatMessage] = useState('');
  const [chatMessageTrack, setChatMessageTrack] = useState('');
  const [activateBot, setActivateBot] = useState(0);
  const [activateHuman, setActivateHuman] = useState(0);

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      sendChatMessage();
      event.preventDefault(); // To prevent a newline or form submission
    }
  };

  const handleInputChange = (event) => {
    setChatMessage(event.target.value);
    setChatMessageTrack(event.target.value);
  };

  const sendChatMessage = () => {
    // alert(chatMessage);
    setTimeout(() => {
      setActivateBot(1);
    }, 2000)
    
    setActivateHuman(1);
    setChatMessageTrack("");
    
  }
  return (
    <div className="Chatbody">
      <div className='chatbody-container'>
            <div className='chat-container'>
            {(activateHuman === 1) && (
              <div className='chat-user-text'>
                <div className='werey-avater'>
                  <div class="avatar-circle">
                    <img src="https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg?t=st=1696722213~exp=1696722813~hmac=6fb296d005f9834f2ef6f3010e74fdf0522d0282facb30f9beb1165284eb2402" alt="User Avatar" />
                  </div>
                </div>
                 <div className='data'>{chatMessage}</div>
              </div>
            )}
               

                {(activateBot === 1) && (
                  <div className='chat-ai-reply'>
                    <div className='werey-avater'>
                      <div class="avatar-circle">
                        <img src="https://img.freepik.com/premium-vector/young-smiling-man-avatar-man-with-brown-beard-mustache-hair-wearing-yellow-sweater-sweatshirt-3d-vector-people-character-illustration-cartoon-minimal-style_365941-860.jpg?w=740" alt="User Avatar" />
                      </div>
                    </div>
                   
                    <div className='data'>
                    {/* Hello! I am a paralegal from morgan morgan. That's one of the well-known personal injury law firms in the U.S. How can I assist you today in your capacity as a paralegal from Morgan & Morgan? Whether you have questions about legal research, documentation, or any other topic, feel free to ask! */}
                      <TypeIt
                        options={{
                          strings: ["Hello! I am a paralegal from morgan morgan. That's one of the well-known personal injury law firms in the U.S. How can I assist you today in your capacity as a paralegal from Morgan & Morgan? Whether you have questions about legal research, documentation, or any other topic, feel free to ask!"],
                          speed: 10,
                          waitUntilVisible: true,
                        }}
                      />
                      </div>
                  </div>
                )}
                
            </div>
      </div>
      <div className="input-container">
          <form>
              <div class="file-container" title='Upload a file'>
                  <input type="file" id="fileInput" hidden />
                  <label for="fileInput">
                    <i class="fa fa-file-text-o" aria-hidden="true"></i>
                  </label>
              </div>
              <input className="input-text" value={chatMessageTrack} onChange={handleInputChange} onKeyDown={handleKeyDown} type="text" placeholder="Send a Message" />
              <button type="button" className="input-button" title='Send a Message' onClick={() => sendChatMessage()}>
                  <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
              </button>
          </form>
        </div>
    </div>
  );
}

export default Chatbody;
