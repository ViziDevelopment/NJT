import React, { useEffect, useState } from "react";
 
import "../css/shop.css";
import { useNavigate } from "react-router-dom";
import http from "../api/http";

const RestaurantsPage = () => {
  const [restaurants, setRestaurants] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    http.get("/restaurant").then((res) => setRestaurants(res.data));
  }, []);

  return (
    <div className="rest-grid">
      {restaurants.map((r) => (
        <div
          key={r.id}
          className="rest-card"
          onClick={() => navigate(`/products/${r.id}`)}
        >
          <div className="thumb">
            <img src="https://picsum.photos/400/200" alt={r.name} />
          </div>
          <div className="content">
            <h3>{r.name}</h3>
            <p>{r.address}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default RestaurantsPage;
