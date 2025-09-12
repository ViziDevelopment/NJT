import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import http from "../api/http";
import "../css/shop.css";

const ProductsPage = ({ addToCart }) => {
  const { id } = useParams(); // restaurantId
  const [products, setProducts] = useState([]);

  useEffect(() => {
   http.get(`/product/restaurant/${id}`).then((res) => {
    setProducts(res.data);
        });
  }, [id]);

  return (
    <div>
      <div className="dishes-grid">
        {products.map((p) => (
          <div key={p.id} className="dish">
            <img
              src={p.imageUrl && p.imageUrl.trim() !== "" 
                     ? p.imageUrl 
                     : "https://via.placeholder.com/300x200?text=No+Image"}
              alt={p.name}
            />
            <div className="row">
              <h4>{p.name}</h4>
              <span className="price">{p.price} RSD</span>
            </div>
            <p>{p.description}</p>
            <button
              onClick={() => {
                addToCart(p);
                alert(`${p.name} dodat u korpu!`);
              }}
              className="btn-add"
            >
              Dodaj u korpu
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProductsPage;
