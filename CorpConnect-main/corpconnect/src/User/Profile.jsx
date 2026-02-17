import { jwtDecode } from 'jwt-decode';
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserProfile } from './services/UserProfile'; // Assuming this is correct

const Profile = () => {
  const [profile, setProfile] = useState(null);
  const navigate = useNavigate();
  
  // Use a constant placeholder image URL
  const DEFAULT_PROFILE_IMAGE = 'https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80';

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (!token) {
      alert("Session expired. Please login again.");
      navigate('/login');
      return;
    }

    try {
      const decoded = jwtDecode(token);
      // Ensure 'sub' field is present in the decoded token
      const username = decoded.sub;

      if (!username) {
        throw new Error("Token missing username ('sub') claim.");
      }

      const fetchProfile = async () => {
        // Assume UserProfile returns the data object or null/undefined on failure
        const data = await UserProfile();
        console.log("Fetched data:", data);

        // Check if data is an object and not null/undefined/empty array
        if (data && Object.keys(data).length > 0) {
          setProfile(data);
        } else {
          // This block runs if the backend returns no user data, 
          // indicating an issue with the token or user existence.
          alert("User not found or session expired. Please login again.");
          navigate('/login');
        }
      };

      fetchProfile();
    } catch (err) {
      // Catches errors from jwtDecode or custom errors thrown above
      console.error("Token error or session invalid:", err);
      alert("Invalid session. Please login.");
      navigate('/login');
    }

    // ✅ SOLUTION: Use an empty dependency array to run only ONCE on mount.
    // This prevents the fetch from potentially causing an infinite loop or 
    // being repeatedly interrupted.
  }, []); 

  // Use optional chaining or an empty object for safe destructuring
  const {
    id,
    fullName,
    email,
    roles,
  } = profile || {}; 

  // --- Rendering part remains the same (truncated for brevity) ---
  return (
    <>
      <div className="min-h-screen bg-gray-100 p-8">
        <div className="max-w-3xl mx-auto bg-white shadow-xl rounded-xl overflow-hidden">
          
          {/* Header Section: Image and Name */}
          <div className="bg-indigo-600 p-6 sm:p-8 flex items-center">
            <div className="flex-shrink-0">
              {/* Profile Image - Use constant default image */}
              <img
                className="h-24 w-24 rounded-full object-cover ring-4 ring-white"
                src={DEFAULT_PROFILE_IMAGE} 
                alt={fullName || "User Profile"}
              />
            </div>
            <div className="ml-6">
              {/* Name - Use fullName */}
              <h1 className="text-3xl font-extrabold text-white">
                {fullName || 'User Name'}
              </h1>
            </div>
          </div>

          {/* Details Section */}
          <div className="p-6 sm:p-8 space-y-6">
            <h2 className="text-xl font-semibold text-gray-800 border-b pb-2">
              Contact Information
            </h2>

            <dl className="grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-2">
              
              {/* Email - Use email */}
              <div className="sm:col-span-1">
                <dt className="text-sm font-medium text-gray-500">
                  Email Address
                </dt>
                <dd className="mt-1 text-sm text-gray-900">
                  {email || 'N/A'} {/* Added fallback for clarity */}
                </dd>
              </div>
              
              {/* User ID - Display ID if available */}
              <div className="sm:col-span-1">
                <dt className="text-sm font-medium text-gray-500">
                  User ID
                </dt>
                <dd className="mt-1 text-sm text-gray-900">
                  {id || 'N/A'} 
                </dd>
              </div>
              
              {/* Roles - Display roles */}
              <div className="sm:col-span-2">
                <dt className="text-sm font-medium text-gray-500">
                  Roles
                </dt>
                <dd className="mt-1 text-sm text-gray-900">
                  {roles && roles.length > 0 ? roles.join(', ') : 'No Roles'} 
                </dd>
              </div>

            </dl>
            
            {/* Optional: Add an Edit Button */}
            <div className="pt-6 border-t border-gray-200">
              <button
                type="button"
                className="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
              >
                Edit Profile
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Profile