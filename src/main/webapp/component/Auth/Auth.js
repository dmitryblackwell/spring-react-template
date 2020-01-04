import React, {useState} from "react";
import classes from './Auth.module.css';
import {Button, Input, Popover} from "antd";

import Raccoon from '../../assets/raccoon.svg';

export default function () {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const content = (
        <div>
            <p>username: user</p>
            <p>password: test</p>
        </div>
    );
    return (
      <div className={classes.Root}>
          <Raccoon className={classes.Logo}/>
          <Input placeholder="username"
                 value={username}
                 onChange={e => setUsername(e.target.value)} />
          <Input placeholder="password"
                 type="password"
                 value={password}
                 onChange={e => setPassword(e.target.value)}/>
          <Button type="primary">Log In</Button>
          <Popover content={content}>
              <Button type="link">help</Button>
          </Popover>
      </div>
    );
}