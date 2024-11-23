import React, { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";
import { Book, Code, Info, HelpCircle, X } from "lucide-react";

const sections = [
  {
    icon: Info,
    title: "Getting Started",
    content: "Learn how to sign up, explore beach listings, and use our platform for the best experience. Follow our simple guide to get started today!",
    details: [
      "Create an account in under 5 minutes",
      "Browse comprehensive beach listings",
      "Personalize your beach exploration experience",
      "Access real-time beach conditions and safety information"
    ]
  },
  {
    icon: Code,
    title: "API Integration",
    content: "Integrate our API to fetch real-time beach conditions, safety recommendations, and SOS services. Perfect for developers and third-party partners.",
    details: [
      "Comprehensive REST API documentation",
      "Real-time beach condition endpoints",
      "Safety recommendation data streams",
      "Authentication and rate limit guidelines"
    ]
  },
  {
    icon: Book,
    title: "Partner Setup",
    content: "Are you a business near a beach? Discover how to list your services, manage bookings, and attract more customers through OceanVista.",
    details: [
      "Easy business profile creation",
      "Service and booking management tools",
      "Marketing support for local beach businesses",
      "Analytics and customer engagement features"
    ]
  },
  {
    icon: HelpCircle,
    title: "FAQs & Troubleshooting",
    content: "Find answers to common questions and troubleshooting tips to resolve any issues while using our platform.",
    details: [
      "Comprehensive troubleshooting guides",
      "Step-by-step problem resolution",
      "Customer support contact information",
      "Community forum access"
    ]
  }
];

export const Documentation: React.FC = () => {
  const [selectedSection, setSelectedSection] = useState<number | null>(null);

  const handleSectionClick = (index: number) => {
    setSelectedSection(index);
  };

  const closeModal = () => {
    setSelectedSection(null);
  };

  return (
    <div className="min-h-screen bg-white dark:bg-slate-900 pt-24 relative">
      {/* Hero Section */}
      <section className="container mx-auto px-4 py-16">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.7, ease: "easeOut" }}
          className="text-center max-w-3xl mx-auto"
        >
          <h1 className="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white mb-6 
            bg-clip-text text-transparent bg-gradient-to-r from-blue-600 to-teal-400">
            Documentation
          </h1>
          <p className="text-xl text-gray-600 dark:text-gray-300 
            transition-all duration-300 hover:text-opacity-80">
            Explore the resources and guides to make the most of OceanVista. Whether you're a user or a developer, find everything you need here.
          </p>
        </motion.div>
      </section>

      {/* Documentation Sections */}
      <section className="bg-gray-50 dark:bg-slate-800 py-16">
        <div className="container mx-auto px-4">
          <motion.h2
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.7, ease: "easeOut" }}
            className="text-3xl font-bold text-center text-gray-900 dark:text-white mb-12"
          >
            Explore Our Guides
          </motion.h2>
          <div className="grid md:grid-cols-2 gap-8">
            {sections.map((section, index) => (
              <motion.div
                key={section.title}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                whileHover={{
                  scale: 1.05,
                  boxShadow: "0px 10px 25px rgba(0, 0, 0, 0.1)",
                  transition: { duration: 0.3 },
                }}
                transition={{
                  delay: index * 0.1,
                  duration: 0.7,
                  ease: "easeOut",
                }}
                onClick={() => handleSectionClick(index)}
                className="p-6 rounded-2xl bg-white dark:bg-slate-700 
                  transition-all duration-300 hover:shadow-xl cursor-pointer"
              >
                <div className="flex items-center mb-4">
                  <section.icon className="w-10 h-10 text-primary-500 
                    transition-colors duration-300 hover:text-blue-600 mr-4" />
                  <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                    {section.title}
                  </h3>
                </div>
                <p className="text-gray-600 dark:text-gray-300">{section.content}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* Section Details Modal */}
      <AnimatePresence>
        {selectedSection !== null && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50 p-4"
          >
            <motion.div
              initial={{ scale: 0.9, opacity: 0 }}
              animate={{ scale: 1, opacity: 1 }}
              exit={{ scale: 0.9, opacity: 0 }}
              className="bg-white dark:bg-slate-800 rounded-2xl p-8 max-w-2xl w-full relative"
            >
              <button
                onClick={closeModal}
                className="absolute top-4 right-4 text-gray-600 hover:text-gray-900 dark:text-gray-300 dark:hover:text-white"
              >
                <X className="w-6 h-6" />
              </button>
              <div className="flex items-center mb-6">
              {React.createElement(sections[selectedSection].icon, { className: "w-12 h-12 text-primary-500 mr-4" })}
                <h2 className="text-2xl font-bold text-gray-900 dark:text-white">
                  {sections[selectedSection].title}
                </h2>
              </div>
              <p className="text-gray-600 dark:text-gray-300 mb-6">
                {sections[selectedSection].content}
              </p>
              <div className="space-y-4">
                <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                  Key Details:
                </h3>
                <ul className="list-disc list-inside text-gray-700 dark:text-gray-200">
                  {sections[selectedSection].details.map((detail, idx) => (
                    <motion.li
                      key={idx}
                      initial={{ opacity: 0, x: -20 }}
                      animate={{ opacity: 1, x: 0 }}
                      transition={{ delay: idx * 0.1 }}
                    >
                      {detail}
                    </motion.li>
                  ))}
                </ul>
              </div>
            </motion.div>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Resources Section - Unchanged from previous version */}
      <section className="container mx-auto px-4 py-16">
        <motion.h2
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.7, ease: "easeOut" }}
          className="text-3xl font-bold text-center text-gray-900 dark:text-white mb-12"
        >
          Resources & Downloads
        </motion.h2>
        {/* Resources grid remains the same as in previous version */}
        <div className="grid md:grid-cols-2 gap-8">
          <motion.div
            whileHover={{ scale: 1.05 }}
            className="bg-white dark:bg-slate-700 p-6 rounded-2xl shadow-lg"
          >
            <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-4">
              API Documentation
            </h3>
            <p className="text-gray-600 dark:text-gray-300 mb-4">
              Detailed reference for developers looking to integrate with OceanVista's APIs. 
              Includes endpoints, parameters, and examples.
            </p>
            <a
              href="#"
              className="text-blue-600 dark:text-teal-400 hover:underline"
            >
              View API Docs
            </a>
          </motion.div>
          <motion.div
            whileHover={{ scale: 1.05 }}
            className="bg-white dark:bg-slate-700 p-6 rounded-2xl shadow-lg"
          >
            <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-4">
              User Guide
            </h3>
            <p className="text-gray-600 dark:text-gray-300 mb-4">
              Step-by-step guide to using OceanVista, including account setup, exploring beaches, 
              and utilizing safety features.
            </p>
            <a
              href="#"
              className="text-blue-600 dark:text-teal-400 hover:underline"
            >
              Download User Guide
            </a>
          </motion.div>
        </div>
      </section>
    </div>
  );
};