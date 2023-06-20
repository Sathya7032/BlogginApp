import React, { useEffect, useRef, useState } from 'react'
import { Button, Card, CardBody,Container,Form, Input, Label } from 'reactstrap'
import loadAllCategories from '../services/category-service'
import JoditEditor from 'jodit-react';
import createPost from '../services/Post-service';
import {getCurrentUserDetails} from "../auth"

const Addpost = () => {

  const editor = useRef(null)

  const [content,setContent] = useState('')

  const [user,setUser]=useState(undefined)

  const [categories,setCategory] = useState([])
  
  const[post , setPost]=useState({
          title:'',
          content:'',
          categoryId:''
  })

  const fieldChanged=(event)=>{
    setPost({...post,[event.target.name]:event.target.value})
  }

 // const config={
   // placeholder:"Start Typing......"
//  }

  const contentFieldChange=(data)=>{
    setPost({...post,'content':data})
  }

  useEffect(
    ()=>{

      setUser(getCurrentUserDetails())
      loadAllCategories().then((data)=>{
        
        setCategory(data)
      }).catch(error=>{
        console.log(error)
      })
    },[]
  )

  const createPostHandler=(event)=>{
    event.preventDefault();
    console.log(post)

    if(post.title.trim()===''){
      alert("POST TITLE IS REQUIRED.....")
      return;
    }

    if(post.content.trim()===''){
      alert("POST CONTENT REQUIRED.....")
      return;
    }
    if(post.categoryId===''){
      alert("SELECT CATEGORY....")
      return;
    }
  
  post['userId']=user.id
  createPost(post).then(postData=>{
    alert("post created")
    console.log(post)
  }).catch((error)=>{
    alert("error")
    console.log(error)
  })

}
  return (
    <div className='wrapper' style={{"backgroundColor":"yellowgreen"}}>
      <Card>
        <CardBody>
          {JSON.stringify(post)}
            <h3>whats going in your mind</h3>
            <Form onSubmit={createPostHandler}>

                <div>
                    <Label for ="title">Post Title</Label>
                    <Input type="text" id="title" placeholder='Enter post title' name="title" onChange={fieldChanged}></Input>
                </div><br/>

                <div>
                    <Label for ="content">Post content</Label>
                    <JoditEditor 
                    ref={editor}
                    value={content}
                    
                    onChange={contentFieldChange}
                    />
                </div><br/>

                <div>
                    <Label for ="categoryId">Post Category</Label>
                    <Input type="select" id="categoryId" name="categoryId" onChange={fieldChanged}>
                      <option disabled selected>--Select Category--</option>
                    {
                      categories.map((category)=>(
                        <option key = {category.categoryId}>
                          
                          {category.categoryId}
                         
                        </option>
                        
                      ))
                    }
                    </Input>
                </div><br/>
                <Container className='text-center'>
                <Button className='rounded-12' color="primary">ADD POST</Button>
                <Button className='rounded-12 ms-2' color="danger">RESET POST</Button>
                </Container>
            </Form>
        </CardBody>
      </Card>
    </div>
  )
}

export default Addpost
