

import { createSlice } from "@reduxjs/toolkit";

const loginSlice = createSlice({
    name: 'login',
    initialState: {
        username: '',
        password: '',
        status: false
    },
    reducers: {
        usernameInput(state, actions)
        {
            state.username = actions.payload;
        },
        passwordInput(state, actions)
        {
            state.password = actions.payload;
        },
        login(state) 
        {
            if( ((state.username === 'user1' || state.username === 'user2' || state.username === 'admin') && state.password === '123') )
            {
                state.status = true;
            } 
        },
        logout(state) 
        {
            state.status = false;
        },
    }
});

export const loginActions = loginSlice.actions;

export default loginSlice;