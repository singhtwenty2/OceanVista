import React from 'react';
import { motion } from 'framer-motion';
import { Compass, Shield, Anchor, Map, Waves, Sun } from 'lucide-react';

const features = [
  {
    icon: Compass,
    title: 'Smart Property Management',
    description: 'Efficiently manage your coastal properties with our intuitive dashboard',
  },
  {
    icon: Shield,
    title: 'Secure Bookings',
    description: 'Advanced security measures to protect your business transactions',
  },
  {
    icon: Anchor,
    title: 'Premium Listings',
    description: 'Showcase your properties with high-quality, optimized listings',
  },
  {
    icon: Map,
    title: 'Location Analytics',
    description: 'Gain insights into prime locations and market trends',
  },
  {
    icon: Waves,
    title: 'Seasonal Insights',
    description: 'Make data-driven decisions with our seasonal analysis tools',
  },
  {
    icon: Sun,
    title: '24/7 Support',
    description: 'Round-the-clock assistance for you and your guests',
  },
];

export const Features: React.FC = () => {
  return (
    <section className="py-24 bg-white dark:bg-slate-900">
      <div className="container mx-auto px-4">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-center mb-16"
        >
          <h2 className="text-4xl font-bold text-gray-900 dark:text-white mb-4">
            Premium Features for Premium Properties
          </h2>
          <p className="text-xl text-gray-600 dark:text-gray-300 max-w-3xl mx-auto">
            Everything you need to manage and grow your coastal property business
          </p>
        </motion.div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {features.map((feature, index) => (
            <motion.div
              key={feature.title}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: index * 0.1 }}
              whileHover={{ 
                scale: 1.05,
                boxShadow: "0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)"
              }}
              className="bg-gray-50 dark:bg-slate-800 rounded-2xl p-8 transition-all duration-300 hover:bg-white dark:hover:bg-slate-700"
            >
              <div className="flex items-center gap-4 mb-4">
                <feature.icon className="w-8 h-8 text-primary-500" />
                <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                  {feature.title}
                </h3>
              </div>
              <p className="text-gray-600 dark:text-gray-300">{feature.description}</p>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
};