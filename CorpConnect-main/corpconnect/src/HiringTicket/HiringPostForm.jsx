import React, { useState } from 'react'

const HiringPostForm = () => {
  const [formData, setFormData] = useState({
    title: '',
    description: '',
    requiredSkills: '', // Can be a comma-separated string
    experience: ''
  });

  // State for submission status/message
  const [isSubmitted, setIsSubmitted] = useState(false);

  // Handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevData => ({
      ...prevData,
      [name]: value
    }));
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    setIsSubmitted(true);
    // In a real application, you would send formData to an API here
    console.log('Form Data Submitted:', formData);

    // Reset form after a brief moment (optional)
    setTimeout(() => {
      setIsSubmitted(false);
      setFormData({
        title: '',
        description: '',
        requiredSkills: '',
        experience: ''
      });
    }, 3000);
  };
  
  return (
   <>
   <div className="max-w-xl mx-auto p-6 bg-white rounded-xl shadow-2xl">
      <h2 className="text-3xl font-extrabold text-gray-900 mb-6 text-center">
        Create New Hiring Post
      </h2>

      {isSubmitted && (
        <div className="mb-4 p-4 bg-green-100 text-green-700 border border-green-300 rounded-lg">
          Post successfully created! (Check console for data)
        </div>
      )}

      <form onSubmit={handleSubmit} className="space-y-6">

        {/* 1. Title of Position */}
        <div>
          <label htmlFor="title" className="block text-sm font-medium text-gray-700">
            Job Title
          </label>
          <input
            type="text"
            name="title"
            id="title"
            value={formData.title}
            onChange={handleChange}
            required
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm transition duration-150 ease-in-out"
            placeholder="e.g., Senior Frontend Developer"
          />
        </div>

        {/* 2. Description */}
        <div>
          <label htmlFor="description" className="block text-sm font-medium text-gray-700">
            Job Description
          </label>
          <textarea
            id="description"
            name="description"
            rows="4"
            value={formData.description}
            onChange={handleChange}
            required
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm resize-none transition duration-150 ease-in-out"
            placeholder="Outline the responsibilities and team role."
          ></textarea>
        </div>

        {/* 3. Required Skills */}
        <div>
          <label htmlFor="requiredSkills" className="block text-sm font-medium text-gray-700">
            Required Skills (Comma Separated)
          </label>
          <input
            type="text"
            name="requiredSkills"
            id="requiredSkills"
            value={formData.requiredSkills}
            onChange={handleChange}
            required
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm transition duration-150 ease-in-out"
            placeholder="e.g., React, JavaScript, Tailwind CSS, API Integration"
          />
        </div>

        {/* 4. Experience */}
        <div>
          <label htmlFor="experience" className="block text-sm font-medium text-gray-700">
            Required Experience
          </label>
          <input
            type="text"
            name="experience"
            id="experience"
            value={formData.experience}
            onChange={handleChange}
            required
            className="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm transition duration-150 ease-in-out"
            placeholder="e.g., 3+ Years, Entry Level, Senior"
          />
        </div>

        {/* Submission Button */}
        <div>
          <button
            type="submit"
            className="w-full flex justify-center py-3 px-4 border border-transparent rounded-md shadow-sm text-lg font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition duration-150 ease-in-out"
          >
            Publish Job Post
          </button>
        </div>
      </form>
    </div>
   </>
  )
}

export default HiringPostForm
