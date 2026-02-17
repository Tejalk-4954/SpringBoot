import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getJobPostById, getApplicationsByJob } from "../InternalTicket/services/JobPostServices";

export default function JobPostDetails() {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [applications, setApplications] = useState([]);

  useEffect(() => {
    getJobPostById(id).then((res) => setPost(res.data));
    getApplicationsByJob(id).then((res) => setApplications(res.data));
  }, [id]);

  if (!post) return <p>Loading...</p>;

  return (
    <div className="p-8 max-w-5xl mx-auto">
      <h2 className="text-3xl font-bold text-blue-700 mb-6">
        {post.title}
      </h2>

      <p className="text-gray-700 mb-4">{post.description}</p>

      <h3 className="text-2xl text-blue-600 mt-6">Applications</h3>

      <table className="w-full mt-4 border-collapse">
        <thead>
          <tr className="bg-blue-600 text-white">
            <th className="p-3 border">Candidate</th>
            <th className="p-3 border">Email</th>
            <th className="p-3 border">Status</th>
            <th className="p-3 border">Action</th>
          </tr>
        </thead>
        <tbody>
          {applications.map((a) => (
            <tr key={a.id} className="bg-gray-50">
              <td className="p-3 border">{a.candidateName}</td>
              <td className="p-3 border">{a.candidateEmail}</td>
              <td className="p-3 border">{a.status}</td>
              <td className="p-3 border">
                <Link
                  to={`/interview/schedule/${a.id}`}
                  className="bg-blue-600 text-white px-4 py-2 rounded-lg"
                >
                  Schedule Interview
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
