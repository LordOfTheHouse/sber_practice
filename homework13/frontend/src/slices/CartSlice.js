import {createSlice} from '@reduxjs/toolkit'


export const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        cart: [],
        total: 0,
        percent: 0
    },
    reducers: {
        set: (state, action) => {
            state.cart = action.payload
        },
        setPercent: (state, action) => {
            state.precent = action.payload.percent
        },
        calculateTotal: (state) => {
            let total = 0;
            state.cart.forEach(item => {
                total += item.price * item.amount;
            });
            let discount = (100 - state.percent) / 100
            state.total = total * discount;
        },
    },

})

// Action creators are generated for each case reducer function
export const {set, calculateTotal, setPercent} = cartSlice.actions

export default cartSlice.reducer