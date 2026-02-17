import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { getEmployeesByDepartment, assignTicket } from "./services/TicketServices"; // import assignTicket

export default function AssignTicket() {
  const navigate = useNavigate();
  const location = useLocation();
  const { ticketId, departmentId } = location.state || {};

  const [employees, setEmployees] = useState([]);
  const [selectedEmployee, setSelectedEmployee] = useState("");

  // Fetch employees for the department
  useEffect(() => {
    if (!departmentId) return;

    const fetchEmployees = async () => {
      try {
        const data = await getEmployeesByDepartment(departmentId);
        setEmployees(data);
      } catch (err) {
        console.error(err);
      }
    };

    fetchEmployees();
  }, [departmentId]);

  const handleAssign = async () => {
    if (!selectedEmployee) {
      alert("Please select an employee to assign");
      return;
    }

    try {
      // ✅ Use the assignTicket JS function
      await assignTicket(ticketId, selectedEmployee);

      alert("Ticket assigned successfully!");
      navigate("/internal/ticket/listAll/ticket"); // redirect back to ticket list
    } catch (err) {
      console.error("Error assigning ticket:", err);
      alert("Failed to assign ticket");
    }
  };

  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-xl">
      <h2 className="text-2xl font-bold text-gray-800 mb-6 text-center">
        Assign Ticket
      </h2>

      <div className="space-y-4">
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">
            Select Employee
          </label>
          <select
            className="w-full border rounded-md p-2"
            value={selectedEmployee}
            onChange={(e) => setSelectedEmployee(e.target.value)}
          >
            <option value="">-- Select Employee --</option>
            {employees.map((emp) => (
              <option key={emp.id} value={emp.email}>
                {emp.fullName}
              </option>
            ))}
          </select>
        </div>

        <div className="flex gap-4 justify-center mt-6">
          <button
            onClick={handleAssign}
            className="px-4 py-2 bg-indigo-600 hover:bg-indigo-700 text-white rounded-md"
          >
            Assign
          </button>
          <button
            onClick={() => navigate("/internal/tickets")}
            className="px-4 py-2 bg-gray-400 hover:bg-gray-500 text-white rounded-md"
          >
            Back
          </button>
        </div>
      </div>
    </div>
  );
}
