import React from 'react';
import { motion } from 'framer-motion';
import { CheckCircle, Clock } from 'lucide-react';

const roadmap = [
  {
    title: 'Platform Launch',
    description:
      'Core platform development with sophisticated property listing and management capabilities.',
    completed: true,
  },
  {
    title: 'Strategic Partnerships',
    description:
      'Integration with premier resorts, luxury amenities, and comprehensive medical services.',
    completed: true,
  },
  {
    title: 'Enterprise Analytics',
    description:
      'Advanced business intelligence tools enabling data-driven decision making for partners.',
    completed: false,
  },
  {
    title: 'Mobile Experience',
    description:
      'Native mobile application delivering personalized recommendations and seamless service access.',
    completed: false,
  },
  {
    title: 'International Markets',
    description:
      'Strategic expansion to world-class beach destinations across premium international markets.',
    completed: false,
  },
];

export const Roadmap: React.FC = () => {
  return (
    <section className="py-32 bg-gradient-to-b from-gray-50 to-white dark:from-slate-900 dark:to-slate-800">
      <div className="container mx-auto px-4">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-center mb-24"
        >
          <h2 className="text-5xl font-bold tracking-tight text-gray-900 dark:text-white mb-6">
            Strategic Roadmap
          </h2>
          <p className="text-xl font-light text-gray-600 dark:text-gray-300 max-w-2xl mx-auto leading-relaxed">
            Charting our course towards revolutionizing the luxury coastal living experience.
          </p>
        </motion.div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {roadmap.map((phase, index) => (
            <motion.div
              key={phase.title}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: index * 0.1 }}
              whileHover={{
                scale: 1.02,
                boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.15)',
              }}
              className={`rounded-3xl p-8 backdrop-blur-sm shadow-xl transition-all duration-500 ${
                phase.completed
                  ? 'bg-gradient-to-br from-green-50/90 to-green-50/50 dark:from-green-900/90 dark:to-green-900/50'
                  : 'bg-gradient-to-br from-white/90 to-white/50 dark:from-slate-900/90 dark:to-slate-900/50'
              }`}
            >
              <div className="flex items-center justify-between mb-6">
                <h4 className="text-2xl font-semibold tracking-tight text-gray-900 dark:text-white">
                  {phase.title}
                </h4>
                {phase.completed ? (
                  <CheckCircle className="w-6 h-6 text-green-500" />
                ) : (
                  <Clock className="w-6 h-6 text-gray-400 dark:text-gray-500" />
                )}
              </div>
              <p className="text-gray-600 dark:text-gray-300 leading-relaxed mb-6">
                {phase.description}
              </p>
              <div className="mt-auto">
                <span
                  className={`px-6 py-2 text-sm font-medium rounded-full ${
                    phase.completed
                      ? 'bg-green-500/10 text-green-700 dark:text-green-300'
                      : 'bg-gray-100 text-gray-600 dark:bg-gray-800 dark:text-gray-300'
                  }`}
                >
                  {phase.completed ? 'Implemented' : 'In Development'}
                </span>
              </div>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Roadmap;