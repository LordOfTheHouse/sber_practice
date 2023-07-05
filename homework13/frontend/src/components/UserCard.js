import React, { useState } from 'react';
import { Card, Button, Input } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import userService from "../services/userService";
import { AuthForm } from './AutoForm';
import { set, setAuth } from "../slices/UserSlice";

export const UserCard = () => {
    const user = useSelector((state) => state.user.user);
    const isAuthForm = useSelector((state) => state.user.isAuth);
    const [isEditing, setEditing] = useState(false);
    const [editedEmail, setEditedEmail] = useState(user.email);
    const [editedName, setEditedName] = useState(user.name);
    const dispatch = useDispatch();

    const editUser = () => {
        if (isEditing) {
            const updatedUser = {
                id: user.id,
                name: editedName,
                email: editedEmail,
            };
            userService.updateUser(dispatch, updatedUser);
            setEditing(false);
        } else {
            setEditing(true);
        }
    };

    const handleLogout = () => {
        dispatch(set({}));
        dispatch(setAuth(true));
    };

    return (
        <>
            {isAuthForm ? (
                <AuthForm />
            ) : (
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
                    cover={<img alt="example" src={user.image} style={{ borderRadius: '10px 10px 0 0' }} />}
                >
                    <Card.Meta
                        title={
                            isEditing ? (
                                <Input
                                    type="text"
                                    value={editedName}
                                    onChange={(e) => setEditedName(e.target.value)}
                                    style={{ marginBottom: '10px' }}
                                />
                            ) : (
                                <h3 style={{ marginBottom: '10px' }}>{user.name}</h3>
                            )
                        }
                        description={
                            isEditing ? (
                                <Input
                                    type="text"
                                    value={editedEmail}
                                    onChange={(e) => setEditedEmail(e.target.value)}
                                />
                            ) : (
                                user.email
                            )
                        }
                        style={{ textAlign: 'center' }}
                    />
                    <div style={{ margin: '10px 0', textAlign: 'center' }}>
                        <Button
                            type="primary"
                            style={{ marginBottom: '10px', width: '100%' }}
                            onClick={editUser}
                        >
                            {isEditing ? 'Сохранить' : 'Редактировать'}
                        </Button>
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
            )}
        </>
    );
};