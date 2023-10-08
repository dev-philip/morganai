import './header.css';
import Logo from '../../img/MorganLogo.svg';
import { Link} from 'react-router-dom';

function Header() {
  return (
    <div className="Header">
        <div className='header-container'>
                <div>
                    <a href="https://portal.forthepeople.com/">
                        <img src={Logo} className="companyLogo" alt="company Logo" />
                    </a>
                </div>
                <div className="header-text-wrapper">
                    <div className="header-text"> 
                        <span className="text-1">
                            <Link to='/login' className="customLink">Login</Link>
                        </span>
                        <span className="text-2">
                            <Link to='/signup' className="customLink">SignUp</Link>
                        </span>
                    </div>
                </div>
               
        </div>
    </div>
  );
}

export default Header;
