import { ArrowPathIcon, CheckCircleIcon, TicketIcon } from '@heroicons/react/20/solid';
import React from 'react'
import MetricCard from './MetricCard';
import { useNavigate } from 'react-router-dom';

const Home = () => {

  const navigate = useNavigate();
  const toTicketHome = ()=>{
    navigate("/internal/ticket")
  }

  const employeeData = {
  name: 'Alex Johnson',
  role: 'Ticket Raiser', // or 'Ticket Resolver'
  metrics: {
    raised: 15,
    resolved: 10,
    inProgress: 5,
  }
};
  const {name,role,metrics} = employeeData;

  const metricItems = [
    {
      title : 'Ticekts Raised',
      count : metrics.raised,
      icon : TicketIcon,
      colorClass : 'text-indigo-600'
    },
    {
      title : 'Ticekts Resolved',
      count : metrics.resolved,
      icon : CheckCircleIcon,
      colorClass : 'text-green-600'
    },
    {
      title : 'In progress',
      count : metrics.inProgress,
      icon : ArrowPathIcon,
      colorClass : 'text-yellow-600'
    }
  ]
  return (
    <div className='min-h-screen bg-gray-50 p-6 sm:p-10' >
      <header className="pb-6 border-b border-gray-200">
        <h1 className="text-3xl font-bold leading-tight text-gray-900">
          Welcome back, {name}!
        </h1>
        <p className="mt-1 text-md text-gray-500">
          Your role: <span className="font-semibold text-indigo-600">{role}</span>
        </p>
      </header>
      
      {/* Metric Cards Grid */}
      <main className="mt-8">
        <h2 className="text-2xl font-semibold text-gray-800 mb-6">Your Ticket Snapshot</h2>
        
        <div className="grid grid-cols-1 gap-6 sm:grid-cols-3">
          {metricItems.map((item) => (
            <MetricCard key={item.title} {...item} />
          ))}
        </div>
      </main>

      {/* --- Additional Sections (Customizable) --- */}
      <section className="mt-12">
        <h2 className="text-2xl font-semibold text-gray-800 mb-4">Quick Actions</h2>
        <div className="flex space-x-4">
          <button onClick={toTicketHome} className="px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700">
            + Raise New Ticket
          </button>
          {role === 'Ticket Resolver' && (
            <button className="px-4 py-2 border border-gray-300 text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50">
              View Assigned Tickets
            </button>
          )}
        </div>
      </section>

      {/* --- Resolver Specific Data (Conditional Rendering) --- */}
      {role === 'Ticket Resolver' && (
        <section className="mt-12 bg-white p-6 rounded-lg shadow-md">
          <h3 className="text-xl font-semibold text-gray-800 mb-4">Resolver Performance</h3>
          <p className="text-sm text-gray-600">
            Average Resolution Time: <span className="font-bold text-green-700">4.2 hours</span>
          </p>
        </section>
      )}
    </div>
  )
}

export default Home
