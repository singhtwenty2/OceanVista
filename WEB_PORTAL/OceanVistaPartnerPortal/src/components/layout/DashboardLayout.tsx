import React, { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { TopBar } from './TopBar';
import { Sidebar } from 'lucide-react';

export const DashboardLayout: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState(false);

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-slate-900">
      <TopBar 
        isSidebarCollapsed={isSidebarCollapsed} 
        onToggleSidebar={() => setIsSidebarCollapsed(!isSidebarCollapsed)} 
      />
      <div className="flex">
        <Sidebar isCollapsed={isSidebarCollapsed} />
        <main className={`flex-1 transition-all duration-300 ${
          isSidebarCollapsed ? 'ml-20' : 'ml-64'
        } min-h-[calc(100vh-4rem)]`}>
          <div className="p-6 pt-24">
            {children}
          </div>
          <Footer />
        </main>
      </div>
    </div>
  );
};