// src/Hiring/components/InterviewCard.jsx
import React from "react";

export default function InterviewCard({ interview, onCall, onDetails }) {
  return (
    <div className="p-3 border rounded flex items-center justify-between">
      <div>
        <div className="font-semibold">{interview.applicationId}</div>
        <div className="text-sm text-gray-600">{interview.interviewDate} · {interview.mode}</div>
      </div>

      <div className="flex gap-2">
        <button onClick={() => onCall(interview.id)} className="bg-blue-600 text-white px-3 py-1 rounded">Call</button>
        <button onClick={() => onDetails(interview.id)} className="px-3 py-1 border rounded">Details</button>
      </div>
    </div>
  );
}
