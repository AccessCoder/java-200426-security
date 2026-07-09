import {Navigate, Outlet} from "react-router-dom";

type ProtectedRouteProps = {
    username: string | null| undefined
}
export default function ProtectedRoute(props:Readonly<ProtectedRouteProps>){

    if (props.username === undefined){
        return <h1>loading...</h1>
    }

    return(
        <>
            {props.username ? <Outlet /> : <Navigate to={"/"} />}
        </>
    )
}