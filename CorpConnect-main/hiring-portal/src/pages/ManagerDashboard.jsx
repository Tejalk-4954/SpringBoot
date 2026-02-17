import { Link } from "react-router-dom";

export default function ManagerDashboard() {
  return (
    <div className="max-w-4xl mx-auto p-8 bg-white rounded-lg shadow-lg mt-12">
      <h1 className="text-4xl font-bold text-blue-700 mb-8">Manager Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <Link
          to="/create-jobpost"
          className="flex flex-col items-center justify-center p-6 bg-indigo-600 text-white rounded-lg shadow-md hover:bg-indigo-700 transition cursor-pointer"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="w-12 h-12 mb-4"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4v16m8-8H4" />
          </svg>
          <span className="text-xl font-semibold">Create Job Post</span>
        </Link>

        <Link
          to="/view-tickets"
          className="flex flex-col items-center justify-center p-6 bg-green-600 text-white rounded-lg shadow-md hover:bg-green-700 transition cursor-pointer"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="w-12 h-12 mb-4"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 17v-1a4 4 0 014-4h1" />
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <span className="text-xl font-semibold">View Tickets</span>
        </Link>

        <Link
          to="/view-applicants"
          className="flex flex-col items-center justify-center p-6 bg-yellow-600 text-white rounded-lg shadow-md hover:bg-yellow-700 transition cursor-pointer"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="w-12 h-12 mb-4"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5.121 17.804A12 12 0 0112 3a12 12 0 016.879 14.804M15 10a3 3 0 11-6 0 3 3 0 016 0z" />
          </svg>
          <span className="text-xl font-semibold">View Applicants</span>
        </Link>

        <Link
          to="/schedule-interview"
          className="flex flex-col items-center justify-center p-6 bg-purple-600 text-white rounded-lg shadow-md hover:bg-purple-700 transition cursor-pointer"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            className="w-12 h-12 mb-4"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7H3v12a2 2 0 002 2z" />
          </svg>
          <span className="text-xl font-semibold">Schedule Interview</span>
        </Link>
      </div>
    </div>
  );
}
