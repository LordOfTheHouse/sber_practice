import {createSlice} from '@reduxjs/toolkit'
import ava from "../img/sasha.jpg"


export const userSlice = createSlice({
    name: 'user',
    initialState: {
        user:
            {
                id: 1,
                name: "Sasha",
                email: "1@mail.ru",
                image: ava,
                password: "11111",
                cart: []
            },
        isAuth: false,
        isReg: false,
        users: [
            {
                id: 1,
                name: "Sasha",
                email: "1@mail.ru",
                image: ava,
                password: "11111",
                cart: []
            },
            {
                id: 2,
                name: "Albert",
                email: "2@mail.ru",
                image: ava,
                password: "11111",
                cart: []
            },
        ]
    },
    reducers: {
        createUser: (state, action) => {
            const {name, password, email} = action.payload
            state.isReg = false;
            state.isAuth = false;
            let newUser = {
                id: Math.floor(Math.random() * 1_000_000),
                name: name,
                email: email,
                image: ava,
                password: password,
                cart: []
            }
            state.user = newUser;
            state.users.push(newUser);

        },
        edit: (state, action) => {
            const {name, email, id} = action.payload;
            // eslint-disable-next-line array-callback-return
            state.users.map(userItem => {
                if (id === userItem.id) {
                    userItem.name = name;
                    userItem.email = email;
                }
            });
            state.user.name = name;
            state.user.email = email;
        },
        logout: (state) => {
            state.user = {};
            state.isAuth = true;
        },
        registration: (state) => {
            state.isReg = true;
        },
        signIn: (state, action) => {
            const {name, password} = action.payload;
            // eslint-disable-next-line array-callback-return
            state.users.map(userItem => {
                if (name === userItem.email && password === userItem.password) {
                    state.isAuth = false;
                    state.user = userItem
                }
            });
            if (state.isAuth) {
                alert("Неправильный логин или пароль");
            }

        },
        saveCart: (state, action) => {
            state.users.map(userItem => {
                if (state.user.id === userItem.id) {
                    userItem.cart = action.payload;
                }
            });
        },
    }
})

// Action creators are generated for each case reducer function
export const {createUser, saveCart, edit, logout, signIn, registration} = userSlice.actions

export default userSlice.reducer