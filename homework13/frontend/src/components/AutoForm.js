import React, { useState } from 'react';
import { Card, Button, Input } from 'antd';
import { useDispatch, useSelector } from "react-redux";
import { RegistrationForm } from "./Registration";
import userService from "../services/userService";
import { setAuth, setReg } from "../slices/UserSlice";

export const AuthForm = () => {
    const isRegistrationForm = useSelector((state) => state.user.isReg);
    const currUser = useSelector((state) => state.user.user);
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();

    const handleLogin = () => {
        userService.signIn(dispatch, name);
        if (currUser) {
            dispatch(setAuth(false));
        } else {
            alert("Неправильный логин или пароль");
        }
    };

    const handleRegister = () => {
        dispatch(setReg(true));
    };

    return (
        <>
            {isRegistrationForm ? (
                <RegistrationForm />
            ) : (
                <Card
                    hoverable
                    style={{
                        width: 300,
                        margin: '0 auto',
                        marginTop: 100,
                        borderRadius: 10,
                        boxShadow: '0 2px 8px rgba(0, 0, 0, 0.15)',
                        padding: '20px 30px',
                        textAlign: 'center',
                    }}
                >
                    <Input
                        placeholder="Email"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        style={{ marginBottom: '10px' }}
                    />
                    <Input.Password
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        style={{ marginBottom: '10px' }}
                    />
                    <Button type="primary" style={{ marginBottom: '10px' }} onClick={handleLogin}>
                        Войти
                    </Button>
                    <Button style={{ marginTop: '10px' }} onClick={handleRegister}>
                        Зарегистрироваться
                    </Button>
                </Card>
            )}
        </>
    );
};
