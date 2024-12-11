import React, { useEffect, useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { 
  Check, X, Star, 
  Rocket, 
  Layers, 
  Zap, 
  Trophy, 
  ShieldCheck,
  Package,
  Compass
} from 'lucide-react';
import axiosInstance from '../api/axiosInstance';

interface Plan {
  planId: number;
  name: string;
  description: string;
  price: number;
  maxShops: number;
  maxServices: number;
  platformFee: number;
  additionalFeatures: string;
  includedFeatures: string[];
  excludedFeatures: string[];
}

// Map plan names to icons and unique gradient backgrounds
const getPlanDetails = (planName: string) => {
  const lowerCaseName = planName.toLowerCase();
  
  const iconMap = {
    starter: { icon: Layers, gradient: 'from-blue-100 to-blue-200 dark:from-blue-900 dark:to-blue-800' },
    basic: { icon: Package, gradient: 'from-green-100 to-green-200 dark:from-green-900 dark:to-green-800' },
    pro: { icon: Zap, gradient: 'from-purple-100 to-purple-200 dark:from-purple-900 dark:to-purple-800' },
    enterprise: { icon: Trophy, gradient: 'from-orange-100 to-orange-200 dark:from-orange-900 dark:to-orange-800' },
    premium: { icon: ShieldCheck, gradient: 'from-indigo-100 to-indigo-200 dark:from-indigo-900 dark:to-indigo-800' },
    ultimate: { icon: Rocket, gradient: 'from-red-100 to-red-200 dark:from-red-900 dark:to-red-800' }
  };

  const defaultDetails = { 
    icon: Compass, 
    gradient: 'from-gray-100 to-gray-200 dark:from-slate-900 dark:to-slate-800' 
  };

  return Object.keys(iconMap).find(key => lowerCaseName.includes(key)) 
    ? iconMap[Object.keys(iconMap).find(key => lowerCaseName.includes(key))!]
    : defaultDetails;
};

// Skeleton Loading Component
const PlanSkeleton: React.FC = () => {
  return (
    <motion.div 
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      className="bg-white dark:bg-slate-800 rounded-3xl p-6 shadow-2xl border-2 border-gray-200 dark:border-slate-700 space-y-4 overflow-hidden relative"
    >
      <div className="absolute inset-0 bg-gradient-to-br from-gray-50 to-gray-100 dark:from-slate-900 dark:to-slate-800 opacity-10"></div>
      <div className="relative z-10">
        <div className="flex flex-col items-center mb-4">
          <div className="w-20 h-20 bg-gray-200 dark:bg-slate-700 rounded-full mb-3 animate-pulse"></div>
          <div className="h-6 w-1/2 bg-gray-200 dark:bg-slate-700 rounded animate-pulse mb-2"></div>
          <div className="h-4 w-3/4 bg-gray-200 dark:bg-slate-700 rounded animate-pulse"></div>
        </div>
        
        <div className="space-y-4">
          <div className="h-8 bg-gray-200 dark:bg-slate-700 rounded animate-pulse"></div>
          <div className="h-12 bg-gray-200 dark:bg-slate-700 rounded animate-pulse"></div>
          <div className="space-y-2">
            {[1, 2, 3].map((_, index) => (
              <div key={index} className="h-4 bg-gray-200 dark:bg-slate-700 rounded animate-pulse"></div>
            ))}
          </div>
          <div className="h-12 bg-gray-200 dark:bg-slate-700 rounded animate-pulse mt-4"></div>
        </div>
      </div>
    </motion.div>
  );
};

export const Pricing: React.FC = () => {
  const [plans, setPlans] = useState<Plan[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchPlans = async () => {
      try {
        const response = await axiosInstance.get('subscription-plan/all');
        setPlans(response.data);
        setLoading(false);
      } catch (err) {
        setError('Failed to load plans. Please try again later.');
        setLoading(false);
      }
    };

    fetchPlans();
  }, []);

  if (loading) {
    return (
      <div className="min-h-screen bg-gradient-to-b from-gray-50 to-white dark:from-slate-900 dark:to-slate-800 py-24">
        <div className="container mx-auto px-4">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            className="text-center mb-16"
          >
            <h1 className="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white mb-4">
              Choose Your Perfect Plan
            </h1>
            <p className="text-xl text-gray-600 dark:text-gray-300">
              Scale your business with flexible pricing
            </p>
          </motion.div>

          <div className="grid md:grid-cols-4 gap-6 max-w-7xl mx-auto">
            {[1, 2, 3, 4].map((_, index) => (
              <PlanSkeleton key={index} />
            ))}
          </div>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen bg-gradient-to-b from-gray-50 to-white dark:from-slate-900 dark:to-slate-800 py-24 flex items-center justify-center">
        <motion.div 
          initial={{ opacity: 0, scale: 0.9 }}
          animate={{ opacity: 1, scale: 1 }}
          className="text-center bg-white dark:bg-slate-800 p-8 rounded-3xl shadow-2xl"
        >
          <X className="w-16 h-16 text-red-500 mx-auto mb-4" />
          <h2 className="text-2xl font-bold text-gray-900 dark:text-white mb-2">
            Oops! Something Went Wrong
          </h2>
          <p className="text-gray-600 dark:text-gray-300 mb-4">{error}</p>
          <button 
            onClick={() => window.location.reload()}
            className="px-6 py-3 bg-primary-500 text-white rounded-full hover:bg-primary-600 transition-colors shadow-lg"
          >
            Retry
          </button>
        </motion.div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-b from-gray-50 to-white dark:from-slate-900 dark:to-slate-800 py-24">
      <div className="container mx-auto px-4">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="text-center mb-16"
        >
          <h1 className="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white mb-4">
            Choose Your Perfect Plan
          </h1>
          <p className="text-xl text-gray-600 dark:text-gray-300">
            Scale your business with flexible pricing
          </p>
        </motion.div>

        <AnimatePresence>
          <div className="grid md:grid-cols-4 gap-6 max-w-7xl mx-auto">
            {plans.map((plan, index) => {
              const { icon: PlanIcon, gradient } = getPlanDetails(plan.name);
              return (
                <motion.div
                  key={plan.planId}
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ 
                    opacity: 1, 
                    y: 0,
                    transition: { 
                      delay: index * 0.1,
                      type: "spring",
                      stiffness: 100 
                    }
                  }}
                  exit={{ opacity: 0, y: 20 }}
                  whileHover={{
                    scale: 1.05,
                    transition: { duration: 0.2 },
                  }}
                  className={`relative rounded-3xl bg-white dark:bg-slate-800 p-6 shadow-2xl hover:shadow-2xl transform transition-all duration-300 border-2 border-gray-200 dark:border-slate-700 
                    ${index === 2 ? 'ring-4 ring-primary-500 scale-105' : ''}`}
                >
                  {/* Gradient Background */}
                  <div className={`absolute inset-0 bg-gradient-to-br ${gradient} opacity-10 rounded-3xl -z-10`}></div>
                  
                  {index === 2 && (
                    <div className="absolute -top-4 left-1/2 transform -translate-x-1/2 bg-primary-500 text-white px-4 py-2 rounded-full text-sm flex items-center gap-2 shadow-lg z-10">
                      <Star className="w-4 h-4 fill-current" />
                      Most Popular
                    </div>
                  )}
                  
                  <div className="flex flex-col items-center mb-6">
                    <div className={`mb-4 p-4 bg-gradient-to-br ${gradient} rounded-2xl shadow-md`}>
                      <PlanIcon className="w-8 h-8 text-primary-500" />
                    </div>
                    <h3 className="text-2xl font-bold text-gray-900 dark:text-white text-center">
                      {plan.name}
                    </h3>
                  </div>

                  <p className="text-gray-600 dark:text-gray-300 text-center mb-6 min-h-[50px] px-2 text-sm">
                    {plan.description}
                  </p>

                  <div className="text-center mb-6">
                    <span className="text-5xl font-extrabold text-gray-900 dark:text-white">
                      {plan.price % 1 === 0 ? plan.price.toFixed(0) : plan.price.toFixed(2)}
                    </span>
                    <span className="text-gray-600 dark:text-gray-300 text-lg ml-1">/month</span>
                  </div>

                  <div className="text-center text-gray-600 dark:text-gray-300 mb-4 font-medium">
                    Platform Fee: {plan.platformFee.toFixed(2)}%
                  </div>

                  <div className="text-center text-sm text-gray-600 dark:text-gray-300 mb-6 bg-gray-50 dark:bg-slate-900 p-3 rounded-xl">
                    <div className="font-medium mb-1">Limits</div>
                    Max Shops: {plan.maxShops === -1 ? 'Unlimited' : plan.maxShops}
                    <br />
                    Max Services: {plan.maxServices === -1 ? 'Unlimited' : plan.maxServices}
                  </div>

                  <div className="space-y-6">
                    <div>
                      <h4 className="font-semibold text-gray-800 dark:text-white mb-3 text-center">What's Included</h4>
                      <ul className="space-y-2">
                        {plan.includedFeatures.map((feature) => (
                          <li key={feature} className="flex items-center gap-2">
                            <Check className="w-4 h-4 text-primary-500 flex-shrink-0" />
                            <span className="text-gray-600 dark:text-gray-300 text-sm">{feature}</span>
                          </li>
                        ))}
                      </ul>
                    </div>

                    {plan.excludedFeatures.length > 0 && (
                      <div>
                        <h4 className="font-semibold text-gray-800 dark:text-white mb-3 text-center">Not Included</h4>
                        <ul className="space-y-2">
                          {plan.excludedFeatures.map((feature) => (
                            <li key={feature} className="flex items-center gap-2">
                              <X className="w-4 h-4 text-red-500 flex-shrink-0" />
                              <span className="text-gray-500 dark:text-gray-400 text-sm line-through">{feature}</span>
                            </li>
                          ))}
                        </ul>
                      </div>
                    )}
                  </div>

                  <motion.button
                    whileHover={{ scale: 1.02 }}
                    whileTap={{ scale: 0.98 }}
                    className="mt-6 w-full py-3.5 px-6 rounded-full font-semibold bg-primary-500 text-white hover:bg-primary-600 transition-colors duration-200 shadow-lg hover:shadow-xl"
                  >
                    Get Started
                  </motion.button>
                </motion.div>
              );
            })}
          </div>
        </AnimatePresence>
      </div>
    </div>
  );
};