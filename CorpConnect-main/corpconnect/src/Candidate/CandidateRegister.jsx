import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../index.css";
import { CandidateReg } from "./services/CandidateServices";
import { RegenerateEmailOtpRequest, RegenerateMobileOtpRequest } from "./services/CandidateServices";

function CandidateRegister() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: "",
    emailOtp: "",
    phoneOtp: ""
  });

  const [emailOtpStatus, setEmailOtpStatus] = useState("");
  const [phoneOtpStatus, setPhoneOtpStatus] = useState("");
  const [error, setError] = useState("");

  // Handle input change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Send Email OTP
  const handleSendEmailOtp = async () => {
    if (!formData.email) {
      setEmailOtpStatus("❌ Enter email first");
      return;
    }

    try {
      await RegenerateEmailOtpRequest(formData.email);
      setEmailOtpStatus("✅ OTP sent to email!");
    } catch (err) {
      setEmailOtpStatus("❌ Failed to send email OTP");
    }
  };

  // Send Phone OTP
  const handleSendPhoneOtp = async () => {
    if (!formData.phone) {
      setPhoneOtpStatus("❌ Enter phone first");
      return;
    }

    try {
      await RegenerateMobileOtpRequest(formData.phone);
      setPhoneOtpStatus("✅ OTP sent to phone!");
    } catch (err) {
      setPhoneOtpStatus("❌ Failed to send phone OTP");
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match!");
      return;
    }

    const dto = {
      fullName: formData.fullName,
      email: formData.email,
      phone: formData.phone,
      password: formData.password,
      emailOtp: formData.emailOtp,
      phoneOtp: formData.phoneOtp
    };

    try {
      await CandidateReg(dto);
      alert("Registration success");
      navigate("/login")
      // navigate("/userotp", {
      //   state: { email: formData.email }
      // });
    } catch (err) {
      console.error("Registration failed", err);
      setError("Registration failed. Check OTPs or try again.");
    }
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <form className="bg-white p-6 rounded shadow-md w-96" onSubmit={handleRegister}>
        
        {/* Name */}
        <label>Name</label>
        <input
          type="text"
          name="fullName"
          placeholder="Enter name"
          value={formData.fullName}
          onChange={handleChange}
          className="w-full p-2 mb-3 border rounded"
          required
        />

        {/* Email + Send OTP */}
        <label>Email</label>
        <div className="flex gap-2 mb-3">
          <input
            type="email"
            name="email"
            placeholder="Enter email"
            value={formData.email}
            onChange={handleChange}
            className="flex-1 p-2 border rounded"
            required
          />
          <button
            type="button"
            className="bg-blue-500 text-white px-3 rounded"
            onClick={handleSendEmailOtp}
          >
            Send OTP
          </button>
        </div>
        {emailOtpStatus && <p className="text-sm text-blue-600">{emailOtpStatus}</p>}

        {/* Email OTP Field */}
        <input
          type="text"
          name="emailOtp"
          placeholder="Enter Email OTP"
          value={formData.emailOtp}
          onChange={handleChange}
          className="w-full p-2 mb-3 border rounded"
          required
        />

        {/* Phone + Send OTP */}
        <label>Phone</label>
        <div className="flex gap-2 mb-3">
          <input
            type="text"
            name="phone"
            placeholder="Enter phone"
            value={formData.phone}
            onChange={handleChange}
            className="flex-1 p-2 border rounded"
            required
          />
          <button
            type="button"
            className="bg-green-500 text-white px-3 rounded"
            onClick={handleSendPhoneOtp}
          >
            Send OTP
          </button>
        </div>
        {phoneOtpStatus && <p className="text-sm text-green-600">{phoneOtpStatus}</p>}

        {/* Phone OTP Field */}
        <input
          type="text"
          name="phoneOtp"
          placeholder="Enter Phone OTP"
          value={formData.phoneOtp}
          onChange={handleChange}
          className="w-full p-2 mb-3 border rounded"
          required
        />

        {/* Password */}
        <label>Password</label>
        <input
          type="password"
          name="password"
          placeholder="Enter password"
          value={formData.password}
          onChange={handleChange}
          className="w-full p-2 mb-3 border rounded"
          required
        />

        {/* Confirm Password */}
        <label>Confirm Password</label>
        <input
          type="password"
          name="confirmPassword"
          placeholder="Confirm password"
          value={formData.confirmPassword}
          onChange={handleChange}
          className="w-full p-2 mb-3 border rounded"
          required
        />

        {/* Register Button */}
        <button
          type="submit"
          className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
        >
          Register
        </button>

      </form>

      {error && <p className="text-red-600 mt-4">{error}</p>}
    </div>
  );
}

export default CandidateRegister;
