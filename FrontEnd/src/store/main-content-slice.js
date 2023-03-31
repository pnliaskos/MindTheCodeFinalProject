
import { createSlice } from "@reduxjs/toolkit";

const contentSlice =  createSlice({
    name: 'content',
    initialState: { content: 'products' },
    reducers: {
        toggle(state, actions) 
        {
            state.content = actions.payload;
        }
    }
});

export const contentActions = contentSlice.actions;

export default contentSlice;