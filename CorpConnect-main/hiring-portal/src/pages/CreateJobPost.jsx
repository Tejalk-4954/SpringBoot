import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

export default function CreateJobPost() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    title: "",
    description: "",
    department: "",
    location: "",
    ticketId: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    setSuccessMsg("");

    try {
      const payload = {
        title: form.title,
        description: form.description,
        department: form.department,
        location: form.location,
        ticketId: form.ticketId || null,
      };

      await api.post("/job-posts", payload);

      setSuccessMsg("Job post created successfully!");
      setTimeout(() => {
        navigate("/view-tickets");
      }, 2000);
    } catch (err) {
      setError("Failed to create job post. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-3xl mx-auto p-8 bg-white rounded-lg shadow-lg mt-12">
      <h1 className="text-3xl font-bold text-blue-700 mb-6">Create Job Post</h1>

      <form onSubmit={handleSubmit} className="space-y-5">
        <div>
          <label className="block mb-2 font-semibold">Job Title</label>
          <input
            type="text"
            name="title"
            required
            value={form.title}
            onChange={handleChange}
            className="input input-bordered w-full"
            placeholder="Enter job title"
          />
        </div>

        <div>
          <label className="block mb-2 font-semibold">Job Description</label>
          <textarea
            name="description"
            required
            value={form.description}
            onChange={handleChange}
            rows="4"
            className="textarea textarea-bordered w-full"
            placeholder="Enter detailed job description"
          />
        </div>

        <div>
          <label className="block mb-2 font-semibold">Department</label>
          <input
            type="text"
            name="department"
            value={form.department}
            onChange={handleChange}
            className="input input-bordered w-full"
            placeholder="Department (e.g., Engineering, Marketing)"
          />
        </div>

        <div>
          <label className="block mb-2 font-semibold">Location</label>
          <input
            type="text"
            name="location"
            value={form.location}
            onChange={handleChange}
            className="input input-bordered w-full"
            placeholder="Job location"
          />
        </div>

        <div>
          <label className="block mb-2 font-semibold">Ticket ID (optional)</label>
          <input
            type="text"
            name="ticketId"
            value={form.ticketId}
            onChange={handleChange}
            className="input input-bordered w-full"
            placeholder="Link to ticket if applicable"
          />
        </div>

        {error && <p className="text-red-600">{error}</p>}
        {successMsg && <p className="text-green-600">{successMsg}</p>}

        <button
          type="submit"
          disabled={loading}
          className="btn bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-6 rounded"
        >
          {loading ? "Creating..." : "Create Job Post"}
        </button>
      </form>
    </div>
  );
}
