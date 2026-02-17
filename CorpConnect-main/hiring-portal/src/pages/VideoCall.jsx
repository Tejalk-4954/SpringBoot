import { useEffect, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";

export default function VideoCall() {
  const { roomId } = useParams();
  const containerRef = useRef(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (!roomId) {
      navigate("/manager");
      return;
    }

    // Load Jitsi Meet API script dynamically if not already loaded
    if (!window.JitsiMeetExternalAPI) {
      const script = document.createElement("script");
      script.src = "https://meet.jit.si/external_api.js";
      script.async = true;
      script.onload = startConference;
      document.body.appendChild(script);
    } else {
      startConference();
    }

    function startConference() {
      const domain = "meet.jit.si";
      const options = {
        roomName: roomId,
        parentNode: containerRef.current,
        width: "100%",
        height: 700,
        configOverwrite: {
          startWithAudioMuted: true,
          startWithVideoMuted: true,
        },
        interfaceConfigOverwrite: {
          filmStripOnly: false,
          SHOW_JITSI_WATERMARK: false,
          SHOW_WATERMARK_FOR_GUESTS: false,
          SHOW_BRAND_WATERMARK: false,
          TOOLBAR_BUTTONS: [
            "microphone",
            "camera",
            "desktop",
            "fullscreen",
            "hangup",
            "chat",
            "settings",
            "raisehand",
            "videoquality",
            "tileview",
          ],
        },
      };

      // Initialize JitsiMeetExternalAPI
      const api = new window.JitsiMeetExternalAPI(domain, options);

      // Optional: Handle events, e.g., when conference ends, redirect back
      api.addListener("readyToClose", () => {
        navigate("/manager");
      });
    }

    // Cleanup on unmount
    return () => {
      if (window.JitsiMeetExternalAPI) {
        window.JitsiMeetExternalAPI.dispose();
      }
    };
  }, [roomId, navigate]);

  return (
    <div className="max-w-6xl mx-auto p-4 mt-12">
      <h1 className="text-3xl font-bold text-blue-700 mb-4 text-center">Video Interview Room: {roomId}</h1>
      <div
        ref={containerRef}
        className="border border-gray-300 rounded-lg shadow-lg"
        style={{ height: 700, width: "100%" }}
      />
    </div>
  );
}
