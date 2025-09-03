import logo from './logo.svg';
import './App.css';  
import Pocetna from './pages/Pocetna';
import { Footer } from './components/Footer';
import { Proizvodi } from './pages/Proizvodi';
import { Routes, Route, BrowserRouter } from "react-router-dom";
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
          </Routes>
          
           <Footer></Footer>
    </BrowserRouter>
  );
}

export default App;
