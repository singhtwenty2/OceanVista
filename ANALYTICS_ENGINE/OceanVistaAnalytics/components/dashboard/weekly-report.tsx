"use client";

import { useState } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Download, Mail, Loader2 } from 'lucide-react';
import { generateBookingData, generateRevenueData } from '@/lib/data';
import { WeeklyReportDocument } from '@/lib/pdf/weekly-report';
import { PDFDownloadLink } from '@react-pdf/renderer';
import { motion } from 'framer-motion';
import { toast } from 'sonner';

const weeklyRevenue = generateRevenueData(7);
const weeklyBookings = generateBookingData(7);

const totalRevenue = weeklyRevenue.reduce((sum, rev) => sum + rev.amount, 0);
const totalBookings = weeklyBookings.length;
const canceledBookings = weeklyBookings.filter(b => b.status === 'canceled').length;

export default function WeeklyReport() {
  const [isEmailSending, setIsEmailSending] = useState(false);

  const handleEmailReport = async () => {
    setIsEmailSending(true);
    // Simulate email sending
    await new Promise(resolve => setTimeout(resolve, 2000));
    setIsEmailSending(false);
    toast.success('Report sent successfully!');
  };

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
    >
      <Card className="hover:shadow-lg transition-shadow duration-300">
        <CardHeader className="flex flex-row items-center justify-between">
          <CardTitle>Weekly Performance Report</CardTitle>
          <div className="flex space-x-2">
            <Button 
              variant="outline" 
              size="sm"
              onClick={handleEmailReport}
              disabled={isEmailSending}
            >
              {isEmailSending ? (
                <Loader2 className="h-4 w-4 mr-2 animate-spin" />
              ) : (
                <Mail className="h-4 w-4 mr-2" />
              )}
              Email Report
            </Button>
            
            <PDFDownloadLink 
              document={<WeeklyReportDocument />} 
              fileName="weekly-report.pdf"
            >
              {({ loading }) => (
                <Button size="sm" disabled={loading}>
                  {loading ? (
                    <Loader2 className="h-4 w-4 mr-2 animate-spin" />
                  ) : (
                    <Download className="h-4 w-4 mr-2" />
                  )}
                  Download Report
                </Button>
              )}
            </PDFDownloadLink>
          </div>
        </CardHeader>
        <CardContent>
          <motion.div 
            className="grid gap-6 md:grid-cols-3"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ delay: 0.2 }}
          >
            <div className="space-y-2 p-4 rounded-lg bg-primary/5 hover:bg-primary/10 transition-colors">
              <p className="text-sm text-muted-foreground">Total Revenue</p>
              <p className="text-2xl font-bold">
                {new Intl.NumberFormat('en-IN', {
                  style: 'currency',
                  currency: 'INR',
                  maximumFractionDigits: 0,
                }).format(totalRevenue)}
              </p>
            </div>
            <div className="space-y-2 p-4 rounded-lg bg-primary/5 hover:bg-primary/10 transition-colors">
              <p className="text-sm text-muted-foreground">Total Bookings</p>
              <p className="text-2xl font-bold">{totalBookings}</p>
            </div>
            <div className="space-y-2 p-4 rounded-lg bg-primary/5 hover:bg-primary/10 transition-colors">
              <p className="text-sm text-muted-foreground">Canceled Bookings</p>
              <p className="text-2xl font-bold">{canceledBookings}</p>
            </div>
          </motion.div>

          <motion.div 
            className="mt-8"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ delay: 0.4 }}
          >
            <h3 className="text-lg font-medium mb-4">Key Highlights</h3>
            <ul className="space-y-3">
              {[
                'Highest revenue day: Friday (₹8,50,000)',
                'Most active partner: Taj Hotels (45% of bookings)',
                'Average booking value increased by 12%',
                'Customer satisfaction rate: 4.8/5'
              ].map((highlight, index) => (
                <motion.li 
                  key={index}
                  className="flex items-center text-sm p-3 rounded-lg bg-muted/50 hover:bg-muted/70 transition-colors"
                  initial={{ opacity: 0, x: -20 }}
                  animate={{ opacity: 1, x: 0 }}
                  transition={{ delay: 0.5 + index * 0.1 }}
                >
                  • {highlight}
                </motion.li>
              ))}
            </ul>
          </motion.div>
        </CardContent>
      </Card>
    </motion.div>
  );
}