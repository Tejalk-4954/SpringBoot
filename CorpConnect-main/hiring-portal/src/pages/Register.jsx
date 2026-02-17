import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Register() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    mobile: "",
    emailVerificationCode: "",
    mobileVerificationCode: "",
  });
  const [success, setSuccess] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleRegister = (e) => {
    e.preventDefault();
    // TODO: Connect with backend API for actual registration
    setSuccess(true);
    setTimeout(() => navigate("/"), 1500);
  };

  return (
    <div className="flex items-center justify-center h-screen bg-gradient-to-r from-blue-100 to-indigo-200">
      <form
        className="bg-white p-10 rounded-lg shadow-xl w-full max-w-md"
        onSubmit={handleRegister}
      >
        <h1 className="text-2xl font-bold mb-6 text-center text-blue-700">
          Candidate Registration
        </h1>
        <input
          type="text"
          name="name"
          placeholder="Full Name"
          className="input input-bordered w-full mb-4"
          value={form.name}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          className="input input-bordered w-full mb-4"
          value={form.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          className="input input-bordered w-full mb-4"
          value={form.password}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="mobile"
          placeholder="Mobile Number"
          className="input input-bordered w-full mb-4"
          value={form.mobile}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="emailVerificationCode"
          placeholder="Email Verification Code"
          className="input input-bordered w-full mb-4"
          value={form.emailVerificationCode}
          onChange={handleChange}
        />
        <input
          type="text"
          name="mobileVerificationCode"
          placeholder="Mobile Verification Code"
          className="input input-bordered w-full mb-4"
          value={form.mobileVerificationCode}
          onChange={handleChange}
        />
        <button
          type="submit"
          className="btn w-full bg-blue-600 text-white font-semibold py-2 rounded-lg hover:bg-blue-700 transition"
        >
          Register
        </button>
        {success && (
          <p className="text-green-500 text-center mt-4">
            Registration Successful! Redirecting to login...
          </p>
        )}
        <p className="text-sm text-center mt-4">
          Already registered?{" "}
          <Link to="/" className="text-indigo-600 underline">
            Login here
          </Link>
        </p>
      </form>
    </div>
  );
}
