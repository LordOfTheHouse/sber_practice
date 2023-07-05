import React, { useState } from 'react';
import { Card, Button, Input } from 'antd';
import { useDispatch } from 'react-redux';
import userService from "../services/userService";
import { setReg, setAuth } from "../slices/UserSlice";

export const RegistrationForm = () => {
    const [name, setName] = useState('');
    const [login, setLogin] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();

    const createAccount = () => {
        userService.createUser(dispatch, {
            name: name,
            login: login,
            email: email,
            password: password
        });
        dispatch(setAuth(false));
        dispatch(setReg(false));
    };

    const cancelRegistration = () => {
        dispatch(setReg(false));
    };

    return (
        <Card
            hoverable
            style={{
                width: 300,
                borderRadius: 10,
                boxShadow: '0 2px 8px rgba(0, 0, 0, 0.15)',
                display: 'flex',
                margin: '0 auto',
                marginTop: 100,
                flexDirection: 'column',
                alignItems: 'center',
            }}
        >
            <Input
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                style={{ marginBottom: '10px', width: '100%' }}
            />
            <Input
                placeholder="Login"
                value={login}
                onChange={(e) => setLogin(e.target.value)}
                style={{ marginBottom: '10px', width: '100%' }}
            />
            <Input
                placeholder="Имя"
                value={name}
                onChange={(e) => setName(e.target.value)}
                style={{ marginBottom: '10px', width: '100%' }}
            />
            <Input.Password
                placeholder="Пароль"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                style={{ marginBottom: '10px', width: '100%' }}
            />
            <Button type="primary" style={{ marginBottom: '10px', width: '100%' }} onClick={createAccount}>
                Зарегистрироваться
            </Button>
            <Button danger style={{ width: '100%', marginTop: '10px' }} onClick={cancelRegistration}>
                Отмена
            </Button>
        </Card>
    );
};
