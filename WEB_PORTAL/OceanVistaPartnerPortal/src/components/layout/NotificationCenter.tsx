import React, { useState } from 'react';
import { Bell, X, Settings, CheckCheck, Package, CreditCard, Shield, Mail, Calendar, User } from 'lucide-react';
import { motion, AnimatePresence } from 'framer-motion';

export const NotificationCenter: React.FC = () => {
  const [isOpen, setIsOpen] = useState(false);

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

  // Sample notifications data
  const notifications = [
    {
      id: 1,
      icon: <Package className="w-4 h-4" />,
      title: "New Resort Booking Received",
      description: "John Smith booked Ocean View Suite for 5 nights",
      time: "2 minutes ago",
      type: "new"
    },
    {
      id: 2,
      icon: <CreditCard className="w-4 h-4" />,
      title: "Payment Successfully Processed",
      description: "Payment of $1,299 received for booking #12345",
      time: "15 minutes ago",
      type: "success"
    },
    {
      id: 3,
      icon: <Calendar className="w-4 h-4" />,
      title: "Upcoming Check-in Reminder",
      description: "2 guests checking in tomorrow at 2:00 PM",
      time: "1 hour ago",
      type: "reminder"
    },
    {
      id: 4,
      icon: <Shield className="w-4 h-4" />,
      title: "Security Alert",
      description: "New device signed in to your account",
      time: "2 hours ago",
      type: "alert"
    },
    {
      id: 5,
      icon: <Mail className="w-4 h-4" />,
      title: "New Review Received",
      description: "A guest left a 5-star review for their stay",
      time: "3 hours ago",
      type: "new"
    }
  ];

  return (
    <div className="relative">
      <motion.button
        onClick={() => setIsOpen(!isOpen)}
        whileHover={{ scale: 1.02 }}
        whileTap={{ scale: 0.98 }}
        className="p-2 rounded-lg text-gray-600 dark:text-gray-300
          hover:bg-gray-100/80 dark:hover:bg-gray-800/80 transition-colors duration-200 relative"
        aria-label="Notifications"
      >
        <Bell className="w-5 h-5" />
        <span className="absolute top-2 right-2 w-2 h-2 bg-primary-500 rounded-full ring-2 ring-white dark:ring-slate-900" />
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
              className="absolute right-0 mt-2 w-96 bg-white/95 dark:bg-slate-800/95 
                rounded-xl shadow-lg border border-gray-200/50 dark:border-gray-700/50 
                backdrop-blur-md z-40 overflow-hidden"
            >
              <motion.div 
                variants={itemVariants}
                className="px-4 py-3 border-b border-gray-200/50 dark:border-gray-700/50 flex items-center justify-between"
              >
                <div>
                  <h2 className="text-base font-medium text-gray-900 dark:text-white">
                    Notifications
                  </h2>
                  <p className="text-sm text-gray-500 dark:text-gray-400">
                    You have 5 new notifications
                  </p>
                </div>
                <button 
                  onClick={() => setIsOpen(false)}
                  className="p-1 rounded-lg hover:bg-gray-100/80 dark:hover:bg-slate-700/80 transition-colors duration-200"
                >
                  <X className="w-5 h-5 text-gray-500 dark:text-gray-400" />
                </button>
              </motion.div>

              <div className="divide-y divide-gray-200/50 dark:divide-gray-700/50 max-h-[450px] overflow-y-auto">
                {notifications.map((notification, index) => (
                  <motion.div
                    key={notification.id}
                    variants={itemVariants}
                    custom={index}
                    className="p-4 hover:bg-gray-100/80 dark:hover:bg-slate-700/80 transition-colors duration-200 cursor-pointer"
                  >
                    <div className="flex gap-3">
                      <div className={`mt-1 rounded-full p-2 ${
                        notification.type === 'new' ? 'bg-blue-100 text-blue-600 dark:bg-blue-900/50 dark:text-blue-400' :
                        notification.type === 'success' ? 'bg-green-100 text-green-600 dark:bg-green-900/50 dark:text-green-400' :
                        notification.type === 'alert' ? 'bg-red-100 text-red-600 dark:bg-red-900/50 dark:text-red-400' :
                        'bg-gray-100 text-gray-600 dark:bg-gray-900/50 dark:text-gray-400'
                      }`}>
                        {notification.icon}
                      </div>
                      <div className="flex-1">
                        <h3 className="text-sm font-medium text-gray-900 dark:text-white">
                          {notification.title}
                        </h3>
                        <p className="text-sm text-gray-500 dark:text-gray-400 mt-0.5">
                          {notification.description}
                        </p>
                        <p className="text-xs text-gray-400 dark:text-gray-500 mt-1">
                          {notification.time}
                        </p>
                      </div>
                    </div>
                  </motion.div>
                ))}
              </div>

              <motion.div 
                variants={itemVariants}
                className="border-t border-gray-200/50 dark:border-gray-700/50 p-4"
              >
                <div className="flex justify-between items-center">
                  <button className="text-sm text-primary-600 hover:text-primary-700 dark:text-primary-400 dark:hover:text-primary-300 transition-colors duration-200 flex items-center gap-2">
                    <CheckCheck className="w-4 h-4" />
                    Mark all as read
                  </button>
                  <button className="text-sm text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-300 transition-colors duration-200 flex items-center gap-2">
                    <Settings className="w-4 h-4" />
                    Notification settings
                  </button>
                </div>
              </motion.div>
            </motion.div>
          </>
        )}
      </AnimatePresence>
    </div>
  );
};