import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import { UserPlus, Mail, Phone, Lock, Building, Eye, EyeOff } from 'lucide-react';
import { Link } from 'react-router-dom';
import { registerPartner, SignupPayload } from '../services/authService';

const partnerTypeOptions = [
  { value: 'RESORT', label: 'Luxury Resort & Accommodation' },
  { value: 'VENDOR', label: 'Experience & Activity Provider' },
  { value: 'MEDICAL_SERVICE', label: 'Wellness & Medical Services' },
];

const ProgressLoader = () => (
  <motion.div 
    className="fixed top-0 left-0 right-0 z-50"
    initial={{ opacity: 0 }}
    animate={{ opacity: 1 }}
    exit={{ opacity: 0 }}
  >
    <motion.div 
      className="h-1 bg-gradient-to-r from-primary-400 via-primary-500 to-primary-600"
      initial={{ width: "0%" }}
      animate={{ 
        width: "100%",
        transition: { 
          duration: 2,
          ease: "easeInOut",
          repeat: Infinity,
        }
      }}
    />
    <div className="h-1 bg-gray-100/20 dark:bg-gray-800/20 backdrop-blur-sm" />
  </motion.div>
);

const LoadingOverlay = () => (
  <motion.div 
    className="absolute inset-0 bg-white/50 dark:bg-slate-900/50 backdrop-blur-sm flex items-center justify-center z-40"
    initial={{ opacity: 0 }}
    animate={{ opacity: 1 }}
    exit={{ opacity: 0 }}
  >
    <motion.div
      className="flex flex-col items-center gap-4"
      initial={{ y: 20, opacity: 0 }}
      animate={{ y: 0, opacity: 1 }}
    >
      <motion.div 
        className="w-12 h-12 rounded-full border-4 border-primary-500 border-t-transparent"
        animate={{ rotate: 360 }}
        transition={{ duration: 1, repeat: Infinity, ease: "linear" }}
      />
      <p className="text-gray-600 dark:text-gray-300 font-medium">Creating your account...</p>
    </motion.div>
  </motion.div>
);

const Signup: React.FC = () => {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState<SignupPayload>({
    name: '',
    email: '',
    phoneNumber: '',
    password: '',
    partnerType: '',
  });
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [apiError, setApiError] = useState<string | null>(null);

  const validateForm = () => {
    const newErrors: Record<string, string> = {};
    if (!formData.name.trim()) newErrors.name = 'Name is required';
    if (!formData.email.trim()) newErrors.email = 'Email is required';
    if (!formData.phoneNumber.trim()) newErrors.phoneNumber = 'Phone number is required';
    if (!formData.password) newErrors.password = 'Password is required';
    if (!formData.partnerType) newErrors.partnerType = 'Please select a partner type';
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setApiError(null);

    if (validateForm()) {
      setIsSubmitting(true);
      try {
        await registerPartner(formData);
        navigate('/verify-email');
      } catch (error: any) {
        setApiError(error.message || 'Failed to register');
      } finally {
        setIsSubmitting(false);
      }
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    if (errors[name]) {
      setErrors({ ...errors, [name]: '' });
    }
  };

  return (
    <div className="min-h-screen flex bg-gradient-to-br from-primary-500/20 via-white to-primary-500/20 dark:from-slate-900 dark:via-slate-800 dark:to-slate-900">
      <AnimatePresence>
        {isSubmitting && <ProgressLoader />}
      </AnimatePresence>

      {/* Left Side - Decorative Elements */}
      <div className="hidden lg:flex w-1/2 flex-col items-center justify-center p-12 relative">
        <div className="absolute inset-0 overflow-hidden">
          <svg className="absolute top-1/4 left-1/4 w-64 h-64 text-primary-500/10" viewBox="0 0 100 100">
            <circle cx="50" cy="50" r="40" fill="currentColor" />
          </svg>
          <svg className="absolute bottom-1/4 right-1/4 w-48 h-48 text-primary-500/5" viewBox="0 0 100 100">
            <rect x="20" y="20" width="60" height="60" fill="currentColor" />
          </svg>
        </div>
        <motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ duration: 0.8 }}
          className="relative z-10 text-center space-y-6"
        >
          <h2 className="text-4xl font-bold text-gray-900 dark:text-white">Welcome to Our Platform</h2>
          <p className="text-xl text-gray-600 dark:text-gray-300 max-w-md">
            Join our network of premium partners and grow your business with us
          </p>
        </motion.div>
      </div>

      {/* Right Side - Form */}
      <div className="w-full mt-8 lg:w-1/2 flex items-center justify-center px-4 py-12 overflow-y-auto">
        <motion.div
          initial={{ opacity: 0, x: 20 }}
          animate={{ opacity: 1, x: 0 }}
          className="bg-white dark:bg-slate-800 rounded-2xl p-8 shadow-xl w-full max-w-2xl relative"
        >
          <AnimatePresence>
            {isSubmitting && <LoadingOverlay />}
          </AnimatePresence>

          <div className="text-center mb-8">
            <motion.div
              initial={{ scale: 0 }}
              animate={{ scale: 1 }}
              transition={{ type: "spring", stiffness: 100 }}
              className="flex justify-center mb-4"
            >
              <UserPlus className="w-12 h-12 text-primary-500" />
            </motion.div>
            <h1 className="text-2xl font-bold text-gray-900 dark:text-white mb-2">
              Partner Registration
            </h1>
            <p className="text-gray-600 dark:text-gray-300">
              Create an account to manage your properties
            </p>
          </div>

          <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label htmlFor="name" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                Business Name
              </label>
              <div className="relative">
                <input
                  type="text"
                  id="name"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-300 dark:border-gray-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                  placeholder="Enter your business name"
                />
                <Building className="w-5 h-5 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
              </div>
              {errors.name && <span className="text-red-500 text-sm mt-1">{errors.name}</span>}
            </div>

            <div>
              <label htmlFor="email" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                Email Address
              </label>
              <div className="relative">
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-300 dark:border-gray-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                  placeholder="you@example.com"
                />
                <Mail className="w-5 h-5 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
              </div>
              {errors.email && <span className="text-red-500 text-sm mt-1">{errors.email}</span>}
            </div>

            <div>
              <label htmlFor="phoneNumber" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                Phone Number
              </label>
              <div className="relative">
                <input
                  type="tel"
                  id="phoneNumber"
                  name="phoneNumber"
                  value={formData.phoneNumber}
                  onChange={handleChange}
                  className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-300 dark:border-gray-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                  placeholder="Enter your phone number"
                />
                <Phone className="w-5 h-5 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
              </div>
              {errors.phoneNumber && <span className="text-red-500 text-sm mt-1">{errors.phoneNumber}</span>}
            </div>

            <div>
              <label htmlFor="password" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                Password
              </label>
              <div className="relative">
                <input
                  type={showPassword ? 'text' : 'password'}
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  className="w-full pl-10 pr-12 py-2 rounded-lg border border-gray-300 dark:border-gray-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent"
                  placeholder="••••••••"
                />
                <Lock className="w-5 h-5 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600 dark:hover:text-gray-200"
                >
                  {showPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                </button>
              </div>
              {errors.password && <span className="text-red-500 text-sm mt-1">{errors.password}</span>}
            </div>

            <div className="md:col-span-2">
              <label htmlFor="partnerType" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
                Partner Type
              </label>
              <div className="relative">
                <select
                  id="partnerType"
                  name="partnerType"
                  value={formData.partnerType}
                  onChange={handleChange}
                  className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-300 dark:border-gray-600 bg-white dark:bg-slate-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-primary-500 focus:border-transparent appearance-none"
                >
                  <option value="">Select a type</option>
                  {partnerTypeOptions.map(option => (
                    <option key={option.value} value={option.value}>
                      {option.label}
                    </option>
                  ))}
                </select>
                <Building className="w-5 h-5 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2" />
              </div>
              {errors.partnerType && <span className="text-red-500 text-sm mt-1">{errors.partnerType}</span>}
            </div>

            {apiError && (
              <div className="md:col-span-2 bg-red-50 dark:bg-red-900/50 text-red-500 dark:text-red-200 p-3 rounded-lg text-sm">
                {apiError}
              </div>
            )}

            <div className="md:col-span-2">
              <motion.button
                type="submit"
                disabled={isSubmitting}
                whileHover={{ scale: 1.02 }}
                whileTap={{ scale: 0.98 }}
                className="w-full py-3 px-6 bg-primary-500 text-white rounded-full font-medium hover:bg-primary-600 focus:outline-none focus:ring-2 focus:ring-primary-500 focus:ring-offset-2 flex items-center justify-center gap-2 disabled:opacity-50 disabled:cursor-not-allowed"
              >
                <UserPlus className="w-5 h-5" />
                {isSubmitting ? 'Creating Account...' : 'Sign Up'}
              </motion.button>
            </div>

            <div className="md:col-span-2 text-center">
              <p className="text-sm text-gray-600 dark:text-gray-300">
                Already have an account?{' '}
                <Link to="/login" className="font-medium text-primary-500 hover:text-primary-600">
                  Sign in
                </Link>
                </p>
            </div>
          </form>
        </motion.div>
      </div>
    </div>
  );
};

export default Signup;