import {createSlice} from '@reduxjs/toolkit'


export const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        cart: [],
        total: 0,
    },
    reducers: {
        addToCart: (state, action) => {
            const {id, name, price, amount} = action.payload;
            const existingProductIndex = state.cart.findIndex((product) => product.id === id);

            if (existingProductIndex !== -1) {
                state.cart[existingProductIndex].amount += amount;
            } else {
                state.cart.push({id, name, price, amount});
            }

            state.total += price * amount;
        },
        removeFromCart: (state, action) => {
            const {id, amount, price} = action.payload

            state.total -= price * amount;
            state.cart = state.cart.filter(product => product.id !== id);
        },
        clearCart: (state) => {
            state.cart = [];
            state.total = 0;
        },
        updateQuantity: (state, action) => {
            const product = action.payload;
            const updatedCart = state.cart.filter(productCart => productCart.id !== product.id);
            if (state.cart.length !== updatedCart.length) {
                state.cart = [...updatedCart, product];

                let total = 0;
                for (const item of state.cart) {
                    total += item.price * item.amount;
                }
                state.total = total;
            }

        },
        update: (state, action) => {
            const nProduct = action.payload;
            state.cart = state.cart.map(product => {
                if (product.id === nProduct.id) {
                    return {
                        ...nProduct,
                        name: nProduct.name || product.name,
                        price: nProduct.price || product.price,
                    };
                }
                return product;
            });
            alert("kek")
            let total = 0;
            for (const item of state.cart) {
                total += item.price * item.amount;
            }
            state.total = total;
        },
    },
})

// Action creators are generated for each case reducer function
export const {removeFromCart, addToCart, clearCart, updateQuantity, update} = cartSlice.actions

export default cartSlice.reducer