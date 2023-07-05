import axios from "axios";
import {calculateTotal, set} from "../slices/UserSlice";

const API_URL = "http://localhost:8080/customers";

export const getUser = (dispatch, id) => {
    return axios.get(`${API_URL}/${id}`).then(
        (response) => {
            dispatch(set(response.data));
            dispatch(calculateTotal());
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();
            console.error(_content)
            dispatch(set([]));
        });
};

export const createUser = (dispatch, user) => {
    return axios.post(API_URL, user).then(
        (response) => {
            getUser(dispatch, response.data);
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const updateUser = (dispatch, user) => {
    return axios.post(API_URL, user).then(
        (response) => {
            getUser(dispatch, user.id)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};
const signIn = (dispatch, email) => {
    return axios.get(API_URL+"/email?email="+email).then(
        (response) => {
            dispatch(set(response.data))
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const userService = {
    getUser, createUser, updateUser, signIn
};

export default userService