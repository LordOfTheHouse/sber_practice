import React, {useState} from 'react';
import {Card, Button, Input} from 'antd';
import {useDispatch} from "react-redux";
import {createUser} from "../slices/UserSlice";

export const RegistrationForm = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    const createAccount = () => {
        dispatch(createUser({
            name: name,
            email: email,
            password: password
        }))
    };

    return (
        <Card
            hoverable
            style={{
                width: 300,
            }}
        >
            <Input
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                style={{marginBottom: '10px'}}
            />
            <Input
                placeholder="Имя"
                value={name}
                onChange={(e) => setName(e.target.value)}
                style={{marginBottom: '10px'}}
            />
            <Input.Password
                placeholder="Пароль"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                style={{marginBottom: '10px'}}
            />
            <Button type="primary" style={{marginBottom: '10px'}} onClick={createAccount}>
                Зарегистрироваться
            </Button>
        </Card>
    );
};
