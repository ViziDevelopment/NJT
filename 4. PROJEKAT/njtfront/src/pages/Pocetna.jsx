
import { useState } from "react";
import "../css/Pocetna.css";
import { Footer } from "../components/Footer";

export default function Pocetna() {
  const [email, setEmail] = useState("");

  const categories = [
    { name: "Burgers", img: "https://images.unsplash.com/photo-1550547660-d9450f859349?q=80&w=1200&auto=format&fit=crop" },
    { name: "Pizza", img: "https://images.unsplash.com/photo-1548365328-9f547fb0953d?q=80&w=1200&auto=format&fit=crop" },
    { name: "Pasta", img: "https://images.unsplash.com/photo-1523986371872-9d3ba2e2f642?q=80&w=1200&auto=format&fit=crop" },
    { name: "Sushi", img: "https://images.unsplash.com/photo-1604908176997-431651c9d2e0?q=80&w=1200&auto=format&fit=crop" },
    { name: "Salads", img: "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?q=80&w=1200&auto=format&fit=crop" },
    { name: "Desserts", img: "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?q=80&w=1200&auto=format&fit=crop" },
  ];

  const featured = [
    { id: 1, name: "Urban Bite", rating: 4.7, eta: "25–35 min", fee: "Besplatna dostava", img: "https://images.unsplash.com/photo-1550547660-2a9a56f7c1de?q=80&w=1300&auto=format&fit=crop" },
    { id: 2, name: "Roma Pizza", rating: 4.6, eta: "20–30 min", fee: "od 150 RSD", img: "https://images.unsplash.com/photo-1542838132-92c53300491e?q=80&w=1300&auto=format&fit=crop" },
    { id: 3, name: "Green Bowl", rating: 4.8, eta: "30–40 min", fee: "Besplatna dostava", img: "https://images.unsplash.com/photo-1540189549336-e6e99c3679fe?q=80&w=1300&auto=format&fit=crop" },
  ];

  const dishes = [
    { id: 1, name: "BBQ Burger", price: 690, img: "https://images.unsplash.com/photo-1550547660-7a38d22d31b7?q=80&w=1200&auto=format&fit=crop" },
    { id: 2, name: "Margherita", price: 890, img: "https://images.unsplash.com/photo-1548365328-9f547fb0953d?q=80&w=1200&auto=format&fit=crop" },
    { id: 3, name: "Pad Thai", price: 990, img: "https://images.unsplash.com/photo-1604908554027-54b1b0e2a3b6?q=80&w=1200&auto=format&fit=crop" },
    { id: 4, name: "Caesar Salad", price: 620, img: "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?q=80&w=1200&auto=format&fit=crop" },
    { id: 5, name: "Tiramisu", price: 520, img: "https://images.unsplash.com/photo-1511920170033-f8396924c348?q=80&w=1200&auto=format&fit=crop" },
    { id: 6, name: "Ramen", price: 1050, img: "https://images.unsplash.com/photo-1543353071-10c8ba85a904?q=80&w=1200&auto=format&fit=crop" },
  ];

  return (
    <>
      
      {/* HERO */}
      <section className="hero">
        <div className="container hero-grid">
          <div className="hero-copy">
            <span className="pill">4.8 ★ od 12k korisnika</span>
            <h1>Hrana do vrata brže nego što kažeš “gladan”.</h1>
            <p>Pronađi omiljeni restoran, poruči za par klikova i prati dostavu uživo. Dostupno u tvojoj blizini.</p>
            <form className="hero-search" onSubmit={(e) => e.preventDefault()}>
              <input placeholder="Unesi adresu (npr. Knez Mihailova 1)" />
              <button className="btn primary">Pronađi restorane</button>
            </form>
            <div className="stats">
              <div><strong>1.5k+</strong><span>Restorana</span></div>
              <div><strong>25–35</strong><span>Min prosečna dostava</span></div>
              <div><strong>0 RSD</strong><span>Besplatna dostava*</span></div>
            </div>
          </div>
          <div className="hero-art">
            <div className="hero-card big">
              <img src="https://images.unsplash.com/photo-1515003197210-e0cd71810b5f?q=80&w=1200&auto=format&fit=crop" alt="Food collage" />
              <div className="badge">Top izbor nedelje</div>
            </div>
            <div className="hero-card small">
              <img src="https://images.unsplash.com/photo-1544025162-d76694265947?q=80&w=800&auto=format&fit=crop" alt="Delivery rider" />
              <div className="badge">Dostava uživo</div>
            </div>
          </div>
        </div>
      </section>

      {/* CATEGORIES */}
      <section id="categories" className="section">
        <div className="container">
          <div className="section-head">
            <h2>Kategorije</h2>
            <a className="link" href="#">Vidi sve</a>
          </div>
          <div className="grid cats">
            {categories.map((c) => (
              <a key={c.name} className="cat" href="#menu">
                <img src={c.img} alt={c.name} />
                <span>{c.name}</span>
              </a>
            ))}
          </div>
        </div>
      </section>

      {/* FEATURED RESTAURANTS */}
      <section id="restaurants" className="section alt">
        <div className="container">
          <div className="section-head">
            <h2>Izdvojeni restorani</h2>
            <div className="filters">
              <button className="chip active">Najbliži</button>
              <button className="chip">Najbolje ocenjeni</button>
              <button className="chip">Besplatna dostava</button>
            </div>
          </div>
          <div className="grid rest">
            {featured.map((r) => (
              <article className="rest-card" key={r.id}>
                <div className="thumb">
                  <img src={r.img} alt={r.name} />
                  <div className="overlay">
                    <span>{r.eta}</span>
                    <span>{r.fee}</span>
                  </div>
                </div>
                <div className="content">
                  <h3>{r.name}</h3>
                  <div className="meta">
                    <span className="rating">★ {r.rating}</span>
                    <button className="btn tiny">Pogledaj meni</button>
                  </div>
                </div>
              </article>
            ))}
          </div>
        </div>
      </section>

      {/* POPULAR DISHES */}
      <section id="menu" className="section">
        <div className="container">
          <div className="section-head">
            <h2>Popularna jela</h2>
            <a className="link" href="#">Pogledaj više</a>
          </div>
          <div className="grid dishes">
            {dishes.map((d) => (
              <article className="dish" key={d.id}>
                <img src={d.img} alt={d.name} />
                <div className="row">
                  <h4>{d.name}</h4>
                  <span className="price">{d.price} RSD</span>
                </div>
                <p>Odličan odnos ukusa i cene. Spremno za 15 minuta.</p>
                <button className="btn block">Dodaj u korpu</button>
              </article>
            ))}
          </div>
        </div>
      </section>

      {/* HOW IT WORKS */}
      <section id="how" className="section alt">
        <div className="container">
          <h2>Kako funkcioniše</h2>
          <div className="steps">
            <div className="step">
              <div className="step-num">1</div>
              <h4>Unesi adresu</h4>
              <p>Pronađi restorane koji dostavljaju do tebe.</p>
            </div>
            <div className="step">
              <div className="step-num">2</div>
              <h4>Izaberi jelo</h4>
              <p>Pretraži kategorije i filtere ukusa.</p>
            </div>
            <div className="step">
              <div className="step-num">3</div>
              <h4>Praćenje u realnom vremenu</h4>
              <p>Gledaj na mapi kako kurir stiže do tebe.</p>
            </div>
          </div>
        </div>
      </section>

      {/* APP PROMO */}
      <section className="section">
        <div className="container app-promo">
          <div className="promo-copy">
            <h2>Brža porudžbina u aplikaciji</h2>
            <p>Sačuvaj omiljene porudžbine, prati popuste i primi obaveštenja o dostavi.</p>
            <div className="store-badges">
              <a className="store" href="#">App Store</a>
              <a className="store" href="#">Google Play</a>
            </div>
          </div>
          <div className="promo-art">
            <div className="phone">
              <img src="https://images.unsplash.com/photo-1556745753-b2904692b3cd?q=80&w=800&auto=format&fit=crop" alt="App preview" />
            </div>
          </div>
        </div>
      </section>

 
               
      
     
    </>
  );
}

