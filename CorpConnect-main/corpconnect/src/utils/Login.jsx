import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginRequest } from '../Axios/RegisterAxios';
import { loginSuccess } from '../state-management/authSlice';

const Login = () => {
  const [dto, setDto] = useState({ email: '', password: '' });
  const navigate = useNavigate();
  const dispatch = useDispatch();

  // Check if user already logged in
  // useEffect(() => {
  //   const hasLoggedIn = localStorage.getItem('hasLoggedIn');
  //   if (hasLoggedIn === 'true') {
  //     navigate('/dashboard'); // or any page you want
  //   }
  // }, [navigate]);

  const handleChange = (e) => {
    setDto({ ...dto, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try{
      const response = await loginRequest(dto);
      localStorage.setItem('accessToken',response.accessToken);
      localStorage.setItem('hasLoggedIn', 'true');
      dispatch(loginSuccess(response.accessToken));
      

      const decoded = jwtDecode(response.accessToken);
      const roles = decoded.roles || [];
      const role = roles.length > 0 ? roles[0]: '';
      // console.log(role);
      console.log(response);
      alert("Login success");

      const redirectMap = {
        ROLE_ADMIN: '/admin/home',
        ROLE_USER: '/user/home',
        // SUPER_ADMIN: '/super-admin/home',
        // RESOURCE: '/resource/home'
      };

      console.log(role);
      const path = redirectMap[role] || "/" ;
      navigate(path, { replace: true });
    }
    catch(err){
      console.error('Login failed:', err);
    }
   
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">
        <h2 className="text-2xl font-bold text-center mb-4 text-blue-700">Login</h2>
        <form onSubmit={handleLogin} className="space-y-4">
          <input
            name="email"
            type="email"
            placeholder="Email"
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          <input
            name="password"
            type="password"
            placeholder="Password"
            onChange={handleChange}
            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition duration-300"
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
};

export default Login;