import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import '../styles/style.css'

const Header = () => {
  
 

  return (
    <div>
      <div class="fixed-top">
        <header class="topbar">
          <div class="container">
            <div class="row">
              <div class="col-sm-12">
                <ul class="social-network">
                  <li>
                    <a class="waves-effect waves-dark" href="sa">
                      <i class="fa fa-facebook"></i>
                    </a>
                  </li>
                  <li>
                    <a class="waves-effect waves-dark" href="s">
                      <i class="fa fa-twitter"></i>
                    </a>
                  </li>
                  <li>
                    <a class="waves-effect waves-dark" href="sa">
                      <i class="fa fa-linkedin"></i>
                    </a>
                  </li>
                  <li>
                    <a class="waves-effect waves-dark" href="sa">
                      <i class="fa fa-pinterest"></i>
                    </a>
                  </li>
                  <li>
                    <a class="waves-effect waves-dark" href="sa">
                      <i class="fa fa-google-plus"></i>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </header>
        <nav class="navbar navbar-expand-lg navbar-dark mx-background-top-linear">
          <div class="container">
            <a
              class="navbar-brand"
              href="index.html"
              style={{ "text-transform": "uppercase" }}
            >
              {" "}
              CODEMAMA.COM
            </a>
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarResponsive"
              aria-controls="navbarResponsive"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                  <a class="nav-link" href="sa">
                    Home
                    <span class="sr-only">(current)</span>
                  </a>
                </li>

                <li class="nav-item">
                  <a class="nav-link" href="/about">
                    About
                  </a>
                </li>

                <li class="nav-item">
                  <a class="nav-link" href="/signup">
                    Register
                  </a>
                </li>
                 
                <li class="nav-item">
                  <a class="nav-link" href="/login">
                    Login
                  </a>
                </li>

                <li class="nav-item">
                  <a class="nav-link" href="/user/dashboard">
                    dashboard
                  </a>
                </li>
                
                <li class="nav-item">
                  <a class="nav-link" href="sa">
                    Blog
                  </a>
                </li>

                <li class="nav-item">
                  <a class="nav-link" href="sa">
                    Contact
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </div>
  );
};

export default Header;