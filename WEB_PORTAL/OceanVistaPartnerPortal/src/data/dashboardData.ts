// types.ts
export interface ServiceMetric {
    id: string;
    label: string;
    value: string | number;
    change: string;
    changeType: 'increase' | 'decrease';
    description: string;
  }
  
  export interface RecentBooking {
    id: string;
    customerName: string;
    service: string;
    amount: string;
    status: 'completed' | 'pending' | 'cancelled';
    dateTime: string;
    details: {
      location: string;
      duration: string;
      serviceFee: string;
      customerContact: string;
    };
  }
  
  export interface ServiceCategory {
    id: string;
    name: string;
    activeProviders: number;
    totalBookings: number;
    revenue: string;
    growth: string;
  }
  
  // data.ts
  import { ServiceMetric, RecentBooking, ServiceCategory } from './types';
  
  export const dashboardMetrics: ServiceMetric[] = [
    {
      id: 'total-earnings',
      label: 'Total Earnings',
      value: '₹1,42,850',
      change: '+12.5%',
      changeType: 'increase',
      description: 'Total earnings from all services this month'
    },
    {
      id: 'active-bookings',
      label: 'Active Bookings',
      value: 48,
      change: '+8.2%',
      changeType: 'increase',
      description: 'Currently ongoing service bookings'
    },
    {
      id: 'completion-rate',
      label: 'Completion Rate',
      value: '94.2%',
      change: '+2.1%',
      changeType: 'increase',
      description: 'Service completion rate in last 30 days'
    },
    {
      id: 'response-time',
      label: 'Avg. Response Time',
      value: '18 mins',
      change: '-25%',
      changeType: 'decrease',
      description: 'Average time to respond to booking requests'
    }
  ];
  
  export const recentBookings: RecentBooking[] = [
    {
      id: 'bk-001',
      customerName: 'Rahul Sharma',
      service: 'Deep House Cleaning',
      amount: '₹2,800',
      status: 'completed',
      dateTime: '2024-01-31 14:30',
      details: {
        location: 'Sector 62, Noida',
        duration: '4 hours',
        serviceFee: '₹2,800',
        customerContact: '+91 98765 43210'
      }
    },
    {
      id: 'bk-002',
      customerName: 'Priya Patel',
      service: 'Car Service',
      amount: '₹3,500',
      status: 'pending',
      dateTime: '2024-01-31 16:00',
      details: {
        location: 'Indirapuram, Ghaziabad',
        duration: '2 hours',
        serviceFee: '₹3,500',
        customerContact: '+91 98765 43211'
      }
    },
    {
      id: 'bk-003',
      customerName: 'Amit Kumar',
      service: 'Salon at Home',
      amount: '₹1,200',
      status: 'cancelled',
      dateTime: '2024-01-31 11:00',
      details: {
        location: 'Vasant Kunj, Delhi',
        duration: '1 hour',
        serviceFee: '₹1,200',
        customerContact: '+91 98765 43212'
      }
    }
  ];
  
  export const serviceCategories: ServiceCategory[] = [
    {
      id: 'cat-001',
      name: 'Home Cleaning',
      activeProviders: 45,
      totalBookings: 1248,
      revenue: '₹3,24,500',
      growth: '+15%'
    },
    {
      id: 'cat-002',
      name: 'Car Services',
      activeProviders: 32,
      totalBookings: 856,
      revenue: '₹2,85,600',
      growth: '+18%'
    },
    {
      id: 'cat-003',
      name: 'Salon Services',
      activeProviders: 28,
      totalBookings: 724,
      revenue: '₹1,95,800',
      growth: '+12%'
    },
    {
      id: 'cat-004',
      name: 'Appliance Repair',
      activeProviders: 36,
      totalBookings: 648,
      revenue: '₹1,56,400',
      growth: '+8%'
    }
  ];