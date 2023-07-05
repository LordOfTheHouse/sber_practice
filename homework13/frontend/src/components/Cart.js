import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { Button, Empty, InputNumber } from 'antd';
import cartService from "../services/cartService";
import { pay } from "../services/paymentService";

const Cart = () => {
    const user = useSelector((state) => state.user.user);
    const total = useSelector((state) => state.user.total);
    const dispatch = useDispatch();
    const [sortType, setSortType] = useState('');

    const handleRemoveFromCart = (id) => {
        cartService.deleteProductCart(dispatch, user.id, id);
    };

    const handleClearCart = () => {
        user.cart.map(productCart => cartService.deleteProductCart(dispatch, user.id, productCart.id));
    };

    const handleQuantityChange = (id, quantity) => {
        const newProduct = {
            id: id,
            amount: quantity
        };
        cartService.updateProductIntCart(dispatch, user.id, newProduct);
    };

    const handlePayment = () => {
        pay(dispatch, {
            "numberCart": "1111",
            "idUser": user.id,
            "promoCode": "sale20"
        });
    };

    const handleSort = (sortType) => {
        setSortType(sortType);
    };

    const sortedCart = [...user.cart].sort((a, b) => {
        if (sortType === "nameAZ") {
            return a.name.localeCompare(b.name);
        }
        if (sortType === "nameZA") {
            return b.name.localeCompare(a.name);
        }
        if (sortType === "priceLowToHigh") {
            return a.price - b.price;
        }
        if (sortType === "priceHighToLow") {
            return b.price - a.price;
        }
        return 0;
    });

    return (
        <div>
            {user.cart === undefined || user.cart === [] ? (
                <Empty description="Корзина пуста" />
            ) : (
                <div style={{ margin: '0 auto', marginTop: 100 }}>
                    <div>
                        <Button onClick={() => handleSort('nameAZ')} style={{ marginRight: '10px' }}>По имени (А-Я)</Button>
                        <Button onClick={() => handleSort('nameZA')} style={{ marginRight: '10px' }}>По имени (Я-А)</Button>
                        <Button onClick={() => handleSort('priceLowToHigh')} style={{ marginRight: '10px' }}>По цене (вверх)</Button>
                        <Button onClick={() => handleSort('priceHighToLow')} style={{ marginRight: '10px' }}>По цене (вниз)</Button>
                    </div>
                    {sortedCart.map(({ id, name, price, amount }) => (
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