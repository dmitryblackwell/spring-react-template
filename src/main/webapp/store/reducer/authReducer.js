import * as constants from '../constants';
import axios from "../../utils/axios";

const authStart = (state, action) => {
    const {dispatch} = action;
    const data = {
        username: action.username,
        password: action.password,
    };
    axios.post("/auth/login", data)
        .then(response => {
            dispatch({type: constants.AUTH_SUCCESS, response});
        })
        .catch(() => {
            dispatch({type: constants.AUTH_FAILED});
        });
    return state;
};

const authSuccess = (state, action) => {
    const { response } = action;
    let responseData = {
        id: response.data.user.id,
        username: response.data.user.username,
        token: response.data.accessToken
    };
    localStorage.setItem("user", JSON.stringify(responseData));

    responseData = {
        ...responseData,
        isError: false,
        isLoading: false,
    };
    return updateState(state, responseData);
};

const authFailed = (state, action) => {
    return updateState(state, {isAuthFailed: true});
};

const authInit = (state, action) => {
    const savedUser = localStorage.getItem("user");
    return updateState(state, savedUser);
};
const logout = (state, action) => {
    console.log("logout");
    localStorage.removeItem("user");
    return updateState(state, {
        id: null,
        username: null,
        token: null
    });
};

const updateState = (state, data) => {
  return {...state, ...data};
};

const authReducer = (state, action) => {
    console.log({authReducerAction: action});
    switch (action.type) {
        case constants.AUTH_START: return authStart(state, action);
        case constants.AUTH_SUCCESS: return authSuccess(state, action);
        case constants.AUTH_FAILED: return authFailed(state,action);
        case constants.AUTH_INIT: return authInit(state, action);
        case constants.LOGOUT: return logout(state, action);
        default: return state;
    }
};

export default authReducer;