import {  privateAxios } from "./helper"

const createPost=(postData)=>{
    console.log(postData)
    return privateAxios.post(`/users/${postData.userId}/category/${postData.categoryId}/posts`,postData).then(response=>response.data)
}
export default createPost