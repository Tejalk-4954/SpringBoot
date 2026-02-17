import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // ✅ Import navigation
import "../index.css";
import { RegisterRequest } from "../Axios/RegisterAxios";

function Register() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const [showPopup, setShowPopup] = useState(false);
  const [error, setError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

 

  const handleRegister = async e => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setError("Passwords do not match!");
      return;
    }
    const dto = {
      fullName : formData.fullName,
      email : formData.email,
      password : formData.password,
    };
    try{
      await RegisterRequest(dto);
      alert("Registration success");
      navigate("/login")
    //    navigate("/userotp", {
    //   state: { email: formData.email }
    // });
      
    }
    catch(err){
      console.error("Registration failed",err);
    }
  }

  return (
    <div className="flex justify-center items-center h-screen bg-gray-100">
      <form className="bg-white p-6 rounded shadow-md w-96" onSubmit={handleRegister}>
        <div className="input-group">
          <label>Name</label>
          <input
            type="text"
            name="fullName"
            placeholder="Enter your name"
            value={formData.fullName}
            onChange={handleChange}
            className="w-full p-2 mb-3 border rounded"
            required
          />
        </div>
        <div className="input-group">
          <label>Email</label>
          <input
            type="email"
            name="email"
            placeholder="Enter your email"
            value={formData.email}
            onChange={handleChange}
            className="w-full p-2 mb-3 border rounded"
            required
          />
        </div>
        <div className="input-group">
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
        </div>
        <div className="input-group">
          <label>Confirm Password</label>
          <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm password"
            value={formData.confirmPassword}
            className="w-full p-2 mb-3 border rounded"
            onChange={handleChange}
            required
          />
        </div>
     

        {/* ✅ Register Button */}
        <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600" >Register</button>

        {/* ✅ Login Button */}
        <a
          href={"/login"}
        >
          Already Registered? Login
        </a>
      </form>

      {/* ✅ Error Message */}
      {error && <p style={{ color: "red", marginTop: "10px" }}>{error}</p>}

      {/* ✅ Success Popup */}
      {/* {showPopup && (
        <div className="popup-overlay">
          <div className="popup">
            <h2>✅ Registration Successful!</h2>
            <p>Your account has been created successfully.</p>
            <button
              onClick={() => {
                setShowPopup(false);
                setFormData({
                  name: "",
                  email: "",
                  password: "",
                  confirmPassword: "",
                  contact: "",
                  role: "",
                });
              }}
            >
              Close
            </button>
          </div>
        </div>
      )} */}
    </div>
  );
}

export default Register;