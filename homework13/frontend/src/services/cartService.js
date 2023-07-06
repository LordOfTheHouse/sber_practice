import axios from "axios";
import {getUser} from "./userService";

const API_URL = "http://localhost:8080/carts";


export const addProductCart = (dispatch, idUser, product) => {
    return axios.post(API_URL + `/${idUser}`, product).then(
        (response) => {
            getUser(dispatch, idUser);

        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const updateProductIntCart = (dispatch, idUser, product) => {
    return axios.put(API_URL + `/${idUser}/product/${product.id}`, product).then(
        (response) => {
            getUser(dispatch, idUser)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const deleteProductCart = (dispatch, userId, productId) => {
    return axios.delete(API_URL + `/${userId}/products/${productId}`).then(
        (response) => {
            getUser(dispatch, userId)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const cartService = {
    addProductCart, updateProductIntCart, deleteProductCart
};



export default cartService;