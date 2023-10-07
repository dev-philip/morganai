import './chatbody.css';

function Chatbody() {
  return (
    <div className="Chatbody">
        <div className="input-container">
                <form>
                    <input className="input-text" type="text" placeholder="Send a Message" />
                    <button type="button" className="input-button">
                        <i class="fa fa-paper-plane-o" aria-hidden="true"></i>
                    </button>
                </form>
        </div>
    </div>
  );
}

export default Chatbody;
