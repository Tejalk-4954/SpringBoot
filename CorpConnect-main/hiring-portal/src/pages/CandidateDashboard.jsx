import { Link, useNavigate } from "react-router-dom";

export default function CandidateDashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    // TODO: Clear auth tokens/session and redirect to login
    navigate("/");
  };

  return (
    <div className="min-h-screen bg-gradient-to-r from-indigo-100 to-blue-100 p-8">
      <div className="max-w-4xl mx-auto bg-white p-10 rounded-lg shadow-lg">
        <h1 className="text-3xl font-bold mb-6 text-center text-indigo-700">
          Candidate Dashboard
        </h1>
        <p className="mb-8 text-center text-gray-700">
          Welcome! Here you can browse jobs, apply, and track your applications.
        </p>
        <div className="flex flex-col space-y-4 max-w-md mx-auto">
          <Link
            to="/jobs"
            className="text-center bg-indigo-600 text-white py-3 rounded-md font-semibold hover:bg-indigo-700 transition"
          >
            View All Job Posts
          </Link>
          <Link
            to="/applied-jobs"
            className="text-center bg-blue-600 text-white py-3 rounded-md font-semibold hover:bg-blue-700 transition"
          >
            View My Applied Jobs
          </Link>
          <button
            onClick={handleLogout}
            className="text-center bg-red-600 text-white py-3 rounded-md font-semibold hover:bg-red-700 transition"
          >
            Logout
          </button>
        </div>
      </div>
    </div>
  );
}
