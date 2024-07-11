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

const initialState = {
  cart: null,
  loading: false,
  error: null,
  cartItems: [],
  updateCartItem: null,
  deleteCartItem: null,
};

export const cartReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_ITEM_TO_CART_REQUEST:
    case GET_CART_REQUEST:
    case REMOVE_CART_ITEM_REQUEST:
    case UPDATE_CART_ITEM_REQUEST:
      return { ...state, loading: true, error: null };
    case ADD_ITEM_TO_CART_SUCCESS:
    case GET_CART_SUCCESS:
      return { ...state, cart: action.payload, loading: false };
    case REMOVE_CART_ITEM_SUCCESS:
    case UPDATE_CART_ITEM_SUCCESS:
      return { ...state, loading: false };
    case ADD_ITEM_TO_CART_FAILURE:
    case GET_CART_FAILURE:
    case REMOVE_CART_ITEM_FAILURE:
    case UPDATE_CART_ITEM_FAILURE:
      return { ...state, error: action.payload, loading: false };
    default:
      return state;
  }
};
