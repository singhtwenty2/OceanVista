import {
    Building2, Calendar, Users, TrendingUp,
    ShoppingBag, Plus, ArrowUp, ArrowDown
  } from 'lucide-react';

export const stats = [
    {
      label: 'Total Properties',
      value: '8',
      icon: Building2,
      trend: '+2',
      trendLabel: 'vs last month',
      trendUp: true,
    },
    {
      label: 'Active Vendors',
      value: '24',
      icon: ShoppingBag,
      trend: '+5',
      trendLabel: 'vs last month',
      trendUp: true,
    },
    {
      label: 'Medical Services',
      value: '12',
      icon: Plus,
      trend: '+1',
      trendLabel: 'vs last month',
      trendUp: true,
    },
    {
      label: 'Total Bookings',
      value: '284',
      icon: Calendar,
      trend: '+18%',
      trendLabel: 'vs last month',
      trendUp: true,
    },
    {
      label: 'Active Users',
      value: '892',
      icon: Users,
      trend: '+12%',
      trendLabel: 'vs last month',
      trendUp: true,
    },
    {
      label: 'Revenue',
      value: '$32,621',
      icon: TrendingUp,
      trend: '+24%',
      trendLabel: 'vs last month',
      trendUp: true,
    },
  ];