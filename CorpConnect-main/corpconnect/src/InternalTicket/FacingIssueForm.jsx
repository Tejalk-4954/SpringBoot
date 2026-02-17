import React, { useState, useEffect } from 'react';
import { Listbox, Transition } from '@headlessui/react';
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid';
import { jwtDecode } from "jwt-decode";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const priorities = [
  { id: 1, name: 'Urgent', value: 'urgent' },
  { id: 2, name: 'Take it Easy', value: 'take-it-easy' },
];

const departments = [
  { id: 1, name: 'IT' },
  { id: 2, name: 'HR' },
  { id: 3, name: 'Finance' },
  { id: 4, name: 'Admin' },
  { id: 5, name: 'Maintenance' },
];

export default function FacingIssueForm() {
  const [userId, setUserId] = useState(null);
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    priority: priorities[0],
    department: departments[0],
  });

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    if (!token) return;

    try {
      const decoded = jwtDecode(token);
      const id = decoded.id || decoded.userId || decoded.subId;
      setUserId(id);
    } catch (err) {
      console.error("Token decode error:", err);
    }
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handlePriorityChange = (selectedPriority) => {
    setFormData(prev => ({ ...prev, priority: selectedPriority }));
  };

  const handleDepartmentChange = (selectedDept) => {
    setFormData(prev => ({ ...prev, department: selectedDept }));
  };

  // ------------------------------------------
  // 🔥 Direct API POST + Token Based Headers
  // ------------------------------------------
  const handleSubmit = async (e) => {
  e.preventDefault();

  const payload = {
    title: formData.title,
    description: formData.description,
    priority: formData.priority.value,
    departmentId: formData.department.name,
  };

  try {
    const token = localStorage.getItem("accessToken");

    const response = await axios.post(
      "http://localhost:8090/api/tickets",
      payload,
      {
        headers: {
          "Content-Type": "application/json",
          ...(token && { Authorization: `Bearer ${token}` })
        }
      }
    );

    // 🔥 Correct success handler for Axios
    console.log("Ticket created:", response.data);
    alert("Ticket created successfully!");
    navigate("/internal/ticket");

  } catch (err) {
    console.error("API error:", err);
    alert("Something went wrong. Check console.");
  }
};


  return (
    <div className="max-w-xl mx-auto p-6 bg-white shadow-lg rounded-lg">
      <h2 className="text-2xl font-bold mb-6 text-gray-800">Create New Task</h2>

      <form onSubmit={handleSubmit} className="space-y-4">

        {/* TITLE */}
        <div>
          <label className="block text-sm font-medium text-gray-700">Title</label>
          <input
            type="text"
            name="title"
            value={formData.title}
            required
            onChange={handleChange}
            className="mt-1 block w-full rounded-md border p-2"
            placeholder="e.g., Laptop not working"
          />
        </div>

        {/* DESCRIPTION */}
        <div>
          <label className="block text-sm font-medium text-gray-700">Description</label>
          <textarea
            name="description"
            rows="3"
            value={formData.description}
            onChange={handleChange}
            className="mt-1 block w-full rounded-md border p-2"
            placeholder="Describe the issue..."
          />
        </div>

        {/* PRIORITY */}
        <div className="w-72">
          <label className="block text-sm font-medium text-gray-700 mb-1">Priority</label>

          <Listbox value={formData.priority} onChange={handlePriorityChange}>
            {({ open }) => (
              <>
                <Listbox.Button className="relative w-full cursor-pointer rounded-lg bg-white py-2 pl-3 pr-10 border shadow-sm">
                  <span>{formData.priority.name}</span>
                  <span className="absolute inset-y-0 right-0 flex items-center pr-2">
                    <ChevronUpDownIcon className="h-5 w-5 text-gray-400" />
                  </span>
                </Listbox.Button>

                <Transition show={open}>
                  <Listbox.Options className="absolute mt-1 max-h-60 w-full rounded-md bg-white shadow-lg">
                    {priorities.map((priority) => (
                      <Listbox.Option
                        key={priority.id}
                        value={priority}
                        className="cursor-pointer select-none py-2 pl-10 pr-4"
                      >
                        {({ selected }) => (
                          <>
                            <span className={selected ? "font-semibold" : "font-normal"}>
                              {priority.name}
                            </span>
                            {selected && (
                              <span className="absolute inset-y-0 left-0 flex items-center pl-3 text-indigo-600">
                                <CheckIcon className="h-5 w-5" />
                              </span>
                            )}
                          </>
                        )}
                      </Listbox.Option>
                    ))}
                  </Listbox.Options>
                </Transition>
              </>
            )}
          </Listbox>
        </div>

        {/* DEPARTMENT */}
        <div className="w-72">
          <label className="block text-sm font-medium text-gray-700 mb-1">Department</label>

          <Listbox value={formData.department} onChange={handleDepartmentChange}>
            {({ open }) => (
              <>
                <Listbox.Button className="relative w-full cursor-pointer rounded-lg bg-white py-2 pl-3 pr-10 border shadow-sm">
                  <span>{formData.department.name}</span>
                  <span className="absolute inset-y-0 right-0 flex items-center pr-2">
                    <ChevronUpDownIcon className="h-5 w-5 text-gray-400" />
                  </span>
                </Listbox.Button>

                <Transition show={open}>
                  <Listbox.Options className="absolute mt-1 max-h-60 w-full rounded-md bg-white shadow-lg">
                    {departments.map((dept) => (
                      <Listbox.Option
                        key={dept.id}
                        value={dept}
                        className="cursor-pointer select-none py-2 pl-10 pr-4"
                      >
                        {({ selected }) => (
                          <>
                            <span className={selected ? "font-semibold" : "font-normal"}>
                              {dept.name}
                            </span>
                            {selected && (
                              <span className="absolute inset-y-0 left-0 flex items-center pl-3 text-indigo-600">
                                <CheckIcon className="h-5 w-5" />
                              </span>
                            )}
                          </>
                        )}
                      </Listbox.Option>
                    ))}
                  </Listbox.Options>
                </Transition>
              </>
            )}
          </Listbox>
        </div>

        <button
          type="submit"
          className="w-full rounded-md bg-indigo-600 py-2 text-white font-medium hover:bg-indigo-700"
        >
          Create Task
        </button>
      </form>
    </div>
  );
}
