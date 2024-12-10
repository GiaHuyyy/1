import { configureStore } from "@reduxjs/toolkit";
import bikeReducer from './bikeSlice.js';
import userReducer from './userSlice.js';
const store = configureStore({
    reducer: {
        bikes: bikeReducer,
        user: userReducer,
    }
})
export default store