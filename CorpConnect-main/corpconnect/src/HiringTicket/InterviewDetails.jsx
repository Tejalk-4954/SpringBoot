// src/Hiring/InterviewDetails.jsx
import React, { useEffect, useState } from "react";
import { getInterviewById, decideInterview } from "../InternalTicket/services/InterviewServices";
import { useParams, useNavigate } from "react-router-dom";

export default function InterviewDetails() {
  const { id } = useParams();
  const [info, setInfo] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    getInterviewById(id).then(r => setInfo(r.data)).catch(e => console.error(e));
  }, [id]);

  const handleDecision = async (decision) => {
    try {
      await decideInterview(id, decision);
      alert("Decision saved");
      navigate(-1);
    } catch (err) {
      console.error(err);
      alert("Failed to save decision");
    }
  };

  if (!info) return <div className="p-6">Loading...</div>;

  return (
    <div className="max-w-3xl mx-auto my-8 p-6 bg-white rounded-xl shadow">
      <div className="mb-4">
        <h2 className="text-xl font-semibold text-blue-700">Interview Details</h2>
        <div className="text-gray-600 text-sm">ID: {info.id}</div>
      </div>

      <div className="space-y-2">
        <div><b>Application:</b> {info.applicationId}</div>
        <div><b>Interviewer:</b> {info.interviewerId}</div>
        <div><b>Date:</b> {info.interviewDate}</div>
        <div><b>Mode:</b> {info.mode}</div>
        <div><b>Status:</b> {info.status}</div>
        <div><b>Notes:</b> {info.notes}</div>
        <div><b>Meet Link:</b> {info.meetLink || "—"}</div>
      </div>

      <div className="mt-6 flex gap-3">
        <button onClick={() => handleDecision("SELECTED")} className="bg-green-600 text-white px-4 py-2 rounded">Select</button>
        <button onClick={() => handleDecision("REJECTED")} className="bg-red-600 text-white px-4 py-2 rounded">Reject</button>
        <button onClick={() => navigate(`/hiring/interviews/${id}/call`)} className="px-4 py-2 rounded border">Start Call</button>
      </div>
    </div>
  );
}
