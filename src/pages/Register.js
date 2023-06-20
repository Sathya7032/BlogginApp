import React, { useState, useEffect } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import { signUp } from "../services/user-service";
import { toast } from "react-toastify";
import {  CardBody, FormFeedback } from "reactstrap";
import { Button, Form, FormGroup, Label, Input } from "reactstrap";


const Register = () => {
 
  const [data, setData] = useState({
    fullName: "",
    userName: "",
    email: "",
    password: "",
  });

  const [error, setError] = useState({
    error: {},
    isError: false,
  });

  

  useEffect(() => {}, [data]);

  const resetData = () => {
    setData({
      fullName: "",
      userName: "",
      email: "",
      password: "",
    });
  };
  const handleChange = (event, property) => {
    setData({ ...data, [property]: event.target.value });
  };

  const submitForm = (event) => {
    event.preventDefault();
    console.log(data);
    
    if (error.isError) {
      toast.error("Form Data is invalid, enter valid details");
      setError({
        ...error,isError:false
      })
      return;
    }

    signUp(data)
      .then((response) => {
        console.log(response);
        console.log("success");
        toast.success("user registered successfully");
        
        setData({
          fullName: "",
          userName: "",
          email: "",
          password: "",
        });
        
      })
      .catch((error) => {
        console.log(error);
        console.log("Errorlog");
        if(error.response.status===401){
          toast.error("email or username already exists..")
          return;
        }
        setError({
          errors: error,
          isError: true,
        });
      });
     
  };
  return (
    <div>
      <Header />
      <br></br>
      <br />
      <br />

      <div>
        <section
          class="vh-100"
          style={{
            backgroundColor: " #eee",
            paddingTop: "20px",
            paddingBottom: "800px",
          }}
        >
          <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
              <div class="col-lg-12 col-xl-11">
                <div
                  class="card text-black"
                  style={{ "border-radius": " 25px" }}
                >
                  <div class="card-body p-md-5">
                    <div class="row justify-content-center">
                      <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                        <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">
                          Register
                        </p>
                        
                        <CardBody>
                          <Form onSubmit={submitForm}>
                            <FormGroup>
                            <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                              <Label for="FullName">Enter Your FullName</Label>
                              <Input
                                type="text"
                                name="fullName"
                                id="exampleEmail"
                                placeholder="Enter Your FullName"
                                onChange={(e)=>handleChange(e,'fullName')}
                                value={data.fullName}
                                invalid={error.errors?.response?.data?.fullName ? true:false}
                              />
                              <FormFeedback>
                                { error.errors?.response?.data?.fullName  }
                              </FormFeedback>
                            </FormGroup>
                            <FormGroup>
                            <i class="fa fa-user fa-lg me-3 fa-fw"></i>
                              <Label for="UserName">Enter Your UserName</Label>
                              <Input
                                type="text"
                                name="userName"
                                id="exampleEmail"
                                placeholder="Enter Your User Name"
                                onChange={(e)=>handleChange(e,'userName')}
                                value={data.userName}
                                invalid={error.errors?.response?.data?.userName ? true:false}
                              />
                              <FormFeedback>
                                { error.errors?.response?.data?.userName  }
                              </FormFeedback>
                            </FormGroup>
                            
                            <FormGroup>
                            <i class="fa fa-envelope fa-lg me-3 fa-fw"></i>
                              <Label for="email">Enter Your Email</Label>
                              <Input
                                type="email"
                                name="email"
                                id="exampleEmail"
                                placeholder="Enter Your Email"
                                onChange={(e)=>handleChange(e,'email')}
                                value={data.email}
                                invalid={error.errors?.response?.data?.email ? true:false}
                              />
                              <FormFeedback>
                                { error.errors?.response?.data?.email  }
                              </FormFeedback>
                            </FormGroup>
                            <FormGroup>
                              <i class="fa fa-lock fa-lg me-3 fa-fw"></i>
                              <Label for="password">Enter Your Password</Label>
                              <Input
                                type="password"
                                name="password"
                                id="exampleEmail"
                                placeholder="Enter Your password"
                                onChange={(e)=>handleChange(e,'password')}
                                value={data.password}
                                invalid={error.errors?.response?.data?.password ? true:false}
                              />
                              <FormFeedback>
                                { error.errors?.response?.data?.password  }
                              </FormFeedback>
                            </FormGroup>
                            <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                            <Button class="btn btn-primary ">Register</Button> 
                            <Button onClick={resetData} class="btn btn-primary bt2">Reset</Button>
                            </div>
                            
                          </Form>
                        </CardBody>
                      </div>
                      <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                        <img
                          src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                          class="img-fluid"
                          alt="Sample"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      <Footer />
    </div>
  );
};

export default Register;
