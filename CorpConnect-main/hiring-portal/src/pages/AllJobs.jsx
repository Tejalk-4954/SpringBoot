import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../services/api";

export default function AllJobs() {
  const [jobs, setJobs] = useState([]);

  useEffect(() => {
    api
      .get("/job-posts/public")
      .then((res) => setJobs(res.data))
      .catch((error) => {
        console.error("Failed to load jobs", error);
        setJobs([]);
      });
  }, []);

  return (
    <div className="max-w-5xl mx-auto p-6">
      <h1 className="text-3xl font-bold text-blue-700 mb-6">Open Job Posts</h1>

      {jobs.length === 0 ? (
        <p className="text-gray-600">No open job posts available at the moment.</p>
      ) : (
        <div className="grid gap-6 md:grid-cols-2">
          {jobs.map((job) => (
            <div key={job.id} className="border rounded-lg shadow-sm p-6 bg-white hover:shadow-lg transition">
              <h2 className="text-xl font-semibold text-indigo-700 mb-2">{job.title}</h2>
              <p className="text-gray-700 mb-2">{job.description}</p>
              <p className="text-sm text-gray-500 mb-2">
                Department: <span className="font-medium">{job.department}</span>
              </p>
              <p className="text-sm text-gray-500 mb-4">
                Location: <span className="font-medium">{job.location}</span>
              </p>

              <Link
                to={`/apply/${job.id}`}
                className="inline-block bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700 font-semibold"
              >
                Apply Now
              </Link>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
