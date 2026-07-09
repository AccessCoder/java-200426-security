import {useEffect, useState} from 'react'
import './App.css'
import axios from "axios";
import {Link, Route, Routes} from "react-router-dom";
import HelloPage from "./HelloPage.tsx";
import ProtectedRoute from "./ProtectedRoute.tsx";
import CiaoPage from "./CiaoPage.tsx";

function App() {
  const [username, setUsername] = useState<string | undefined | null>(undefined)

    function login(){
      const host = window.location.host === "localhost:5173" ?
          "http://localhost:8080"
          :
          window.location.origin

        window.open(host + '/oauth2/authorization/github', '_self')
    }

    function logout(){
        const host = window.location.host === "localhost:5173" ?
            "http://localhost:8080"
            :
            window.location.origin

        window.open(host + '/logout', '_self')
    }

    function getMe(){
        axios.get("/api/user")
            .then(r => setUsername(r.data))
            .catch(() => setUsername(null))
    }

    useEffect(() => {
        getMe()
    }, []);

  return (
    <>
        <Link to={"/"}>Hello </Link>
        <Link to={"/ciao"}>Ciao </Link>
      <h1>{username}</h1>
        <button onClick={login}>Login</button>
        <button onClick={logout}>Logout</button>

        <button onClick={getMe}>WhoAmI?</button>
        <Routes>
            <Route path={"/"} element={<HelloPage/>}/>
            <Route element={<ProtectedRoute username={username}/>}>
                <Route path={"/ciao"} element={<CiaoPage/>}/>
            </Route>
        </Routes>
    </>
  )
}

export default App
