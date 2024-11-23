import React, { useRef } from 'react';
import { motion, useScroll, useTransform } from 'framer-motion';
import { Hero } from '../components/Hero';
import { Features } from '../components/Features';
import { Roadmap } from '../components/Testimonials';
import { CallToAction } from '../components/CallToAction';

export const Home: React.FC = () => {
  const containerRef = useRef<HTMLDivElement>(null);
  const { scrollYProgress } = useScroll({
    target: containerRef,
    offset: ["start start", "end end"]
  });

  const opacity = useTransform(scrollYProgress, [0, 0.5], [1, 0]);
  const scale = useTransform(scrollYProgress, [0, 0.5], [1, 0.8]);

  return (
    <main ref={containerRef} className="relative">
      <motion.div style={{ opacity, scale }}>
        <Hero />
      </motion.div>
      <Features />
      <Roadmap />
      <CallToAction />
    </main>
  );
};