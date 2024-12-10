import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

// Thực hiện đăng ký
export const registerUser = createAsyncThunk(
    'user/registerUser',
    async (userData) => {
        const response = await axios.post('https://67585e7660576a194d10388b.mockapi.io/cuoiki/users', userData);
        return response.data; // Dữ liệu người dùng trả về
    }
);

// Thực hiện đăng nhập
export const loginUser = createAsyncThunk(
    'user/loginUser',
    async (userData) => {
        const response = await axios.post('https://67585e7660576a194d10388b.mockapi.io/cuoiki/users', userData);
        return response.data; // Dữ liệu người dùng trả về
    }
);

const userSlice = createSlice({
    name: 'user',
    initialState: {
        user: null,
        isLoggedIn: false,
        status: 'idle', // idle | loading | succeeded | failed
        error: null,
    },
    reducers: {
        logout: (state) => {
            state.user = null;
            state.isLoggedIn = false;
        },
    },
    extraReducers: (builder) => {
        builder
            .addCase(registerUser.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(registerUser.fulfilled, (state, action) => {
                state.status = 'succeeded';
                state.user = action.payload;
                state.isLoggedIn = true;
            })
            .addCase(registerUser.rejected, (state, action) => {
                state.status = 'failed';
                state.error = action.error.message;
            })
            .addCase(loginUser.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(loginUser.fulfilled, (state, action) => {
                state.status = 'succeeded';
                state.user = action.payload;
                state.isLoggedIn = true;
            })
            .addCase(loginUser.rejected, (state, action) => {
                state.status = 'failed';
                state.error = action.error.message;
            });
    },
});

export const { logout } = userSlice.actions;
export default userSlice.reducer;
