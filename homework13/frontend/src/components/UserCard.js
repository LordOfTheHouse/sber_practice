import React from 'react';
import { Card, Button} from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import put from '../img/put.jpeg';
import authService from '../services/authService'
import { logout } from "../slices/UserSlice";
import {set} from "../slices/CartSlice";

export const UserCard = () => {

    const user = useSelector((state) => state.user.user);
    const dispatch = useDispatch();

    const handleLogout = () => {
        authService.logout();
        dispatch(logout());
        set([]);
    };

    return (
                <Card
                    hoverable
                    style={{
                        width: 240,
                        borderRadius: 10,
                        boxShadow: '0 2px 8px rgba(0, 0, 0, 0.15)',
                        display: 'flex',
                        flexDirection: 'column',
                        margin: '0 auto',
                        marginTop: 100,
                    }}
                    cover={<img alt="example" src={put} style={{ borderRadius: '10px 10px 0 0' }} />}
                >
                    <Card.Meta
                        title={<h3 style={{ marginBottom: '10px' }}>{user.username}</h3>}
                        description={user.email}
                        style={{ textAlign: 'center' }}
                    />
                    <div style={{ margin: '10px 0', textAlign: 'center' }}>
                        <Button
                            type="primary"
                            danger
                            style={{ width: '100%' }}
                            onClick={handleLogout}
                        >
                            Выйти
                        </Button>
                    </div>
                </Card>
    );
};