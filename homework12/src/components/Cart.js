import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, InputNumber } from 'antd';
import {removeFromCart, clearCart, updateQuantity} from '../slices/CartSlice';

const Cart = () => {
    const cart = useSelector((state) => state.cart.cart);
    const total = useSelector((state) => state.cart.total);
    const dispatch = useDispatch();

    const handleRemoveFromCart = (product) => {
        dispatch(removeFromCart(product));
    };

    const handleClearCart = () => {
        dispatch(clearCart());
    };

    const handleQuantityChange = (product, quantity) => {
        const newProduct = {
            id: product.id,
            name: product.name,
            price: product.price,
            amount: quantity
        };
        dispatch(updateQuantity(newProduct));
    };

    const handlePayment = () => {

        if(total===0){
            alert("Корзина пуста");
        }else{
            alert("Оплачено");
            dispatch(clearCart());
        }

    };

    return (
        <div style={{ backgroundColor: "blue", color: "white", padding: "20px" }}>
            {cart.map((product) => (
                <div key={product.id}>
                    <p>Название: {product.name}</p>
                    <p>Цена: {product.price}</p>
                    <p>Количество:
                        <InputNumber
                            min={1}
                            max={100}
                            value={product.amount}
                            onChange={(value) => handleQuantityChange(product, value)}
                        />
                    </p>
                    <Button onClick={() => handleRemoveFromCart(product)}>Удалить</Button>
                    <hr />
                </div>
            ))}
            <div>Общая стоимость: {total}</div>
            <Button onClick={handleClearCart}>Очистить корзину</Button>
            <Button onClick={handlePayment}>Оплатить</Button>
        </div>
    );
};

export default Cart;