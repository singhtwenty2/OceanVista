import { Partner, Revenue, Booking, PartnerPerformance } from './types';

export const partners: Partner[] = [
  {
    id: 'taj-hotels',
    name: 'Taj Hotels',
    location: 'Mumbai',
    logo: 'https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?w=400',
    category: 'Luxury',
    contactEmail: 'partnerships@tajhotels.com',
  },
  {
    id: 'oberoi',
    name: 'The Oberoi Group',
    location: 'Delhi',
    logo: 'https://images.unsplash.com/photo-1564501049412-61c2a3083791?w=400',
    category: 'Ultra Luxury',
    contactEmail: 'partner-relations@oberoi.com',
  },
  {
    id: 'lemon-tree',
    name: 'Lemon Tree Hotels',
    location: 'Bangalore',
    logo: 'https://images.unsplash.com/photo-1571896349842-33c89424de2d?w=400',
    category: 'Mid-Range',
    contactEmail: 'partnerships@lemontree.com',
  },
];

// Enhanced Revenue Generation
export const generateRevenueData = (days: number): Revenue[] => {
  const data: Revenue[] = [];
  const now = new Date();

  for (let i = 0; i < days; i++) {
    partners.forEach((partner) => {
      const date = new Date(now);
      date.setDate(date.getDate() - i);
      
      // More nuanced revenue generation based on partner category
      const baseAmount = partner.category === 'Luxury' ? 800000 :
                         partner.category === 'Ultra Luxury' ? 1200000 : 500000;
      
      const amount = Math.floor(
        baseAmount + 
        (Math.random() * baseAmount * 0.5) - 
        (baseAmount * 0.25)
      );
      
      data.push({
        id: `rev-${partner.id}-${i}`,
        partnerId: partner.id,
        amount,
        date: date.toISOString(),
        category: partner.category,
      });
    });
  }

  return data;
};

// Enhanced Booking Generation
export const generateBookingData = (days: number): Booking[] => {
  const data: Booking[] = [];
  const now = new Date();
  const locations = ['Mumbai', 'Delhi', 'Bangalore', 'Chennai', 'Kolkata'];
  const statuses: ('confirmed' | 'canceled' | 'pending')[] = ['confirmed', 'canceled', 'pending'];

  for (let i = 0; i < days; i++) {
    partners.forEach(partner => {
      // Vary bookings based on partner category
      const baseBookings = partner.category === 'Luxury' ? 15 :
                           partner.category === 'Ultra Luxury' ? 10 : 25;
      
      const bookingsPerDay = Math.floor(Math.random() * 5 + baseBookings);
      
      for (let j = 0; j < bookingsPerDay; j++) {
        const date = new Date(now);
        date.setDate(date.getDate() - i);
        
        const value = partner.category === 'Ultra Luxury' ? 
          Math.floor(Math.random() * (25000 - 15000) + 15000) :
          partner.category === 'Luxury' ?
          Math.floor(Math.random() * (15000 - 8000) + 8000) :
          Math.floor(Math.random() * (8000 - 3000) + 3000);
        
        const status = statuses[Math.floor(Math.random() * statuses.length)];
        const location = locations[Math.floor(Math.random() * locations.length)];
        
        data.push({
          id: `book-${partner.id}-${i}-${j}`,
          partnerId: partner.id,
          value,
          status,
          date: date.toISOString(),
          customerLocation: location,
          category: partner.category,
        });
      }
    });
  }

  return data;
};

// Comprehensive Partner Performance Generation
export const generatePartnerData = (days: number): PartnerPerformance[] => {
  const revenueData = generateRevenueData(days);
  const bookingData = generateBookingData(days);

  return partners.map(partner => {
    // Filter data for specific partner
    const partnerRevenue = revenueData.filter(r => r.partnerId === partner.id);
    const partnerBookings = bookingData.filter(b => b.partnerId === partner.id);

    // Calculate performance metrics
    const totalRevenue = partnerRevenue.reduce((sum, rev) => sum + rev.amount, 0);
    const totalBookings = partnerBookings.length;
    const confirmedBookings = partnerBookings.filter(b => b.status === 'confirmed').length;
    const canceledBookings = partnerBookings.filter(b => b.status === 'canceled').length;
    
    // Calculate average booking value
    const avgBookingValue = totalRevenue / confirmedBookings || 0;
    
    // Calculate cancellation rate
    const cancellationRate = (canceledBookings / totalBookings) * 100;
    
    // Calculate revenue trend
    const revenueTrend = partnerRevenue.reduce((acc, curr, index, arr) => {
      if (index > 0) {
        const change = ((curr.amount - arr[index-1].amount) / arr[index-1].amount) * 100;
        acc.push(change);
      }
      return acc;
    }, [] as number[]);

    const averageRevenueTrend = revenueTrend.reduce((sum, val) => sum + val, 0) / revenueTrend.length || 0;

    return {
      partnerId: partner.id,
      name: partner.name,
      category: partner.category,
      totalRevenue,
      totalBookings,
      confirmedBookings,
      canceledBookings,
      avgBookingValue,
      cancellationRate,
      averageRevenueTrend,
      location: partner.location,
      contactEmail: partner.contactEmail,
    };
  });
};

export const generateCustomerData = (days: number): CustomerInsights[] => {
  const bookingData = generateBookingData(days);
  const locations = ['Mumbai', 'Delhi', 'Bangalore', 'Chennai', 'Kolkata'];
  const categories = ['Luxury', 'Mid-Range', 'Ultra Luxury'];

  return locations.map(location => {
    const locationBookings = bookingData.filter(b => b.customerLocation === location);
    
    const bookingCount = locationBookings.length;
    const avgSpend = locationBookings.length > 0 
      ? locationBookings.reduce((sum, booking) => sum + booking.value, 0) / locationBookings.length
      : 0;
    
    // Simulate retention rate and preferred category
    const retentionRate = Math.min(Math.random() * 100, 85); // Max 85% for realism
    const preferredCategory = categories[Math.floor(Math.random() * categories.length)];

    return {
      location,
      bookingCount,
      avgSpend,
      retentionRate,
      preferredCategory
    };
  });
};

// Updated types to support the new data structure
export interface PartnerPerformance {
  partnerId: string;
  name: string;
  category: string;
  totalRevenue: number;
  totalBookings: number;
  confirmedBookings: number;
  canceledBookings: number;
  avgBookingValue: number;
  cancellationRate: number;
  averageRevenueTrend: number;
  location: string;
  contactEmail: string;
}

// Extend existing types
export interface Revenue {
  id: string;
  partnerId: string;
  amount: number;
  date: string;
  category?: string;
}

export interface Booking {
  id: string;
  partnerId: string;
  value: number;
  status: 'confirmed' | 'canceled' | 'pending';
  date: string;
  customerLocation: string;
  category?: string;
}

export interface Partner {
  id: string;
  name: string;
  location: string;
  logo: string;
  category: string;
  contactEmail: string;
}


export interface CustomerInsights {
  location: string;
  bookingCount: number;
  avgSpend: number;
  retentionRate: number;
  preferredCategory: string;
}