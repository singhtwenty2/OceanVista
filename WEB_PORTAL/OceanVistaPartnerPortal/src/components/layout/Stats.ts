import { Briefcase, Calendar, Clock, Store, TrendingUp, Users } from "lucide-react";

export const stats = [
  {
    label: 'Active Services',
    value: '248',
    icon: Briefcase,
    trend: '+12',
    trendLabel: 'new this month',
    trendUp: true,
  },
  {
    label: 'Service Providers',
    value: '1,284',
    icon: Store,
    trend: '+85',
    trendLabel: 'vs last month',
    trendUp: true,
  },
  {
    label: 'Total Bookings',
    value: '3,842',
    icon: Calendar,
    trend: '+18%',
    trendLabel: 'vs last month',
    trendUp: true,
  },
  {
    label: 'Active Users',
    value: '12,892',
    icon: Users,
    trend: '+22%',
    trendLabel: 'vs last month',
    trendUp: true,
  },
  {
    label: 'Monthly Revenue',
    value: '₹68,42,000',
    icon: TrendingUp,
    trend: '+24%',
    trendLabel: 'vs last month',
    trendUp: true,
  },
  {
    label: 'Avg. Response Time',
    value: '2.4 hrs',
    icon: Clock,
    trend: '-15%',
    trendLabel: 'vs last month',
    trendUp: true,
  },
];
