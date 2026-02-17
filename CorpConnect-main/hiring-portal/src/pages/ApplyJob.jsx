import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../services/api";

export default function ApplyJob() {
  const { jobId } = useParams();
  const navigate = useNavigate();

  const [job, setJob] = useState(null);
  const [form, setForm] = useState({
    candidateName: "",
    candidateEmail: "",
    skills: "",
    experienceYears: "",
    location: "",
    resumeFile: null,
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  useEffect(() => {
    api
      .get(`/job-posts/${jobId}`)
      .then((res) => setJob(res.data))
      .catch(() => setError("Failed to load job details"));
  }, [jobId]);

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    if (name === "resumeFile") {
      setForm({ ...form, resumeFile: files[0] });
    } else {
      setForm({ ...form, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    try {
      // Upload resume file first to get file ID via presign (you can implement this upload flow)
      // For demo, assume resumeFileId is a UUID or uploaded path

      // This example skips actual file upload for brevity
      const resumeFileId = form.resumeFile ? form.resumeFile.name : null;

      const payload = {
        jobId: jobId,
        candidateName: form.candidateName,
        candidateEmail: form.candidateEmail,
        skills: form.skills,
        experienceYears: Number(form.experienceYears),
        location: form.location,
        resumeFileId,
      };

      await api.post("/job-applications", payload);

      setSuccessMsg("Application submitted successfully!");
      setTimeout(() => {
        navigate("/applied-jobs");
      }, 2000);
    } catch (err) {
      setError("Failed to submit application. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  if (!job) {
    return (
      <div className="p-6 max-w-3xl mx-auto text-center text-gray-600">
        {error ? error : "Loading job details..."}
      </div>
    );
  }

  return (
    <div className="max-w-3xl mx-auto p-6 bg-white rounded-lg shadow-lg mt-8">
      <h1 className="text-3xl font-bold text-blue-700 mb-4">Apply for: {job.title}</h1>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block mb-1 font-semibold">Full Name</label>
          <input
            type="text"
            name="candidateName"
            required
            value={form.candidateName}
            onChange={handleChange}
            className="input input-bordered w-full"
          />
        </div>

        <div>
          <label className="block mb-1 font-semibold">Email</label>
          <input
            type="email"
            name="candidateEmail"
            required
            value={form.candidateEmail}
            onChange={handleChange}
            className="input input-bordered w-full"
          />
        </div>

        <div>
          <label className="block mb-1 font-semibold">Skills</label>
          <input
            type="text"
            name="skills"
            required
            value={form.skills}
            onChange={handleChange}
            placeholder="Comma separated skills"
            className="input input-bordered w-full"
          />
        </div>

        <div>
          <label className="block mb-1 font-semibold">Experience (Years)</label>
          <input
            type="number"
            name="experienceYears"
            min="0"
            required
            value={form.experienceYears}
            onChange={handleChange}
            className="input input-bordered w-full"
          />
        </div>

        <div>
          <label className="block mb-1 font-semibold">Location</label>
          <input
            type="text"
            name="location"
            required
            value={form.location}
            onChange={handleChange}
            className="input input-bordered w-full"
          />
        </div>

        <div>
          <label className="block mb-1 font-semibold">Resume (PDF)</label>
          <input
            type="file"
            name="resumeFile"
            accept=".pdf"
            onChange={handleChange}
            className="w-full"
            required
          />
        </div>

        {error && <p className="text-red-600">{error}</p>}
        {successMsg && <p className="text-green-600">{successMsg}</p>}

        <button
          type="submit"
          disabled={loading}
          className="btn bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-6 rounded mt-4"
        >
          {loading ? "Submitting..." : "Submit Application"}
        </button>
      </form>
    </div>
  );
}
