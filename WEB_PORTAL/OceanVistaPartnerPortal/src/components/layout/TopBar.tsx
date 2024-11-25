import React from 'react';
import { Menu, Bell, Search, Sun, Moon, Compass } from 'lucide-react';
import { motion, useScroll, useTransform, AnimatePresence } from 'framer-motion';
import { UserProfile } from './UserProfile';
import { useTheme } from '../../context/ThemeContext';
import { NotificationCenter } from './NotificationCenter';
import { NavLink } from 'react-router-dom';

export const TopBar: React.FC<{
  isSidebarCollapsed: boolean;
  onToggleSidebar: () => void;
}> = ({ isSidebarCollapsed, onToggleSidebar }) => {
  const { theme, toggleTheme } = useTheme();
  const isDarkMode = theme === 'dark';
  const { scrollY } = useScroll();

  // More subtle background transparency
  const lightBackground = useTransform(
    scrollY,
    [0, 100],
    ['rgba(255, 255, 255, 0.6)', 'rgba(255, 255, 255, 0.8)']
  );

  const darkBackground = useTransform(
    scrollY,
    [0, 100],
    ['rgba(15, 23, 42, 0.6)', 'rgba(15, 23, 42, 0.8)']
  );

  // Subtle scale animation for buttons
  const buttonVariants = {
    hover: { scale: 1.05, transition: { duration: 0.2 } },
    tap: { scale: 0.95, transition: { duration: 0.1 } },
  };

  return (
    <motion.header
      initial={{ opacity: 0, y: -10 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.3 }}
      style={{
        backgroundColor: isDarkMode ? darkBackground : lightBackground,
        backdropFilter: 'blur(16px)',
      }}
      className="fixed top-0 right-0 h-16 border-b border-gray-200/5 dark:border-gray-800/5 z-50 w-full shadow-sm"
    >
      <div className="h-full px-6 flex items-center justify-between max-w-[2000px] mx-auto">
        {/* Left Section */}
        <div className="flex items-center gap-6">
          <motion.button
            variants={buttonVariants}
            whileHover="hover"
            whileTap="tap"
            onClick={onToggleSidebar}
            className="p-2 rounded-lg text-gray-600 dark:text-gray-300
              hover:bg-gray-100/80 dark:hover:bg-gray-800/80 transition-colors duration-200"
            aria-label={isSidebarCollapsed ? "Expand Sidebar" : "Collapse Sidebar"}
          >
            <Menu className="w-5 h-5" />
          </motion.button>

          <motion.div 
            className="flex items-center"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ duration: 0.3, delay: 0.1 }}
          >
            <NavLink to="/" className="flex items-center space-x-2 z-50">
              <Compass className="h-8 w-8 text-primary-600 dark:text-primary-400" />
              <span className="font-bold text-xl text-gray-900 dark:text-white">OceanVista</span>
            </NavLink>
          </motion.div>
        </div>

        {/* Center Section - Search */}
        <div className="flex-1 max-w-xl mx-8">
          <motion.div 
            className="relative"
            initial={{ opacity: 0, scale: 0.98 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.3, delay: 0.2 }}
          >
            <Search className="w-4 h-4 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
            <input
              type="text"
              placeholder="Search anything..."
              className="w-full pl-9 pr-4 py-2 bg-gray-50/50 dark:bg-slate-800/30
                border border-gray-200/50 dark:border-gray-700/30 rounded-lg
                focus:ring-2 focus:ring-primary-500/50 focus:border-transparent
                dark:text-white placeholder-gray-400 dark:placeholder-gray-500
                text-sm transition-all duration-300 hover:bg-gray-50/80 
                dark:hover:bg-slate-800/50"
            />
          </motion.div>
        </div>

        {/* Right Section */}
        <motion.div 
          className="flex items-center space-x-2"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 0.3, delay: 0.3 }}
        >
          <motion.button
            variants={buttonVariants}
            whileHover="hover"
            whileTap="tap"
            onClick={toggleTheme}
            className="p-2 rounded-lg text-gray-600 dark:text-gray-300
              hover:bg-gray-100/80 dark:hover:bg-gray-800/80 transition-colors duration-200"
            aria-label={isDarkMode ? "Switch to Light Mode" : "Switch to Dark Mode"}
          >
            <AnimatePresence mode="wait">
              <motion.div
                key={isDarkMode ? 'dark' : 'light'}
                initial={{ rotate: -180, opacity: 0 }}
                animate={{ rotate: 0, opacity: 1 }}
                exit={{ rotate: 180, opacity: 0 }}
                transition={{ duration: 0.3 }}
              >
                {isDarkMode ? (
                  <Sun className="w-5 h-5" />
                ) : (
                  <Moon className="w-5 h-5" />
                )}
              </motion.div>
            </AnimatePresence>
          </motion.button>

          <NotificationCenter />

          <div className="pl-2 ml-2 border-l border-gray-200/30 dark:border-gray-700/30">
            <UserProfile />
          </div>
        </motion.div>
      </div>
    </motion.header>
  );
};