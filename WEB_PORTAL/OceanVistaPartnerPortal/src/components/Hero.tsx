import React, { useState, useEffect } from 'react';
import { motion } from 'framer-motion';
import { Anchor, Compass } from 'lucide-react';
import { Link } from 'react-router-dom';

export const Hero: React.FC = () => {
  const [isVideoLoaded, setIsVideoLoaded] = useState(false);
  const [currentVideoUrl, setCurrentVideoUrl] = useState('');

  // Array of carefully selected beach and ocean videos
  const videoUrls = [
    "https://videos.pexels.com/video-files/15030029/15030029-hd_1920_1080_24fps.mp4",
    "https://videos.pexels.com/video-files/4873241/4873241-uhd_2560_1440_25fps.mp4", 
    "https://videos.pexels.com/video-files/9324434/9324434-uhd_2560_1440_25fps.mp4", 
    "https://videos.pexels.com/video-files/14962940/14962940-hd_1920_1080_24fps.mp4", 
    "https://videos.pexels.com/video-files/1093652/1093652-uhd_2560_1440_30fps.mp4",
    "https://videos.pexels.com/video-files/15029730/15029730-hd_1920_1080_24fps.mp4" ,
    "https://videos.pexels.com/video-files/5085845/5085845-hd_720_992_30fps.mp4",
    "https://videos.pexels.com/video-files/11598248/11598248-hd_1920_1080_25fps.mp4",
    "https://videos.pexels.com/video-files/15029730/15029730-hd_1920_1080_24fps.mp4",
    "https://videos.pexels.com/video-files/1409899/1409899-uhd_2560_1440_25fps.mp4",
    "https://videos.pexels.com/video-files/1739010/1739010-hd_1920_1080_30fps.mp4",
    "https://videos.pexels.com/video-files/3115738/3115738-uhd_2560_1440_24fps.mp4"
  ];
  
  // Fallback image in case video fails to load
  const fallbackImageUrl = "https://images.pexels.com/photos/1680140/pexels-photo-1680140.jpeg?auto=compress&cs=tinysrgb&w=1920";

  // Function to get random video URL
  const getRandomVideo = () => {
    const randomIndex = Math.floor(Math.random() * videoUrls.length);
    return videoUrls[randomIndex];
  };

  // Set random video on initial load
  useEffect(() => {
    setCurrentVideoUrl(getRandomVideo());
  }, []);

  return (
    <section className="relative min-h-screen flex items-center justify-center overflow-hidden">
      <div className="absolute inset-0 z-0">
        {/* Loading state background */}
        <div 
          className={`absolute inset-0 bg-slate-900 transition-opacity duration-1000 ${isVideoLoaded ? 'opacity-0' : 'opacity-100'}`}
        />
        
        {currentVideoUrl && (
          <video
            key={currentVideoUrl} // Key ensures React recreates video element when URL changes
            autoPlay
            muted
            loop
            playsInline
            onLoadedData={() => setIsVideoLoaded(true)}
            poster={fallbackImageUrl}
            className={`w-full h-full object-cover transition-opacity duration-1000 ${isVideoLoaded ? 'opacity-100' : 'opacity-0'}`}
          >
            <source src={currentVideoUrl} type="video/mp4" />
            {/* Fallback message if video fails */}
            Your browser does not support the video tag.
          </video>
        )}

        {/* Gradient overlay - adjusted for better text visibility */}
        <div className="absolute inset-0 bg-gradient-to-b from-black/60 via-black/40 to-black/60" />
      </div>

      <div className="container mx-auto px-4 z-10">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
          className="text-center"
        >
          <motion.div
            initial={{ scale: 0 }}
            animate={{ scale: 1 }}
            transition={{ delay: 0.2, type: "spring", stiffness: 100 }}
            className="flex justify-center items-center mb-6"
          >
            <Compass className="w-16 h-16 text-white" />
          </motion.div>

          <motion.h1
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.4 }}
            className="text-5xl md:text-7xl font-bold text-white mb-6"
          >
            OceanVista
          </motion.h1>

          <motion.p
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.6 }}
            className="text-xl md:text-2xl text-white/90 mb-12 max-w-2xl mx-auto"
          >
            Your Safety, Our Priority. Experience the Perfect Balance of 
            Adventure and Security at Every Beach
          </motion.p>

          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.8 }}
            className="flex flex-col sm:flex-row gap-4 justify-center items-center"
          >
            <Link to="/signup">
              <motion.button
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                className="w-full sm:w-auto px-6 py-2 sm:px-8 sm:py-3 bg-white text-slate-900 rounded-full font-semibold flex items-center justify-center gap-2 hover:bg-white/90 transition-colors text-sm sm:text-base"
              >
                <Anchor className="w-5 h-5" />
                Partner with Us
              </motion.button>
            </Link>
            <Link to="/documentation">
              <motion.button
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
                className="w-full sm:w-auto px-6 py-2 sm:px-8 sm:py-3 bg-transparent border-2 border-white text-white rounded-full font-semibold hover:bg-white/10 transition-colors text-sm sm:text-base"
              >
                Learn More
              </motion.button>
            </Link>
          </motion.div>
        </motion.div>
      </div>

      <motion.div
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ delay: 1.2 }}
        className="absolute bottom-8 left-1/2 transform -translate-x-1/2"
      >
        <motion.div
          animate={{ y: [0, 10, 0] }}
          transition={{ repeat: Infinity, duration: 2 }}
          className="text-white/80"
        >
          <motion.div className="w-6 h-12 border-2 border-white/80 rounded-full flex justify-center">
            <motion.div className="w-1.5 h-1.5 bg-white/80 rounded-full mt-2" />
          </motion.div>
        </motion.div>
      </motion.div>
    </section>
  );
};
