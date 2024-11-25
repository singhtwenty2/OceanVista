import React, { useState } from 'react';
import { LogOut, User, Settings, HelpCircle, Mail, Shield } from 'lucide-react';
import { motion, AnimatePresence } from 'framer-motion';
import { useAuth } from '../../utils/authToken';
import { useUserProfile } from '../../utils/useUserProfile';
import { useNavigate } from 'react-router-dom';

export const UserProfile: React.FC = () => {
  const [isOpen, setIsOpen] = useState(false);
  const { clearToken } = useAuth();
  const { user, loading, error } = useUserProfile();
  const navigate = useNavigate();

  const menuVariants = {
    hidden: { opacity: 0, scale: 0.95, y: 10 },
    visible: {
      opacity: 1,
      scale: 1,
      y: 0,
      transition: {
        duration: 0.2,
        ease: 'easeOut'
      }
    },
    exit: {
      opacity: 0,
      scale: 0.95,
      y: 10,
      transition: {
        duration: 0.15,
        ease: 'easeIn'
      }
    }
  };

  const itemVariants = {
    hidden: { opacity: 0, x: -10 },
    visible: {
      opacity: 1,
      x: 0,
      transition: {
        duration: 0.2
      }
    }
  };

  if (loading) {
    return (
      <div className="relative flex items-center justify-center">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-gray-900 dark:border-white"></div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-red-500 text-sm">
        Unable to load user profile
      </div>
    );
  }

  return (
    <div className="relative">
      <motion.button
        onClick={() => setIsOpen(!isOpen)}
        whileHover={{ scale: 1.02 }}
        whileTap={{ scale: 0.98 }}
        className="flex items-center gap-3 p-2 hover:bg-gray-100/80 dark:hover:bg-slate-700/80 
          rounded-lg transition-all duration-200"
      >
        <motion.img
          src="/avatar.png"
          alt="User"
          className="w-8 h-8 rounded-full ring-2 ring-gray-100 dark:ring-gray-700"
          whileHover={{ scale: 1.05 }}
          transition={{ duration: 0.2 }}
        />
        <div className="hidden md:block text-left">
          <p className="text-sm font-medium text-gray-900 dark:text-white">
            {user?.name}
          </p>
          <p className="text-xs text-gray-500 dark:text-gray-400">
            {user?.partnerType.replace('_', ' ')}
          </p>
        </div>
      </motion.button>

      <AnimatePresence>
        {isOpen && (
          <>
            <motion.div
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              className="fixed inset-0 z-30"
              onClick={() => setIsOpen(false)}
            />
            <motion.div
              variants={menuVariants}
              initial="hidden"
              animate="visible"
              exit="exit"
              className="absolute right-0 mt-2 w-80 bg-white/95 dark:bg-slate-800/95 
                rounded-xl shadow-lg border border-gray-200/50 dark:border-gray-700/50 
                backdrop-blur-md z-40 overflow-hidden"
            >
              <motion.div
                variants={itemVariants}
                className="px-4 py-3 border-b border-gray-200/50 dark:border-gray-700/50"
              >
                <div className="flex items-center gap-3">
                  <img
                    src="/avatar.png"
                    alt={user?.name}
                    className="w-16 h-16 rounded-full ring-2 ring-gray-100 dark:ring-gray-700"
                  />
                  <div>
                    <p className="text-base font-medium text-gray-900 dark:text-white">
                      {user?.name}
                    </p>
                    <p className="text-sm text-gray-500 dark:text-gray-400">
                      {user?.partnerType.replace('_', ' ')}
                    </p>
                    <p className="text-sm text-gray-500 dark:text-gray-400">
                      {user?.email}
                    </p>
                  </div>
                </div>
              </motion.div>

              <div className="py-2">
                <motion.div variants={itemVariants}>
                  <button
                    onClick={() => navigate('/dashboard/account/details')}
                    className="w-full px-4 py-2.5 text-left text-sm text-gray-700 dark:text-gray-300 
                    hover:bg-gray-100/80 dark:hover:bg-slate-700/80 flex   items-center gap-3 transition-colors duration-200"
                  >
                    <User className="w-4 h-4" />
                    Manage your Account
                  </button>
                  <button className="w-full px-4 py-2.5 text-left text-sm text-gray-700 dark:text-gray-300 
                    hover:bg-gray-100/80 dark:hover:bg-slate-700/80 flex items-center gap-3 transition-colors duration-200">
                    <Shield className="w-4 h-4" />
                    Privacy & Security
                  </button>
                  <button className="w-full px-4 py-2.5 text-left text-sm text-gray-700 dark:text-gray-300 
                    hover:bg-gray-100/80 dark:hover:bg-slate-700/80 flex items-center gap-3 transition-colors duration-200">
                    <Settings className="w-4 h-4" />
                    Settings & Preferences
                  </button>
                </motion.div>
              </div>

              <div className="py-2 border-t border-gray-200/50 dark:border-gray-700/50">
                <motion.div variants={itemVariants}>
                  <button className="w-full px-4 py-2.5 text-left text-sm text-gray-700 dark:text-gray-300 
                    hover:bg-gray-100/80 dark:hover:bg-slate-700/80 flex items-center gap-3 transition-colors duration-200">
                    <HelpCircle className="w-4 h-4" />
                    Help & Support Center
                  </button>
                  <button className="w-full px-4 py-2.5 text-left text-sm text-gray-700 dark:text-gray-300 
                    hover:bg-gray-100/80 dark:hover:bg-slate-700/80 flex items-center gap-3 transition-colors duration-200">
                    <Mail className="w-4 h-4" />
                    Feedback & Suggestions
                  </button>
                </motion.div>
              </div>

              <motion.div
                variants={itemVariants}
                className="border-t border-gray-200/50 dark:border-gray-700/50 py-2"
              >
                <button
                  className="w-full px-4 py-2.5 text-left text-sm text-red-600 hover:bg-gray-100/80 
                    dark:hover:bg-slate-700/80 flex items-center gap-3 transition-colors duration-200"
                  onClick={clearToken}
                >
                  <LogOut className="w-4 h-4" />
                  Sign out of account
                </button>
              </motion.div>
            </motion.div>
          </>
        )}
      </AnimatePresence>
    </div>
  );
};