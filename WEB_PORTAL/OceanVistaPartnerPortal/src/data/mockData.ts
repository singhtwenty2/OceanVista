import {
    Service, BusinessStats, Booking, BookingStats,
    EarningsPeriod, EarningsStats, Message, MessageStats,
    SubscriptionPlan, SubscriptionStatus
  } from '../types/dashboard';
  
  // Business Mock Data
  export const mockServices: Service[] = [
    {
      id: '1',
      name: 'Sunset Beach Shack',
      category: 'Restaurant',
      rating: 4.8,
      totalReviews: 156,
      location: 'Calangute Beach',
      distance: '0.2 km',
      status: 'active',
      monthlyEarnings: 85000,
      image: '/api/placeholder/400/300'
    },
    {
      id: '2',
      name: 'Wave Riders Equipment',
      category: 'Shop',
      rating: 4.6,
      totalReviews: 89,
      location: 'Baga Beach',
      distance: '1.5 km',
      status: 'active',
      monthlyEarnings: 65000,
      image: '/api/placeholder/400/300'
    },
    {
      id: '3',
      name: 'Beach Front Villa',
      category: 'Accommodation',
      rating: 4.9,
      totalReviews: 45,
      location: 'Anjuna Beach',
      distance: '3.0 km',
      status: 'active',
      monthlyEarnings: 150000,
      image: '/api/placeholder/400/300'
    }
  ];
  
  export const mockBusinessStats: BusinessStats = {
    totalServices: 12,
    activeServices: 8,
    pendingApprovals: 3,
    averageRating: 4.7,
    totalEarnings: 985000,
    monthlyGrowth: 12.5
  };
  
  // Bookings Mock Data
  export const mockBookings: Booking[] = [
    {
      id: 'BK001',
      serviceName: 'Sunset Beach Shack',
      customerName: 'Rahul Sharma',
      date: '2025-01-31',
      time: '19:00',
      status: 'confirmed',
      amount: 2500,
      paymentStatus: 'paid'
    },
    {
      id: 'BK002',
      serviceName: 'Wave Riders Equipment',
      customerName: 'Priya Patel',
      date: '2025-02-01',
      time: '10:00',
      status: 'pending',
      amount: 1800,
      paymentStatus: 'pending'
    },
    {
      id: 'BK003',
      serviceName: 'Beach Front Villa',
      customerName: 'Alex Thompson',
      date: '2025-02-02',
      time: '14:00',
      status: 'confirmed',
      amount: 15000,
      paymentStatus: 'paid'
    }
  ];
  
  export const mockBookingStats: BookingStats = {
    totalBookings: 156,
    confirmedBookings: 120,
    pendingBookings: 25,
    cancelledBookings: 11,
    todayBookings: 8,
    weeklyBookings: 45
  };
  
  // Earnings Mock Data
  export const mockEarningsPeriods: EarningsPeriod[] = [
    {
      period: 'Jan 2025',
      earnings: 285000,
      bookings: 45,
      growth: 15.8
    },
    {
      period: 'Dec 2024',
      earnings: 246000,
      bookings: 38,
      growth: 12.3
    },
    {
      period: 'Nov 2024',
      earnings: 219000,
      bookings: 35,
      growth: 8.5
    }
  ];
  
  export const mockEarningsStats: EarningsStats = {
    totalEarnings: 3250000,
    monthlyEarnings: 285000,
    weeklyEarnings: 72000,
    pendingPayouts: 45000,
    averageOrderValue: 4500,
    topService: 'Beach Front Villa'
  };
  
  // Messages Mock Data
  export const mockMessages: Message[] = [
    {
      id: 'MSG001',
      sender: 'Rahul Sharma',
      content: 'Is the beach shack available for a private dinner tomorrow?',
      timestamp: '2025-01-31T10:30:00',
      read: false,
      avatar: '/api/placeholder/40/40',
      isCustomer: true
    },
    {
      id: 'MSG002',
      sender: 'Priya Patel',
      content: 'Can I get a refund for my cancelled booking?',
      timestamp: '2025-01-31T09:15:00',
      read: true,
      avatar: '/api/placeholder/40/40',
      isCustomer: true
    },
    {
      id: 'MSG003',
      sender: 'System',
      content: 'New booking request received for Wave Riders Equipment',
      timestamp: '2025-01-31T08:45:00',
      read: true,
      avatar: '/api/placeholder/40/40',
      isCustomer: false
    }
  ];
  
  export const mockMessageStats: MessageStats = {
    totalMessages: 158,
    unreadMessages: 12,
    averageResponseTime: '25 minutes',
    customerSatisfaction: 4.8
  };
  
  // Subscription Mock Data
  export const mockSubscriptionPlans: SubscriptionPlan[] = [
    {
      id: 'basic',
      name: 'Basic',
      price: 999,
      billingCycle: 'monthly',
      features: [
        'Up to 5 service listings',
        'Basic analytics',
        'Email support',
        'Standard visibility'
      ]
    },
    {
      id: 'pro',
      name: 'Professional',
      price: 2499,
      billingCycle: 'monthly',
      features: [
        'Up to 15 service listings',
        'Advanced analytics',
        'Priority support',
        'Enhanced visibility',
        'Custom branding'
      ],
      isPopular: true
    },
    {
      id: 'premium',
      name: 'Premium',
      price: 4999,
      billingCycle: 'monthly',
      features: [
        'Unlimited service listings',
        'Real-time analytics',
        '24/7 priority support',
        'Maximum visibility',
        'Custom branding',
        'API access'
      ]
    }
  ];
  
  export const mockSubscriptionStatus: SubscriptionStatus = {
    currentPlan: 'Professional',
    status: 'active',
    nextBillingDate: '2025-02-28',
    amount: 2499
  };