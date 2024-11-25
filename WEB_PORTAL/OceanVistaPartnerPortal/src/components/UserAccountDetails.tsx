import React from 'react';
import { motion } from 'framer-motion';
import { 
  Calendar, 
  Mail, 
  Phone, 
  CreditCard, 
  MapPin, 
  UserCircle, 
  Settings 
} from 'lucide-react';
import { useUserProfile } from '../utils/useUserProfile';

export const UserAccountDetails: React.FC = () => {
  const { user, loading, error } = useUserProfile();

  if (loading) {
    return (
      <div className="flex justify-center items-center h-screen">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-gray-900 dark:border-white"></div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-red-500 text-center mt-10">
        Unable to load user details
      </div>
    );
  }

  return (
    <div className="container mt-16 mx-auto px-4 py-8">
      <motion.div 
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="max-w-2xl mx-auto bg-white dark:bg-slate-800 rounded-xl shadow-lg p-6"
      >
        <div className="flex items-center space-x-6 mb-6">
          <img 
            src="/avatar.png" 
            alt={user?.name} 
            className="w-24 h-24 rounded-full ring-4 ring-gray-200 dark:ring-gray-700"
          />
          <div>
            <h2 className="text-2xl font-bold text-gray-900 dark:text-white">
              {user?.name}
            </h2>
            <p className="text-gray-500 dark:text-gray-400">
              {user?.partnerType.replace('_', ' ')}
            </p>
          </div>
        </div>

        <div className="grid md:grid-cols-2 gap-6">
          <div className="space-y-4">
            <div className="flex items-center space-x-3">
              <UserCircle className="text-gray-500 w-5 h-5" />
              <span className="text-gray-700 dark:text-gray-300">
                User ID: {user?.userId}OV
              </span>
            </div>
            <div className="flex items-center space-x-3">
              <Mail className="text-gray-500 w-5 h-5" />
              <span className="text-gray-700 dark:text-gray-300">
                {user?.email}
              </span>
            </div>
            <div className="flex items-center space-x-3">
              <Phone className="text-gray-500 w-5 h-5" />
              <span className="text-gray-700 dark:text-gray-300">
                {user?.phoneNumber}
              </span>
            </div>
          </div>

          <div className="space-y-4">
            <div className="flex items-center space-x-3">
              <CreditCard className="text-gray-500 w-5 h-5" />
              <span className="text-gray-700 dark:text-gray-300">
                Partner Status: {user?.partnerStatus}
              </span>
            </div>
            <div className="flex items-center space-x-3">
              <MapPin className="text-gray-500 w-5 h-5" />
              <span className="text-gray-700 dark:text-gray-300">
                Max Beaches: {user?.maxAllowedBeachCount}
              </span>
            </div>
            <div className="flex items-center space-x-3">
              <Calendar className="text-gray-500 w-5 h-5" />
              <span className="text-gray-700 dark:text-gray-300">
                Joined: {new Date(user?.createdAt || '').toLocaleDateString()}
              </span>
            </div>
          </div>
        </div>

        <div className="mt-8 flex justify-end space-x-4">
          <button className="flex items-center space-x-2 px-4 py-2 bg-gray-100 dark:bg-slate-700 text-gray-700 dark:text-gray-300 rounded-lg hover:bg-gray-200 dark:hover:bg-slate-600 transition">
            <Settings className="w-4 h-4" />
            <span>Edit Profile</span>
          </button>
        </div>
      </motion.div>
    </div>
  );
};