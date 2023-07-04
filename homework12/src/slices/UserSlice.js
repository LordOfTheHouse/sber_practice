import {createSlice} from '@reduxjs/toolkit'
import ava from "../img/sasha.jpg"

export const userSlice = createSlice({
    name: 'user',
    initialState: {
        user:
            {
                id: 1,
                name: "Sasha",
                email: "@mail.ru",
                image: ava,
            },
    },
    reducers: {
        edit: (state, action) => {
            const {name, email} = action.payload;
            state.user.name = name;
            state.user.email = email;
        },
    }
})

// Action creators are generated for each case reducer function
export const {edit} = userSlice.actions

export default userSlice.reducer