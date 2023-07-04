import {Card, Button, InputNumber, Input} from 'antd';
import React, {useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {edit} from "../slices/UserSlice";


export const UserCard = () => {
    const user = useSelector((state) => state.user.user);

    const [isEditing, setEditing] = useState(false);
    const [editedEmail, setEditedEmail] = useState(user.email);
    const [editedName, setEditedName] = useState(user.name);

    const dispatch = useDispatch()

    const editUser = () => {
        if (isEditing) {
            const eUser = {
                name: editedName,
                email: editedEmail,
            };
            dispatch(edit(eUser));
            setEditing(false);

        } else {
            setEditing(true);
        }
    };

    return (
        <Card
            hoverable
            style={{
                width: 240,
            }}
            cover={<img alt="example" src={user.image}/>}
        >
            <Card.Meta
                title={isEditing ?
                    <Input type="text" value={editedName} onChange={e => setEditedName(e.target.value)}/> : user.name}
                description={isEditing ? <Input type="text" value={editedEmail}
                                                onChange={e => setEditedEmail(e.target.value)}/> : user.email}
            />
            <div style={{
                display: 'flex',
                justifyContent: 'center',
                marginTop: '8px'
            }}>
                <Button type="primary" style={{marginRight: '8px', width: '100%'}} onClick={editUser}>
                    {isEditing ? 'Save' : 'Edit'}
                </Button>
            </div>
        </Card>
    );
};