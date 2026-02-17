import React from 'react'

const MetricCard = ({ title, count, icon: Icon, colorClass }) => {
  return (
    <div>
      <div className={`p-5 bg-white rounded-lg shadow-lg transform hover:scale-[1.02] transition duration-300 ${colorClass}`}>
    <div className="flex items-center">
      <div className={`flex-shrink-0 p-3 rounded-full ${colorClass.replace('text-', 'bg-')}`}>
        <Icon className="h-6 w-6 text-white" aria-hidden="true" />
      </div>
      <div className="ml-5 w-0 flex-1">
        <dl>
          <dt className="text-sm font-medium text-gray-500 truncate">{title}</dt>
          <dd className="flex items-baseline">
            <div className="text-3xl font-bold text-gray-900">{count}</div>
          </dd>
        </dl>
      </div>
    </div>
  </div>
    </div>
  )
}

export default MetricCard
