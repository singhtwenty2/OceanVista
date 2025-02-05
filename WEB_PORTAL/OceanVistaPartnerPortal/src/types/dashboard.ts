// Business Types
export interface Service {
    id: string;
    name: string;
    category: 'Shop' | 'Restaurant' | 'Activity' | 'Accommodation' | 'Vendor';
    rating: number;
    totalReviews: number;
    location: string;
    distance: string;
    status: 'active' | 'pending' | 'inactive';
    monthlyEarnings: number;
    image: string;
  }
  
  export interface BusinessStats {
    totalServices: number;
    activeServices: number;
    pendingApprovals: number;
    averageRating: number;
    totalEarnings: number;
    monthlyGrowth: number;
  }
  
  // Booking Types
  export interface Booking {
    id: string;
    serviceName: string;
    customerName: string;
    date: string;
    time: string;
    status: 'confirmed' | 'pending' | 'cancelled' | 'completed';
    amount: number;
    paymentStatus: 'paid' | 'pending' | 'refunded';
  }
  
  export interface BookingStats {
    totalBookings: number;
    confirmedBookings: number;
    pendingBookings: number;
    cancelledBookings: number;
    todayBookings: number;
    weeklyBookings: number;
  }
  
  // Earnings Types
  export interface EarningsPeriod {
    period: string;
    earnings: number;
    bookings: number;
    growth: number;
  }
  
  export interface EarningsStats {
    totalEarnings: number;
    monthlyEarnings: number;
    weeklyEarnings: number;
    pendingPayouts: number;
    averageOrderValue: number;
    topService: string;
  }
  
  // Message Types
  export interface Message {
    id: string;
    sender: string;
    content: string;
    timestamp: string;
    read: boolean;
    avatar: string;
    isCustomer: boolean;
  }
  
  export interface MessageStats {
    totalMessages: number;
    unreadMessages: number;
    averageResponseTime: string;
    customerSatisfaction: number;
  }
  
  // Subscription Types
  export interface SubscriptionPlan {
    id: string;
    name: string;
    price: number;
    billingCycle: 'monthly' | 'yearly';
    features: string[];
    isPopular?: boolean;
  }
  
  export interface SubscriptionStatus {
    currentPlan: string;
    status: 'active' | 'expired' | 'pending';
    nextBillingDate: string;
    amount: number;
  }