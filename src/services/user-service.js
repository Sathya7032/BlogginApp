import { myaxios } from "./helper";

export const signUp =(user)=>{
    return myaxios.post("/auth/register",user).then((response)=>response.data)
}

export const loginUser=(loginDetails)=>{
    return myaxios.post('/auth/login',loginDetails).then((response)=>response.data)
}