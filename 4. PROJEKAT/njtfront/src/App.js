import logo from './logo.svg';
import './App.css';  
import Pocetna from './pages/Pocetna';
import { Footer } from './components/Footer';
import { Proizvodi } from './pages/Proizvodi';
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Restorani from './pages/Restorani';
function App() {
  //////////
  ///

  ///
  return (
     <BrowserRouter>
          {/* <Navbar></Navbar> */}
        <Routes>
            <Route path="/" element={<Pocetna />} />
             <Route path="/proizvodi" element={<Proizvodi />} />
             <Route path="/restorani" element={<Restorani />} />

          </Routes>
          
           <Footer></Footer>
    </BrowserRouter>
  );
}

export default App;
