import axios from "axios";
import { message } from 'antd';
import authHeader from "./authHeader";
import {API_URL} from "./API_URL";

const API_URL_PAYMENT = API_URL + "payment"
export const pay = (transfer) => {
    return axios.post(API_URL_PAYMENT, transfer, {headers: authHeader()}).then(
        (response) => {
            message.info("Оплата прошла успешно "+response.data);
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            message.error(_content);
        });
};

const payService = {
    pay
};



export default payService;