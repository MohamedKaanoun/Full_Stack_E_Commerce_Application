import { api } from "../../config/ApiConfig";
import {
  FIND_PRODUCT_BY_ID_FAILURE,
  FIND_PRODUCT_BY_ID_REQUEST,
  FIND_PRODUCT_BY_ID_SUCCESS,
  FIND_PRODUCT_PRODUCTS_FAILURE,
  DELETE_PRODUCT_REQUEST,
  FIND_PRODUCT_PRODUCTS_SUCCESS,
  CREATE_PRODUCT_REQUEST,
  CREATE_PRODUCT_SUCCESS,
  CREATE_PRODUCT_FAILURE,
  DELETE_PRODUCT_SUCCESS,
  DELETE_PRODUCT_FAILURE,
} from "./ActionType";

export const findProducts = (reqData) => async (dispatch) => {
  console.log("Request Data: ", reqData); // Log the request data

  dispatch({ type: DELETE_PRODUCT_REQUEST });

  const {
    colors,
    sizes,
    minPrice,
    maxPrice,
    minDiscount,
    category,
    stock,
    sort,
    pageNumber,
    pageSize,
  } = reqData;

  const params = new URLSearchParams();
  if (colors && colors.length > 0) params.append("color", colors);
  if (sizes && sizes.length > 0) params.append("size", sizes);
  if (minPrice !== undefined) params.append("minPrice", minPrice);
  if (maxPrice !== undefined) params.append("maxPrice", maxPrice);
  if (minDiscount !== undefined) params.append("minDiscount", minDiscount);
  if (category) params.append("category", category);
  if (stock !== undefined) params.append("stock", stock);
  if (sort) params.append("sort", sort);
  if (pageNumber !== undefined) params.append("pageNumber", pageNumber);
  if (pageSize !== undefined) params.append("pageSize", pageSize);

  const queryString = params.toString();
  console.log("Query String: ", queryString);

  try {
    const { data } = await api.get(`/products?${queryString}`);
    console.log("Response Data: ", data);
    dispatch({ type: FIND_PRODUCT_PRODUCTS_SUCCESS, payload: data });
  } catch (error) {
    console.error("Error fetching products:", error);
    dispatch({ type: FIND_PRODUCT_PRODUCTS_FAILURE, payload: error.message });
  }
};

export const findProductsById = (reqData) => async (dispatch) => {
  dispatch({ type: FIND_PRODUCT_BY_ID_REQUEST });
  const { productId } = reqData;
  try {
    const { data } = await api.get(`/products/${productId}`);
    console.log("data", data);
    dispatch({ type: FIND_PRODUCT_BY_ID_SUCCESS, payload: data });
  } catch (error) {
    console.error("Error fetching product by ID:", error);
    dispatch({ type: FIND_PRODUCT_BY_ID_FAILURE, payload: error.message });
  }
};

export const createProduct = (product) => async (dispatch) => {
  try {
    dispatch({ type: CREATE_PRODUCT_REQUEST });
    const { data } = await api.post("/admin/products/", product);
    dispatch({ type: CREATE_PRODUCT_SUCCESS, payload: data });
  } catch (error) {
    dispatch({ type: CREATE_PRODUCT_FAILURE, payload: error.message });
  }
};

export const deleteProduct = (productId) => async (dispatch) => {
  try {
    dispatch({ type: DELETE_PRODUCT_REQUEST });
    await api.delete(`/admin/products/${productId}/delete`);
    dispatch({ type: DELETE_PRODUCT_SUCCESS, payload: productId });
  } catch (error) {
    dispatch({ type: DELETE_PRODUCT_FAILURE, payload: error.message });
  }
};
