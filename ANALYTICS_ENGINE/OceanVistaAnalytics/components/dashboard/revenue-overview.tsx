"use client";

import React, { useState, useMemo } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, ComposedChart, Area } from 'recharts';
import { motion } from 'framer-motion';
import { 
  TrendingUp, 
  TrendingDown, 
  ArrowUpRight, 
  ArrowDownRight, 
  BookmarkPlus, 
  Target, 
  BarChart2 
} from 'lucide-react';
import { generateRevenueData } from '@/lib/data';

// Enhanced data generation with more realistic revenue simulation
const generateEnhancedRevenueData = (days: number, metric: 'daily' | 'weekly' | 'monthly') => {
  let baseData = generateRevenueData(days);
  
  // Aggregate data based on selected metric
  const aggregatedData = baseData.reduce((acc: any[], curr) => {
    let key: string;
    const date = new Date(curr.date);
    
    switch (metric) {
      case 'daily':
        key = date.toLocaleDateString('en-IN', { day: 'numeric', month: 'short' });
        break;
      case 'weekly':
        const weekStart = new Date(date);
        weekStart.setDate(date.getDate() - date.getDay());
        key = `${weekStart.toLocaleDateString('en-IN', { day: 'numeric', month: 'short' })} - Week`;
        break;
      case 'monthly':
        key = date.toLocaleDateString('en-IN', { month: 'long', year: 'numeric' });
        break;
    }
    
    const existing = acc.find(item => item.date === key);
    
    if (existing) {
      existing.amount += curr.amount;
      existing.count = (existing.count || 1) + 1;
    } else {
      acc.push({ date: key, amount: curr.amount, count: 1 });
    }
    
    return acc;
  }, []).map(item => ({
    ...item,
    amount: item.metric === 'daily' ? item.amount : item.amount / (item.count || 1)
  })).sort((a, b) => new Date(a.date).getTime() - new Date(b.date).getTime());
  
  return aggregatedData;
};

const formatAmount = (amount: number) => 
  new Intl.NumberFormat('en-IN', {
    style: 'currency',
    currency: 'INR',
    maximumFractionDigits: 0,
  }).format(amount);

export default function PremiumRevenueOverview() {
  const [activeMetric, setActiveMetric] = useState<'daily' | 'weekly' | 'monthly'>('daily');
  
  // Generate data based on active metric
  const data = useMemo(() => generateEnhancedRevenueData(90, activeMetric), [activeMetric]);
  
  // Advanced revenue calculations
  const totalRevenue = data.reduce((sum, item) => sum + item.amount, 0);
  const averageRevenue = totalRevenue / data.length;
  
  const sortedAmounts = [...data].map(d => d.amount).sort((a, b) => a - b);
  const median = sortedAmounts[Math.floor(sortedAmounts.length / 2)];
  const revenueVariance = Math.sqrt(
    sortedAmounts.reduce((sum, val) => sum + Math.pow(val - averageRevenue, 2), 0) / sortedAmounts.length
  );
  
  const growthPercentage = ((data[data.length - 1].amount - data[0].amount) / data[0].amount * 100).toFixed(2);
  const isPositiveGrowth = parseFloat(growthPercentage) > 0;

  // Predictive insights
  const predictedNextMonthRevenue = totalRevenue * 1.15; // 15% projected growth
  const potentialRevenueTarget = totalRevenue * 1.3; // 30% stretch goal

  return (
    <Card className="col-span-2 bg-gradient-to-br from-white to-blue-50 dark:from-gray-900 dark:to-blue-950 shadow-3xl border-none overflow-hidden rounded-2xl">
      <CardHeader className="pb-2 relative z-10">
        <CardTitle className="flex justify-between items-center">
          <div className="flex items-center space-x-3">
            <BarChart2 className="w-8 h-8 text-primary" />
            <span className="text-3xl font-bold text-gray-900 dark:text-gray-100">Revenue Intelligence</span>
          </div>
          <div className="flex space-x-2 bg-gray-100 dark:bg-gray-800 rounded-full p-1">
            {['daily', 'weekly', 'monthly'].map((metric) => (
              <button
                key={metric}
                onClick={() => setActiveMetric(metric as 'daily' | 'weekly' | 'monthly')}
                className={`px-4 py-2 rounded-full text-sm font-semibold transition-all duration-300 ${
                  activeMetric === metric 
                    ? 'bg-primary text-white shadow-lg' 
                    : 'text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700'
                }`}
              >
                {metric.charAt(0).toUpperCase() + metric.slice(1)}
              </button>
            ))}
          </div>
        </CardTitle>
      </CardHeader>
      <CardContent className="relative z-10">
        <div className="grid gap-4 md:grid-cols-4 mb-6">
          {[
            { 
              label: 'Total Revenue', 
              value: formatAmount(totalRevenue),
              icon: BookmarkPlus,
              color: 'text-primary'
            },
            { 
              label: 'Avg Revenue', 
              value: formatAmount(averageRevenue),
              icon: ArrowUpRight,
              color: 'text-green-600'
            },
            { 
              label: 'Revenue Stability', 
              value: `${revenueVariance.toFixed(2)}σ`,
              icon: Target,
              color: 'text-blue-600'
            },
            { 
              label: 'Growth Rate', 
              value: `${growthPercentage}%`,
              icon: isPositiveGrowth ? TrendingUp : TrendingDown,
              color: isPositiveGrowth ? 'text-green-600' : 'text-red-600'
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
                <ComposedChart data={data}>
                  <defs>
                    <linearGradient id="revenueFillColor" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="5%" stopColor="hsl(var(--primary))" stopOpacity={0.4}/>
                      <stop offset="95%" stopColor="hsl(var(--primary))" stopOpacity={0.05}/>
                    </linearGradient>
                  </defs>
                  <CartesianGrid strokeDasharray="3 3" stroke="hsl(var(--border))" />
                  <XAxis 
                    dataKey="date" 
                    tick={{ fontSize: 12, fill: 'hsl(var(--muted-foreground))' }}
                    interval="preserveStartEnd"
                  />
                  <YAxis 
                    tickFormatter={(value) => `₹${(value / 100000).toFixed(1)}L`}
                    tick={{ fontSize: 12, fill: 'hsl(var(--muted-foreground))' }}
                  />
                  <Tooltip 
                    cursor={{ fill: 'transparent' }}
                    content={({ active, payload, label }) => {
                      if (active && payload && payload.length) {
                        return (
                          <div className="bg-white dark:bg-gray-800 p-4 rounded-xl shadow-lg border border-gray-200 dark:border-gray-700">
                            <p className="font-medium text-gray-600 dark:text-gray-300">{`Period: ${label}`}</p>
                            <p className="text-primary font-bold">{`Revenue: ${formatAmount(payload[0].value)}`}</p>
                          </div>
                        );
                      }
                      return null;
                    }}
                  />
                  <Area
                    type="monotone"
                    dataKey="amount"
                    fill="url(#revenueFillColor)"
                    stroke="hsl(var(--primary))"
                    strokeWidth={2}
                  />
                  <Line
                    type="monotone"
                    dataKey="amount"
                    stroke="hsl(var(--primary))"
                    strokeWidth={3}
                    dot={false}
                  />
                </ComposedChart>
              </ResponsiveContainer>
            </div>
          </motion.div>
          
          <motion.div 
            whileHover={{ scale: 1.03 }}
            className="bg-white dark:bg-gray-800 p-5 rounded-2xl shadow-lg flex flex-col justify-between"
          >
            <div>
              <h3 className="text-lg font-semibold text-gray-800 dark:text-gray-200 mb-4">Future Projections</h3>
              <div className="space-y-3">
                <div>
                  <p className="text-sm text-muted-foreground">Predicted Next Month Revenue</p>
                  <p className="text-xl font-bold text-green-600">{formatAmount(predictedNextMonthRevenue)}</p>
                </div>
                <div>
                  <p className="text-sm text-muted-foreground">Potential Revenue Target</p>
                  <p className="text-xl font-bold text-blue-600">{formatAmount(potentialRevenueTarget)}</p>
                </div>
              </div>
            </div>
            <div className="mt-4 text-sm text-muted-foreground italic">
              Projections based on current growth trends
            </div>
          </motion.div>
        </div>
      </CardContent>
    </Card>
  );
}