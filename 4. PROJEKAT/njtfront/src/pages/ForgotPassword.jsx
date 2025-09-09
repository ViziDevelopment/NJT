import React, { useState } from "react";
import http from "../api/http";
import "../css/auth.css";

export default function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState("");
  const [err, setErr] = useState("");

  async function handleSubmit(e) {
    e.preventDefault();
    setMsg(""); setErr(""); setLoading(true);
    try {
      await http.post("/auth/forgot-password", { email });
      setMsg("Ako nalog postoji, poslali smo link za reset lozinke.");
    } catch (e) {
      // i ovde je isto 200 u većini slučajeva, ali svakako prikazi generičnu poruku
      setMsg("Ako nalog postoji, poslali smo link za reset lozinke.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="auth-wrap">
      <div className="auth-card">
        <h2>Zaboravljena lozinka</h2>
        <p className="muted">Unesi mejl da pošaljemo link za reset.</p>

        {err && <div className="auth-alert">{err}</div>}
        {msg && <div className="auth-success">{msg}</div>}

        <form onSubmit={handleSubmit} className="auth-form">
          <div className="field">
            <label>Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <button className="btn-primary" disabled={loading}>
            {loading ? "Slanje…" : "Pošalji link"}
          </button>
        </form>

        <div className="auth-footer">
          <a href="/login">Nazad na prijavu</a>
        </div>
      </div>
    </div>
  );
}
