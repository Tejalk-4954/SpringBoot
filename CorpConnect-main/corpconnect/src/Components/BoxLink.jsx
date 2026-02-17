import React from "react";

const BoxLink = ({ href, title, description, color }) => {
  return (
    // We wrap the entire content in an anchor tag and use block display 
    // to make the whole div clickable.
    <a 
      href={href} 
      // Apply flex styles to the anchor itself
      className={`flex flex-col items-center justify-center p-6 rounded-lg shadow-lg 
                  aspect-square w-full transition duration-300 transform hover:scale-105 
                  ${color} text-white hover:shadow-xl`}
    >
      <h3 className="text-xl font-bold mb-2">{title}</h3>
      <p className="text-center text-sm opacity-90">{description}</p>
    </a>
  );
};

export default BoxLink;