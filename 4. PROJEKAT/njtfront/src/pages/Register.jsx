import React, { useState } from "react";
import http from "../api/http";
import "../css/auth.css";

export default function Register({ onSuccess }) {
  const [form, setForm] = useState({ username: "", email: "", password: "" });
  const [loading, setLoading] = useState(false);
  const [err, setErr] = useState("");
  const [ok, setOk] = useState("");

  async function handleSubmit(e) {
    e.preventDefault();
    setErr(""); setOk("");
    setLoading(true);
    try {
      // 1) registracija
      await http.post("/auth/register", form);
      setOk("✅ Nalog je kreiran. Proverite email i kliknite na link za aktivaciju.");
      





      // const loginRes = await http.post("/auth/login", {
      //   username: form.username,
      //   password: form.password,
      // });
      // localStorage.setItem("token", loginRes.data.token);
      // localStorage.setItem("me", JSON.stringify(loginRes.data.user));
   
      if (typeof onSuccess === "function") onSuccess();
    } catch (e) {
      setErr(e?.response?.data?.message || "Registration failed");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="auth-wrap">
      <div className="auth-card">
        <h2>Create account</h2>
        <p className="muted">Join and start ordering</p>

        {err && <div className="auth-alert">{err}</div>}
        {ok && <div className="auth-success">{ok}</div>}

        <form onSubmit={handleSubmit} className="auth-form">
          <div className="field">
            <label>Username</label>
            <input
              type="text"
              value={form.username}
              onChange={(e) => setForm((f) => ({ ...f, username: e.target.value }))}
              required
            />
          </div>

          <div className="field">
            <label>Email</label>
            <input
              type="email"
              value={form.email}
              onChange={(e) => setForm((f) => ({ ...f, email: e.target.value }))}
              required
            />
          </div>

          <div className="field">
            <label>Password</label>
            <input
              type="password"
              value={form.password}
              onChange={(e) => setForm((f) => ({ ...f, password: e.target.value }))}
              required
              minLength={6}
            />
          </div>

          <button className="btn-primary" disabled={loading}>
            {loading ? "Creating…" : "Create account"}
          </button>
        </form>

        <div className="auth-footer">
          <span className="muted">Already have an account?</span>
          <a href="/login">Sign in</a>
        </div>
      </div>
    </div>
  );
}
