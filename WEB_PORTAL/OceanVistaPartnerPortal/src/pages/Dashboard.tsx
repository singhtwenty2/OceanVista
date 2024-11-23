import React from 'react';
import { motion } from 'framer-motion';
import {
  LayoutDashboard,
  Building2,
  BarChart3,
  Settings,
  LogOut,
  Plus,
  TrendingUp,
  Users,
  Calendar,
} from 'lucide-react';
import { Link, Routes, Route, useLocation } from 'react-router-dom';

const stats = [
  {
    label: 'Total Properties',
    value: '12',
    icon: Building2,
    trend: '+2 this month',
  },
  {
    label: 'Total Bookings',
    value: '284',
    icon: Calendar,
    trend: '+18% vs last month',
  },
  {
    label: 'Active Users',
    value: '892',
    icon: Users,
    trend: '+12% vs last month',
  },
  {
    label: 'Revenue',
    value: '$32,621',
    icon: TrendingUp,
    trend: '+24% vs last month',
  },
];

const sidebarLinks = [
  { icon: LayoutDashboard, label: 'Dashboard', path: '/dashboard' },
  { icon: Building2, label: 'Properties', path: '/dashboard/properties' },
  { icon: BarChart3, label: 'Analytics', path: '/dashboard/analytics' },
  { icon: Settings, label: 'Settings', path: '/dashboard/settings' },
];

export const Dashboard: React.FC = () => {
  const location = useLocation();

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-slate-900 flex">
      {/* Sidebar */}
      <motion.aside
        initial={{ x: -200 }}
        animate={{ x: 0 }}
        className="w-64 bg-white dark:bg-slate-800 border-r border-gray-200 dark:border-gray-700 fixed h-screen pt-24"
      >
        <nav className="px-4 space-y-2">
          {sidebarLinks.map((link) => (
            <Link
              key={link.path}
              to={link.path}
              className={`flex items-center gap-3 px-4 py-3 rounded-lg transition-colors duration-200 ${
                location.pathname === link.path
                  ? 'bg-primary-50 dark:bg-primary-900/20 text-primary-600 dark:text-primary-400'
                  : 'text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-slate-700'
              }`}
            >
              <link.icon className="w-5 h-5" />
              <span className="font-medium">{link.label}</span>
            </Link>
          ))}
        </nav>

        <div className="absolute bottom-8 w-full px-4">
          <button className="flex items-center gap-3 px-4 py-3 rounded-lg text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-slate-700 w-full transition-colors duration-200">
            <LogOut className="w-5 h-5" />
            <span className="font-medium">Logout</span>
          </button>
        </div>
      </motion.aside>

      {/* Main Content */}
      <main className="flex-1 ml-64 pt-24">
        <div className="px-8 py-6">
          <div className="flex justify-between items-center mb-8">
            <h1 className="text-2xl font-bold text-gray-900 dark:text-white">
              Dashboard Overview
            </h1>
            <motion.button
              whileHover={{ scale: 1.02 }}
              whileTap={{ scale: 0.98 }}
              className="flex items-center gap-2 px-4 py-2 bg-primary-500 text-white rounded-lg hover:bg-primary-600 transition-colors duration-200"
            >
              <Plus className="w-5 h-5" />
              Add Property
            </motion.button>
          </div>

          {/* Stats Grid */}
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
            {stats.map((stat, index) => (
              <motion.div
                key={stat.label}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ delay: index * 0.1 }}
                className="bg-white dark:bg-slate-800 rounded-xl p-6 shadow-sm"
              >
                <div className="flex items-center justify-between mb-4">
                  <stat.icon className="w-6 h-6 text-primary-500" />
                  <span className="text-sm text-green-500">{stat.trend}</span>
                </div>
                <h3 className="text-2xl font-bold text-gray-900 dark:text-white mb-1">
                  {stat.value}
                </h3>
                <p className="text-sm text-gray-600 dark:text-gray-400">{stat.label}</p>
              </motion.div>
            ))}
          </div>

          {/* Routes for dashboard sections */}
          <Routes>
            <Route path="/properties" element={<div>Properties Content</div>} />
            <Route path="/analytics" element={<div>Analytics Content</div>} />
            <Route path="/settings" element={<div>Settings Content</div>} />
          </Routes>
        </div>
      </main>
    </div>
  );
};