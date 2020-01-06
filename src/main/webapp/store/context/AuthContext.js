import React, {createContext, useReducer} from "react";
import authReducer from "../reducer/authReducer";

export const AuthContext = createContext();

const AuthContextProvider = props => {

    const initData = {
        id: null,
        username: null,
        token: null,
        isError: false,
        isLoading: false,
    };

    const [authData, dispatch] = useReducer(authReducer,initData, () => {
        const savedUser = localStorage.getItem("user");
        return savedUser ? JSON.parse(savedUser) : initData;
    });

    const value = {
        ...authData,
        dispatch,
        isAuth: authData.token !== null,
    };

    return (
        <AuthContext.Provider value={value}>
            {props.children}
        </AuthContext.Provider>
    )

};

export default AuthContextProvider;