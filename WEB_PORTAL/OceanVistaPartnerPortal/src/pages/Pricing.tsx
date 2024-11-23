import React from 'react';
import { motion } from 'framer-motion';
import { Check, X, Compass, Rocket, Crown, Globe } from 'lucide-react';

const plans = [
  {
    name: 'Basic',
    price: '₹0',
    description: 'Get started with essential tools for small vendors and services',
    icon: Compass,
    features: [
      'List 1 business or service',
      'Basic visibility on the portal',
      'Access to community support',
    ],
    notIncludes: [
      'Detailed analytics',
      'Booking management tools',
      'Enhanced listing customization',
      'Priority support',
      'API access',
    ],
  },
  {
    name: 'Premium',
    price: '₹499',
    description: 'Best for growing vendors and small businesses',
    icon: Rocket,
    features: [
      'List up to 3 businesses or services',
      'Standard analytics',
      'Booking management tools',
      'Enhanced listing customization',
      'Email support',
    ],
    notIncludes: [
      'Priority support',
      'Customer review management',
      'Real-time analytics',
      'API access',
    ],
  },
  {
    name: 'Business',
    price: '₹1,499',
    description: 'Powerful tools for established businesses',
    icon: Crown,
    features: [
      'List up to 10 businesses or services',
      'Advanced analytics',
      'Priority support',
      'Customer review management',
      'Booking and inquiry tools',
      'Prominent listing placement',
    ],
    notIncludes: [
      'Unlimited listings',
      '24/7 premium support',
      'Custom integrations',
      'API access',
    ],
    popular: true,
  },
  {
    name: 'Enterprise',
    price: '₹4,999',
    description: 'Complete suite for large-scale businesses and enterprises',
    icon: Globe,
    features: [
      'Unlimited business or service listings',
      'Real-time analytics and insights',
      '24/7 premium support',
      'Custom integrations',
      'API access for automation',
      'Dedicated account manager',
      'Enhanced booking and payment tools',
    ],
    notIncludes: [],
  },
];

export const Pricing: React.FC = () => {
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
          {plans.map((plan, index) => (
            <motion.div
              key={plan.name}
              initial={{ opacity: 0, y: 20 }}
              whileHover={{ 
                scale: 1.03, 
                transition: { duration: 0.2 } 
              }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: index * 0.1 }}
              className={`relative rounded-2xl bg-white dark:bg-slate-800 p-6 shadow-lg hover:shadow-xl transform transition-all duration-300 
                ${plan.popular ? 'ring-2 ring-primary-500' : 'border border-gray-200 dark:border-slate-700'}`}
            >
              {plan.popular && (
                <div className="absolute -top-4 left-1/2 -translate-x-1/2">
                  <span className="bg-primary-500 text-white px-4 py-1 rounded-full text-sm font-medium">
                    Most Popular
                  </span>
                </div>
              )}

              <div className="flex justify-center mb-6">
                <plan.icon className="w-12 h-12 text-primary-500" />
              </div>

              <h3 className="text-2xl font-bold text-gray-900 dark:text-white text-center mb-4">
                {plan.name}
              </h3>
              <p className="text-gray-600 dark:text-gray-300 text-center mb-6 min-h-[50px]">
                {plan.description}
              </p>

              <div className="text-center mb-8">
                <span className="text-4xl font-bold text-gray-900 dark:text-white">
                  {plan.price}
                </span>
                <span className="text-gray-600 dark:text-gray-300">/month</span>
              </div>

              <div className="space-y-6">
                <div>
                  <h4 className="font-semibold text-gray-800 dark:text-white mb-3">What's Included</h4>
                  <ul className="space-y-2">
                    {plan.features.map((feature) => (
                      <li key={feature} className="flex items-center gap-2">
                        <Check className="w-4 h-4 text-primary-500 flex-shrink-0" />
                        <span className="text-gray-600 dark:text-gray-300 text-sm">{feature}</span>
                      </li>
                    ))}
                  </ul>
                </div>

                {plan.notIncludes.length > 0 && (
                  <div>
                    <h4 className="font-semibold text-gray-800 dark:text-white mb-3">Not Included</h4>
                    <ul className="space-y-2">
                      {plan.notIncludes.map((feature) => (
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
                className={`mt-6 w-full py-3 px-6 rounded-full font-medium transition-colors duration-200 ${
                  plan.popular
                    ? 'bg-primary-500 text-white hover:bg-primary-600'
                    : 'bg-gray-100 dark:bg-slate-700 text-gray-900 dark:text-white hover:bg-gray-200 dark:hover:bg-slate-600'
                }`}
              >
                Get Started
              </motion.button>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
};