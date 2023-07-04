import {Product} from './Product';
import {Row, Col, Button} from 'react-bootstrap';
import {useState} from "react";
import logo from './imgs/logo.svg';
import {SearchBar} from "./Search";

export const CartList = ({products, addToCart}) => {
    const [productList, setProductItems] = useState(products);
    const [productQuery, setProductQuery] = useState(products);
    const addNewProduct = () => {
        // Создайте новый объект товара
        const newProduct = {
            "id": productList.length + 1,
            "name": "Новый товар",
            "image":logo,
            "price": 0,
        };
        setProductItems([...productList, newProduct]);
        setProductQuery([...productList, newProduct]);
    };
    return (
        <div>
            <div style={{padding: 20}}>
                <SearchBar productList={productList} setProductItems={setProductQuery}/>
            </div>
            <Row>
                {productQuery.map((product) => (
                    <Col key={product.id} xs={12} md={6} lg={4} xl={3}>
                        <Product product={product} addToCart={addToCart} setProductItems={setProductItems}/>
                    </Col>
                ))}
            </Row>
            <div style={{ display: 'flex', justifyContent: 'center', padding: '20px' }}>
                <Button variant="primary" onClick={addNewProduct}>Добавить товар</Button>
            </div>
        </div>
    );
};