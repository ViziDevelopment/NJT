import React from "react";
import http from "../api/http";
import "../css/shop.css";

const CartPage = ({ cart, setCart, userId }) => {
  const updateQuantity = (id, newQty) => {
    if (newQty < 1) return;
    setCart(
      cart.map((c) =>
        c.id === id ? { ...c, quantity: newQty } : c
      )
    );
  };

  const removeItem = (id) => {
    setCart(cart.filter((c) => c.id !== id));
  };

  const createOrder = async () => {
    const dto = {
      userId,
      napomena: "Poručeno iz React app",
      items: cart.map((c) => ({
        productId: c.id,
        quantity: c.quantity || 1,
        unitPrice: c.price,
      })),
    };

    try {
      await http.post("/order", dto);
      alert("Narudžbina kreirana!");
      setCart([]);
    } catch (err) {
      console.error("Greška:", err);
    }
  };

  return (
    <div className="cart-wrap">
      <h2>Moja korpa</h2>
      {cart.length === 0 ? (
        <p>Korpa je prazna.</p>
      ) : (
        <ul>
          {cart.map((c) => (
            <li key={c.id}>
              <span>{c.name} - {c.price} RSD</span>
              
              {/* Kontrole za količinu */}
              <div className="qty-controls">
                <button onClick={() => updateQuantity(c.id, (c.quantity || 1) - 1)}>-</button>
                <input
                  type="number"
                  min="1"
                  value={c.quantity || 1}
                  onChange={(e) => updateQuantity(c.id, parseInt(e.target.value))}
                />
                <button onClick={() => updateQuantity(c.id, (c.quantity || 1) + 1)}>+</button>
              </div>

              <button onClick={() => removeItem(c.id)} className="btn-delete">
                Ukloni
              </button>
            </li>
          ))}
        </ul>
      )}
      {cart.length > 0 && (
        <button onClick={createOrder} className="btn-order">
          Pošalji narudžbinu
        </button>
      )}
    </div>
  );
};

export default CartPage;
