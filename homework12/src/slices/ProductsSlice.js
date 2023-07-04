import {createSlice} from '@reduxjs/toolkit'
import logo from "../logo.svg"
import {removeFromCart, update} from './CartSlice';

export const productsSlice = createSlice({
    name: 'products',
    initialState: {
        products: [
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

        ],
        query: [
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

        ],
    },
    reducers: {
        push: (state, action) => {
            const product = action.payload;
            product.id = Math.floor(Math.random() * 1_000_000);
            state.products = [product, ...state.products]
            state.query = state.products
        },

        remove: (state, action) => {
            state.products = state.products.filter(product => product.id !== action.payload.id)
            state.query = state.products
        },

        edit: (state, action) => {
            const {id, name, price} = action.payload;
            state.products = state.products.map(product => {
                if (product.id === id) {
                    const newProduct = {
                        ...product,
                        name: name || product.name,
                        price: price || product.price,
                    };
                    update(newProduct);
                    return newProduct;
                }
                return product;
            });
            state.query = state.products
        },
        searchProducts: (state, action) => {
            const query = action.payload.toLowerCase(); // Приводим запрос к нижнему регистру для регистронезависимого поиска
            // Фильтруем список продуктов на основе запроса
            state.query = state.products.filter(product => product.name.toLowerCase().includes(query));
        },


    }
})

// Action creators are generated for each case reducer function
export const {searchProducts, push, remove, edit} = productsSlice.actions

export default productsSlice.reducer