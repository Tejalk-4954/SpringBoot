import React, { useEffect, useState } from "react";
import { getPublicJobPosts } from "../InternalServices/services/JobPostServices";
import { Link } from "react-router-dom";

export default function JobPostList() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    getPublicJobPosts().then((res) => setPosts(res.data));
  }, []);

  return (
    <div className="p-8 max-w-5xl mx-auto">
      <h2 className="text-3xl font-bold text-blue-700 mb-6">Open Job Posts</h2>

      <div className="grid grid-cols-1 gap-4">
        {posts.map((p) => (
          <div
            key={p.id}
            className="bg-white shadow-md p-5 rounded-lg border border-gray-200"
          >
            <h3 className="text-xl font-semibold text-blue-600">{p.title}</h3>
            <p className="text-gray-600">{p.description}</p>

            <Link
              to={`/jobs/${p.id}`}
              className="mt-3 inline-block bg-blue-600 text-white px-4 py-2 rounded-lg"
            >
              View Details
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}
