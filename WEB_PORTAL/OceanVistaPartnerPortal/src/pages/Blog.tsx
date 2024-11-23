import React from 'react';
import { motion } from 'framer-motion';
import { Clock, Tag, Bookmark } from 'lucide-react';

const blogPosts = [
  {
    title: 'Top 10 Coastal Destinations in India',
    author: 'Aryan Raj',
    date: 'November 15, 2024',
    tags: ['Travel', 'Beaches', 'Destinations'],
    excerpt:
      'Explore the most stunning coastal destinations in India, known for their pristine beaches and breathtaking sunsets.',
    image: 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=400&h=300&q=80',
  },
  {
    title: 'The Importance of Sustainability in Beach Tourism',
    author: 'Amaresh Kumar',
    date: 'November 10, 2024',
    tags: ['Sustainability', 'Environment', 'Tourism'],
    excerpt:
      'Discover how sustainable practices can preserve our beautiful beaches for future generations.',
    image: 'https://images.pexels.com/photos/635279/pexels-photo-635279.jpeg?auto=compress&cs=tinysrgb&w=800',
  },
  {
    title: 'How to Manage Beachfront Properties Effectively',
    author: 'Anshu Banu',
    date: 'November 5, 2024',
    tags: ['Business', 'Property Management', 'Tips'],
    excerpt:
      'Learn effective strategies for managing and maintaining beachfront properties to maximize their value.',
    image: 'https://images.unsplash.com/photo-1506765515384-028b60a970df?auto=format&fit=crop&w=400&h=300&q=80',
  },
  {
    title: 'Safety Tips for Visitors to Indian Beaches',
    author: 'Aryan Singh',
    date: 'November 1, 2024',
    tags: ['Safety', 'Travel Tips', 'Beaches'],
    excerpt:
      'Ensure a safe and enjoyable beach experience with these essential safety tips for travelers.',
    image: 'https://images.pexels.com/photos/163872/italy-cala-gonone-air-sky-163872.jpeg?auto=compress&cs=tinysrgb&w=800',
  },
];

export const Blog: React.FC = () => {
  return (
    <div className="min-h-screen bg-white dark:bg-slate-900 pt-24">
      {/* Hero Section */}
      <section className="container mx-auto px-4 py-16">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.7, ease: 'easeOut' }}
          className="text-center max-w-3xl mx-auto"
        >
          <h1 className="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white mb-6 
            bg-clip-text text-transparent bg-gradient-to-r from-blue-600 to-teal-400">
            Blog & Insights
          </h1>
          <p className="text-xl text-gray-600 dark:text-gray-300 
            transition-all duration-300 hover:text-opacity-80">
            Stay updated with the latest trends, tips, and insights in beach tourism, 
            coastal property management, and sustainability.
          </p>
        </motion.div>
      </section>

      {/* Blog Grid */}
      <section className="py-16 bg-gray-50 dark:bg-slate-800">
        <div className="container mx-auto px-4">
          <motion.h2
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.7, ease: 'easeOut' }}
            className="text-3xl font-bold text-center text-gray-900 dark:text-white mb-12"
          >
            Latest Articles
          </motion.h2>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
            {blogPosts.map((post, index) => (
              <motion.div
                key={post.title}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                whileHover={{ scale: 1.05, boxShadow: '0px 10px 25px rgba(0, 0, 0, 0.1)' }}
                transition={{
                  delay: index * 0.1,
                  duration: 0.7,
                  ease: 'easeOut',
                }}
                className="rounded-lg overflow-hidden bg-white dark:bg-slate-700 
                  transition-all duration-300 hover:shadow-xl"
              >
                <img
                  src={post.image}
                  alt={post.title}
                  className="w-full h-48 object-cover"
                />
                <div className="p-6">
                  <h3 className="text-lg font-bold text-gray-900 dark:text-white mb-2
                    transition-colors duration-300 hover:text-blue-600">
                    {post.title}
                  </h3>
                  <p className="text-sm text-gray-600 dark:text-gray-300 mb-4">{post.excerpt}</p>
                  <div className="flex items-center justify-between text-sm text-gray-500 dark:text-gray-400">
                    <span className="flex items-center">
                      <Clock className="w-4 h-4 mr-1" /> {post.date}
                    </span>
                    <span className="flex items-center">
                      <Tag className="w-4 h-4 mr-1" /> {post.tags[0]}
                    </span>
                  </div>
                </div>
              </motion.div>
            ))}
          </div>
        </div>
      </section>
    </div>
  );
};
