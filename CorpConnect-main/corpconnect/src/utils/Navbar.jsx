import React, { useEffect } from "react";
import { Popover, PopoverButton, PopoverPanel } from "@headlessui/react";
import logo from "../assets/logo.jpg";
import { Bars3Icon, BellIcon } from "@heroicons/react/20/solid";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, NavLink } from "react-router-dom";
import { logout } from "../state-management/authSlice";
 
const Navbar = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
 
  const user = useSelector((state) => state.auth.user);
  const role = user?.role;
  const token = user?.token;
 
  useEffect(() => {
    const localtoken = localStorage.getItem("accessToken");
    if (!localtoken && token) {
      dispatch(logout());
      navigate("/", { replace: true });
    }
  }, [token, dispatch, navigate]);
 
  const handleLogout = () => {
    dispatch(logout());
    navigate("/");
  };
 
  const handleRegister = () => navigate("/register");
  const handleCandidateRegister = () => navigate("/candidate/register");
  const handleLogin = () => navigate("/login");
 
  const navLinkClass = ({ isActive }) =>
    `rounded-md px-3 py-2 text-sm font-medium ${
      isActive ? "bg-indigo-600 text-white" : "text-gray-300 hover:bg-white/5 hover:text-white"
    }`;
 
  // Role-specific left links when logged in
  const renderLinksByRole = () => {
    if (!token) return null;
 
    switch ((role || "").toUpperCase()) {
      case "ROLE_MANAGER":
        return (
          <>
            <NavLink to="/" className={navLinkClass}>Dashboard</NavLink>
{/*             <NavLink to="/admin/user/list" className={navLinkClass}>User List</NavLink> */}
            <NavLink to="/internal/ticket/user/list" className={navLinkClass}>My Ticekts</NavLink>
            <NavLink to="/internal/ticket/list" className={navLinkClass}>Tickets Assigned</NavLink>
            <NavLink to="/internal/ticket/listAll/ticket" className={navLinkClass}>All Tickets</NavLink>
          </>
        );
      case "ROLE_ADMIN":
        return (
          <>
            <NavLink to="/admin/home" className={navLinkClass}>Dashboard</NavLink>
            <NavLink to="/admin/user/list" className={navLinkClass}>User List</NavLink>
            <NavLink to="/internal/ticket/user/list" className={navLinkClass}>My Ticekts</NavLink>
            <NavLink to="/internal/ticket/list" className={navLinkClass}>View Tickets</NavLink>
          </>
        );
//       case "ROLE_HR":
//         return (
//           <>
//             <NavLink to="/" className={navLinkClass}>Dashboard</NavLink>
//             <NavLink to="/internal/ticket/user/list" className={navLinkClass}>My Ticekts</NavLink>
//             <NavLink to="/internal/ticket/list" className={navLinkClass}>View Tickets</NavLink>
//             <NavLink to="/internal/ticket/listAll/ticket" className={navLinkClass}>All Tickets</NavLink>
//           </>
//         );
      case "ROLE_DEVELOPER":
        return (
          <>
            <NavLink to="/internal/ticket/list" className={navLinkClass}>Assigned Tickets</NavLink>
            <NavLink to="/internal/ticket/user/list" className={navLinkClass}>My Ticekts</NavLink>
          </>
        );

      case "ROLE_ITS_HEAD":
        return (
          <>
            <NavLink to="/internal/ticket/list" className={navLinkClass}>Assigned Tickets</NavLink>
            <NavLink to="/internal/ticket/user/list" className={navLinkClass}>My Ticekts</NavLink>
            <NavLink to="/internal/ticket/listAll/ticket" className={navLinkClass}>All Tickets</NavLink>
          </>
        );

      case "ROLE_CANDIDATE":
        return (
          <>
            <NavLink to="/user/profile" className={navLinkClass}>My Profile</NavLink>
            <NavLink to="/internal/ticket" className={navLinkClass}>Raise Ticket</NavLink>
            <NavLink to="/internal/ticket/user/list" className={navLinkClass}>My Ticekts</NavLink>
            <NavLink to="/internal/ticket/list" className={navLinkClass}>My Tickets</NavLink>
          </>
        );
      default:
        return null;
    }
  };
 
  // Hiring dropdown only for HR and MANAGER
  const renderHiringDropdown = () => {
    const r = (role || "").toUpperCase();
    if (r === "ROLE_HR" || r === "ROLE_MANAGER") {
      return (
        <Popover className="relative">
          <PopoverButton className="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-white/5 hover:text-white focus:outline-none">
            Hiring Ticket
          </PopoverButton>
          <PopoverPanel
            className="absolute z-20 w-44 mt-2 origin-top-left divide-y divide-gray-600 rounded-md bg-gray-700 shadow-lg ring-1 ring-black ring-opacity-5"
          >
            <div className="p-1">
              <NavLink to="/hiring/ticket/post/list" className="block rounded-md px-3 py-2 text-sm font-medium text-white hover:bg-gray-600">
                View Posts
              </NavLink>
              <NavLink to="/hiring/ticket/post" className="block rounded-md px-3 py-2 text-sm font-medium text-white hover:bg-gray-600">
                Create Post
              </NavLink>
            </div>
          </PopoverPanel>
        </Popover>
      );
    }
    return null;
  };
 
  return (
    <div>
      <nav className="relative bg-gray-800 shadow-md">
        <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
          <div className="relative flex h-16 items-center justify-between">
            {/* Mobile button */}
            <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
              <button type="button" className="relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-white/5 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white" aria-controls="mobile-menu" aria-expanded="false">
                <Bars3Icon className="block h-6 w-6" aria-hidden="true" />
              </button>
            </div>
 
            {/* Logo + Links */}
            <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
              <div className="flex shrink-0 items-center">
                <NavLink to="/"><img src={logo} alt="CorpConnect" className="h-8 w-auto" /></NavLink>
              </div>
 
              <div className="hidden sm:ml-6 sm:block">
                <div className="flex space-x-4">
                  {/* If NOT logged in: show About only */}
                  {!token && (
                    <NavLink to="/about" className={navLinkClass}>About</NavLink>
                  )}
                  
                  {/* If logged in: show role-specific links and services dropdown */}
                  {token && (
                    <>
                      {renderLinksByRole()}
 
                      {/* Services popover */}
                      <Popover className="relative">
                        <PopoverButton className="rounded-md px-3 py-2 text-sm font-medium text-gray-300 hover:bg-white/5 hover:text-white focus:outline-none">
                          Services
                        </PopoverButton>
                        <PopoverPanel className="absolute z-10 w-48 mt-2 origin-top-left divide-y divide-gray-700 rounded-md bg-gray-700 shadow-lg ring-1 ring-black ring-opacity-5">
                          <div className="p-1">
                            <NavLink to="/internal/ticket" className="block rounded-md px-3 py-2 text-sm font-medium text-white hover:bg-gray-600">Raise Ticket</NavLink>
                          </div>
 
                          {/* Hiring dropdown included inside Services for authorized roles */}
                          {renderHiringDropdown()}
                        </PopoverPanel>
                      </Popover>
                    </>
                  )}
                </div>
              </div>
            </div>
 
            {/* Right side: AUTH/PROFILE */}
            <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0 space-x-2">
              
              {/* When NOT logged in: show Register & Login buttons */}
              {!token && (
                <>
                  <button type="button" onClick={handleCandidateRegister} className="inline-flex items-center rounded-md bg-indigo-500 px-3 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-600">
                  Candidate Register
                  </button>
                  <button type="button" onClick={handleRegister} className="inline-flex items-center rounded-md bg-indigo-500 px-3 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-600">
                   Emp Register
                  </button>
                  <button type="button" onClick={handleLogin} className="inline-flex items-center rounded-md bg-indigo-500 px-3 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-600">
                    Login
                  </button>
                </>
              )}
              
              {/* When logged in: show Notification & Profile popover */}
              {token && (
                <>
                  {/* Notification */}
                  <button type="button" className="relative rounded-full bg-gray-800 p-1 text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                    <BellIcon className="h-6 w-6" aria-hidden="true" />
                  </button>
 
                  {/* Profile popover (always shown when logged in) */}
                  <Popover className="relative ml-3">
                    <PopoverButton className="relative flex rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                      <img src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e" alt="" className="h-8 w-8 rounded-full" />
                    </PopoverButton>
 
                    <PopoverPanel className="absolute z-10 mt-2 w-48 origin-top-right divide-y divide-gray-700 rounded-md bg-gray-700 py-1 shadow-lg ring-1 ring-black ring-opacity-5">
                      <NavLink to="/user/profile" className="block px-4 py-2 text-sm text-gray-200 hover:bg-gray-600">Your Profile</NavLink>
                      <NavLink to="/internal/ticket/list" className="block px-4 py-2 text-sm text-gray-200 hover:bg-gray-600">My Tickets</NavLink>
                      <button type="button" onClick={handleLogout} className="w-full text-left px-4 py-2 text-sm text-gray-200 hover:bg-gray-600">Sign out</button>
                    </PopoverPanel>
                  </Popover>
                </>
              )}
            </div>
          </div>
        </div>
      </nav>
 
      {/* Mobile menu placeholder - add toggle logic if needed */}
      <div className="sm:hidden" id="mobile-menu">
        <div className="space-y-1 px-2 pb-3 pt-2">
          {/* Add mobile links if you want (kept empty intentionally) */}
        </div>
      </div>
    </div>
  );
};
 
export default Navbar;