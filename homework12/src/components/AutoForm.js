import React, {useState} from 'react';
import {Card, Button, Input} from 'antd';
import {useDispatch, useSelector} from "react-redux";
import {signIn, registration} from "../slices/UserSlice";
import {newCart} from "../slices/CartSlice";
import {RegistrationForm} from "./Registration";

export const AuthForm = () => {
    const isRegistrationForm = useSelector((state) => state.user.isReg);
    const currUser = useSelector((state) => state.user.user);
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatch();
    const handleLogin = () => {
        const user = {
            name: name,
            password: password
        };
        dispatch(signIn(user));
    };

    const handleRegister = () => {
        dispatch(registration())
    };

    return (
        <>
            {isRegistrationForm ? (
                <RegistrationForm/>
            ) : (
                <Card
                    hoverable
                    style={{
                        width: 300,
                    }}
                >
                    <Input
                        placeholder="Email"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        style={{marginBottom: '10px'}}
                    />
                    <Input.Password
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        style={{marginBottom: '10px'}}
                    />
                    <Button type="primary" style={{marginBottom: '10px'}} onClick={handleLogin}>
                        Войти
                    </Button>
                    <Button onClick={handleRegister}>Зарегистрироваться</Button>
                </Card>
            )}
        </>
    );
};