import React from 'react';
import { motion } from 'framer-motion';
import {
  Building2,
  Calendar,
  Users,
  TrendingUp,
  ShoppingBag,
  Plus,
} from 'lucide-react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

const stats = [
  {
    label: 'Total Properties',
    value: '8',
    icon: Building2,
    trend: '+2 this month',
    trendUp: true,
  },
  {
    label: 'Active Vendors',
    value: '24',
    icon: ShoppingBag,
    trend: '+5 this month',
    trendUp: true,
  },
  {
    label: 'Medical Services',
    value: '12',
    icon: Plus,
    trend: '+1 this month',
    trendUp: true,
  },
  {
    label: 'Total Bookings',
    value: '284',
    icon: Calendar,
    trend: '+18% vs last month',
    trendUp: true,
  },
  {
    label: 'Active Users',
    value: '892',
    icon: Users,
    trend: '+12% vs last month',
    trendUp: true,
  },
  {
    label: 'Revenue',
    value: '$32,621',
    icon: TrendingUp,
    trend: '+24% vs last month',
    trendUp: true,
  },
];

const chartData = [
  { name: 'Jan', bookings: 65, revenue: 4000 },
  { name: 'Feb', bookings: 78, revenue: 4800 },
  { name: 'Mar', bookings: 82, revenue: 5200 },
  { name: 'Apr', bookings: 95, revenue: 6000 },
  { name: 'May', bookings: 110, revenue: 7200 },
  { name: 'Jun', bookings: 102, revenue: 6800 },
];