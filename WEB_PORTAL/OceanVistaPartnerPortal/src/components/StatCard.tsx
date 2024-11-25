import React, { useState, useRef, useEffect } from 'react';
import { motion } from 'framer-motion';
import { ArrowUp, ArrowDown } from 'lucide-react';

const StatCard = ({ stat }) => {
  const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 });
  const cardRef = useRef(null);

  const handleMouseMove = (e) => {
    if (!cardRef.current) return;
    
    const rect = cardRef.current.getBoundingClientRect();
    setMousePosition({
      x: e.clientX - rect.left,
      y: e.clientY - rect.top
    });
  };

  return (
    <motion.div
      ref={cardRef}
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      whileHover={{ 
        y: -5,
        transition: { duration: 0.2 }
      }}
      onMouseMove={handleMouseMove}
      className="relative bg-white dark:bg-slate-800 rounded-xl p-6 shadow-sm transition-all duration-200"
      style={{
        isolation: 'isolate'
      }}
    >
      {/* Animated glow effect */}
      <div
        className="absolute inset-0 -z-10 opacity-0 hover:opacity-100 transition-opacity duration-300"
        style={{
          background: `radial-gradient(circle 100px at ${mousePosition.x}px ${mousePosition.y}px, 
            rgba(59, 130, 246, 0.15), 
            transparent 40%
          )`
        }}
      />
      
      {/* Card content with enhanced styling */}
      <div className="flex justify-between items-start">
        <div className="space-y-2">
          <motion.span 
            initial={{ opacity: 0.8 }}
            whileHover={{ opacity: 1 }}
            className="text-sm font-medium text-gray-500 dark:text-gray-400"
          >
            {stat.label}
          </motion.span>
          <div className="flex items-baseline space-x-2">
            <motion.h3 
              initial={{ scale: 1 }}
              whileHover={{ scale: 1.02 }}
              className="text-2xl font-bold text-gray-900 dark:text-white"
            >
              {stat.value}
            </motion.h3>
            <motion.div 
              initial={{ opacity: 0.8 }}
              whileHover={{ opacity: 1 }}
              className="flex items-center space-x-1"
            >
              {stat.trendUp ? (
                <ArrowUp className="w-4 h-4 text-green-500" />
              ) : (
                <ArrowDown className="w-4 h-4 text-red-500" />
              )}
              <span className={`text-sm font-medium ${
                stat.trendUp ? 'text-green-500' : 'text-red-500'
              }`}>
                {stat.trend}
              </span>
            </motion.div>
          </div>
          <span className="text-xs text-gray-500 dark:text-gray-400">
            {stat.trendLabel}
          </span>
        </div>
        <motion.div 
          whileHover={{ scale: 1.05 }}
          className="p-3 bg-primary-50 dark:bg-primary-900/20 rounded-lg relative overflow-hidden"
        >
          <div className="absolute inset-0 bg-gradient-to-r from-transparent via-primary-200/10 to-transparent animate-shine" />
          <stat.icon className="w-6 h-6 text-primary-500" />
        </motion.div>
      </div>

      {/* Subtle border glow on hover */}
      <div className="absolute inset-0 rounded-xl transition-all duration-300 group-hover:shadow-lg group-hover:shadow-primary-500/25" />
    </motion.div>
  );
};

export default StatCard;