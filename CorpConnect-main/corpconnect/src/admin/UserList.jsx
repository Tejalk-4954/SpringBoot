import React, { useEffect, useState } from 'react';
// Assuming you will implement AssignDepartment in your adminServices.js
import { AssignRole, fetchUserList, AssignDepartment } from './services/adminServices'; 

// Example list of departments for the dropdown
const DEPARTMENT_OPTIONS = [
  "IT",
  "HR",
  "Finance",
  "Marketing",
  "Sales",
  "Operations",
  "Support",
];

export const UserList = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null); // Keep error state for potential future use

  useEffect(() => {
    const loadUsers = async () => {
      setLoading(true);
      try{
        // NOTE: Ensure fetchUserList returns user objects with 'department' property
        const data = await fetchUserList(); 
      setUsers(data);
      }
      catch(err){
        console.error("Failed to fetch user list", err);
        setError("Failed to fetch user list.");
        setUsers([]); // Clear users on error
      }
      finally{
        setLoading(false);
      }
    };

    loadUsers();
  }, []);


  // --- Role Assignment Handler (Existing Logic) ---
  const handleRoleChange = async(userid, event) => {
    const newRole = event.target.value;
    const currentRole = event.target.defaultValue;

    if(!newRole){return}

    const isConfirmed = window.confirm(
      `Are you sure you want to assign the role "${newRole}" to this user?`
    );
    
    if(isConfirmed){
      try{
        await AssignRole(userid, newRole);

        // Update local state: Assume 'roles' is replaced with the new single role
        setUsers(prevUsers =>
          prevUsers.map(user =>
            user.id === userid ? { ...user, roles : [newRole] } : user
          )
        );
        alert(`Role successfully assigned to user ${userid}: ${newRole}!`)
      }
      catch(err){
        console.error("Role assignment failed:", err);
        alert("Failed to assign role. Check console for details.");
        // Revert dropdown value on failure
        event.target.value = currentRole; 
      }
    }
    else{
      // Revert dropdown value if confirmation is cancelled
      event.target.value = currentRole;
    }
  }

  // --- Department Assignment Handler (New Logic) ---
  const handleDepartmentChange = async(userid, event) => {
    const newDepartment = event.target.value;
    const currentDepartment = event.target.defaultValue;

    if(!newDepartment){return}

    const isConfirmed = window.confirm(
        `Are you sure you want to assign the department "${newDepartment}" to this user?`
    );

    if(isConfirmed){
        try{
            // IMPORTANT: You must implement AssignDepartment in adminServices
            await AssignDepartment(userid, newDepartment);

            // Update local state: Assume 'department' property is a string
            setUsers(prevUsers =>
                prevUsers.map(user =>
                    user.id === userid ? { ...user, department : newDepartment } : user
                )
            );
            alert(`Department successfully assigned to user ${userid}: ${newDepartment}!`);
        }
        catch(err){
            console.error("Department assignment failed:", err);
            alert("Failed to assign department. Check console for details.");
            // Revert dropdown value on failure
            event.target.value = currentDepartment; 
        }
    }
    else{
        // Revert dropdown value if confirmation is cancelled
        event.target.value = currentDepartment;
    }
  }

  // Function to format roles array into a single string
  const formatRoles = (roles) => {
    return roles && roles.length > 0 ? roles.join(', ') : 'N/A';
  };

  if (loading) {
    return (
      <div className="text-center mt-8 text-lg font-semibold text-gray-700">
        Loading users...
      </div>
    );
  }

  if (error) {
    return (
        <div className="text-center mt-8 text-lg text-red-600">
            Error: {error}
        </div>
    );
  }

  if (users.length === 0) {
    return (
      <div className="text-center mt-8 text-lg text-gray-600">
        No users found.
      </div>
    );
  }

  return (
    <div className="text-center mt-8 p-4">
      <h2 className="text-2xl font-bold mb-4 text-gray-800">User Table</h2>
      <div className="overflow-x-auto shadow-lg rounded-lg border border-gray-200">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-blue-600 text-white">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider border-r border-blue-500">Name</th>
              <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider border-r border-blue-500">Email</th>
              <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider border-r border-blue-500">Role</th>
              <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider border-r border-blue-500">Department</th> {/* NEW COLUMN */}
              <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider border-r border-blue-500">Assign Role</th>
              <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">Assign Department</th> {/* NEW COLUMN */}
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {users.map((user) => (
              <tr key={user.id} className="hover:bg-gray-50">
                {/* User Details */}
                <td className="px-6 py-4 whitespace-nowrap font-semibold text-gray-900 border-r">
                  {user.fullName}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-gray-700 border-r">
                  {user.email}
                </td>
                <td className="px-6 py-4 whitespace-nowrap text-gray-700 border-r">
                  {formatRoles(user.roles)}
                </td>
                {/* Department Display */}
                <td className="px-6 py-4 whitespace-nowrap text-gray-700 border-r">
                  {user.department || 'N/A'} {/* Assumes user object has a 'department' field */}
                </td>
                {/* Assign Role Dropdown */}
                <td className="px-6 py-4 whitespace-nowrap border-r">
                  <select
                    className="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm rounded-md shadow-sm"
                    defaultValue={user.roles && user.roles.length > 0 ? user.roles[0] : ''} 
                    onChange={(e)=> handleRoleChange(user.id, e)}
                  >
                    <option value="">Select Role</option>
                    <option value="CANDIDATE">CANDIDATE</option>
                    <option value="ADMIN">ADMIN</option>
                    <option value="DEVELOPER">DEVELOPER</option>
                    <option value="HR">HR</option>
                    <option value="ITS_HEAD">ITS_HEAD</option>
                    <option value="MANAGER">MANAGER</option>
                    <option value="SUPPORT">SUPPORT</option>
                  </select>
                </td>
                {/* Assign Department Dropdown (NEW) */}
                <td className="px-6 py-4 whitespace-nowrap">
                  <select
                    className="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm rounded-md shadow-sm"
                    defaultValue={user.department || ''} // Assumes user.department is a string
                    onChange={(e) => handleDepartmentChange(user.id, e)}
                  >
                    <option value="">Select Dept</option>
                    {DEPARTMENT_OPTIONS.map(dept => (
                        <option key={dept} value={dept}>{dept}</option>
                    ))}
                  </select>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};