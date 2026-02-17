import React, { useState } from 'react';

// The data structure remains the same
const faqs = [
  { 
    question: "How do I request time off?", 
    answer: "Access the **HR Portal** via the internal network. Navigate to the 'Time & Attendance' section and fill out the 'Leave Request Form'. Submit it at least two weeks in advance." 
  },
  { 
    question: "What is the procedure for reporting a technical issue?", 
    answer: "Submit a ticket through the **IT Helpdesk System** accessible from your desktop shortcut or internal URL. For urgent issues (e.g., system down), call the IT hotline at **x5555**." 
  },
  { 
    question: "Where can I find the latest company policy manual?", 
    answer: "The most current version is located in the **'Policies & Procedures' folder** on the shared company drive (Drive S:). Always refer to the one with the latest date stamp." 
  },
  { 
    question: "Who is the contact person for payroll inquiries?", 
    answer: "For questions regarding paychecks, deductions, or direct deposit, contact **Maria Rodriguez** in the Finance Department at **maria.rodriguez@company.com**." 
  },
  { 
    question: "How do I expense a business purchase?", 
    answer: "Complete the **Expense Report Form** found on the Finance Department's intranet page. Attach all original receipts and submit to your direct manager for approval by the 15th of the month." 
  },
  { 
    question: "What should I do if a conflict arises with a co-worker?", 
    answer: "First, try to resolve the issue directly and respectfully with the co-worker. If that fails, contact your **direct manager** or **Human Resources** to mediate the situation confidentially." 
  },
  { 
    question: "When is the next company-wide meeting or town hall?", 
    answer: "Upcoming dates are posted on the **Internal Communications SharePoint site** and the details are typically sent out via an all-staff email from the CEO's office one week prior." 
  },
  { 
    question: "How can I update my personal contact information?", 
    answer: "Log into the **HR Self-Service Platform**. Go to 'My Profile' and update your address, phone number, or emergency contacts. This ensures you receive important documents." 
  },
  { 
    question: "Is there a dress code, and what are the guidelines?", 
    answer: "The dress code is **Business Casual** Monday-Thursday. **Casual Friday** allows for jeans (no shorts, tank tops, or athletic wear). Consult the HR manual for specifics." 
  },
  { 
    question: "How do I book a conference room for a meeting?", 
    answer: "Use the **Outlook Calendar** system. Create a new meeting invitation and add the desired conference room (e.g., 'Conf Room A - 3rd Floor') as a required attendee." 
  },
];

const ServiceChatFaq = () => {
  // State to track which FAQ item is currently open. -1 means none are open.
  const [openIndex, setOpenIndex] = useState(-1);

  const toggleFAQ = (index) => {
    // If the clicked index is already open, close it (-1).
    // Otherwise, set the clicked index as the new open index.
    setOpenIndex(openIndex === index ? -1 : index);
  };

  return (
    <div className="max-w-4xl mx-auto p-6 bg-white shadow-2xl rounded-xl">
      <h2 className="text-3xl font-extrabold text-gray-900 mb-8 border-b-4 border-indigo-600 pb-2">
        Employee Frequently Asked Questions (FAQ)
      </h2>

      <div className="space-y-4">
        {faqs.map((item, index) => {
          const isOpen = index === openIndex;

          return (
            <div 
              key={index} 
              className="border border-gray-200 rounded-lg overflow-hidden transition-shadow duration-300 hover:shadow-md"
            >
              {/* Question Header (Toggle Button) */}
              <button
                className={`flex justify-between items-center w-full p-5 text-left font-semibold transition-colors duration-300 ${
                  isOpen ? 'bg-indigo-50 text-indigo-700' : 'bg-gray-50 text-gray-800 hover:bg-gray-100'
                }`}
                onClick={() => toggleFAQ(index)}
                aria-expanded={isOpen}
              >
                <span className="text-lg">{item.question}</span>
                {/* Plus/Minus Icon */}
                <svg
                  className={`w-5 h-5 transition-transform duration-300 ${isOpen ? 'rotate-180 text-indigo-700' : 'text-gray-500'}`}
                  fill="none"
                  stroke="currentColor"
                  viewBox="0 0 24 24"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d={isOpen ? "M18 12H6" : "M12 6v6m0 0v6m0-6h6m-6 0H6"}></path>
                </svg>
              </button>

              {/* Answer Content (Dropdown) */}
              <div
                className={`transition-all duration-500 ease-in-out overflow-hidden ${
                  isOpen ? 'max-h-96 opacity-100 p-5' : 'max-h-0 opacity-0 p-0'
                }`}
              >
                {/* Only render content when open to avoid text overflow issues when max-h is zero */}
                {isOpen && (
                  <p 
                    className="text-gray-600 leading-relaxed pt-2 border-t border-gray-100"
                    // Warning: dangerouslySetInnerHTML is used here to render the bold markdown (**text**)
                    // This is generally safe for static, internal content like this.
                    dangerouslySetInnerHTML={{ __html: item.answer.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>') }}
                  />
                )}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default ServiceChatFaq;