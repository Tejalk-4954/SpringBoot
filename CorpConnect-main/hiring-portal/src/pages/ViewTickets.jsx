import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../services/api";

export default function ViewTickets() {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    api
      .get("/job-posts/my")
      .then((res) => {
        setTickets(res.data);
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to load tickets");
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div className="text-center mt-12 text-gray-600">Loading tickets...</div>;
  }

  if (error) {
    return (
      <div className="text-center mt-12 text-red-600 font-semibold">
        {error}
      </div>
    );
  }

  return (
    <div className="max-w-6xl mx-auto p-6">
      <h1 className="text-3xl font-bold text-blue-700 mb-6">My Job Tickets</h1>

      {tickets.length === 0 ? (
        <p className="text-gray-600">No tickets (job posts) found.</p>
      ) : (
        <table className="min-w-full bg-white border rounded-lg shadow-sm">
          <thead>
            <tr className="bg-blue-100">
              <th className="py-3 px-5 text-left font-semibold text-gray-700">Ticket ID</th>
              <th className="py-3 px-5 text-left font-semibold text-gray-700">Job Title</th>
              <th className="py-3 px-5 text-left font-semibold text-gray-700">Status</th>
              <th className="py-3 px-5 text-left font-semibold text-gray-700">Actions</th>
            </tr>
          </thead>
          <tbody>
            {tickets.map((ticket) => (
              <tr key={ticket.id} className="border-b hover:bg-gray-100">
                <td className="py-3 px-5">{ticket.ticketId || "-"}</td>
                <td className="py-3 px-5">{ticket.title}</td>
                <td
                  className={`py-3 px-5 font-semibold ${
                    ticket.status === "OPEN"
                      ? "text-green-700"
                      : "text-red-600"
                  }`}
                >
                  {ticket.status}
                </td>
                <td className="py-3 px-5">
                  <Link
                    to={`/view-applicants/${ticket.id}`}
                    className="text-indigo-600 hover:underline font-semibold"
                  >
                    View Applicants
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
