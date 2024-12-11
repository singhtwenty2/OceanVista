"use client";

import React, { useState } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { PieChart, Pie, Cell, ResponsiveContainer, Legend, Tooltip } from 'recharts';
import { motion } from 'framer-motion';
import { 
  Calendar, 
  TrendingUp, 
  CheckCircle, 
  XCircle, 
  Clock, 
  BarChart2 
} from 'lucide-react';
import { generateBookingData } from '@/lib/data';

const generateEnhancedBookingData = (days: number) => {
  const bookings = generateBookingData(days);
  
  const bookingStatuses = bookings.reduce((acc: any, curr) => {
    acc[curr.status] = {
      count: (acc[curr.status]?.count || 0) + 1,
      totalValue: (acc[curr.status]?.totalValue || 0) + curr.value
    };
    return acc;
  }, {});

  const statusData = Object.entries(bookingStatuses).map(([name, data]: [string, any]) => ({
    name: name.charAt(0).toUpperCase() + name.slice(1),
    count: data.count,
    value: data.count,
    totalValue: data.totalValue,
    averageValue: data.totalValue / data.count
  }));

  return { bookings, statusData };
};

const COLORS = ['#10B981', '#EF4444', '#F59E0B'];
const STATUS_ICONS = {
  'Confirmed': CheckCircle,
  'Pending': Clock,
  'Cancelled': XCircle
};

export default function PremiumBookingStats() {
  const [activeView, setActiveView] = useState<'count' | 'value'>('count');
  const { bookings, statusData } = generateEnhancedBookingData(90);

  const totalBookings = bookings.length;
  const averageBookingValue = bookings.reduce((sum, booking) => sum + booking.value, 0) / totalBookings;
  
  const monthOverMonthGrowth = ((bookings.length / 30) * 100).toFixed(2);
  const highestValueBooking = Math.max(...bookings.map(b => b.value));

  return (
    <Card className="h-full w-full bg-white dark:bg-gray-900 shadow-lg rounded-2xl overflow-hidden">
      <CardHeader className="p-4 border-b border-gray-200 dark:border-gray-700">
        <CardTitle className="flex justify-between items-center">
          <div className="flex items-center space-x-3">
            <BarChart2 className="w-6 h-6 text-primary" />
            <span className="text-xl font-bold text-gray-900 dark:text-gray-100">Booking Insights</span>
          </div>
          <div className="flex space-x-2 bg-gray-100 dark:bg-gray-800 rounded-full p-1">
            {['count', 'value'].map((metric) => (
              <button
                key={metric}
                onClick={() => setActiveView(metric as 'count' | 'value')}
                className={`px-3 py-1 rounded-full text-xs font-semibold transition-all duration-300 ${
                  activeView === metric 
                    ? 'bg-primary text-white' 
                    : 'text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700'
                }`}
              >
                {metric === 'count' ? 'Count' : 'Value'}
              </button>
            ))}
          </div>
        </CardTitle>
      </CardHeader>
      <CardContent className="p-4 space-y-4">
        <div className="grid grid-cols-2 gap-4">
          {[
            { 
              label: 'Total Bookings', 
              value: totalBookings,
              icon: Calendar,
              color: 'text-primary'
            },
            { 
              label: 'Avg Booking Value', 
              value: new Intl.NumberFormat('en-IN', {
                style: 'currency',
                currency: 'INR',
                maximumFractionDigits: 0,
              }).format(averageBookingValue),
              icon: TrendingUp,
              color: 'text-green-600'
            }
          ].map(({ label, value, icon: Icon, color }, index) => (
            <div 
              key={label}
              className="bg-gray-50 dark:bg-gray-800 p-3 rounded-xl shadow-sm"
            >
              <div className="flex justify-between items-center mb-1">
                <p className="text-xs text-muted-foreground">{label}</p>
                <Icon className={`w-4 h-4 ${color} opacity-70`} />
              </div>
              <p className={`text-lg font-bold ${color}`}>{value}</p>
            </div>
          ))}
        </div>

        <div className="flex space-x-4">
          <div className="flex-grow bg-gray-50 dark:bg-gray-800 rounded-xl p-2">
            <div className="h-[250px]">
              <ResponsiveContainer width="100%" height="100%">
                <PieChart>
                  <Pie
                    data={statusData}
                    cx="50%"
                    cy="50%"
                    innerRadius={40}
                    outerRadius={60}
                    paddingAngle={5}
                    dataKey={activeView === 'count' ? 'count' : 'totalValue'}
                  >
                    {statusData.map((entry, index) => (
                      <Cell key={`cell-${index}`} fill={COLORS[index]} />
                    ))}
                  </Pie>
                  <Tooltip 
                    content={({ active, payload }) => {
                      if (active && payload && payload.length) {
                        const data = payload[0].payload;
                        const Icon = STATUS_ICONS[data.name as keyof typeof STATUS_ICONS];
                        return (
                          <div className="bg-white dark:bg-gray-800 p-3 rounded-lg shadow-lg border border-gray-200 dark:border-gray-700">
                            <div className="flex items-center mb-1">
                              {Icon && <Icon className="mr-2 w-4 h-4 text-primary" />}
                              <p className="font-semibold text-sm">{data.name}</p>
                            </div>
                            {activeView === 'count' ? (
                              <>
                                <p className="text-xs">Bookings: {data.count}</p>
                                <p className="text-xs">Avg Value: ₹{data.averageValue.toFixed(0)}</p>
                              </>
                            ) : (
                              <>
                                <p className="text-xs">Total Value: ₹{data.totalValue.toFixed(0)}</p>
                                <p className="text-xs">Avg Booking: ₹{data.averageValue.toFixed(0)}</p>
                              </>
                            )}
                          </div>
                        );
                      }
                      return null;
                    }}
                  />
                  <Legend 
                    height={30}
                    iconSize={10}
                    layout="horizontal"
                    verticalAlign="bottom"
                    wrapperStyle={{ 
                      fontSize: '0.7rem',
                      color: 'hsl(var(--muted-foreground))'
                    }}
                  />
                </PieChart>
              </ResponsiveContainer>
            </div>
          </div>
          
          <div className="w-1/3 bg-gray-50 dark:bg-gray-800 rounded-xl p-3 flex flex-col justify-between">
            <div>
              <h3 className="text-sm font-semibold text-gray-800 dark:text-gray-200 mb-3">Additional Insights</h3>
              <div className="space-y-2">
                <div>
                  <p className="text-xs text-muted-foreground">Month-over-Month Growth</p>
                  <p className="text-base font-bold text-green-600">{monthOverMonthGrowth}%</p>
                </div>
                <div>
                  <p className="text-xs text-muted-foreground">Highest Value Booking</p>
                  <p className="text-base font-bold text-blue-600">
                    ₹{new Intl.NumberFormat('en-IN', {
                      maximumFractionDigits: 0,
                    }).format(highestValueBooking)}
                  </p>
                </div>
              </div>
            </div>
            <div className="mt-2 text-xs text-muted-foreground italic">
              Insights based on recent trends
            </div>
          </div>
        </div>
      </CardContent>
    </Card>
  );
}