import React, {useEffect, useState} from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Empty, InputNumber } from 'antd';
import cartService from "../services/cartService";
import { pay } from "../services/paymentService";


const Cart = () => {
    const user = useSelector((state) => state.user.user);
    const isAuth = useSelector((state) => state.user.isAuth);
    const cart = useSelector((state) => state.cart.cart);
    const total = useSelector((state) => state.cart.total);
    const dispatch = useDispatch();

    useEffect(() => {
        if (!isAuth) {
            cartService.getCart(dispatch, user.id);
        }

    }, []);
    const handleRemoveFromCart = (id) => {

        cartService.deleteProductCart(dispatch, user.id, id);
    };

    const handleClearCart = () => {
        cart.map(productCart => cartService.deleteProductCart(dispatch, user.id, productCart.id));
    };

    const handleQuantityChange = (id, quantity) => {
        const newProduct = {
            id: id,
            amount: quantity
        };
        cartService.updateProductIntCart(dispatch, user.id, newProduct);
    };

    const handlePayment = () => {
        pay({
            numberCart: "1111",
            idUser: user.id,
            promoCode: "sale20"
        });
        cartService.getCart(dispatch, user.id);
    };


    return (
        <div>
            {(cart === []) ? (
                <Empty description="Корзина пуста" />
            ) : (
                <div style={{ margin: '0 auto', marginTop: 100 }}>
                    {cart.map(({ id, name, price, amount }) => (
                        <div key={id}>
                            <p>Название: {name}</p>
                            <p>Цена: {price}</p>
                            <p>Количество:
                                <InputNumber
                                    min={1}
                                    max={100}
                                    value={amount}
                                    onChange={(value) => handleQuantityChange(id, value)}
                                />
                            </p>
                            <Button onClick={() => handleRemoveFromCart(id)} style={{ marginRight: '10px' }}>Удалить</Button>
                            <hr />
                        </div>
                    ))}
                    <div>Общая стоимость: {total}</div>
                    <Button onClick={handleClearCart} style={{ marginRight: '10px' }}>Очистить корзину</Button>
                    <Button onClick={handlePayment}>Оплатить</Button>
                </div>
            )}
        </div>
    );
};

export default Cart;