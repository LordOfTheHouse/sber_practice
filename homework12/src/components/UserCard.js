import React, {useState} from 'react';
import {Card, Button, Input} from 'antd';
import {useDispatch, useSelector} from 'react-redux';
import {edit, logout, saveCart} from '../slices/UserSlice';
import {clearCart} from "../slices/CartSlice";
import {AuthForm} from './AutoForm';

export const UserCard = () => {
    const user = useSelector((state) => state.user.user);
    const cart = useSelector((state) => state.cart.cart);
    const isAuthForm = useSelector((state) => state.user.isAuth);
    const [isEditing, setEditing] = useState(false);
    const [editedEmail, setEditedEmail] = useState(user.email);
    const [editedName, setEditedName] = useState(user.name);
    const dispatch = useDispatch();

    const editUser = () => {
        if (isEditing) {
            const eUser = {
                id: user.id,
                name: editedName,
                email: editedEmail,
            };
            dispatch(edit(eUser));
            setEditing(false);
        } else {
            setEditing(true);
        }
    };

    const handleLogout = () => {
        dispatch(saveCart(cart));
        dispatch(logout());
        dispatch(clearCart());
    };

    return (
        <>
            {isAuthForm ? (
                <AuthForm/>
            ) : (
                <Card
                    hoverable
                    style={{
                        width: 240,
                    }}
                    cover={<img alt="example" src={user.image}/>}
                >
                    <Card.Meta
                        title={
                            isEditing ? (
                                <Input
                                    type="text"
                                    value={editedName}
                                    onChange={(e) => setEditedName(e.target.value)}
                                />
                            ) : (
                                user.name
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
                    />
                    <div
                        style={{
                            display: 'flex',
                            justifyContent: 'center',
                            marginTop: '8px',
                        }}
                    >
                        <Button
                            type="primary"
                            style={{marginRight: '8px', width: '100%'}}
                            onClick={editUser}
                        >
                            {isEditing ? 'Save' : 'Edit'}
                        </Button>
                    </div>
                    <div
                        style={{
                            display: 'flex',
                            justifyContent: 'center',
                            marginTop: '8px',
                        }}
                    >
                        <Button
                            type="primary"
                            danger
                            style={{width: '100%'}}
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