import React, { useMemo, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import http from "../api/http";
import "../css/auth.css";

export default function ResetPassword() {
  const [sp] = useSearchParams();
  const nav = useNavigate();

  // token čitamo iz URL-a: /reset?token=....
  const token = useMemo(() => sp.get("token") || "", [sp]);
  const [password, setPassword] = useState("");
  const [confirm, setConfirm] = useState("");
  
  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState("");
  const [err, setErr] = useState("");

  const invalidLink = !token;

  async function handleSubmit(e) {
    e.preventDefault();
    setErr(""); setMsg("");
    if (password.length < 6) {
      setErr("Lozinka mora imati bar 6 karaktera.");
      return;
    }
    if (password !== confirm) {
      setErr("Lozinke se ne poklapaju.");
      return;
    }
    setLoading(true);
    try {
      await http.post("/auth/reset-password", { token, password });
      setMsg("Lozinka je promenjena. Sada se možete prijaviti.");
      // opciono: redirect nakon 2s
      setTimeout(() => nav("/login"), 1500);
    } catch (e) {
      setErr(e?.response?.data || "Link je neispravan ili istekao.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="auth-wrap">
      <div className="auth-card">
        <h2>Nova lozinka</h2>
        <p className="muted">Unesi i potvrdi novu lozinku.</p>

        {invalidLink && (
          <div className="auth-alert">
            Link nije validan. Zatraži novi na stranici{" "}
            <a href="/forgot">zaboravljena lozinka</a>.
          </div>
        )}

        {err && <div className="auth-alert">{err}</div>}
        {msg && <div className="auth-success">{msg}</div>}

        {!invalidLink && (
          <form onSubmit={handleSubmit} className="auth-form">
            <div className="field">
              <label>Nova lozinka</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                minLength={6}
                required
              />
            </div>
            <div className="field">
              <label>Potvrdi lozinku</label>
              <input
                type="password"
                value={confirm}
                onChange={(e) => setConfirm(e.target.value)}
                minLength={6}
                required
              />
            </div>
            <button className="btn-primary" disabled={loading}>
              {loading ? "Snima se…" : "Sačuvaj lozinku"}
            </button>
          </form>
        )}

        <div className="auth-footer">
          <a href="/login">Nazad na prijavu</a>
        </div>
      </div>
    </div>
  );
}
