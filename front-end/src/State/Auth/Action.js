import axios from "axios";
import { API_BASE_URL } from "../../config/ApiConfig";
import {
  GET_USER_FAILURE,
  GET_USER_REQUEST,
  GET_USER_SUCCESS,
  LOGIN_FAILURE,
  LOGIN_REQUEST,
  LOGIN_SUCCESS,
  LOGOUT,
  REGISTER_FAILURE,
  REGISTER_REQUEST,
  REGISTER_SUCCESS,
} from "./ActionType";

const registerRequest = () => ({ type: REGISTER_REQUEST });
const registerSuccess = (user) => ({ type: REGISTER_SUCCESS, payload: user });
const registerFailure = (error) => ({ type: REGISTER_FAILURE, payload: error });

export const register = (userData, navigate) => async (dispatch) => {
  dispatch(registerRequest());
  try {
    const response = await axios.post(API_BASE_URL + "auth/register", userData);
    const user = response.data;
    dispatch(registerSuccess(user));
    navigate("/login");
  } catch (error) {
    dispatch(registerFailure(error.message));
  }
};

const loginRequest = () => ({ type: LOGIN_REQUEST });
const loginSuccess = (user) => ({ type: LOGIN_SUCCESS, payload: user });
const loginFailure = (error) => ({ type: LOGIN_FAILURE, payload: error });

export const login = (userData) => async (dispatch) => {
  dispatch(loginRequest());
  try {
    const response = await axios.post(
      API_BASE_URL + "auth/authenticate",
      userData
    );
    const jwt = response.data.token; // Assuming the response contains a 'token' field
    if (!jwt) {
      throw new Error("JWT token not found in response");
    }
    localStorage.setItem("jwt", jwt);
    const userResponse = await axios.get(API_BASE_URL + "users/profile", {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    const user = userResponse.data;
    console.log(user);
    dispatch(loginSuccess(user));
    return { payload: user };
  } catch (error) {
    dispatch(loginFailure(error.message));
    return { error: error.message };
  }
};

const getUserRequest = () => ({ type: GET_USER_REQUEST });
const getUserSuccess = (user) => ({ type: GET_USER_SUCCESS, payload: user });
const getUserFailure = (error) => ({ type: GET_USER_FAILURE, payload: error });

export const getUser = (jwt) => async (dispatch) => {
  dispatch(getUserRequest());
  try {
    const response = await axios.get(API_BASE_URL + "users/profile", {
      headers: {
        Authorization: `Bearer ${jwt}`,
      },
    });
    const user = response.data;
    dispatch(getUserSuccess(user));
  } catch (error) {
    dispatch(getUserFailure(error.message));
  }
};

export const logout = () => (dispatch) => {
  dispatch({ type: LOGOUT, payload: null });
  localStorage.clear();
};
