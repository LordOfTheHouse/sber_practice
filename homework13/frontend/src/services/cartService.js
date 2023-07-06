import axios from "axios";
import authHeader from "./authHeader";
import {set, calculateTotal} from "../slices/CartSlice";
import {API_URL} from "./API_URL";

const API_URL_CART = API_URL + "carts"
export const getCart = (dispatch, id) => {
    return axios.get(`${API_URL_CART}/${id}`).then(
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

export const addProductCart = (dispatch, idUser, product) => {
    return axios.post(API_URL_CART + `/${idUser}`, product, {headers: authHeader()}).then(
        (response) => {
            getCart(dispatch, idUser);

        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const updateProductIntCart = (dispatch, idUser, product) => {
    return axios.put(API_URL_CART + `/${idUser}/product/${product.id}`, product, {headers: authHeader()}).then(
        (response) => {
            getCart(dispatch, idUser)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const deleteProductCart = (dispatch, userId, productId) => {
    return axios.delete(API_URL_CART + `/${userId}/products/${productId}`, {headers: authHeader()}).then(
        (response) => {
            getCart(dispatch, userId)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const cartService = {
    addProductCart, updateProductIntCart, deleteProductCart, getCart
};



export default cartService;