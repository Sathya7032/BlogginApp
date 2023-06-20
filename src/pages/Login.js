import React, { useState } from "react";
import { toast } from "react-toastify";
import {
  Card,
  CardBody,
  CardHeader,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Container,
  Row,
  Col,
} from "reactstrap";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { loginUser } from "../services/user-service";
import { doLogin } from "../auth";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [loginDetail, setLoginDetail] = useState({
    username: "",
    password: "",
  });
  const handleChange = (event, field) => {
    let actualValue = event.target.value;
    setLoginDetail({
      ...loginDetail,
      [field]: actualValue,
    });
  };

  const navigate = useNavigate()
  const handleReset = () => {
    setLoginDetail({
      username: "",
      password: "",
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(loginDetail);

    if (loginDetail.username === "" || loginDetail.password === "") {
      toast.error("username or password is required");
      return;
    }

    loginUser(loginDetail)
      .then((data) => {
        console.log("user login");
        console.log(data)

        doLogin(data,()=>{
          console.log("login details is saved to local storage")
        })
        toast.success("LOGIN SUCCESSFUL..")
        navigate("/user/dashboard")
      })
      .catch((error) => {
        console.log(error)
        if(error.response.status===404){
          toast.error(error.response.data.message)
        }else{
          toast.error("something went wrong on server")
        }
      });
  };
  return (
    <div style={{ backgroundColor: "grey" }}>
      <Header />
      <br />
      <br />
      <br />
      <br />
      <Container>
        <Row>
          <Col
            sm={{
              size: 6,
              offset: 3,
            }}
          >
            <Card color="secondary">
              <CardHeader>
                <h3>USER LOGIN </h3>
              </CardHeader>
              <CardBody>
                <Form onSubmit={handleSubmit}>
                  <FormGroup>
                    <Label for="exampleEmail" hidden>
                      Email
                    </Label>
                    <Input
                      type="text"
                      name="username"
                      id="exampleEmail"
                      placeholder="username"
                      value={loginDetail.username}
                      onChange={(e) => handleChange(e, "username")}
                    />
                  </FormGroup>{" "}
                  <FormGroup>
                    <Label for="examplePassword" hidden>
                      Password
                    </Label>
                    <Input
                      type="password"
                      name="password"
                      id="examplePassword"
                      placeholder="Password"
                      value={loginDetail.password}
                      onChange={(e) => handleChange(e, "password")}
                    />
                  </FormGroup>{" "}
                  <Container>
                    <Button type="submit">Submit</Button>
                    <Button className="ms-2" type="reset" onClick={handleReset}>
                      Reset
                    </Button>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
      <br />
      <Footer />
    </div>
  );
};

export default Login;
