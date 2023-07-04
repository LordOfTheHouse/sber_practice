import { Row, Col, Button } from 'antd';
import logo from '../logo.svg';
import { useDispatch, useSelector } from 'react-redux';
import { push } from '../slices/ProductsSlice';
import Search from './Search';
import {Product} from "./Product";
import { useMediaQuery } from 'react-responsive';

export const ListProduct = () => {
    const productList = useSelector((state) => state.products.query);
    const dispatch = useDispatch();

    const addNewProduct = () => {
        // Создайте новый объект товара
        const newProduct = {
            id: 0,
            name: 'Новый товар',
            image: logo,
            price: 100,
        };
        dispatch(push(newProduct));
    };

    const isMobile = useMediaQuery({ maxWidth: 768 });
    const isTablet = useMediaQuery({ minWidth: 769, maxWidth: 1024 });

    let columnCount = 5;
    if (isTablet) {
        columnCount = 2;
    } else if (isMobile) {
        columnCount = 1;
    }

    return (
        <div>
            <Search />
            <Row gutter={[16, 24]} style={{ display: 'flex', justifyContent: 'center', marginTop: '8px' }}>
                {productList.map((product) => (
                    <Col key={product.id} span={24 / columnCount}>
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