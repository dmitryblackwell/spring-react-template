import React, {useContext} from 'react';
import {Menu, Typography} from "antd";

import GirlIcon from '../../assets/girl.svg';
import classes from './Home.module.css';
import {AuthContext} from "../../store/context/AuthContext";
import * as constants from '../../store/constants';
import {Redirect} from "react-router";

const { Title } = Typography;

export default function () {
    const {username, isAuth, dispatch} = useContext(AuthContext);

    const authRedirect = isAuth ? null : <Redirect to={"/auth"} />;

    return (
        <div className={classes.Root}>
            {authRedirect}
            <Menu mode="horizontal">
                <Menu.Item
                    className={classes.Logout}
                    onClick={() => dispatch({type: constants.LOGOUT})}>
                        logout
                </Menu.Item>
            </Menu>
            <div className={classes.Card}>
                <GirlIcon className={classes.Avatar}/>
                <Title level={2}>Hello, {username}!</Title>
            </div>
        </div>
    )
}