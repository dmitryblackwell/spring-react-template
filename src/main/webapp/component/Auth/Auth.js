import React, {useContext, useState} from "react";
import classes from './Auth.module.css';
import {Button, Input, Popover} from "antd";

import Raccoon from '../../assets/raccoon.svg';
import {AuthContext} from "../../store/context/AuthContext";
import {Redirect} from "react-router";
import Title from "antd/lib/typography/Title";
import * as constants from '../../store/constants';

export default function () {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const popoverContent = (
        <div>
            <p>username: user</p>
            <p>password: test</p>
        </div>
    );

    const { dispatch, isAuth, isAuthFailed } = useContext(AuthContext);

    const authRedirect = isAuth ? <Redirect to={"/"} /> : null;

    const failedMessage = isAuthFailed ?
        (
            <Title level={4}>
                Wrong username/password combination!
            </Title>
        ) : null;

    const onSubmit = () => {
        dispatch({
            type: constants.AUTH_START,
            username, password, dispatch
        });
    };

    return (
      <div className={classes.Root}>
          {authRedirect}
          <Raccoon className={classes.Logo}/>
          {failedMessage}
          <Input placeholder="username"
                 value={username}
                 onChange={e => setUsername(e.target.value)} />
          <Input placeholder="password"
                 type="password"
                 value={password}
                 onChange={e => setPassword(e.target.value)}
                 onPressEnter={onSubmit}/>
          <Button type="primary" onClick={onSubmit}>Log In</Button>
          <Popover content={popoverContent}>
              <Button type="link">help</Button>
          </Popover>
      </div>
    );
}