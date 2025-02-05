import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import {
  LayoutDashboard, BarChart3, Settings,
  Calendar,
  Store, Wallet, MessageSquare,
  CreditCard
} from 'lucide-react';

// Utility function to handle external navigation
const handleExternalNavigation = (path: string) => {
  if (path === '/dashboard/analytics') {
    window.open('http://localhost:3000', '_blank');
    return true;
  }
  return false;
};

// Updated sidebar links with isExternal property
export const sidebarLinks = [
  { icon: LayoutDashboard, label: 'Overview', path: '/dashboard' },
  { icon: BarChart3, label: 'Analytics', path: '/dashboard/analytics', isExternal: true },
  { icon: Store, label: 'My Business', path: '/dashboard/business' },
  { icon: Calendar, label: 'Bookings', path: '/dashboard/bookings' },
  { icon: Wallet, label: 'Earnings', path: '/dashboard/earnings' },
  { icon: MessageSquare, label: 'Messages', path: '/dashboard/messages' },
  { icon: CreditCard, label: 'Subscription', path: '/dashboard/subscription' },
  { icon: Settings, label: 'Settings', path: '/dashboard/settings' },
];

export const Sidebar: React.FC<{
  isCollapsed: boolean;
  isMobile?: boolean;
  isOpen?: boolean;
  onClose?: () => void;
}> = ({ isCollapsed, isMobile = false, isOpen = false, onClose }) => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleNavigation = (link: typeof sidebarLinks[0], event: React.MouseEvent) => {
    if (link.isExternal) {
      event.preventDefault();
      handleExternalNavigation(link.path);
      if (isMobile && onClose) {
        onClose();
      }
    }
  };

  return (
    <motion.aside
      initial={false}
      animate={{
        width: isCollapsed ? '5rem' : '16rem',
        x: isMobile ? (isOpen ? 0 : '-100%') : 0
      }}
      className={`
        fixed h-screen top-0 left-0 z-40
        bg-white dark:bg-slate-800/95 
        border-r border-gray-200 dark:border-slate-700
        overflow-y-auto scrollbar-thin scrollbar-thumb-gray-300 dark:scrollbar-thumb-gray-600
        transition-transform duration-300 ease-in-out
        backdrop-blur-sm supports-[backdrop-filter]:bg-white/60
        ${isMobile ? 'shadow-xl' : ''}
      `}
    >
      {/* Logo Section */}
      <div className="h-16 flex items-center justify-center border-b border-gray-200 dark:border-slate-700 px-4">
        <motion.div
          initial={false}
          animate={{ scale: isCollapsed ? 0.9 : 1 }}
          className="flex items-center gap-2"
        >
          <div className="w-8 h-8 bg-primary-500 rounded-lg flex items-center justify-center">
            <span className="text-lg font-bold text-white">
              {isCollapsed ? "S" : "SM"}
            </span>
          </div>
          {!isCollapsed && (
            <motion.span
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              className="text-lg font-semibold text-gray-900 dark:text-white"
            >
              ServiceHub
            </motion.span>
          )}
        </motion.div>
      </div>

      {/* Navigation */}
      <nav className="p-4 space-y-1">
        {sidebarLinks.map((link) => (
          <Link
            key={link.path}
            to={link.path}
            onClick={(e) => handleNavigation(link, e)}
            className={`
              flex items-center gap-3 px-4 py-3 rounded-lg
              transition-all duration-200 group
              ${location.pathname === link.path
                ? 'bg-primary-50 dark:bg-primary-900/20 text-primary-600 dark:text-primary-400 shadow-sm'
                : 'text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-slate-700/50'
              }
              ${link.isExternal ? 'cursor-pointer hover:bg-blue-50 dark:hover:bg-blue-900/20' : ''}
            `}
          >
            <link.icon className={`
              w-5 h-5 flex-shrink-0
              transition-transform duration-200
              ${location.pathname === link.path
                ? 'transform scale-110'
                : 'group-hover:scale-105'
              }
            `} />
            {(!isCollapsed || isMobile) && (
              <motion.span
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                className="font-medium whitespace-nowrap flex items-center gap-2"
              >
                {link.label}
                {link.isExternal && (
                  <svg
                    className="w-3 h-3 opacity-60"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14"
                    />
                  </svg>
                )}
              </motion.span>
            )}
          </Link>
        ))}
      </nav>

      {/* Pro Badge */}
      {!isCollapsed && (
        <div className="p-4 mt-4">
          <div className="p-4 rounded-lg bg-gradient-to-br from-primary-500 to-primary-600 text-white">
            <p className="text-sm font-semibold">ServiceHub Pro</p>
            <p className="text-xs mt-1 opacity-90">Access advanced features</p>
          </div>
        </div>
      )}
    </motion.aside>
  );
};

export default Sidebar;