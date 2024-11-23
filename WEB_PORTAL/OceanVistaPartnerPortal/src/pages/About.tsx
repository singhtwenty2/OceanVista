import React from 'react';
import { motion } from 'framer-motion';
import { Compass, Users, Target, Heart } from 'lucide-react';

const team = [
  {
    name: 'Aryan Raj',
    role: 'Chief Executive Officer (CEO)',
    expertise: 'Strategic Leadership & Business Growth',
    image: 'https://firebasestorage.googleapis.com/v0/b/imessagebackend.appspot.com/o/leadership%2Fceo.jpeg?alt=media&token=c83b2a3b-11ba-45bf-8264-526f8251ea26',
    linkedin: 'https://www.linkedin.com/in/aryanraj544/'
  },
  {
    name: 'Amaresh Kumar',
    role: 'Chief Operating Officer (COO)',
    expertise: 'Operations Management & Marketing',
    image: 'https://firebasestorage.googleapis.com/v0/b/imessagebackend.appspot.com/o/leadership%2Fcoo.jpeg?alt=media&token=19b76da3-2fdc-4a72-9a3d-893c6b3b8aee',
    linkedin: 'https://www.linkedin.com/in/amaresh3107/'
  },
  {
    name: 'Anshu Banu',
    role: 'Creative Director',
    expertise: 'Design Strategy & Brand Development',
    image: 'https://firebasestorage.googleapis.com/v0/b/imessagebackend.appspot.com/o/leadership%2Fcdo.jpeg?alt=media&token=0f9efd61-5a83-41bf-ac92-6184008eb096',
    linkedin: 'https://www.linkedin.com/in/anshu-b-243a84264/'
  },
  {
    name: 'Aryan Singh',
    role: 'Lead Engineer',
    expertise: 'Software Architecture & Technical Innovation',
    image: 'https://firebasestorage.googleapis.com/v0/b/imessagebackend.appspot.com/o/leadership%2Fle.jpeg?alt=media&token=38c77d9b-1002-48b7-9e38-e0f8d57d14dd',
    linkedin: 'https://www.linkedin.com/in/singhtwenty2/'
  }
];

const values = [
  {
    icon: Compass,
    title: 'Innovation',
    description: 'Pioneering solutions in coastal property management',
  },
  {
    icon: Users,
    title: 'Community',
    description: 'Building lasting relationships with partners and guests',
  },
  {
    icon: Target,
    title: 'Excellence',
    description: 'Maintaining the highest standards in service delivery',
  },
  {
    icon: Heart,
    title: 'Sustainability',
    description: 'Protecting our oceans for future generations',
  },
];

export const About: React.FC = () => {
  const handleLinkedInClick = (linkedinUrl: string) => {
    window.open(linkedinUrl, '_blank', 'noopener,noreferrer');
  };

  return (
    <div className="min-h-screen bg-white dark:bg-slate-900 pt-24">
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
            Our Story
          </h1>
          <p className="text-xl text-gray-600 dark:text-gray-300 
            transition-all duration-300 hover:text-opacity-80">
            Founded in 2024, OceanVista has been transforming the way people experience
            coastal properties. Our mission is to connect exceptional properties with
            those seeking unforgettable ocean experiences.
          </p>
        </motion.div>
      </section>

      {/* Values Section */}
      <section className="bg-gray-50 dark:bg-slate-800 py-16">
        <div className="container mx-auto px-4">
          <motion.h2
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.7, ease: "easeOut" }}
            className="text-3xl font-bold text-center text-gray-900 dark:text-white mb-12"
          >
            Our Values
          </motion.h2>
          <div className="grid md:grid-cols-4 gap-8">
            {values.map((value, index) => (
              <motion.div
                key={value.title}
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                whileHover={{ 
                  scale: 1.05, 
                  boxShadow: "0px 10px 25px rgba(0, 0, 0, 0.1)",
                  transition: { duration: 0.3 }
                }}
                transition={{ 
                  delay: index * 0.1,
                  duration: 0.7,
                  ease: "easeOut"
                }}
                className="text-center p-6 rounded-2xl bg-white dark:bg-slate-700 
                  transition-all duration-300 hover:shadow-xl"
              >
                <div className="flex justify-center mb-4">
                  <value.icon className="w-12 h-12 text-primary-500 
                    transition-colors duration-300 hover:text-blue-600" />
                </div>
                <h3 className="text-xl font-semibold text-gray-900 dark:text-white mb-2">
                  {value.title}
                </h3>
                <p className="text-gray-600 dark:text-gray-300">{value.description}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* Team Section */}
      <section className="container mx-auto px-4 py-16">
        <motion.h2
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.7, ease: "easeOut" }}
          className="text-3xl font-bold text-center text-gray-900 dark:text-white mb-12"
        >
          Leadership Team
        </motion.h2>
        <div className="grid md:grid-cols-4 gap-8">
          {team.map((member, index) => (
            <motion.div
              key={member.name}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              whileHover={{ 
                scale: 1.05, 
                boxShadow: "0px 20px 35px rgba(0, 0, 0, 0.2)",
                transition: { duration: 0.3 }
              }}
              transition={{ 
                delay: index * 0.1,
                duration: 0.7,
                ease: "easeOut"
              }}
              onClick={() => handleLinkedInClick(member.linkedin)}
              className="text-center p-6 rounded-3xl bg-white dark:bg-slate-700 
                border border-gray-100 dark:border-slate-600 
                transition-all duration-300 hover:shadow-2xl
                cursor-pointer"
            >
              <div className="mb-4 relative">
                <div className="absolute -inset-1 bg-gradient-to-r from-blue-600 to-teal-400 
                  rounded-full blur opacity-25 group-hover:opacity-50 transition duration-500"></div>
                <img
                  src={member.image}
                  alt={member.name}
                  className="w-32 h-32 rounded-full mx-auto object-cover 
                    border-4 border-white dark:border-slate-700 
                    relative z-10 shadow-lg transition-transform duration-300"
                />
              </div>
              <h3 className="text-xl font-bold text-gray-900 dark:text-white mb-1
                transition-colors duration-300 hover:text-blue-600">
                {member.name}
              </h3>
              <p className="text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">
                {member.role}
              </p>
              <p className="text-xs text-gray-500 dark:text-gray-400 italic">
                {member.expertise}
              </p>
            </motion.div>
          ))}
        </div>
      </section>
    </div>
  );
};

export default About;