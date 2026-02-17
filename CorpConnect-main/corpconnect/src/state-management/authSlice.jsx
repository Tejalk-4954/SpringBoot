import { createSlice } from "@reduxjs/toolkit";
import { jwtDecode } from "jwt-decode";

const tokenFromStorage = localStorage.getItem('accessToken');
let initialUser=null;

if(tokenFromStorage){
  try{
    const decoded = jwtDecode(tokenFromStorage);
    initialUser = {
      token : tokenFromStorage,
      role : decoded.roles?.[0] || null,
    }
  }
  catch(err){
    console.error("Failed to decode token",err);
  }
}

const authSlice = createSlice({
    name : 'auth',
    initialState : {
        user : initialUser,
    },
    reducers : {
        loginSuccess : (state,action) => {
            const token = action.payload;

            if(typeof token === 'string' && token.trim()!==''){
                localStorage.setItem('accessToken',token);

                try{
                    const decoded = jwtDecode(token);

                    state.user = {
                        token : token,
                        role : decoded.roles?.[0] || null,
                    };
                }
                catch(err){
                    console.error("JWT decode failed",err);
                    state.user=null;
                }
            }
            else{
                console.error("Invalid token passed or loginSuccess");
            }
        },
        logout : (state) => {
            localStorage.removeItem('accessToken');
            state.user = null;
        },
    },
});



export const { loginSuccess, logout } = authSlice.actions;
export default authSlice.reducer;