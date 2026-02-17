// src/components/AboutCorpConnect.jsx

import React from 'react';
import { ClockIcon, ChatBubbleBottomCenterTextIcon, RocketLaunchIcon } from '@heroicons/react/24/outline'; // Assumes you have Heroicons installed

const featureData = [
  {
    icon: ClockIcon,
    title: 'Eliminate Latency',
    description: 'Bypass the tedious cycle of back-and-forth emails. Agents and users engage instantly to cut down resolution time significantly.',
  },
  {
    icon: ChatBubbleBottomCenterTextIcon,
    title: 'Contextual Communication',
    description: 'All chat logs are permanently attached to the ticket, ensuring agents always have the full, immediate context they need.',
  },
  {
    icon: RocketLaunchIcon,
    title: 'Boost Satisfaction',
    description: 'Real-time interaction creates a more positive support experience, turning frustrating delays into efficient problem-solving.',
  },
];

const About = () => {
  return (
    <div className="min-h-screen bg-gray-50 py-12 sm:py-20">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Header Section */}
        <div className="text-center">
          <h2 className="text-base text-indigo-600 font-semibold tracking-wide uppercase">
            Introducing CorpConnect
          </h2>
          <p className="mt-2 text-3xl leading-8 font-extrabold tracking-tight text-gray-900 sm:text-4xl">
            Real-Time Resolution, Right in Your Ticket
          </p>
          <p className="mt-4 max-w-2xl text-xl text-gray-500 lg:mx-auto">
            CorpConnect is the next generation of ticketing support, integrating a powerful chat feature to bridge communication gaps and drastically reduce resolution delays.
          </p>
        </div>

        {/* Feature Grid Section */}
        <div className="mt-10">
          <dl className="space-y-10 md:space-y-0 md:grid md:grid-cols-3 md:gap-x-8 md:gap-y-10">
            {featureData.map((feature) => (
              <div key={feature.title} className="relative">
                <dt>
                  <div className="absolute flex items-center justify-center h-12 w-12 rounded-md bg-indigo-500 text-white">
                    <feature.icon className="h-6 w-6" aria-hidden="true" />
                  </div>
                  <p className="ml-16 text-lg leading-6 font-medium text-gray-900">
                    {feature.title}
                  </p>
                </dt>
                <dd className="mt-2 ml-16 text-base text-gray-500">
                  {feature.description}
                </dd>
              </div>
            ))}
          </dl>
        </div>
        
        {/* Mission Statement/Call to Action */}
        <div className="mt-20 pt-10 border-t border-gray-200">
            <div className="lg:grid lg:grid-cols-3 lg:gap-8 lg:items-center">
                <div className="lg:col-span-2">
                    <h3 className="text-2xl font-extrabold text-gray-900 sm:text-3xl">
                        Our Mission
                    </h3>
                    <p className="mt-3 text-lg text-gray-500">
                        To transform the slow, frustrating nature of traditional support tickets into a fast, collaborative, and human-centered problem-solving experience. We believe instant communication is key to organizational efficiency.
                    </p>
                </div>
                <div className="mt-8 lg:mt-0 lg:col-span-1 flex justify-center lg:justify-end">
                    <button
                        type="button"
                        className="w-full inline-flex items-center justify-center px-6 py-3 border border-transparent text-base font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:w-auto"
                    >
                        Get Started Today
                    </button>
                </div>
            </div>
        </div>

      </div>
    </div>
  );
};

export default About;