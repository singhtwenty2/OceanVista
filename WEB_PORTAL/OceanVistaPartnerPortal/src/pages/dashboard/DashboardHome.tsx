import React, { useEffect, useState } from 'react';
import { motion } from 'framer-motion';
import { Plus } from 'lucide-react';
import {
  XAxis, YAxis, CartesianGrid,
  Tooltip, ResponsiveContainer, Area, AreaChart
} from 'recharts';
import { Sidebar } from '../../components/layout/Sidebar';
import { TopBar } from '../../components/layout/TopBar';
import Footer from '../Footer';
import StatCard from '../../components/StatCard';
import { stats } from '../../components/layout/Stats';
import { chartData } from '../../components/layout/ChartData';

export const DashboardHome: React.FC = () => {
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState(false);
  const [isMobileSidebarOpen, setIsMobileSidebarOpen] = useState(false);
  const [isMobile, setIsMobile] = useState(false);
  const [screenWidth, setScreenWidth] = useState(window.innerWidth);

  const handleSidebarToggle = () => {
    if (screenWidth < 768) {
      setIsMobileSidebarOpen(prev => !prev);
    } else {
      setIsSidebarCollapsed(prev => !prev);
    }
  };

  useEffect(() => {
    const checkMobile = () => {
      const width = window.innerWidth;
      setScreenWidth(width);
      setIsMobile(width < 768);
      
      // Auto-collapse sidebar on mobile
      if (width < 768) {
        setIsSidebarCollapsed(true);
        setIsMobileSidebarOpen(false);
      } else {
        setIsSidebarCollapsed(false);
      }
    };
    
    checkMobile();
    window.addEventListener('resize', checkMobile);
    
    return () => window.removeEventListener('resize', checkMobile);
  }, []);

  return (
    <div className="flex h-screen bg-gray-50 dark:bg-slate-900 overflow-hidden">
      {/* Mobile Sidebar Overlay */}
      {isMobile && isMobileSidebarOpen && (
        <div 
          className="fixed inset-0 bg-black bg-opacity-50 z-40"
          onClick={() => setIsMobileSidebarOpen(false)}
        />
      )}

      {/* Sidebar */}
      <Sidebar 
        isCollapsed={isSidebarCollapsed} 
        isMobile={isMobile}
        isOpen={isMobileSidebarOpen}
        onClose={() => setIsMobileSidebarOpen(false)}
        className={`${isMobile && isMobileSidebarOpen 
          ? 'fixed inset-y-0 left-0 z-50 w-64 transform transition-transform duration-300' 
          : ''}`}
      />

      {/* Main Content Wrapper */}
      <motion.div 
        initial={false}
        animate={{ 
          marginLeft: isMobile 
            ? '0' 
            : (isSidebarCollapsed ? '5rem' : '16rem'),
          opacity: isMobile && isMobileSidebarOpen ? 0.5 : 1
        }}
        className="flex-1 flex flex-col min-h-screen transition-all duration-300"
      >
        {/* TopBar */}
        <TopBar
          isSidebarCollapsed={isSidebarCollapsed}
          onToggleSidebar={handleSidebarToggle}
          className="sticky top-0 z-20 bg-white dark:bg-slate-800 border-b border-gray-200 dark:border-slate-700"
        />

        {/* Scrollable Content Area */}
        <div className={`flex-1 overflow-y-auto ${isMobile && isMobileSidebarOpen ? 'pointer-events-none' : ''}`}>
          <main className="p-4 md:p-8">
            <div className="max-w-7xl mx-auto space-y-6 md:space-y-8">
              {/* Header Section */}
              <div className="flex flex-col mt-4 md:mt-16 md:flex-row justify-between items-start md:items-center gap-4 mb-6 md:mb-8">
                <div className="space-y-1 w-full md:w-auto">
                  <h1 className="text-xl md:text-2xl font-bold text-gray-900 dark:text-white">
                    Dashboard Overview
                  </h1>
                  <p className="text-sm md:text-base text-gray-600 dark:text-gray-400">
                    Welcome back, John! Here's what's happening with your business.
                  </p>
                </div>
                <motion.button
                  whileHover={{ scale: 1.02 }}
                  whileTap={{ scale: 0.98 }}
                  className="w-full md:w-auto px-4 py-2 bg-primary-500 text-white rounded-lg hover:bg-primary-600 transition-colors duration-200 flex items-center justify-center md:justify-start gap-2 shadow-sm"
                >
                  <Plus className="w-5 h-5" />
                  Add New Service
                </motion.button>
              </div>

              {/* Stats Grid */}
              <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 md:gap-6 mb-6 md:mb-8">
                {stats.map((stat) => (
                  <StatCard key={stat.label} stat={stat} />
                ))}
              </div>

              {/* Charts Section */}
              <div className="grid grid-cols-1 lg:grid-cols-2 gap-4 md:gap-6">
                <motion.div
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  className="bg-white dark:bg-slate-800 rounded-xl p-4 md:p-6 shadow-sm"
                >
                  <h3 className="text-base md:text-lg font-semibold text-gray-900 dark:text-white mb-4 md:mb-6">
                    Booking Trends
                  </h3>
                  <div className="h-60 md:h-80">
                    <ResponsiveContainer width="100%" height="100%">
                      <AreaChart data={chartData}>
                        <defs>
                          <linearGradient id="bookingGradient" x1="0" y1="0" x2="0" y2="1">
                            <stop offset="5%" stopColor="#3B82F6" stopOpacity={0.2}/>
                            <stop offset="95%" stopColor="#3B82F6" stopOpacity={0}/>
                          </linearGradient>
                        </defs>
                        <CartesianGrid strokeDasharray="3 3" stroke="#374151" opacity={0.1} />
                        <XAxis 
                          dataKey="name" 
                          stroke="#6B7280"
                          tick={{ fontSize: 12 }}
                          tickMargin={8}
                        />
                        <YAxis 
                          stroke="#6B7280"
                          tick={{ fontSize: 12 }}
                          tickMargin={8}
                        />
                        <Tooltip 
                          contentStyle={{ 
                            backgroundColor: 'rgb(30 41 59)',
                            border: 'none',
                            borderRadius: '8px',
                            color: '#fff',
                            fontSize: '12px'
                          }}
                        />
                        <Area
                          type="monotone"
                          dataKey="bookings"
                          stroke="#3B82F6"
                          fill="url(#bookingGradient)"
                          strokeWidth={2}
                        />
                      </AreaChart>
                    </ResponsiveContainer>
                  </div>
                </motion.div>

                <motion.div
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  className="bg-white dark:bg-slate-800 rounded-xl p-4 md:p-6 shadow-sm"
                >
                  <h3 className="text-base md:text-lg font-semibold text-gray-900 dark:text-white mb-4 md:mb-6">
                    Revenue Overview
                  </h3>
                  <div className="h-60 md:h-80">
                    <ResponsiveContainer width="100%" height="100%">
                      <AreaChart data={chartData}>
                        <defs>
                          <linearGradient id="revenueGradient" x1="0" y1="0" x2="0" y2="1">
                            <stop offset="5%" stopColor="#10B981" stopOpacity={0.2}/>
                            <stop offset="95%" stopColor="#10B981" stopOpacity={0}/>
                          </linearGradient>
                        </defs>
                        <CartesianGrid strokeDasharray="3 3" stroke="#374151" opacity={0.1} />
                        <XAxis 
                          dataKey="name" 
                          stroke="#6B7280"
                          tick={{ fontSize: 12 }}
                          tickMargin={8}
                        />
                        <YAxis 
                          stroke="#6B7280"
                          tick={{ fontSize: 12 }}
                          tickMargin={8}
                        />
                        <Tooltip 
                          contentStyle={{ 
                            backgroundColor: 'rgb(30 41 59)',
                            border: 'none',
                            borderRadius: '8px',
                            color: '#fff',
                            fontSize: '12px'
                          }}
                        />
                        <Area
                          type="monotone"
                          dataKey="revenue"
                          stroke="#10B981"
                          fill="url(#revenueGradient)"
                          strokeWidth={2}
                        />
                      </AreaChart>
                    </ResponsiveContainer>
                  </div>
                </motion.div>
              </div>

              {/* Recent Activity */}
              <div className="mb-6 md:mb-8">
                <motion.div
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  className="bg-white dark:bg-slate-800 rounded-xl p-4 md:p-6 shadow-sm"
                >
                  <h3 className="text-base md:text-lg font-semibold text-gray-900 dark:text-white mb-4 md:mb-6">
                    Recent Activity
                  </h3>
                  <div className="space-y-3 md:space-y-4">
                    {[1, 2, 3, 4, 5].map((_, index) => (
                      <motion.div
                        key={index}
                        initial={{ opacity: 0, x: -20 }}
                        animate={{ opacity: 1, x: 0 }}
                        transition={{ delay: index * 0.1 }}
                        className="flex items-center gap-3 md:gap-4 p-3 md:p-4 bg-gray-50 dark:bg-slate-700/50 rounded-lg hover:bg-gray-100 dark:hover:bg-slate-700 transition-colors duration-200"
                      >
                        <div className="flex-1 min-w-0">
                          <p className="text-xs md:text-sm font-medium text-gray-900 dark:text-white truncate">
                            New booking received
                          </p>
                          <p className="text-xs md:text-sm text-gray-600 dark:text-gray-400 truncate">
                            Resort Spa appointment booked for tomorrow
                          </p>
                        </div>
                      </motion.div>
                    ))}
                  </div>
                </motion.div>
              </div>

              {/* Footer */}
              <Footer />
            </div>
          </main>
        </div>
      </motion.div>
    </div>
  );
};

export default DashboardHome;