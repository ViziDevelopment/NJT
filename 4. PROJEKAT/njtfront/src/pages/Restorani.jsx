import React, { useEffect, useState } from 'react'
import http from '../api/http';
import '../css/Restorani.css';
import RedTabeleRestoran from '../components/RedTabeleRestoran';
const Restorani = () => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [q, setQ] = useState("");
    const [sort, setSort] = useState({ by: "id", dir: "asc" });



    const [novoIme, setNovoIme] = useState("");
    const [novaAdresa, setNovaAdresa] = useState("");

    const [editingId, setEditingId] = useState(null); 



    function toggleSort(col) {
        setSort((s) =>
        s.by === col ? { by: col, dir: s.dir === "asc" ? "desc" : "asc" } : { by: col, dir: "asc" }
        );
    }




    useEffect(() => {
        setLoading(true);

        http
        .get("/restaurant")  
        .then((res) => setData(Array.isArray(res.data) ? res.data : []))
        .catch((e) => setError(e?.response?.data?.message || e.message))
        .finally(() => setLoading(false));
    }, []);

 

    let prikazani = data.filter(
                   (r) =>
            r.name.toLowerCase().includes(q.toLowerCase()) ||
            r.address.toLowerCase().includes(q.toLowerCase())
       );
       
    prikazani.sort((a, b) => {
        const va = a[sort.by];
        const vb = b[sort.by];
        if (va == null) return 1;
        if (vb == null) return -1;
        if (typeof va === "number" && typeof vb === "number") {
        return sort.dir === "asc" ? va - vb : vb - va;
        }
        return sort.dir === "asc"
        ? String(va).localeCompare(String(vb))
        : String(vb).localeCompare(String(va));
    });




    //CRUD

  async function handleDelete(id) {
    if (!window.confirm("Obriši restoran?")) return;
    try {
      await http.delete(`/restaurant/${id}`);
      setData((prev) => prev.filter((r) => r.id !== id));
      //
    } catch (e) {
      alert(e?.response?.data?.message || e.message);
    }
  }
async function handleSubmit(e) {
  e.preventDefault();
  if (!novoIme.trim() || !novaAdresa.trim()) {
    alert("Unesi naziv i adresu.");
    return;
  }

  try {
    if (editingId == null) {
      // CREATE (POST)
      const res = await http.post("/restaurant", {
        name: novoIme.trim(),
        address: novaAdresa.trim(),
      });
      setData((prev) => [...prev, res.data]);
    } else {
      // UPDATE (PUT)
      const res = await http.put(`/restaurant/${editingId}`, {
        id: editingId,             
        name: novoIme.trim(),
        address: novaAdresa.trim(),
      });
      setData((prev) => prev.map((r) => (r.id === editingId ? res.data : r)));
    }

    // reset forme
    setNovoIme("");
    setNovaAdresa("");
    setEditingId(null);
  } catch (e) {
    alert(e?.response?.data?.message || e.message);
  }
}

    function startEdit(row) {
        setEditingId(row.id);
        setNovoIme(row.name ?? "");
        setNovaAdresa(row.address ?? "");
    }
    function cancelEdit() {
    setEditingId(null);
    setNovoIme("");
    setNovaAdresa("");
    }


  return (
    <div className="container admin-wrap">

        <header className="admin-head">
        <div>
          <h1>Restorani</h1>
          <p className="muted">Pregled svih restorana iz baze (admin).</p>
        </div>
        <div className="row-gap">
          <input
            className="input"
            placeholder="Pretraga (naziv, adresa, grad...)"
            value= {q}
            onChange={(e) => {
              setQ(e.target.value);
           
            }}
          />
          <button className="btn" onClick={() => setQ("")}>Osveži</button>
           
        </div>
      </header>
            <form className="panel" onSubmit={handleSubmit} style={{ marginBottom: 12, display: "flex", gap: 8, alignItems: "center", flexWrap: "wrap" }}>
            <input
                className="input"
                placeholder="Naziv"
                value={novoIme}
                onChange={(e) => setNovoIme(e.target.value)}
            />
            <input
                className="input"
                placeholder="Adresa"
                value={novaAdresa}
                onChange={(e) => setNovaAdresa(e.target.value)}
            />
            <button className="btn primary" type="submit">Sačuvaj</button>

              {editingId != null && (
                    <button className="btn" type="button" onClick={cancelEdit}>
                    Otkaži
                    </button>
                )}

            </form>

             <table className="table">
                  <thead>
                    <tr>
                        <th onClick={() => toggleSort("id")}>
                        ID {sort.by === "id" && (sort.dir === "asc" ? "▲" : "▼")}
                        </th>
                        <th onClick={() => toggleSort("name")}>
                        Naziv {sort.by === "name" && (sort.dir === "asc" ? "▲" : "▼")}
                        </th>
                        <th onClick={() => toggleSort("address")}>
                        Adresa {sort.by === "address" && (sort.dir === "asc" ? "▲" : "▼")}
                        </th>
                        <th>Akcije</th>
                    </tr>
                    </thead>
            <tbody>
              {prikazani.map((r) => (
                <RedTabeleRestoran key={r.id} id={r.id} naziv={r.name} adresa={r.address} onDelete={handleDelete}  onUpdate={() => startEdit(r)} />
            ))}
            </tbody>
          </table>

 

    </div>




  )
}

export default Restorani