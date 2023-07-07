import axios from "axios";
import {API_URL} from "./API_URL";
import {message} from "antd";
import {setPercent} from "../slices/CartSlice";

const API_URL_PROMOCODES = API_URL + "promocode"

const getPromocodes = (dispatch, name) => {
    return axios.get(API_URL_PROMOCODES+`/${name}`).then(
        (response) => {
            dispatch(setPercent(response.data));
            message.success("Промокод активирован")
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();
            console.log(_content);
            dispatch(setPercent(100));
            message.error("Промокод не найден")
        });
};

const promocodeService = {
    getPromocodes
};



export default promocodeService;
