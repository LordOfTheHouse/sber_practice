import React, { useState } from 'react';
import { Card, Form, Button } from 'react-bootstrap';

const centeredStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
};

export const ViewUser = ({ user }) => {
    const { image } = user;
    const [isEditMode, setIsEditMode] = useState(false);
    const [editedName, setEditedName] = useState(user.name);
    const [editedEmail, setEditedEmail] = useState(user.email);

    const handleEditClick = () => {
        setIsEditMode(!isEditMode);
    };

    const handleInputChange = (e) => {
        if (e.target.name === "name") {
            setEditedName(e.target.value);
        } else if (e.target.name === "email") {
            setEditedEmail(e.target.value);
        }
    };

    return (
        <div className={"cart-item"} style={centeredStyle}>
            <Card style={{ width: '18rem' }}>
                <Card.Img variant="top" src={image} />
                <Card.Body>
                    {isEditMode ? (
                        <>
                            <Form.Control
                                type="text"
                                name="name"
                                value={editedName}
                                onChange={handleInputChange}
                            />
                            <Form.Control
                                type="email"
                                name="email"
                                value={editedEmail}
                                onChange={handleInputChange}
                            />
                        </>
                    ) : (
                        <>
                            <Card.Title style={centeredStyle}>{editedName}</Card.Title>
                            <Card.Text style={centeredStyle}>{editedEmail}</Card.Text>
                        </>
                    )}
                    <Card.Footer style={centeredStyle}>
                        <Button variant="primary" onClick={handleEditClick}>
                            {isEditMode ? "Сохранить" : "Изменить"}
                        </Button>
                    </Card.Footer>
                </Card.Body>
            </Card>
        </div>
    );
};
