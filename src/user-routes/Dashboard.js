import React, { useEffect, useState }from 'react'
import {getCurrentUserDetails, isLoggedIn} from "../auth"
import Header from '../components/Header'
import { Container } from 'reactstrap'
import Addpost from './Addpost'
const Dashboard = () => {
  const[login,setLogin]=useState(false)

  const[user,setUser]=useState(undefined)
  useEffect(()=>{
    setLogin(isLoggedIn)
    setUser(getCurrentUserDetails())
  },[login])
  
  return (
    <div>
      <Header/><br/><br/><br/><br/>
      <Container>
        <Addpost/>
      </Container>
      
      <a href="/user/profile">profile</a>
    </div>
  )
}

export default Dashboard
