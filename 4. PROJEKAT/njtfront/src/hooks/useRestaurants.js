import { useEffect, useState } from "react";
import http from "../api/http";
 

export default function useRestaurants() {
  const [restaurants, setRestaurants] = useState([]);
  const [rLoading, setLoading] = useState(true);
  const [rError, setError] = useState("");

  // funkcija koja poziva backend i puni state
  async function fetchAll() {
    setLoading(true);
    try {
      const res = await http.get("/restaurant");
      const data = Array.isArray(res.data) ? res.data : [];
      setRestaurants(
        data
          .filter((x) => x && x.id != null)
          .map((x) => ({ id: x.id, name: x.name ?? "", address: x.address ?? "" }))
      );
      setError("");
    } catch (e) {
      setError(e?.response?.data?.message || e.message);
    } finally {
      setLoading(false);
    }
  }

  // poziva se samo jednom na mount (kad se komponenta učita)
  useEffect(() => {
    fetchAll();
  }, []);

  // vraćamo sve što nam treba
  return { restaurants, rLoading, rError, reload: fetchAll };
}
