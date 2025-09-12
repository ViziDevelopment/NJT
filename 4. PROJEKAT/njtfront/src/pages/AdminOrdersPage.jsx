 
import React, { useEffect, useState } from "react";
import http from "../api/http";
import "../css/admin-orders.css";

 
const STATUSES = ["CREATED", "CONFIRMED", "CANCELED", "COMPLETED"];
 
export default function AdminOrdersPage() {
  const [orders, setOrders] = useState([]);
  const [productsById, setProductsById] = useState({});
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState("");

 
  const fmtDate = (s) => (s ? s.replace("T", " ").slice(0, 19) : "");
  const totalOf = (items) =>
    (items || []).reduce((sum, it) => sum + (it.unitPrice || 0) * (it.quantity || 0), 0);
  const productName = (pid) => productsById[pid]?.name || `Proizvod #${pid}`;

  const load = async () => {
    try {
      setLoading(true);
      // učitaj narudžbine + sve proizvode (za nazive u stavkama)
      const [oRes, pRes] = await Promise.all([
        http.get("/order"),
        http.get("/product"),
      ]);
      setOrders(oRes.data || []);
      const map = {};
      (pRes.data || []).forEach((p) => (map[p.id] = p));
      setProductsById(map);
      setErr("");
    } catch (e) {
      console.error(e);
      setErr("Greška pri učitavanju narudžbina.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, []);

  const deleteOrder = async (id) => {
    if (!window.confirm(`Obrisati narudžbinu #${id}?`)) return;
    try {
      await http.delete(`/order/${id}`);
      setOrders(orders.filter((o) => o.id !== id));
    } catch (e) {
      console.error(e);
      alert("Brisanje nije uspelo.");
    }
  };

  const changeStatus = async (id, status) => {
    try {
      await http.patch(`/order/${id}/status?status=${status}`);
      setOrders(orders.map((o) => (o.id === id ? { ...o, status } : o)));
    } catch (e) {
      console.error(e);
      alert("Promena statusa nije uspela.");
    }
  };

  const toggleItems = (id) => {
    setOrders(orders.map((o) => (o.id === id ? { ...o, _open: !o._open } : o)));
  };

  if (loading) return <div className="panel">Učitavanje…</div>;
  if (err) return <div className="panel error">{err}</div>;

  return (
    <div className="admin-wrap">
      <div className="admin-head">
        <h1>Narudžbine</h1>
        <button className="btn-outline" onClick={load}>
          Osveži
        </button>
      </div>

      <div className="table-wrap">
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Korisnik</th>
              <th>Datum</th>
              <th>Status</th>
              <th className="right">Stavki</th>
              <th className="right">Ukupno</th>
              <th>Akcije</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((o) => (
              <React.Fragment key={o.id}>
                <tr>
                  <td data-label="ID">#{o.id}</td>
                  <td data-label="Korisnik">{o.userId ?? o.user?.id ?? "-"}</td>
                  <td data-label="Datum">{fmtDate(o.createdAt)}</td>
                  <td data-label="Status">
                    <select
                      value={o.status}
                      onChange={(e) => changeStatus(o.id, e.target.value)}
                      className="select"
                    >
                      {STATUSES.map((s) => (
                        <option key={s} value={s}>
                          {s}
                        </option>
                      ))}
                    </select>
                  </td>
                  <td data-label="Stavki" className="right">
                    {o.items?.length ?? 0}
                  </td>
                  <td data-label="Ukupno" className="right">
                    {totalOf(o.items)} RSD
                  </td>
                  <td className="actions">
                    <button className="btn" onClick={() => toggleItems(o.id)}>
                      {o._open ? "Sakrij" : "Stavke"}
                    </button>
                    <button className="btn danger" onClick={() => deleteOrder(o.id)}>
                      Obriši
                    </button>
                  </td>
                </tr>

                {o._open && (
                  <tr className="items-row">
                    <td colSpan="7">
                      {(o.items && o.items.length > 0) ? (
                        <ul className="items">
                          {o.items.map((it) => (
                            <li key={it.id}>
                              <span>{productName(it.productId)}</span>
                              <span>
                                x{it.quantity} × {it.unitPrice} RSD
                              </span>
                            </li>
                          ))}
                        </ul>
                      ) : (
                        <em>Nema stavki.</em>
                      )}
                    </td>
                  </tr>
                )}
              </React.Fragment>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
