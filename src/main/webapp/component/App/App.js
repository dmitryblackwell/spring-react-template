import React from 'react';
import classes from './App.css';
import Home from "../Home/Home";
import {BrowserRouter} from "react-router-dom";
import {Route, Switch} from "react-router";
import Error from "../Error/Error";
import Auth from "../Auth/Auth";
import AuthContextProvider from "../../store/context/AuthContext";

function App() {
    return (
        <div className={classes.App}>
            <AuthContextProvider>
                <BrowserRouter>
                    <Switch>
                        <Route path="/" component={Home} exact />
                        <Route path="/auth" component={Auth} exact />
                        <Route component={Error} />
                    </Switch>
                </BrowserRouter>
            </AuthContextProvider>
        </div>
    );
}

export default App;
