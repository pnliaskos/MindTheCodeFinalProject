
import { createSlice } from "@reduxjs/toolkit";

const orderSlice = createSlice({
    name: 'orders',
    initialState: {
        items: []
    },
    reducers: {
        replaceProducts(state, action)
        {
            state.items = action.payload;
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

export const ordersActions = orderSlice.actions;

export default orderSlice;