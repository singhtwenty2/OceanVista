import { 
    LayoutDashboard, Building2, BarChart3, Settings, 
    Calendar, Users, ShoppingBag, Plus,
    Map, Bell, FileText, HelpCircle
  } from 'lucide-react';

export const sidebarLinks = [
    { icon: LayoutDashboard, label: 'Dashboard', path: '/dashboard' },
    { icon: Building2, label: 'Properties', path: '/dashboard/properties' },
    { icon: ShoppingBag, label: 'Vendors', path: '/dashboard/vendors' },
    { icon: Plus, label: 'Medical Services', path: '/dashboard/medical' },
    { icon: Calendar, label: 'Bookings', path: '/dashboard/bookings' },
    { icon: Users, label: 'Customers', path: '/dashboard/customers' },
    { icon: Map, label: 'Locations', path: '/dashboard/locations' },
    { icon: Bell, label: 'Notifications', path: '/dashboard/notifications' },
    { icon: FileText, label: 'Reports', path: '/dashboard/reports' },
    { icon: BarChart3, label: 'Analytics', path: '/dashboard/analytics' },
    { icon: HelpCircle, label: 'Support', path: '/dashboard/support' },
    { icon: Settings, label: 'Settings', path: '/dashboard/settings' },
  ];