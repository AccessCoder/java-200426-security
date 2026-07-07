import {useState} from 'react'
import './App.css'
import axios from "axios";

function App() {
  const [username, setUsername] = useState<string>("")

    function login(){
      const host = window.location.host === "localhost:5173" ?
          "http://localhost:8080"
          :
          window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    function getMe(){
        axios.get("/api/user")
            .then(r => setUsername(r.data))
            .catch(e => console.log(e.message))
    }


  return (
    <>
      <h1>{username}</h1>
        <button onClick={login}>Login</button>
        <button onClick={getMe}>WhoAmI?</button>
    </>
  )
}

export default App
