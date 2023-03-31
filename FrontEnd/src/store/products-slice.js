
import { createSlice } from "@reduxjs/toolkit";

const productsSlice = createSlice({
    name: 'products',
    initialState: {
        items: [],
        pages: 0
    },
    reducers: {
        replaceProducts(state, action)
        {
            state.items = action.payload.content;
            state.pages = action.payload.pages;
        },
        addProduct(state, action) 
        {
            state.items.push(action.payload);
        },
        removeItemFromCart(state, action) 
        {
            state.items = state.items.filter(item => item.id !== action.payload);
        }
    }
});

export const productsActions = productsSlice.actions;

export default productsSlice;