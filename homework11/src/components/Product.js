import "bootstrap/dist/css/bootstrap.css";
import {Card, Button, ButtonGroup} from 'react-bootstrap';
import './CartProduct.css';
import {useState} from 'react';


const centeredStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
};
export const Product = ({product, addToCart, setProductItems}) => {
    const {image, id} = product;
    const [name, setName] = useState(product.name);
    const [price, setPrice] = useState(product.price);
    const [isEditing, setIsEditing] = useState(false);

    const handleAddToCart = () => {
        addToCart({id, name, price});
    };

    const handleEdit = () => {
        setIsEditing(!isEditing);
    };

    const removeFromProduct = () => {
        setProductItems((prevItems) => prevItems.filter((item) => item.id !== id));
    };
    const handleNameChange = (e) => {
        setName(e.target.value);
    };

    const handlePriceChange = (e) => {
        setPrice(e.target.value);
    };


    return (
        <div className={"cart-item"}>
            <Card style={{width: '18rem'}}>
                <Card.Img variant="top" src={image}/>
                <Card.Body>
                    {isEditing ? (
                        <>
                            <input type="text" value={name} onChange={handleNameChange}/>
                            <input type="text" value={price} onChange={handlePriceChange}/>
                        </>
                    ) : (
                        <>
                            <Card.Title style={centeredStyle}>{name}</Card.Title>
                            <Card.Text style={centeredStyle}>{price}</Card.Text>
                        </>
                    )}
                    <ButtonGroup className="d-flex justify-content-center">
                        <Button variant="primary" onClick={handleAddToCart} style={{border: "2px solid #ffa100"}}>
                            Добавить в корзину
                        </Button>
                        <Button variant="primary" onClick={handleEdit} style={{border: "2px solid #ffa100"}}>
                            {isEditing ? 'Сохранить' : 'Изменить'}
                        </Button>
                        <Button variant="primary" onClick={removeFromProduct} style={{border: "2px solid #ffa100"}}>
                            Удалить
                        </Button>
                    </ButtonGroup>
                </Card.Body>
            </Card>
        </div>
    );
};
