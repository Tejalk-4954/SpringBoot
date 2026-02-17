import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../services/api";

export default function ScheduleInterview() {
  const { appId } = useParams();
  const navigate = useNavigate();

  const [application, setApplication] = useState(null);
  const [form, setForm] = useState({
    interviewDate: "",
    interviewerId: "",
    mode: "IN_PERSON",
    notes: "",
    meetLink: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [successMsg, setSuccessMsg] = useState("");
  const [availableSlots, setAvailableSlots] = useState([]);

  useEffect(() => {
    // Fetch application details to show candidate info (optional)
    api.get(`/job-applications/${appId}`)
      .then((res) => setApplication(res.data))
      .catch(() => setError("Failed to load application details"));

    // Fetch available interviewer slots (this endpoint is hypothetical, adjust as needed)
    api.get(`/interview-slots/interviewer`)
      .then((res) => setAvailableSlots(res.data))
      .catch(() => console.warn("Failed to load available slots"));
  }, [appId]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccessMsg("");
    setLoading(true);

    try {
      await api.post("/interviews/schedule", {
        applicationId: appId,
        interviewDate: form.interviewDate,
        interviewerId: form.interviewerId,
        mode: form.mode,
        notes: form.notes,
        meetLink: form.meetLink,
      });

      setSuccessMsg("Interview scheduled successfully! Notifications sent.");
      setTimeout(() => navigate("/manager"), 2000);
    } catch {
      setError("Failed to schedule interview. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-3xl mx-auto p-8 bg-white rounded-lg shadow-lg mt-12">
      <h1 className="text-3xl font-bold text-blue-700 mb-6">Schedule Interview</h1>

      {application && (
        <div className="mb-6 border p-4 rounded bg-blue-50">
          <p><strong>Candidate:</strong> {application.candidateName} ({application.candidateEmail})</p>
          <p><strong>Job Applied:</strong> {application.jobTitle || application.jobId}</p>
        </div>
      )}

      <form onSubmit={handleSubmit} className="space-y-5">
        <div>
          <label className="block mb-2 font-semibold">Interview Date & Time</label>
          <input
            type="datetime-local"
            name="interviewDate"
            value={form.interviewDate}
            onChange={handleChange}
            required
            className="input input-bordered w-full"
          />
        </div>

        <div>
          <label className="block mb-2 font-semibold">Interviewer ID</label>
          <select
            name="interviewerId"
            value={form.interviewerId}
            onChange={handleChange}
            required
            className="select select-bordered w-full"
          >
            <option value="" disabled>Select interviewer</option>
            {/* Assuming availableSlots is an array of interviewer objects; adjust accordingly */}
            {availableSlots.length > 0 ? (
              availableSlots.map((intv) => (
                <option key={intv.id} value={intv.id}>{intv.name}</option>
              ))
            ) : (
              <option disabled>Loading interviewers...</option>
            )}
          </select>
        </div>

        <div>
          <label className="block mb-2 font-semibold">Mode</label>
          <select
            name="mode"
            value={form.mode}
            onChange={handleChange}
            required
            className="select select-bordered w-full"
          >
            <option value="IN_PERSON">In Person</option>
            <option value="VIDEO_CALL">Video Call</option>
            <option value="PHONE_CALL">Phone Call</option>
          </select>
        </div>

        <div>
          <label className="block mb-2 font-semibold">Notes</label>
          <textarea
            name="notes"
            value={form.notes}
            onChange={handleChange}
            rows={3}
            className="textarea textarea-bordered w-full"
            placeholder="Any additional info"
          />
        </div>

        <div>
          <label className="block mb-2 font-semibold">Video Call Invitation Link (optional)</label>
          <input
            type="url"
            name="meetLink"
            value={form.meetLink}
            onChange={handleChange}
            placeholder="https://meet.jit.si/your-room"
            className="input input-bordered w-full"
          />
        </div>

        {error && <p className="text-red-600">{error}</p>}
        {successMsg && <p className="text-green-600">{successMsg}</p>}

        <button
          type="submit"
          disabled={loading}
          className="btn bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-6 rounded"
        >
          {loading ? "Scheduling..." : "Schedule Interview"}
        </button>
      </form>
    </div>
  );
}
