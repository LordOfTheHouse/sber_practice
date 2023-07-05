import axios from "axios";
import {getUser} from "./userService";
import { message } from 'antd';


const API_URL = "http://localhost:8080/payment";

export const pay = (dispatch, transfer) => {
    return axios.post(API_URL, transfer).then(
        (response) => {
            message.info("Ооплата прошла успешно "+response.data);
            getUser(dispatch, transfer.idUser)
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