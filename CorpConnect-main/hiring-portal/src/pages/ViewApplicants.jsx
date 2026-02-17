import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../services/api";

export default function ViewApplicants() {
  const { ticketId } = useParams();
  const [applicants, setApplicants] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [actionLoading, setActionLoading] = useState(false);
  const [actionError, setActionError] = useState("");
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  useEffect(() => {
    setLoading(true);
    api
      .get(`/job-applications/job/${ticketId}`)
      .then((res) => {
        setApplicants(res.data);
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to load applicants");
        setLoading(false);
      });
  }, [ticketId, refreshTrigger]);

  const handleStatusUpdate = async (appId, status) => {
    setActionError("");
    setActionLoading(true);
    try {
      await api.post(`/job-applications/${appId}/status`, null, {
        params: { status: status },
      });
      setRefreshTrigger((prev) => prev + 1); // Refresh list
    } catch {
      setActionError("Failed to update candidate status");
    } finally {
      setActionLoading(false);
    }
  };

  if (loading) {
    return <div className="text-center mt-12 text-gray-600">Loading applicants...</div>;
  }

  if (error) {
    return <div className="text-center mt-12 text-red-600 font-semibold">{error}</div>;
  }

  return (
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-3xl font-bold text-blue-700 mb-6">Applicants for Job ID: {ticketId}</h1>
      
      {actionError && (
        <p className="text-red-600 font-semibold mb-4">{actionError}</p>
      )}

      {applicants.length === 0 ? (
        <p className="text-gray-600">No applicants have applied for this job yet.</p>
      ) : (
        <div className="overflow-auto">
          <table className="min-w-full bg-white border rounded-lg shadow-sm">
            <thead>
              <tr className="bg-blue-100">
                <th className="py-3 px-5 text-left font-semibold text-gray-700">Candidate Name</th>
                <th className="py-3 px-5 text-left font-semibold text-gray-700">Email</th>
                <th className="py-3 px-5 text-left font-semibold text-gray-700">Skills</th>
                <th className="py-3 px-5 text-left font-semibold text-gray-700">Experience (Years)</th>
                <th className="py-3 px-5 text-left font-semibold text-gray-700">Status</th>
                <th className="py-3 px-5 text-left font-semibold text-gray-700">Applied On</th>
                <th className="py-3 px-5 text-center font-semibold text-gray-700">Actions</th>
              </tr>
            </thead>
            <tbody>
              {applicants.map((app) => (
                <tr key={app.id} className="border-b hover:bg-gray-100">
                  <td className="py-3 px-5">{app.candidateName}</td>
                  <td className="py-3 px-5">{app.candidateEmail}</td>
                  <td className="py-3 px-5">{app.skills}</td>
                  <td className="py-3 px-5">{app.experienceYears}</td>
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
                  <td className="py-3 px-5 text-center space-x-2">
                    <a
                      href={app.resumeFileId ? app.resumeFileId : "#"}
                      target="_blank"
                      rel="noopener noreferrer"
                      className="inline-block px-3 py-1 bg-gray-300 rounded hover:bg-gray-400"
                    >
                      View Resume
                    </a>

                    <button
                      disabled={actionLoading || app.status === "SHORTLISTED"}
                      onClick={() => handleStatusUpdate(app.id, "SHORTLISTED")}
                      className={`px-3 py-1 rounded ${
                        app.status === "SHORTLISTED"
                          ? "bg-green-400 cursor-not-allowed"
                          : "bg-green-600 hover:bg-green-700 text-white"
                      }`}
                    >
                      Shortlist
                    </button>

                    <button
                      disabled={actionLoading || app.status === "REJECTED"}
                      onClick={() => handleStatusUpdate(app.id, "REJECTED")}
                      className={`px-3 py-1 rounded ${
                        app.status === "REJECTED"
                          ? "bg-red-400 cursor-not-allowed"
                          : "bg-red-600 hover:bg-red-700 text-white"
                      }`}
                    >
                      Reject
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
