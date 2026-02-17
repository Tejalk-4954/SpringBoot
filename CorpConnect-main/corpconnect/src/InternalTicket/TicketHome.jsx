import React from 'react'
import BoxLink from '../Components/BoxLink'

const TicketHome = () => {
  return (
    <div className='w-full h-full'>
      
      <div className="p-4 md:p-10">
      
      <div className="flex justify-center items-center gap-8 max-w-4xl mx-auto h-full">
        
        
        <div className="flex-1">
          <BoxLink 
            href="/internal/ticket/facingIssue/form" 
            title="I am facing issue" 
           description={"Account Update, Hardware Request, Training Request, Access Permission, Data Extraction."}
            color="bg-blue-500" 
          />
        </div>

        <div className="flex-1">
          <BoxLink 
            href="/profile" 
            title="I need new service" 
           description={"Process Improvement, New Feature/Product Idea, Technical Consultation, Integration Advice, Best Practices."}
            color="bg-green-500" 
          />
        </div>

        <div className="flex-1">
          <BoxLink 
            href="/internal/ticket/service" 
            title="I am looking for solution" 
            description={"Dashboard, Login/Authentication, Payment Gateway, Reporting Module, Mobile App."}
            color="bg-purple-500" 
          />
        </div>
      </div>
    </div>
    </div>
  )
}

export default TicketHome
