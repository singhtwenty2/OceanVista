"use client";

import React, { useState, useMemo } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { PieChart, Pie, Cell, BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, ComposedChart, Line } from 'recharts';
import { motion } from 'framer-motion';
import { 
  Users, 
  TrendingUp, 
  TrendingDown, 
  UserCheck, 
  UserX, 
  Activity,
  Target 
} from 'lucide-react';

// Simulated customer segmentation data generation
const generateCustomerData = () => {
  const customerSegments = [
    { name: 'High Value', count: 450, color: '#0088FE', lifetime_value: 75000 },
    { name: 'Medium Value', count: 750, color: '#00C49F', lifetime_value: 35000 },
    { name: 'Low Value', count: 300, color: '#FFBB28', lifetime_value: 15000 }
  ];

  const ageGroups = [
    { range: '18-25', percentage: 22, count: 330 },
    { range: '26-35', percentage: 35, count: 525 },
    { range: '36-45', percentage: 28, count: 420 },
    { range: '46+', percentage: 15, count: 225 }
  ];

  const churnPrediction = [
    { month: 'Jan', churn_rate: 4.2, retention_rate: 95.8 },
    { month: 'Feb', churn_rate: 3.8, retention_rate: 96.2 },
    { month: 'Mar', churn_rate: 5.1, retention_rate: 94.9 },
    { month: 'Apr', churn_rate: 4.5, retention_rate: 95.5 },
    { month: 'May', churn_rate: 3.5, retention_rate: 96.5 }
  ];

  const bookingPatterns = [
    { day: 'Weekday', percentage: 65 },
    { day: 'Weekend', percentage: 35 }
  ];

  const psychographicInsights = [
    { category: 'Adventure Seekers', percentage: 28 },
    { category: 'Budget Conscious', percentage: 32 },
    { category: 'Luxury Travelers', percentage: 22 },
    { category: 'Family Oriented', percentage: 18 }
  ];

  return {
    customerSegments,
    ageGroups,
    churnPrediction,
    bookingPatterns,
    psychographicInsights
  };
};

const formatAmount = (amount: number) => 
  new Intl.NumberFormat('en-IN', {
    style: 'currency',
    currency: 'INR',
    maximumFractionDigits: 0,
  }).format(amount);

export default function CustomerSegmentationAnalytics() {
  const [activeTab, setActiveTab] = useState<'segments' | 'age' | 'churn'>('segments');
  
  const data = useMemo(() => generateCustomerData(), []);
  
  // Calculations
  const totalCustomers = data.customerSegments.reduce((sum, seg) => sum + seg.count, 0);
  const averageLifetimeValue = data.customerSegments.reduce((sum, seg) => sum + (seg.lifetime_value * seg.count), 0) / totalCustomers;
  
  const predictedChurnRate = data.churnPrediction[data.churnPrediction.length - 1].churn_rate;
  const predictedRetentionRate = data.churnPrediction[data.churnPrediction.length - 1].retention_rate;
  
  const isPositiveRetention = predictedRetentionRate > 95;

  return (
    <Card className="col-span-2 bg-gradient-to-br from-white to-blue-50 dark:from-gray-900 dark:to-blue-950 shadow-3xl border-none overflow-hidden rounded-2xl">
      <CardHeader className="pb-2 relative z-10">
        <CardTitle className="flex justify-between items-center">
          <div className="flex items-center space-x-3">
            <Users className="w-8 h-8 text-primary" />
            <span className="text-3xl font-bold text-gray-900 dark:text-gray-100">Customer Intelligence</span>
          </div>
          <div className="flex space-x-2 bg-gray-100 dark:bg-gray-800 rounded-full p-1">
            {['segments', 'age', 'churn'].map((metric) => (
              <button
                key={metric}
                onClick={() => setActiveTab(metric as 'segments' | 'age' | 'churn')}
                className={`px-4 py-2 rounded-full text-sm font-semibold transition-all duration-300 ${
                  activeTab === metric 
                    ? 'bg-primary text-white shadow-lg' 
                    : 'text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700'
                }`}
              >
                {metric === 'segments' ? 'Segments' : metric === 'age' ? 'Demographics' : 'Churn'}
              </button>
            ))}
          </div>
        </CardTitle>
      </CardHeader>
      <CardContent className="relative z-10">
        <div className="grid gap-4 md:grid-cols-4 mb-6">
          {[
            { 
              label: 'Total Customers', 
              value: totalCustomers.toLocaleString(),
              icon: Users,
              color: 'text-primary'
            },
            { 
              label: 'Avg Lifetime Value', 
              value: formatAmount(averageLifetimeValue),
              icon: Activity,
              color: 'text-green-600'
            },
            { 
              label: 'Churn Rate', 
              value: `${predictedChurnRate}%`,
              icon: UserX,
              color: 'text-red-600'
            },
            { 
              label: 'Retention Rate', 
              value: `${predictedRetentionRate}%`,
              icon: UserCheck,
              color: isPositiveRetention ? 'text-green-600' : 'text-yellow-600'
            }
          ].map(({ label, value, icon: Icon, color }, index) => (
            <motion.div 
              key={label}
              initial={{ opacity: 0, translateY: 20 }}
              animate={{ opacity: 1, translateY: 0 }}
              transition={{ delay: index * 0.1 }}
              whileHover={{ scale: 1.05 }}
              className="bg-white dark:bg-gray-800 p-5 rounded-2xl shadow-lg hover:shadow-xl transition-all group"
            >
              <div className="flex justify-between items-center mb-2">
                <p className="text-sm text-muted-foreground group-hover:text-primary transition-colors">{label}</p>
                <Icon className={`w-5 h-5 ${color} opacity-70 group-hover:opacity-100 transition-all`} />
              </div>
              <p className={`text-2xl font-bold ${color} transition-colors`}>{value}</p>
            </motion.div>
          ))}
        </div>
        
        <div className="grid md:grid-cols-3 gap-4 mb-6">
          <motion.div 
            whileHover={{ scale: 1.03 }}
            className="bg-white dark:bg-gray-800 p-5 rounded-2xl shadow-lg col-span-2"
          >
            <div className="h-[360px]">
              <ResponsiveContainer width="100%" height="100%">
                {activeTab === 'segments' && (
                  <PieChart>
                    <Pie
                      data={data.customerSegments}
                      innerRadius="60%"
                      outerRadius="80%"
                      paddingAngle={2}
                      dataKey="count"
                      label
                    >
                      {data.customerSegments.map((entry, index) => (
                        <Cell key={`cell-${index}`} fill={entry.color} />
                      ))}
                    </Pie>
                    <Tooltip 
                      content={({ payload }) => {
                        if (payload && payload.length) {
                          const data = payload[0].payload;
                          return (
                            <div className="bg-white dark:bg-gray-800 p-4 rounded-xl shadow-lg border border-gray-200 dark:border-gray-700">
                              <p className="font-medium text-gray-600 dark:text-gray-300">{data.name}</p>
                              <p className="text-primary font-bold">{`Customers: ${data.count}`}</p>
                              <p className="text-green-600">{`Lifetime Value: ${formatAmount(data.lifetime_value)}`}</p>
                            </div>
                          );
                        }
                        return null;
                      }}
                    />
                  </PieChart>
                )}
                {activeTab === 'age' && (
                  <BarChart data={data.ageGroups}>
                    <CartesianGrid strokeDasharray="3 3" stroke="hsl(var(--border))" />
                    <XAxis dataKey="range" />
                    <YAxis />
                    <Tooltip />
                    <Bar dataKey="count" fill="hsl(var(--primary))" />
                  </BarChart>
                )}
                {activeTab === 'churn' && (
                  <ComposedChart data={data.churnPrediction}>
                    <CartesianGrid strokeDasharray="3 3" stroke="hsl(var(--border))" />
                    <XAxis dataKey="month" />
                    <YAxis yAxisId="left" />
                    <YAxis yAxisId="right" orientation="right" />
                    <Tooltip />
                    <Bar yAxisId="left" dataKey="churn_rate" fill="#ff6384" />
                    <Line yAxisId="right" type="monotone" dataKey="retention_rate" stroke="#10b981" />
                  </ComposedChart>
                )}
              </ResponsiveContainer>
            </div>
          </motion.div>
          
          <motion.div 
            whileHover={{ scale: 1.03 }}
            className="bg-white dark:bg-gray-800 p-5 rounded-2xl shadow-lg flex flex-col justify-between"
          >
            <div>
              <h3 className="text-lg font-semibold text-gray-800 dark:text-gray-200 mb-4">Behavioral Insights</h3>
              <div className="space-y-3">
                <div>
                  <p className="text-sm text-muted-foreground">Booking Patterns</p>
                  {data.bookingPatterns.map(pattern => (
                    <div key={pattern.day} className="flex justify-between items-center">
                      <p>{pattern.day}</p>
                      <p className="font-bold">{pattern.percentage}%</p>
                    </div>
                  ))}
                </div>
                <div className="mt-4">
                  <p className="text-sm text-muted-foreground">Psychographic Segments</p>
                  {data.psychographicInsights.map(insight => (
                    <div key={insight.category} className="flex justify-between items-center">
                      <p>{insight.category}</p>
                      <p className="font-bold">{insight.percentage}%</p>
                    </div>
                  ))}
                </div>
              </div>
            </div>
            <div className="mt-4 text-sm text-muted-foreground italic">
              Insights derived from comprehensive customer analysis
            </div>
          </motion.div>
        </div>
      </CardContent>
    </Card>
  );
}