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
function App() {
  //////////
  ///

  ///
  return (
     <BrowserRouter>
            <Navbar></Navbar>  
        <Routes>
            <Route path="/" element={<Pocetna />} />


             <Route path="/proizvodi" element={ <ProtectedRoute>  <Proizvodi /> </ProtectedRoute>} />
             <Route path="/restorani" element={<ProtectedRoute>  <Restorani /> </ProtectedRoute>} />

            <Route path="/login" element={<Login onSuccess={() => window.location.href='/'} />} />
            <Route path="/register" element={<Register onSuccess={() => window.location.href='/' }/>} />



          </Routes>
          
           <Footer></Footer>
    </BrowserRouter>
  );
}

export default App;
