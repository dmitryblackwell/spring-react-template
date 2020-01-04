import React from 'react';
import {Avatar, Card, Menu, Typography } from "antd";

import GirlIcon from '../../assets/girl.svg';
import classes from './Home.module.css';

const { Title } = Typography;

export default function () {
    return (
        <div className={classes.Root}>
            <Menu mode="horizontal">
                <Menu.Item className={classes.Logout}>logout</Menu.Item>
            </Menu>
            <div className={classes.Card}>
                <GirlIcon className={classes.Avatar}/>
                <Title level={2}>Hello, Lisa!</Title>
            </div>
        </div>
    )
}