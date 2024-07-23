import { api } from "../../config/ApiConfig";
import {
  ADD_ITEM_TO_CART_FAILURE,
  ADD_ITEM_TO_CART_REQUEST,
  ADD_ITEM_TO_CART_SUCCESS,
  GET_CART_FAILURE,
  GET_CART_REQUEST,
  GET_CART_SUCCESS,
  REMOVE_CART_ITEM_FAILURE,
  REMOVE_CART_ITEM_REQUEST,
  REMOVE_CART_ITEM_SUCCESS,
  UPDATE_CART_ITEM_FAILURE,
  UPDATE_CART_ITEM_REQUEST,
  UPDATE_CART_ITEM_SUCCESS,
} from "./ActioType";

export const get = () => async (dispatch) => {
  dispatch({ type: GET_CART_REQUEST });

  try {
    const { data } = await api.get(`/carts/`);
    dispatch({ type: GET_CART_SUCCESS, payload: data });
    console.log("cart : ", data);
  } catch (error) {
    dispatch({ type: GET_CART_FAILURE, payload: error.message });
  }
};

export const removeCartItem = (cartItemId) => async (dispatch) => {
  dispatch({ type: REMOVE_CART_ITEM_REQUEST });

  try {
    const { data } = await api.delete(`/carts/cartItems/${cartItemId}`);
    dispatch({ type: REMOVE_CART_ITEM_SUCCESS, payload: cartItemId });
    dispatch(get());
  } catch (error) {
    dispatch({ type: REMOVE_CART_ITEM_FAILURE, payload: error.message });
  }
};

export const addItemToCart = (reqData) => async (dispatch) => {
  dispatch({ type: ADD_ITEM_TO_CART_REQUEST });

  try {
    const { data } = await api.put("/carts/add", reqData);
    console.log("req data : ", reqData);
    dispatch({ type: ADD_ITEM_TO_CART_SUCCESS, payload: data });
    dispatch(get()); // Refetch the cart after adding item
  } catch (error) {
    dispatch({ type: ADD_ITEM_TO_CART_FAILURE, payload: error.message });
  }
};

export const updateCartItem = (reqData) => async (dispatch) => {
  dispatch({ type: UPDATE_CART_ITEM_REQUEST });

  try {
    const { data } = await api.put(
      `/carts/cartItems/${reqData.cartItemId}`,
      reqData.data
    );
    console.log(reqData);
    dispatch({ type: UPDATE_CART_ITEM_SUCCESS, payload: data });
    dispatch(get()); // Refetch the cart after updating item
  } catch (error) {
    dispatch({ type: UPDATE_CART_ITEM_FAILURE, payload: error.message });
  }
};
