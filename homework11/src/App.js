import logo from './components/imgs/logo.svg';
import oleg from './components/imgs/oleg.jpg';
import './App.css';
import "bootstrap/dist/css/bootstrap.css";
import {NavbarHome} from './components/Navbar.js';
import {CartList} from './components/ListProduct.js';
import {ViewUser} from "./components/ViewUser";
import {useState} from "react";
import {Cart} from "./components/Cart.js";

function App() {

    const user = {
        id: 1,
        name: 'Oleg',
        email: "kirill@oleg.oleg",
        image: oleg,

    };
    const products = [
        {
            id: 1,
            name: 'Product 1',
            image: logo,
            price: '900',
        },
        {
            id: 2,
            name: 'Product 2',
            image: logo,
            price: '400',
        },
        {
            id: 3,
            name: 'Product 3',
            image: logo,
            price: '15',
        },
        {
            id: 4,
            name: 'Product 4',
            image: logo,
            price: '10',
        },
        {
            id: 5,
            name: 'Product 5',
            image: logo,
            price: '20',
        },
        {
            id: 6,
            name: 'Product 6',
            image: logo,
            price: '$20',
        },
        {
            id: 7,
            name: 'Product 7',
            image: logo,
            price: '200',
        },
        {
            id: 8,
            name: 'Product 8',
            image: logo,
            price: '100',
        },
    ];
    const [isUser, setIsUser] = useState(false)
    const [cartItems, setCartItems] = useState([]);
    const addToCart = (product) => {
        const itemExists = cartItems.find((item) => item.id === product.id);
        if (!itemExists) {
            setCartItems([...cartItems, product]);
        }
    };


    return (<div className="container">
            <div>
                <NavbarHome isUser={isUser} setIsUser={setIsUser}/>
            </div>
            {!isUser ? (
                <div>
                    <CartList products={products} addToCart={addToCart}/>
                </div>

            ) : (
                <>
                    <div style={{padding: 20}}>
                        <ViewUser user={user}/>
                    </div>
                    <div style={{padding: 20}}>
                        <Cart cartItems={cartItems} setCartItems={setCartItems}/>
                    </div>
                </>
            )}
        </div>
    );
};

export default App;
