// // src/Hiring/InterviewSchedule.jsx
// import React, { useState } from "react";
// import { scheduleInterview } from "../InternalTicket/services/InterviewServices";
// import { useNavigate } from "react-router-dom";

// export default function InterviewSchedule() {
//   const navigate = useNavigate();
//   const [dto, setDto] = useState({
//     applicationId: "",
//     interviewerId: "",
//     interviewDate: "",
//     mode: "ONLINE",
//     meetLink: "",
//     notes: "",
//   });
//   const [loading, setLoading] = useState(false);
//   const [error, setError] = useState("");

//   const handleChange = (e) => setDto({ ...dto, [e.target.name]: e.target.value });

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     setError("");
//     setLoading(true);
//     try {
//       const res = await scheduleInterview(dto);
//       setLoading(false);
//       navigate(`/hiring/interviews/${res.data.id}`);
//     } catch (err) {
//       setLoading(false);
//       setError(err.response?.data?.message || err.message || "Failed to schedule");
//       console.error(err);
//     }
//   };

//   return (
//     <div className="max-w-3xl mx-auto my-8 p-6 bg-white rounded-xl shadow">
//       <div className="flex items-center gap-4 mb-6">
//         <img src="/mnt/data/8b2a9d82-e66e-412b-a27e-209c9a86770b.png" alt="logo" className="w-12 h-12 rounded" />
//         <h1 className="text-2xl font-semibold text-blue-700">Schedule Interview</h1>
//       </div>

//       <form onSubmit={handleSubmit} className="space-y-4">
//         <div>
//           <label className="block text-sm">Application ID</label>
//           <input required name="applicationId" value={dto.applicationId} onChange={handleChange}
//                  className="mt-1 w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-200"/>
//         </div>

//         <div>
//           <label className="block text-sm">Interviewer (User ID)</label>
//           <input name="interviewerId" value={dto.interviewerId} onChange={handleChange}
//                  className="mt-1 w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-200"/>
//         </div>

//         <div>
//           <label className="block text-sm">Date & Time</label>
//           <input name="interviewDate" value={dto.interviewDate} onChange={handleChange} type="datetime-local"
//                  className="mt-1 w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-200"/>
//         </div>

//         <div>
//           <label className="block text-sm">Mode</label>
//           <select name="mode" value={dto.mode} onChange={handleChange}
//                   className="mt-1 w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-200">
//             <option value="ONLINE">Online (Jitsi)</option>
//             <option value="OFFLINE">Offline (In person)</option>
//           </select>
//         </div>

//         <div>
//           <label className="block text-sm">Meet Link (optional)</label>
//           <input name="meetLink" value={dto.meetLink} onChange={handleChange}
//                  className="mt-1 w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-200"/>
//         </div>

//         <div>
//           <label className="block text-sm">Notes</label>
//           <textarea name="notes" value={dto.notes} onChange={handleChange}
//                     className="mt-1 w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-200" rows="3"/>
//         </div>

//         <div className="flex items-center gap-3">
//           <button type="submit" disabled={loading} className="bg-blue-600 text-white px-4 py-2 rounded">
//             {loading ? "Scheduling..." : "Schedule"}
//           </button>
//           <button type="button" onClick={() => navigate(-1)} className="px-3 py-2 border rounded">Cancel</button>
//         </div>

//         {error && <div className="text-red-600 mt-2">{error}</div>}
//       </form>
//     </div>
//   );
// }




import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { scheduleInterview } from "../InternalTicket/services/InterviewServices";

export default function InterviewSchedule() {
  const { appId } = useParams();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    applicationId: appId,
    interviewerId: "",
    interviewDate: "",
    mode: "ONLINE",
    meetLink: "",
    notes: "",
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const submit = async (e) => {
    e.preventDefault();
    await scheduleInterview(form);
    alert("Interview Scheduled!");
    navigate("/interviews");
  };

  return (
    <div className="p-8 max-w-xl mx-auto bg-white shadow-lg rounded-xl">
      <h2 className="text-2xl font-bold text-blue-700 mb-4">
        Schedule Interview
      </h2>

      <form onSubmit={submit} className="space-y-4">
        <input
          name="interviewerId"
          placeholder="Interviewer ID"
          className="w-full border p-2 rounded"
          onChange={handleChange}
          required
        />

        <input
          type="datetime-local"
          name="interviewDate"
          className="w-full border p-2 rounded"
          onChange={handleChange}
          required
        />

        <select
          name="mode"
          className="w-full border p-2 rounded"
          onChange={handleChange}
        >
          <option value="ONLINE">Online</option>
          <option value="OFFLINE">Offline</option>
        </select>

        <input
          name="meetLink"
          placeholder="Meeting Link (Optional)"
          className="w-full border p-2 rounded"
          onChange={handleChange}
        />

        <textarea
          name="notes"
          placeholder="Notes"
          className="w-full border p-2 rounded"
          onChange={handleChange}
        />

        <button className="bg-blue-600 text-white px-4 py-2 rounded-lg">
          Schedule
        </button>
      </form>
    </div>
  );
}
