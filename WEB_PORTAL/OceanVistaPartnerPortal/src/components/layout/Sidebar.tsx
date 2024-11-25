import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { motion } from 'framer-motion';
import { sidebarLinks } from './SidebarLinks';

export const Sidebar: React.FC<{ 
  isCollapsed: boolean;
  isMobile?: boolean;
  isOpen?: boolean;
  onClose?: () => void;
}> = ({ isCollapsed, isMobile = false, isOpen = false, onClose }) => { 
  const location = useLocation();

  return (
    <motion.aside
      initial={false}
      animate={{ 
        width: isCollapsed ? '5rem' : '16rem',
        x: isMobile ? (isOpen ? 0 : '-100%') : 0 
      }}
      className={`
        fixed h-screen top-0 left-0 z-40
        bg-white dark:bg-slate-800 
        border-r border-gray-200 dark:border-gray-700 
        overflow-y-auto scrollbar-thin scrollbar-thumb-gray-300 dark:scrollbar-thumb-gray-600
        transition-transform duration-300 ease-in-out
        ${isMobile ? 'shadow-lg' : ''}
      `}
    >
      {/* Logo or Brand Section - 4rem height to match topbar */}
      <div className="h-16 flex items-center justify-center border-b border-gray-200 dark:border-gray-700">
        <span className="text-xl font-bold text-primary-600 dark:text-primary-400">
          {isCollapsed ? "L" : "Logo"}
        </span>
      </div>

      <nav className="p-4 space-y-2">
        {sidebarLinks.map((link) => (
          <Link
            key={link.path}
            to={link.path}
            onClick={() => isMobile && onClose?.()}
            className={`flex items-center gap-3 px-4 py-3 rounded-lg transition-all duration-200 ${
              location.pathname === link.path
                ? 'bg-primary-50 dark:bg-primary-900/20 text-primary-600 dark:text-primary-400'
                : 'text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-slate-700'
            }`}
          >
            <link.icon className="w-5 h-5 flex-shrink-0" />
            {(!isCollapsed || isMobile) && (
              <motion.span
                initial={{ opacity: 0 }}
                animate={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                className="font-medium whitespace-nowrap"
              >
                {link.label}
              </motion.span>
            )}
          </Link>
        ))}
      </nav>
    </motion.aside>
  );
};