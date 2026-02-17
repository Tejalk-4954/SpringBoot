import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="fixed w-full bg-white border-b shadow-lg z-10">
      <div className="container mx-auto flex justify-between items-center px-4 py-3">
        <Link to="/" className="font-extrabold text-xl text-blue-600">Hiring Portal</Link>
        <div className="space-x-4">
          <Link to="/" className="text-gray-700 hover:text-blue-600">Login</Link>
          <Link to="/register" className="text-gray-700 hover:text-blue-600">Register</Link>
        </div>
      </div>
    </nav>
  );
}
