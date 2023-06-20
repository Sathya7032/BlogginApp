import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import Home from "./pages/Home";
import Abou from "./pages/Abou";
import Register from "./pages/Register";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer} from 'react-toastify';
import Login from "./pages/Login";
import Privateroute from "./components/Privateroute";
import Dashboard from "./user-routes/Dashboard";
import Profile from "./user-routes/Profile";
function App() {
  return (
    <div>
     <ToastContainer/>
      <BrowserRouter>
      <Routes>
        <Route path ="/" element = {<Home/>}></Route>
        <Route path ="/about" element = {<Abou/>}></Route>
        <Route path ="/signup" element = {<Register/>}></Route>
        <Route path ="/login" element = {<Login/>}></Route>
        <Route path="/user" element={<Privateroute/>}>
          <Route path="dashboard" element={<Dashboard/>}/>
          <Route path="profile" element={<Profile/>}/>
        </Route>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
