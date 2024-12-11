export interface Partner {
  id: string;
  name: string;
  location: string;
  logo?: string;
}

export interface Revenue {
  id: string;
  partnerId: string;
  amount: number;
  date: string;
}

export interface Booking {
  id: string;
  partnerId: string;
  value: number;
  status: 'confirmed' | 'canceled' | 'pending';
  date: string;
  customerLocation: string;
}

export interface RevenueStats {
  total: number;
  daily: number;
  weekly: number;
  monthly: number;
  growth: number;
}

export interface BookingStats {
  total: number;
  canceled: number;
  averageValue: number;
  conversionRate: number;
}

export interface ForecastData {
  prediction: number;
  confidence: number;
  trend: 'up' | 'down' | 'stable';
  insights: string[];
}