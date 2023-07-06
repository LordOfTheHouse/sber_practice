import {createSlice} from '@reduxjs/toolkit'


export const cartSlice = createSlice({
    name: 'cart',
    initialState: {
        cart: [],
        total: 0,
    },
    reducers: {
      set:(state, action)=>{
           state.cart = action.payload
        },
        calculateTotal: (state) => {
            let total = 0;
            state.cart.forEach(item => {
                total += item.price * item.amount;
            });
            state.total = total;
        },
    },

})

// Action creators are generated for each case reducer function
export const {set, calculateTotal} = cartSlice.actions

export default cartSlice.reducer