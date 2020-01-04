import React from 'react';
import classes from './App.css';
import Home from "../Home/Home";
import {BrowserRouter} from "react-router-dom";
import {Route, Switch} from "react-router";
import Error from "../Error/Error";
import Auth from "../Auth/Auth";

function App() {
    return (
        <div className={classes.App}>
            <BrowserRouter>
                <Switch>
                    <Route path="/" component={Home} exact />
                    <Route path="/auth" component={Auth} exact />
                    <Route component={Error} />
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
