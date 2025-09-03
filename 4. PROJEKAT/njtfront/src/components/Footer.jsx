import React from 'react'

export const Footer = () => {
  return (
     <footer id="contact" className="footer">
        <div className="container foot">
          <div className="brand"><div className="dot" /><span>SwiftBite</span></div>
          <ul className="foot-links">
            <li><a href="#">O nama</a></li>
            <li><a href="#">Karijere</a></li>
            <li><a href="#">Uslovi</a></li>
            <li><a href="#">Privatnost</a></li>
          </ul>
          <small>© {new Date().getFullYear()} SwiftBite</small>
        </div>
      </footer>
  )
}
