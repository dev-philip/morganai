import './header.css';
import Logo from '../../img/MorganLogo.svg';

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
                        <span className="text-1">Login</span>
                        <span className="text-2">Sign-up</span>
                    </div>
                </div>
               
        </div>
    </div>
  );
}

export default Header;
