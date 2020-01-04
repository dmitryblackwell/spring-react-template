import React from 'react';
import Title from "antd/lib/typography/Title";

import classes from './Error.module.css';
import {Button} from "antd";
import {Link} from "react-router-dom";

export default function () {
    return (
        <div className={classes.Root}>
            <Title level={1}>Well, something fucked up!</Title>
            <Title level={3}>Please, consider, opening an issue.</Title>
            <Link to={"/"}><Button type="primary" style={{marginRight: '10px'}}>Go Home</Button></Link>
            <a href="https://github.com/dmitryblackwell/spring-react-template/issues/new"><Button type="danger">Open Issue</Button></a>
        </div>
    )
}