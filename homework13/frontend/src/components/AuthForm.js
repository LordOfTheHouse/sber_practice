import React, { useState } from 'react';
import { Card, Button, Input } from 'antd';
import { useDispatch, useSelector } from "react-redux";
import { RegistrationForm } from "./Registration";
import {setAuth, setReg, login} from "../slices/UserSlice";
import authService from "../services/authService";

export const AuthForm = () => {
    const isRegistrationForm = useSelector((state) => state.user.isReg);
    const currUser = useSelector((state) => state.user.user);
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();


    const handleLogin = () => {
        authService.login({
            username:name,
            password:password
        }).then(user=>{
            dispatch(login(user));
        });

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
                        rules={[
                            {
                                required: true,
                                message: 'Please input your Username!',
                            },
                        ]}
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        style={{ marginBottom: '10px' }}
                    />
                    <Input.Password
                        placeholder="Password"
                        value={password}
                        rules={[
                            {
                                required: true,
                                message: 'Please input your password!',
                            },
                        ]}
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
