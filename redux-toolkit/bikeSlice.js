import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

// Fetch all bikes
export const fetchAll = createAsyncThunk(
    'bike/fetchAll',
    async () => {
        const res = await axios.get("https://67582b7760576a194d0f281c.mockapi.io/cuoiki")
        return res.data;
    }
);

// Add bike
export const addBikes = createAsyncThunk(
    'bike/addBikes',
    async (bikedata) => {
        const res = await axios.post("https://67582b7760576a194d0f281c.mockapi.io/cuoiki", bikedata);
        return res.data;
    }
);

// Update bike
export const updateBikes = createAsyncThunk(
    'bike/updateBikes',
    async ({ id, bikedata }) => {
        const res = await axios.put(`https://67582b7760576a194d0f281c.mockapi.io/cuoiki/${id}`, bikedata);
        return res.data;
    }
);

// Delete bike
export const deleteBikes = createAsyncThunk(
    'bike/deleteBikes',
    async (id) => {
        await axios.delete(`https://67582b7760576a194d0f281c.mockapi.io/cuoiki/${id}`);
        return id;
    }
);

const bikeSlice = createSlice({
    name: 'bikes',
    initialState: {
        data: []
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchAll.fulfilled, (state, action) => {
                state.data = action.payload;
            })
            .addCase(addBikes.fulfilled, (state, action) => {
                state.data.push(action.payload);
            })
            .addCase(updateBikes.fulfilled, (state, action) => {
                const index = state.data.findIndex((bike) => bike.id === action.payload.id);
                if (index !== -1) {
                    state.data[index] = action.payload;
                }
            })
            .addCase(deleteBikes.fulfilled, (state, action) => {
                state.data = state.data.filter((bike) => bike.id !== action.payload);
            });
    }
});

export default bikeSlice.reducer;
