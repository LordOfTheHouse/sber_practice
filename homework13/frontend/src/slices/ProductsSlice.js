import {createSlice} from '@reduxjs/toolkit'
import logo from "../logo.svg"


export const productsSlice = createSlice({
    name: 'products',
    initialState: {
        products: [],
    },
    reducers: {
        set: (state, action) => {
            state.products = action.payload;
        }
    }
})

// Action creators are generated for each case reducer function
export const {set} = productsSlice.actions

export default productsSlice.reducer