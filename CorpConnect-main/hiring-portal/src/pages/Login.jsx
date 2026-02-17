import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

export default function Login() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = (e) => {
    e.preventDefault();
    // TODO: Use axios to authenticate with backend, check user type & redirect
    // Use static routing for now
    if (form.email === "manager@company.com") {
      navigate("/manager");
    } else {
      navigate("/dashboard");
    }
  };

  return (
    <div className="flex items-center justify-center h-screen bg-gradient-to-r from-blue-100 to-indigo-300">
      <form className="bg-white p-10 rounded-lg shadow-xl w-full max-w-md" onSubmit={handleLogin}>
        <h1 className="text-2xl font-bold mb-6 text-center text-blue-700">Login</h1>
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
        <button type="submit" className="btn w-full bg-blue-600 text-white font-semibold py-2 rounded-lg hover:bg-blue-700 transition">
          Login
        </button>
        {error && <p className="text-red-500 text-center mt-4">{error}</p>}
        <p className="text-sm text-center mt-4">
          Don't have an account?{" "}
          <Link to="/register" className="text-indigo-600 underline">
            Register here
          </Link>
        </p>
      </form>
    </div>
  );
}
