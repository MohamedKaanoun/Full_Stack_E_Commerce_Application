import { API_BASE_URL, api } from "../../config/ApiConfig";
import { GET_CART_FAILURE, GET_CART_SUCCESS } from "../Cart/ActioType";
import {
  CREATE_ORDER_FAILURE,
  CREATE_ORDER_REQUEST,
  CREATE_ORDER_SUCCESS,
  GET_ORDER_BY_ID_FAILURE,
  GET_ORDER_BY_ID_REQUEST,
  GET_ORDER_BY_ID_SUCCESS,
  GET_ORDER_HISTORY_FAILURE,
  GET_ORDER_HISTORY_REQUEST,
  GET_ORDER_HISTORY_SUCCESS,
} from "./ActionType";

export const createOrder = (reqData) => async (dispatch) => {
  try {
    dispatch({ type: CREATE_ORDER_REQUEST });
    const { data } = await api.post(`/orders/`, reqData.address);
    if (data.id) {
      reqData.navigate({ search: `step=3&order_id=${data.id}` });
    }
    dispatch({
      type: CREATE_ORDER_SUCCESS,
      payload: data,
    });
  } catch (error) {
    dispatch({
      type: CREATE_ORDER_FAILURE,
      payload: error.message,
    });
  }
};

export const getOrderById = (orderId) => async (dispatch) => {
  try {
    dispatch({ type: GET_ORDER_BY_ID_REQUEST });
    const { data } = await api.get(`/orders/${orderId}`);
    dispatch({
      type: GET_ORDER_BY_ID_SUCCESS,
      payload: data,
    });
    console.log("order : ", data);
  } catch (error) {
    dispatch({
      type: GET_ORDER_BY_ID_FAILURE,
      payload: error.message,
    });
  }
};

export const getOrdersHistory = (jwt) => async (dispatch) => {
  try {
    dispatch({ type: GET_ORDER_HISTORY_REQUEST });
    const { data } = await api.get(`/orders/user`, {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    dispatch({
      type: GET_ORDER_HISTORY_SUCCESS,
      payload: data,
    });
  } catch (error) {
    dispatch({
      type: GET_ORDER_HISTORY_FAILURE,
      payload: error.message,
    });
  }
};
