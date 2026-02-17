import React, { useEffect, useState } from "react";
import { jwtDecode } from "jwt-decode";
import { useNavigate } from "react-router-dom";
import { closeTicket, getTicketsByDepartment } from "./services/TicketServices";
import { getDepartmentByUserId } from "../User/services/UserProfile";

export default function AllTicketList() {
  const navigate = useNavigate();

  const [userId, setUserId] = useState(null);
  const [departmentId, setDepartmentId] = useState(null);
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);

  /* ------------------------------------
      1️⃣ EXTRACT USER ID FROM TOKEN
  ------------------------------------ */
  useEffect(() => {
    try {
      const token = localStorage.getItem("accessToken");
      if (!token) return;

      const decoded = jwtDecode(token);
      const id = decoded.id || decoded.sub || decoded.userId;

      setUserId(id);
    } catch (err) {
      console.error("Token decoding failed:", err);
    }
  }, []);

  /* ------------------------------------
      2️⃣ GET DEPARTMENT USING userId
  ------------------------------------ */
  useEffect(() => {
    if (!userId) return;

    const fetchDept = async () => {
      try {
        const dept = await getDepartmentByUserId(userId);
        setDepartmentId(dept);
      } catch (err) {
        console.error("Failed to fetch department:", err);
      }
    };

    fetchDept();
  }, [userId]);

  /* ------------------------------------
      3️⃣ FETCH TICKETS FOR THAT DEPARTMENT
  ------------------------------------ */
  useEffect(() => {
    if (!departmentId) return;

    const fetchTickets = async () => {
      try {
        const data = await getTicketsByDepartment(departmentId);
        setTickets(data);
      } catch (err) {
        console.error("Error loading department tickets:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchTickets();
  }, [departmentId]);

  /* ------------------------------------
      4️⃣ LOADING SCREEN
  ------------------------------------ */
  if (loading) {
    return (
      <div className="flex justify-center mt-20 text-lg font-semibold">
        Loading tickets...
      </div>
    );
  }

  const handleCloseClick = async (ticketId) => {
    // console.log("Close button clicked for id",ticketId);
    try {
      console.log(ticketId);
      await closeTicket(ticketId);
      alert("ticket closed");
      // remove from list immediately
      setTickets((prev) => prev.filter((t) => (t.serialNo || t.id) !== ticketId));
 
    } catch (err) {
      console.error("Failed to close the ticket:", err);
    }
  };

  return (
    <div className="max-w-6xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-xl">
      <h2 className="text-2xl font-bold text-gray-800 mb-6">
        Department Tickets —{" "}
        <span className="text-indigo-600">{departmentId}</span>
      </h2>

      <div className="overflow-x-auto">
        <table className="min-w-full border border-gray-200">
          <thead>
            <tr className="bg-gray-100 text-left">
              <th className="p-3 border-b">Ticket #</th>
              <th className="p-3 border-b">Title</th>
              <th className="p-3 border-b">Priority</th>
              <th className="p-3 border-b">Status</th>
              <th className="p-3 border-b">Raised By</th>
              <th className="p-3 border-b">Created At</th>
              <th className="p-3 border-b text-center">Action</th>
            </tr>
          </thead>

          <tbody>
            {tickets.map((ticket) => (
              
              <tr key={ticket.id} className="hover:bg-gray-50 transition">
                <td className="p-3 border-b">{ticket.ticketNumber}</td>

                <td className="p-3 border-b font-medium text-gray-800">
                  {ticket.title}
                </td>

                <td className="p-3 border-b">
                  <span
                    className={`px-2 py-1 rounded text-white ${
                      ticket.priority === "urgent"
                        ? "bg-red-500"
                        : "bg-green-500"
                    }`}
                  >
                    {ticket.priority.toUpperCase()}
                  </span>
                </td>

                <td className="p-3 border-b">{ticket.status}</td>
                <td className="p-3 border-b">{ticket.raisedBy}</td>

                <td className="p-3 border-b">
                  {new Date(ticket.createdAt).toLocaleString()}
                </td>

                {/* ASSIGN BUTTON */}
                <td className="p-3 border-b text-center">
                  <button
                    onClick={() =>
                      navigate("/internal/ticket/assign", {
                        state: { ticketId: ticket.id, departmentId: ticket.departmentId  },
                      })
                    }
                    className="px-4 py-1 bg-indigo-600 hover:bg-indigo-700 text-white rounded-md shadow"
                  >
                    Assign
                  </button>
                  <button
                        onClick={() => handleCloseClick(ticket.id)}
                        className="text-white bg-green-600 hover:bg-green-700 focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 font-medium rounded-lg text-sm px-4 py-2"
                      >
                        ✔ Close
                      </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {tickets.length === 0 && (
          <p className="text-center mt-6 text-gray-500">
            No tickets found for this department.
          </p>
        )}
      </div>
    </div>
  );
}
