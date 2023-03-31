
import { configureStore } from "@reduxjs/toolkit";

import uiSlice from "./ui-slice";
import cartSlice from "./cart-slice";
import productsSlice from "./products-slice";
import loginSlice from "./login-slice";
import contentSlice from "./main-content-slice";
import orderSlice from "./orders.slice";

const store = configureStore({
    reducer: { 
        ui: uiSlice.reducer, 
        cart: cartSlice.reducer,
        products: productsSlice.reducer,
        orders: orderSlice.reducer,
        login: loginSlice.reducer,
        content: contentSlice.reducer
    }
});

export default store;