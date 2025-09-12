import logo from './logo.svg';
import './App.css';  
import Pocetna from './pages/Pocetna';
import { Footer } from './components/Footer'; 
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Restorani from './pages/Restorani';
import Proizvodi from './pages/Proizvodi';
import Login from './pages/Login';
import Register from './pages/Register';
import Navbar from './components/Navbar';
import ProtectedRoute from './components/ProtectedRoute';
import ForgotPassword from './pages/ForgotPassword';
import ResetPassword from './pages/ResetPassword';
import RestaurantsPage from './pages/RestaurantsPage';
import ProductsPage from './pages/ProductsPage';
import CartPage from './pages/CartPage';
import { useEffect, useState } from 'react';
import AdminOrdersPage from './pages/AdminOrdersPage';
function App() {


  const [cart, setCart] = useState(() => {
    try {
      return JSON.parse(localStorage.getItem("cart")) || [];
    } catch {
      return [];
    }
  });

  // svaki put kad se cart promeni → snimi u localStorage
  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(cart));
  }, [cart]);
  


 // const [cart, setCart] = useState([]);
  let userId = null;
    try {
      const me = JSON.parse(localStorage.getItem("me"));
      userId = me?.id || null;
    } catch (e) {
      console.error("Ne mogu da pročitam localStorage.me", e);
    }


  const addToCart = (p) => setCart([...cart, p]);
  return (
     <BrowserRouter>
            <Navbar></Navbar>  
        <Routes>
            <Route path="/" element={<Pocetna />} />


             <Route path="/proizvodi" element={ <ProtectedRoute>  <Proizvodi /> </ProtectedRoute>} />
             <Route path="/restorani" element={<ProtectedRoute>  <Restorani /> </ProtectedRoute>} />

            <Route path="/login" element={<Login onSuccess={() => window.location.href='/'} />} />
            <Route path="/register" element={<Register onSuccess={() => window.location.href='/' }/>} />

              <Route path="/forgot" element={<ForgotPassword />} />
              <Route path="/reset" element={<ResetPassword />} />

              <Route path="/admin/orders" element={<ProtectedRoute><AdminOrdersPage/></ProtectedRoute>} />


              <Route path="/restaurants" element={<RestaurantsPage />} />
                <Route
                  path="/products/:id"   //{id}
                  element={<ProductsPage addToCart={addToCart} />}
                />
                <Route
                  path="/cart"
                  element={<CartPage cart={cart} setCart={setCart} userId={userId} />}
                />
           

          </Routes>
          
           <Footer></Footer>
    </BrowserRouter>
  );
}

export default App;
