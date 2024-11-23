import React, { useState, useEffect } from 'react';
import { motion, useScroll, useTransform, AnimatePresence } from 'framer-motion';
import { NavLink, useLocation } from 'react-router-dom';
import { Compass, Menu, X } from 'lucide-react';
import { useTheme } from '../context/ThemeContext';

export const Navbar: React.FC = () => {
  const [isOpen, setIsOpen] = useState(false);
  const { scrollY } = useScroll();
  const location = useLocation();
  const { theme } = useTheme();
  
  // Increased transparency in both initial and scrolled states
  const lightBackground = useTransform(
    scrollY,
    [0, 100],
    ['rgba(255, 255, 255, 0)', 'rgba(255, 255, 255, 0.6)']
  );

  const darkBackground = useTransform(
    scrollY,
    [0, 100],
    ['rgba(15, 23, 42, 0)', 'rgba(15, 23, 42, 0.7)']
  );

  // Close mobile menu on route change or resize
  useEffect(() => {
    const handleResize = () => {
      if (window.innerWidth >= 768) {
        setIsOpen(false);
      }
    };

    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  useEffect(() => {
    setIsOpen(false);
  }, [location]);

  // Prevent scroll when mobile menu is open
  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'unset';
    }
    return () => {
      document.body.style.overflow = 'unset';
    };
  }, [isOpen]);

  const navItems = [
    { path: '/', label: 'Home' },
    { path: '/pricing', label: 'Pricing' },
    { path: '/about', label: 'About' },
    { path: '/contact', label: 'Contact' },
  ];

  const activeClass = "text-primary-600 dark:text-primary-400 font-semibold";
  const inactiveClass = "text-gray-800 dark:text-gray-100 hover:text-primary-600 dark:hover:text-primary-400";

  return (
    <>
      <motion.nav
        style={{ 
          backgroundColor: theme === 'dark' ? darkBackground : lightBackground,
          backdropFilter: 'blur(6px)',  // Reduced blur for more transparency
        }}
        className="fixed w-full z-50 border-b border-gray-200/5 dark:border-gray-800/5"
      >
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between h-16">
            <NavLink to="/" className="flex items-center space-x-2 z-50">
              <Compass className="h-8 w-8 text-primary-600 dark:text-primary-400" />
              <span className="font-bold text-xl text-gray-900 dark:text-white">OceanVista</span>
            </NavLink>

            {/* Desktop Navigation */}
            <div className="hidden md:flex items-center space-x-8">
              {navItems.map(({ path, label }) => (
                <NavLink
                  key={path}
                  to={path}
                  className={({ isActive }) => 
                    `font-medium transition-colors duration-200 ${isActive ? activeClass : inactiveClass}`
                  }
                >
                  {label}
                </NavLink>
              ))}
              <NavLink
                to="/login"
                className="px-4 py-2 rounded-full bg-primary-600/90 text-white hover:bg-primary-700 
                  dark:bg-primary-500/90 dark:hover:bg-primary-600 transition-colors duration-200"
              >
                Sign In
              </NavLink>
            </div>

            {/* Mobile menu button */}
            <button
              onClick={() => setIsOpen(!isOpen)}
              className="md:hidden rounded-md p-2 text-gray-800 dark:text-white 
                hover:bg-gray-100/50 dark:hover:bg-gray-800/30 transition-colors duration-200 z-50"
              aria-label="Toggle menu"
            >
              {isOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
            </button>
          </div>
        </div>
      </motion.nav>

      {/* Mobile Navigation Overlay */}
      <AnimatePresence>
        {isOpen && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            transition={{ duration: 0.2 }}
            className="fixed inset-0 bg-black bg-opacity-30 backdrop-blur-sm z-40 md:hidden"
            onClick={() => setIsOpen(false)}
          >
            <motion.div
              initial={{ x: '100%' }}
              animate={{ x: 0 }}
              exit={{ x: '100%' }}
              transition={{ type: 'tween', duration: 0.3 }}
              className="fixed right-0 top-0 bottom-0 w-3/4 max-w-sm bg-white/90 dark:bg-slate-900/90 
                backdrop-blur-md shadow-xl flex flex-col overflow-y-auto"
              onClick={e => e.stopPropagation()}
            >
              <div className="flex flex-col space-y-4 p-6 mt-16">
                {navItems.map(({ path, label }) => (
                  <NavLink
                    key={path}
                    to={path}
                    className={({ isActive }) =>
                      `px-4 py-3 rounded-md font-medium text-lg ${
                        isActive ? activeClass : inactiveClass
                      }`
                    }
                    onClick={() => setIsOpen(false)}
                  >
                    {label}
                  </NavLink>
                ))}
                <NavLink
                  to="/login"
                  className="mt-4 px-4 py-3 rounded-full bg-primary-600/90 text-white hover:bg-primary-700 
                    dark:bg-primary-500/90 dark:hover:bg-primary-600 transition-colors duration-200 text-center text-lg"
                  onClick={() => setIsOpen(false)}
                >
                  Sign In
                </NavLink>
              </div>
            </motion.div>
          </motion.div>
        )}
      </AnimatePresence>
    </>
  );
};

export default Navbar;