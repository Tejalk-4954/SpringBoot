// tailwind.config.js
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        lightBg: "#f9f9f9",   // background color for light mode
        primary: "#646cff",   // main accent color
        secondary: "#535bf2", // hover accent
        darkBg: "#242424",    // background for dark mode
      },
      fontFamily: {
        inter: ["Inter", "sans-serif"],
      },
    },
  },
  plugins: [],
}
