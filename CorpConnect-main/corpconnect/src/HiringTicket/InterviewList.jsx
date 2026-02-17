// src/Hiring/InterviewList.jsx
import React, { useEffect, useState } from "react";
import  {getInterviewsByInterviewer} from "../InternalTicket/services/InterviewServices"
import { useNavigate } from "react-router-dom";

export default function InterviewList() {
  const [list, setList] = useState([]);
  const navigate = useNavigate();
  const interviewerId = localStorage.getItem("userId") || ""; // ensure userId stored at login

  useEffect(() => {
    const load = async () => {
      try {
        const res = await getInterviewsByInterviewer(interviewerId);
        setList(res.data || []);
      } catch (err) {
        console.error(err);
      }
    };
    if (interviewerId) load();
  }, [interviewerId]);

  return (
    <div className="max-w-5xl mx-auto my-8 p-6 bg-white rounded-xl shadow">
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-2xl font-semibold text-blue-700">My Scheduled Interviews</h2>
      </div>

      <div className="space-y-4">
        {list.length === 0 && <div className="text-gray-500">No interviews scheduled.</div>}
        {list.map((iv) => (
          <div key={iv.id} className="flex items-center justify-between p-3 border rounded">
            <div>
              <div className="font-medium">{iv.applicationId}</div>
              <div className="text-sm text-gray-600">{iv.interviewDate} • {iv.mode}</div>
            </div>

            <div className="flex gap-2">
              <button onClick={() => navigate(`/hiring/interviews/${iv.id}/call`)}
                      className="bg-blue-600 text-white px-3 py-2 rounded">Start Call</button>

              <button onClick={() => navigate(`/hiring/interviews/${iv.id}`)}
                      className="px-3 py-2 rounded border text-gray-700">Details</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
