import { Row, Col, Button } from 'antd';
import logo from '../logo.svg';
import { Product } from './Product';
import {useDispatch, useSelector} from "react-redux";
import {push} from "../slices/ProductsSlice";
import Search from "./Search";

export const ListProduct = () => {
    const productList = useSelector((state) => state.products.query)
    const dispatch = useDispatch()

    const addNewProduct = () => {
        // Создайте новый объект товара
        const newProduct = {
            id: 0,
            name: 'Новый товар',
            image: logo,
            price: 0,
        };
        dispatch(push((newProduct)));

    };

    return (
        <div>
            <Search/>
            <Row gutter={[16, 24]} style={{
                display: 'flex',
                justifyContent: 'center',
                marginTop: '8px'
            }}>
                {productList.map((product) => (
                    <Col key={product.id} span={5}>
                        <Product product={product} />
                    </Col>
                ))}
            </Row>
            <div style={{ display: 'flex', justifyContent: 'center', padding: '20px' }}>
                <Button type="primary" onClick={addNewProduct}>
                    Добавить товар
                </Button>
            </div>
        </div>
    );
};