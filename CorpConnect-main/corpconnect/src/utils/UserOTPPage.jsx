import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import axios from 'axios';
import CandidateAxios from '../Axios/CandidateAuth';
import { CandidateReg } from '../Candidate/services/CandidateServices';

export const UserOTPPage = () => {

     const navigate = useNavigate();
  const location = useLocation();
  const email = location.state?.email;
  const [formData, setFormData] = useState({
    email:'',
    otp:'',
  });
  const [error, setError] = useState('');

  useEffect(() => {
    if (email) {
      setFormData(prev => ({
        ...prev,
        email: email,
      }));
    }
  }, [email]);
 

   // Passed from register page
   const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleRegenerateOtp = async (e) => {
    e.preventDefault();
    try {
      console.log(formData);
      await  CandidateReg("/send-otp",formData.email,formData.otp);
      // alert('OTP verified successfully');
      navigate('/login');
    } catch (err) {
      const message = err.response?.data || 'Something went wrong. Please try again.';
      setError(message);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      console.log(formData);
      const response=await verifyOtpRequest(formData);
      console.log(response);
      alert('OTP verified successfully');
      navigate('/login');
    } catch (err) {
      const message = err.response?.data || 'Something went wrong. Please try again.';
      setError(message);
    }
  };
    


    return (
  <div className="min-h-screen flex items-center justify-center bg-gray-100 px-4">
    <div className="w-full max-w-md bg-white rounded-2xl shadow-lg p-8">
      
      <h2 className="text-2xl font-semibold text-center mb-6 text-gray-800">
        Enter OTP sent to your email
      </h2>

      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="otp"
          placeholder="Enter OTP"
          value={formData.otp}
          onChange={handleChange}
          className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500"
        />

        {error && (
          <p className="text-red-600 text-sm mt-1">{error}</p>
        )}

        <button
          type="submit"
          className="w-full bg-blue-600 text-white py-3 rounded-xl hover:bg-blue-700 transition"
        >
          Verify OTP
        </button>
      </form>

      <div className="text-center mt-6">
        <p className="text-gray-600">
          Didn't receive the OTP?
        </p>

        <button
          type="button"
          onClick={handleRegenerateOtp}
          className="text-blue-600 font-medium hover:underline mt-1"
        >
          Resend OTP
        </button>
      </div>
    </div>
  </div>
);


}
