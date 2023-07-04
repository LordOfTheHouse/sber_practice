import {Card, Button, InputNumber, Input} from 'antd';
import React, {useState} from 'react';
import {useDispatch} from "react-redux";
import {remove, edit} from "../slices/ProductsSlice";
import {addToCart} from "../slices/CartSlice";

export const Product = ({product}) => {

    const [isEditing, setEditing] = useState(false);
    const [editedPrice, setEditedPrice] = useState(product.price);
    const [editedName, setEditedName] = useState(product.name);

    const dispatch = useDispatch()
    const add = () => {
        // Добавить логику для добавления товара в корзину

        const newProductInCart = {
            id: product.id,
            name: product.name,
            price: product.price,
            amount: 1,
        }
        dispatch(addToCart(newProductInCart));
    };
    const editProduct = () => {
        if (isEditing) {
            const newProduct = {
                id: product.id,
                name: editedName,
                price: editedPrice,
            };
            dispatch(edit(newProduct));
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
            cover={<img alt="example" src={product.image}/>}
        >
            <Card.Meta
                title={isEditing ? <Input type="text" value={editedName}
                                          onChange={e => setEditedName(e.target.value)}/> : product.name}
                description={isEditing ? <InputNumber addonBefore="+" addonAfter="р" value={editedPrice}
                                                      onChange={value => setEditedPrice(value)}/> : `Price: ${product.price}`}
            />
            <div style={{marginTop: '16px'}}>
                <Button type="primary" style={{marginRight: '8px', width: '100%'}} onClick={add}>
                    Add to Cart
                </Button>
            </div>
            <div style={{
                display: 'flex',
                justifyContent: 'center',
                marginTop: '8px'
            }}>
                <Button style={{marginRight: '8px'}} onClick={editProduct}>
                    {isEditing ? 'Save' : 'Edit'}
                </Button>
                <Button onClick={() => dispatch(remove(product))}>
                    Delete
                </Button>
            </div>
        </Card>
    );
};