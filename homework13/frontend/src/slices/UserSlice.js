import {createSlice} from '@reduxjs/toolkit'
import ava from "../img/sasha.jpg"


export const userSlice = createSlice({
    name: 'user',
    initialState: {
        user: {},
        isReg: false,
        isAuth: true,
        total: 0

    },
    reducers: {
        set: (state, action) => {
            state.user = action.payload;
        },
        setReg: (state, action) => {
            state.isReg = action.payload
        },
        setAuth: (state, action) => {
            state.isAuth = action.payload
        },
        calculateTotal: state => {
            let total = 0;
            state.user.cart.forEach(item => {
                total += item.price * item.amount;
            });
            state.total = total;
        }
    }
})

// Action creators are generated for each case reducer function
export const {calculateTotal, set, setReg, setAuth} = userSlice.actions

export default userSlice.reducer