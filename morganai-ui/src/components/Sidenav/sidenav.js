import React, { useState } from 'react';
import './sidenav.css';

function Sidenav() {
    const [query, setQuery] = useState('');

    const handleSearch = () => {
        console.log("User searched for:", query);
        alert(query);
    };

  return (
    <div className="Sidenav">
        <div className='sidenav-container'>
            {/* <div className="search-container">
                <input 
                    className="search-input"
                    type="text"
                    placeholder="Search..."
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                />
                <button className="search-button" onClick={handleSearch}>
                    <i class="fa fa-search" aria-hidden="true"></i>
                </button>
            </div> */}
        </div>
    </div>
  );
}

export default Sidenav;
