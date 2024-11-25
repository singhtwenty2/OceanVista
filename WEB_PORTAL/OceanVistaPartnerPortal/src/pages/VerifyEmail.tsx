import React from 'react';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Mail, ArrowRight, LogIn } from 'lucide-react';

const VerifyEmail: React.FC = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-gradient-to-br from-primary-500/20 via-white to-primary-500/20 dark:from-slate-900 dark:via-slate-800 dark:to-slate-900 flex items-center justify-center p-4">
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        className="bg-white dark:bg-slate-800 rounded-2xl p-8 shadow-xl w-full max-w-md text-center relative overflow-hidden"
      >
        {/* Decorative background elements */}
        <div className="absolute inset-0 overflow-hidden">
          <svg className="absolute -right-16 -top-16 text-primary-500/5" width="128" height="128" viewBox="0 0 100 100">
            <circle cx="50" cy="50" r="50" fill="currentColor" />
          </svg>
          <svg className="absolute -left-16 -bottom-16 text-primary-500/5" width="128" height="128" viewBox="0 0 100 100">
            <rect width="100" height="100" fill="currentColor" />
          </svg>
        </div>

        {/* Content */}
        <div className="relative z-10">
          <motion.div
            initial={{ scale: 0 }}
            animate={{ scale: 1 }}
            transition={{ type: "spring", stiffness: 100 }}
            className="flex justify-center mb-6"
          >
            <div className="w-16 h-16 bg-gradient-to-br from-primary-500/20 to-primary-500/10 rounded-full flex items-center justify-center">
              <Mail className="w-8 h-8 text-primary-500" />
            </div>
          </motion.div>

          <h1 className="text-2xl font-bold text-gray-900 dark:text-white mb-4">
            Check Your Email 📧
          </h1>
          
          <p className="text-gray-600 dark:text-gray-300 mb-8">
            We've sent you a verification link to your email address. Please verify your account to continue.
          </p>

          <div className="flex flex-col sm:flex-row gap-6 justify-center items-center mb-8">
            <motion.a
              href="https://gmail.com"
              target="_blank"
              rel="noopener noreferrer"
              whileHover={{ scale: 1.02, y: -2 }}
              whileTap={{ scale: 0.98 }}
              className="group flex items-center justify-center gap-3 px-8 py-3.5 bg-gradient-to-b from-white to-gray-50 dark:from-slate-700 dark:to-slate-800 text-gray-700 dark:text-gray-200 font-medium border border-gray-200 dark:border-gray-600 hover:border-primary-500 dark:hover:border-primary-500 hover:shadow-lg dark:hover:shadow-primary-500/20 transition-all duration-300 w-full sm:w-auto relative overflow-hidden rounded-lg shadow-sm hover:shadow-md backdrop-blur-sm"
            >
              <span className="relative z-10 flex items-center gap-2">
                Open Gmail
                <ArrowRight className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
              </span>
              <div className="absolute inset-0 bg-gradient-to-r from-primary-500/0 via-primary-500/5 to-primary-500/0 opacity-0 group-hover:opacity-100 transition-opacity" />
            </motion.a>

            <motion.button
              onClick={() => navigate('/login')}
              whileHover={{ scale: 1.02, y: -2 }}
              whileTap={{ scale: 0.98 }}
              className="group flex items-center justify-center gap-3 px-8 py-3.5 bg-gradient-to-br from-primary-500 to-primary-600 text-white font-medium hover:shadow-lg hover:shadow-primary-500/25 transition-all duration-300 w-full sm:w-auto relative overflow-hidden rounded-lg shadow-md backdrop-blur-sm"
            >
              <span className="relative z-10 flex items-center gap-2">
                Continue to Login
                <LogIn className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
              </span>
              <div className="absolute inset-0 bg-gradient-to-r from-white/0 via-white/5 to-white/0 opacity-0 group-hover:opacity-100 transition-opacity" />
            </motion.button>
          </div>

          <p className="text-sm text-gray-500 dark:text-gray-400">
            Didn't receive the email? Check your spam folder or contact support 💌
          </p>
        </div>
      </motion.div>
    </div>
  );
};

export default VerifyEmail;