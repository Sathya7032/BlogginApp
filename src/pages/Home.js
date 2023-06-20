import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";



const Home = () => {
  return (
    <div >
      <Header /><br/><br/><br/>

      <div class="jumbotron" style={{
        "margin":"1px",
        "marginTop":"-13px",
        "marginRight":"-5px",
        "top":"15px"}}>
          
        <h1 class="display-4">Hello, world!</h1>
        <p class="lead">
          This is a simple hero unit, a simple jumbotron-style component for
          calling extra attention to featured content or information.
        </p>
        <hr class="my-4" />
        <p>
          It uses utility classes for typography and spacing to space content
          out within the larger container.
        </p>
        <p class="lead">
          
        </p>
       
      </div>

      <Footer />
    </div>
  );
};

export default Home;
