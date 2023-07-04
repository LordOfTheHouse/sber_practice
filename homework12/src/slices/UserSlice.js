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
                password:"11111"
            },
        isAuth: false,
        isReg: false,
        users: [
            {
                id: 1,
                name: "Sasha",
                email: "@mail.ru",
                image: ava,
                password:"11111"
            },
            {
                id: 2,
                name: "Albert",
                email: "@mail.ru",
                image: ava,
                password:"11111"
            },
        ]
    },
    reducers: {
        edit: (state, action) => {
            const {name, email} = action.payload;
            state.user.name = name;
            state.user.email = email;
        },
        logout: (state) => {
          state.isAuth = true;
        },
        signIn:(state, action) => {
            const {name, password} = action.payload;
            state.users.map(userItem => {
                if(name===userItem.name && password===userItem.password){
                    state.isAuth = false;
                    state.user = userItem
                }
            });
            if(state.isAuth){
                alert("Попробуйте снова");
            }

        },
        registration: (state) => {
            state.isReg = true;
        },
        createUser: (state, action) => {
            const {name, password, email} = action.payload
            state.isReg = false;
            state.isAuth = false;
            let newUser ={
                id: 2,
                name:name,
                email: email,
                image: ava,
                password:password
            }
            state.user = newUser;
            state.users.push(newUser);

        },
    }
})

// Action creators are generated for each case reducer function
export const {createUser, edit, logout, signIn, registration} = userSlice.actions

export default userSlice.reducer