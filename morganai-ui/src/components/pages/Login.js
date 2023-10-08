import React from 'react';
import './style.css'; 
import { Link} from 'react-router-dom';

function Login() {
  return (
    <div className="background">
      <div className="container">
        <h2>Login</h2>
        <form>
          <div className="form-group">
            <label htmlFor="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" />
          </div>
          <button type="submit">
            <Link to="/">Submit</Link>
          </button>
          <button type="button">
            <Link to="/">Back</Link>
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;
