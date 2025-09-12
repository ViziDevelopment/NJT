import React from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import "../css/navbar.css";  

function isAuthed() {
  return !!localStorage.getItem("token");
}
function getMe() {
  try { return JSON.parse(localStorage.getItem("me") || "null"); } catch { return null; }
}

export default function Navbar() {
  const nav = useNavigate();
  const authed = isAuthed();
  const me = getMe();

  function handleLogout() {
    localStorage.removeItem("token");
    localStorage.removeItem("me");
    nav("/login");
  }

  const active = ({ isActive }) => ({
    color: isActive ? "var(--text)" : "var(--muted)",
    fontWeight: isActive ? 700 : 500
  });

  return (
    <header className="nav">
      <div className="container nav-inner">
        {/* logo/brand */}
        <div className="brand"><div className="dot" /><span>SwiftBite</span></div>

        {/* links */}
        <nav className="nav-links">
          <NavLink to="/" style={active}>Početna</NavLink>

          {authed && me?.role === "ADMIN" && (
            <>
              <NavLink to="/proizvodi" style={active}>Proizvodi</NavLink>
              <NavLink to="/restorani" style={active}>Restorani</NavLink>
              <NavLink to="/admin/orders" style={active}>Narudžbine</NavLink>
            </>
          )}

          {authed && me?.role === "USER" && (
            <>
              <NavLink to="/restaurants" style={active}>Restorani</NavLink>
              <NavLink to="/cart" style={active}>Korpa</NavLink>
            </>
          )}
        </nav>

        {/* actions (login/register ili user+logout) */}
        <div className="nav-actions">
          {!authed ? (
            <>
              <Link className="btn-outline" to="/login">Prijava</Link>
              <Link className="btn-primary" to="/register">Registracija</Link>
            </>
          ) : (
            <>
              <span className="user-chip">{me?.username ?? "Korisnik"}</span>
              <button className="btn-outline" onClick={handleLogout}>Odjava</button>
            </>
          )}
        </div>
      </div>
    </header>
  );
}
