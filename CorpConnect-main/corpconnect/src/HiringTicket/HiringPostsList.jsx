import React from 'react';
// Importing solid icons from Heroicons
import { 
  ArrowTopRightOnSquareIcon, 
  CheckCircleIcon, 
  XCircleIcon, 
  ClockIcon 
} from '@heroicons/react/24/solid';

// Mock data to simulate the list of posts fetched from an API (unchanged)
const hiringPosts = [
  {
    id: 1,
    title: 'Senior Frontend Developer (React/Next.js)',
    description: 'Lead the development of our next-generation customer-facing platform using modern web technologies.',
    status: 'Open',
    applicationsCount: 45
  },
  {
    id: 2,
    title: 'Cloud Infrastructure Engineer (AWS)',
    description: 'Design, implement, and maintain scalable cloud infrastructure and CI/CD pipelines.',
    status: 'Closed',
    applicationsCount: 78
  },
  {
    id: 3,
    title: 'Product Manager - Data Solutions',
    description: 'Define the roadmap and strategy for our core data analytics products.',
    status: 'Reviewing',
    applicationsCount: 32
  },
];

/**
 * Renders a list of hiring posts with relevant details and an action button, using Heroicons.
 */
const HiringPostsList = () => {
  // Handler function for the 'View Applications' button
  const handleViewApplications = (postId, postTitle) => {
    console.log(`Navigating to applications for Post ID: ${postId} - ${postTitle}`);
    alert(`Viewing applications for: ${postTitle}`);
  };

  // Helper function to determine status styling and icon
  const getStatusInfo = (status) => {
    switch (status) {
      case 'Open':
        return {
          icon: <CheckCircleIcon className="w-5 h-5 text-green-500" />,
          color: 'bg-green-100 text-green-800'
        };
      case 'Closed':
        return {
          icon: <XCircleIcon className="w-5 h-5 text-red-500" />,
          color: 'bg-red-100 text-red-800'
        };
      case 'Reviewing':
        return {
          icon: <ClockIcon className="w-5 h-5 text-yellow-500" />,
          color: 'bg-yellow-100 text-yellow-800'
        };
      default:
        return {
          icon: null,
          color: 'bg-gray-100 text-gray-800'
        };
    }
  };

  return (
    <div className="p-4 sm:p-6 lg:p-8">
      <h2 className="text-3xl font-extrabold text-gray-900 mb-6 border-b pb-2">
        Job Posting Management
      </h2>
      
      <div className="bg-white shadow-xl rounded-lg overflow-hidden">
        {/* Header Row for large screens */}
        <div className="hidden md:grid grid-cols-12 gap-4 p-4 border-b bg-gray-50 text-sm font-semibold text-gray-600 uppercase tracking-wider">
          <div className="col-span-1">No.</div>
          <div className="col-span-3">Title</div>
          <div className="col-span-4">Description</div>
          <div className="col-span-2 text-center">Status</div>
          <div className="col-span-2 text-center">Action</div>
        </div>

        {/* List of Posts */}
        <div className="divide-y divide-gray-200">
          {hiringPosts.map((post) => {
            const statusInfo = getStatusInfo(post.status);
            
            return (
              <div 
                key={post.id} 
                className="grid grid-cols-1 md:grid-cols-12 gap-4 p-4 hover:bg-gray-50 transition duration-150 ease-in-out"
              >
                {/* Post Serial No. (ID) */}
                <div className="col-span-1 font-mono text-sm text-gray-600 md:text-base">
                  <span className="md:hidden font-semibold">No: </span>{post.id}
                </div>

                {/* Post Title */}
                <div className="col-span-3 font-semibold text-gray-900">
                  <span className="md:hidden font-semibold">Title: </span>{post.title}
                </div>

                {/* Post Description */}
                <div className="col-span-4 text-sm text-gray-500 line-clamp-2">
                  <span className="md:hidden font-semibold text-gray-700">Description: </span>{post.description}
                </div>

                {/* Post Status */}
                <div className="col-span-2 flex justify-start md:justify-center items-center">
                  <span className="md:hidden font-semibold text-gray-700 mr-2">Status: </span>
                  <span className={`inline-flex items-center px-3 py-1 text-xs font-medium rounded-full ${statusInfo.color}`}>
                    {statusInfo.icon}
                    <span className="ml-1">{post.status}</span>
                  </span>
                </div>

                {/* Action Button */}
                <div className="col-span-2 flex justify-start md:justify-center items-center">
                  <button
                    onClick={() => handleViewApplications(post.id, post.title)}
                    className="flex items-center justify-center w-full md:w-auto px-4 py-2 text-sm font-medium text-white bg-indigo-600 rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition duration-150 ease-in-out"
                    title={`View ${post.applicationsCount} applications`}
                  >
                    {/* ArrowTopRightOnSquareIcon is a common choice for 'view/external link' */}
                    <ArrowTopRightOnSquareIcon className="mr-2 h-4 w-4" /> 
                    View Apps ({post.applicationsCount})
                  </button>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default HiringPostsList;