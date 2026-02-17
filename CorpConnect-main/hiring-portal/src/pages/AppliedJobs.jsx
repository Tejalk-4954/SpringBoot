import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../services/api";

export default function AppliedJobs() {
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    api
      .get("/job-applications/my")
      .then((res) => {
        setApplications(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError("Failed to load applied jobs");
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div className="text-center mt-12 text-gray-600">Loading your applications...</div>
    );
  }

  if (error) {
    return (
      <div className="text-center mt-12 text-red-600 font-semibold">{error}</div>
    );
  }

  return (
    <div className="max-w-5xl mx-auto p-6">
      <h1 className="text-3xl font-bold text-blue-700 mb-6">My Applied Jobs</h1>

      {applications.length === 0 ? (
        <p className="text-gray-600">You have not applied for any jobs yet.</p>
      ) : (
        <table className="min-w-full bg-white border rounded-lg shadow-sm">
          <thead>
            <tr className="bg-blue-100">
              <th className="py-3 px-5 text-left text-gray-700 font-semibold">Job Title</th>
              <th className="py-3 px-5 text-left text-gray-700 font-semibold">Ticket ID</th>
              <th className="py-3 px-5 text-left text-gray-700 font-semibold">Status</th>
              <th className="py-3 px-5 text-left text-gray-700 font-semibold">Date Applied</th>
              <th className="py-3 px-5 text-left text-gray-700 font-semibold">Actions</th>
            </tr>
          </thead>
          <tbody>
            {applications.map((app) => (
              <tr key={app.id} className="hover:bg-gray-100 border-b">
                <td className="py-3 px-5">{app.jobTitle || "(Job title unavailable)"}</td>
                <td className="py-3 px-5">{app.ticketId || "-"}</td>
                <td
                  className={`py-3 px-5 font-semibold ${
                    app.status === "SHORTLISTED"
                      ? "text-green-700"
                      : app.status === "REJECTED"
                      ? "text-red-600"
                      : "text-blue-700"
                  }`}
                >
                  {app.status}
                </td>
                <td className="py-3 px-5">{new Date(app.appliedAt).toLocaleDateString()}</td>
                <td className="py-3 px-5">
                  <Link
                    to={`/apply/${app.jobId}`}
                    className="text-indigo-600 hover:underline"
                  >
                    View Details
                  </Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
