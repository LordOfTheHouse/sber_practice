import { configureStore } from '@reduxjs/toolkit'
import productsSlice from './slices/ProductsSlice'
import userSlice from './slices/UserSlice'
import cartSlice from "./slices/CartSlice";
export default configureStore({
    reducer: {
        products:productsSlice,
        user: userSlice,
        cart: cartSlice,
    }
})