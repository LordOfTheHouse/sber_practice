import {Card, ListGroup} from 'react-bootstrap';

const centeredStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
};

export const Cart = ({cartItems, setCartItems}) => {
    const handleQuantityChange = (e, item) => {
        const newQuantity = parseInt(e.target.value);
        const updatedCartItems = cartItems.map((cartItem) =>
            cartItem.id === item.id
                ? {
                    ...cartItem,
                    quantity: newQuantity > 1 ? newQuantity : 1,
                    totalPrice: newQuantity > 1 ? cartItem.price * newQuantity: +cartItem.price
                }
                : cartItem
        );

        setCartItems(updatedCartItems);
    };

    const handleClearCart = () => {
        setCartItems([]);
    };
    // Функция для удаления товара из корзины
    const handleRemoveItem = (item) => {
        const updatedCartItems = cartItems.filter((cartItem) => cartItem.id !== item.id);
        setCartItems(updatedCartItems);
    };

    // Функция для подсчета общей суммы
    const calculateTotal = () => {
        let total = 0;
        cartItems.forEach((item) => {
            total += (item.totalPrice) ? item.totalPrice : 0;
        });
        return total;
    };

    const handlePay = () => {
        // Здесь можно добавить логику оплаты
        alert('Оплата прошла успешно!');
        setCartItems([]);
    };

    return (
        <div>
            <h2>Корзина</h2>
            <Card style={{width: '24rem'}}>
                <ListGroup variant="flush">
                    {cartItems.map((item) => (
                        <ListGroup.Item key={item.id}>
                            <div>{item.name}</div>
                            <div>
                                <input
                                    type="number"
                                    value={item.quantity}
                                    onChange={(e) => handleQuantityChange(e, item)}
                                />
                            </div>
                            <div>
                                <span>{item.totalPrice ? item.totalPrice : 0} руб.</span>
                            </div>
                            <button onClick={() => handleRemoveItem(item)}>Удалить</button>
                        </ListGroup.Item>
                    ))}
                </ListGroup>
                <div style={centeredStyle}>
                    <button onClick={handlePay}>Оплатить</button>
                </div>
                <div style={centeredStyle}>
                    <button onClick={handleClearCart}>Очистить корзину</button>
                </div>
                <div style={centeredStyle}>
                    Итого: {calculateTotal()} руб.
                </div>
            </Card>
        </div>
    );
};