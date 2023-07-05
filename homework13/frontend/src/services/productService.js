import axios from "axios";
import {set} from "../slices/ProductsSlice";

const API_URL = "http://localhost:8080/products";

const getProducts = (dispatch) => {
    return axios.get(API_URL).then(
        (response) => {
            dispatch(set(response.data));
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();
            console.error(_content)
            dispatch(set([]));
        });
};

const getProductsName = (dispatch, name) => {
    return axios.get(`${API_URL}?name=${name}`).then(
        (response) => {
            dispatch(set(response.data));
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();
            console.error(_content)
            dispatch(set([]));
        });
};
const getProductsId = (dispatch, id) => {
    return axios.get(API_URL + `/${id}`).then(
        (response) => {
            dispatch(set(response.data));
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();
            console.error(_content)
            dispatch(set([]));
        });
};
export const createProduct = ( dispatch, product) => {
    return axios.post(API_URL, product).then(
        (response) => {
            getProducts(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const updateProduct = (dispatch, product) => {
    return axios.put(API_URL, product).then(
        (response) => {
            getProducts(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const deleteProduct = (dispatch, id) => {
    return axios.delete(API_URL + `/${id}`).then(
        (response) => {
            getProducts(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const productService = {
    getProducts, createProduct, deleteProduct, getProductsId, getProductsName, updateProduct
};

export default productService