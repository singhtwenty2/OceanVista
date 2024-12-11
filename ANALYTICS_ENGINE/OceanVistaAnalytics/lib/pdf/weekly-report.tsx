import React from 'react';
import { 
  Document, 
  Page, 
  Text, 
  View, 
  StyleSheet, 
  Image, 
  PDFDownloadLink,
  Line,
  Chart
} from '@react-pdf/renderer';
import { 
  generateRevenueData, 
  generateBookingData, 
  generatePartnerData,
  generateCustomerData,
  Partner,
  PartnerPerformance,
  CustomerInsights
} from '../data';

// Enhanced Premium Color Palette
const PREMIUM_COLORS = {
  primary: '#0A2342',       // Deep navy for sophistication
  secondary: '#1D4E89',     // Rich blue for depth
  accent: '#35A79C',        // Elegant teal for highlights
  background: '#F4F7F9',    // Soft, professional background
  text: '#2C3E50',          // Dark text for readability
  positive: '#27AE60',      // Vibrant green for positive metrics
  negative: '#E74C3C',      // Robust red for warnings
  neutral: '#95A5A6'        // Neutral gray for balanced information
};

// Enhanced Styling with More Premium Feel
const styles = StyleSheet.create({
  document: {
    fontFamily: 'Helvetica-Bold',
  },
  page: {
    backgroundColor: PREMIUM_COLORS.background,
    padding: 30,
    flexDirection: 'column',
  },
  headerContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
    paddingBottom: 15,
    borderBottomWidth: 2,
    borderBottomColor: PREMIUM_COLORS.primary,
  },
  logo: {
    width: 120,
    height: 60,
    borderRadius: 8,
  },
  companyName: {
    fontSize: 20,
    color: PREMIUM_COLORS.primary,
    fontWeight: 'extrabold',
    letterSpacing: 1,
  },
  title: {
    fontSize: 28,
    color: PREMIUM_COLORS.primary,
    marginBottom: 25,
    textAlign: 'center',
    fontWeight: 'bold',
    letterSpacing: 1.5,
  },
  premiumSection: {
    marginBottom: 20,
    padding: 20,
    backgroundColor: '#FFFFFF',
    borderRadius: 12,
    shadowColor: PREMIUM_COLORS.primary,
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    elevation: 5,
  },
  sectionTitle: {
    fontSize: 22,
    color: PREMIUM_COLORS.primary,
    marginBottom: 15,
    borderBottomWidth: 2,
    borderBottomColor: PREMIUM_COLORS.secondary,
    paddingBottom: 10,
    letterSpacing: 1,
  },
  metricRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 12,
    paddingVertical: 8,
    borderBottomWidth: 1,
    borderBottomColor: '#E0E0E0',
  },
  metricLabel: {
    fontSize: 14,
    color: PREMIUM_COLORS.text,
    fontWeight: 'medium',
  },
  metricValue: {
    fontSize: 16,
    fontWeight: 'bold',
    color: PREMIUM_COLORS.secondary,
  },
  performanceIndicator: {
    fontSize: 14,
    fontWeight: 'bold',
  },
  insightText: {
    fontSize: 12,
    color: PREMIUM_COLORS.neutral,
    fontStyle: 'italic',
    marginTop: 10,
  },
  footer: {
    position: 'absolute',
    bottom: 30,
    left: 30,
    right: 30,
    flexDirection: 'row',
    justifyContent: 'space-between',
    borderTopWidth: 2,
    borderTopColor: PREMIUM_COLORS.primary,
    paddingTop: 15,
  },
  footerText: {
    fontSize: 10,
    color: PREMIUM_COLORS.text,
    letterSpacing: 0.5,
  },
});

export const WeeklyReportDocument = () => {
  // Generate comprehensive data
  const weeklyRevenue = generateRevenueData(7);
  const weeklyBookings = generateBookingData(7);
  const partnerData = generatePartnerData(7);
  const customerInsights = generateCustomerData(7);

  // Advanced Calculations
  const totalRevenue = weeklyRevenue.reduce((sum, rev) => sum + rev.amount, 0);
  const totalBookings = weeklyBookings.length;
  const confirmedBookings = weeklyBookings.filter(b => b.status === 'confirmed').length;
  const canceledBookings = weeklyBookings.filter(b => b.status === 'canceled').length;
  
  // Revenue Trend Analysis
  const averageDailyRevenue = totalRevenue / 7;
  const revenueGrowthPercentage = ((weeklyRevenue[6].amount - weeklyRevenue[0].amount) / weeklyRevenue[0].amount * 100).toFixed(2);

  // Partner Performance
  const topPartner = partnerData.reduce((max, partner) => 
    partner.totalRevenue > max.totalRevenue ? partner : max
  );
  const topPartnerPercentage = ((topPartner.totalRevenue / totalRevenue) * 100).toFixed(2);

  // Customer Insights Analysis
  const topCustomerLocation = customerInsights.reduce((max, insight) => 
    insight.bookingCount > max.bookingCount ? insight : max
  );
  const averageCustomerSpend = customerInsights.reduce((sum, insight) => sum + insight.avgSpend, 0) / customerInsights.length;

  return (
    <Document>
      <Page size="A4" style={styles.page}>
        {/* Premium Header */}
        <View style={styles.headerContainer}>
          <Image 
            src="/api/placeholder/120/60" 
            style={styles.logo} 
            alt="OceanVista Logo" 
          />
          <Text style={styles.companyName}>OceanVista Business</Text>
        </View>

        {/* Comprehensive Title */}
        <Text style={styles.title}>Comprehensive Weekly Performance Insights</Text>

        {/* Revenue Deep Dive */}
        <View style={styles.premiumSection}>
          <Text style={styles.sectionTitle}>Revenue Performance</Text>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Total Revenue</Text>
            <Text style={styles.metricValue}>₹{totalRevenue.toLocaleString('en-IN')}</Text>
          </View>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Average Daily Revenue</Text>
            <Text style={styles.metricValue}>₹{averageDailyRevenue.toLocaleString('en-IN')}</Text>
          </View>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Revenue Growth</Text>
            <Text style={[
              styles.performanceIndicator, 
              { 
                color: parseFloat(revenueGrowthPercentage) > 0 
                  ? PREMIUM_COLORS.positive 
                  : PREMIUM_COLORS.negative 
              }
            ]}>
              {revenueGrowthPercentage}% {parseFloat(revenueGrowthPercentage) > 0 ? '▲' : '▼'}
            </Text>
          </View>
          <Text style={styles.insightText}>
            Insight: Weekly revenue shows {parseFloat(revenueGrowthPercentage) > 0 ? 'positive' : 'negative'} growth trend
          </Text>
        </View>

        {/* Booking Performance Insights */}
        <View style={styles.premiumSection}>
          <Text style={styles.sectionTitle}>Booking Dynamics</Text>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Total Bookings</Text>
            <Text style={styles.metricValue}>{totalBookings}</Text>
          </View>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Confirmed Bookings</Text>
            <Text style={[styles.metricValue, { color: PREMIUM_COLORS.positive }]}>
              {confirmedBookings}
            </Text>
          </View>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Cancellation Rate</Text>
            <Text style={[
              styles.performanceIndicator,
              { 
                color: (canceledBookings / totalBookings * 100) > 15 
                  ? PREMIUM_COLORS.negative 
                  : PREMIUM_COLORS.neutral 
              }
            ]}>
              {((canceledBookings / totalBookings) * 100).toFixed(2)}%
            </Text>
          </View>
          <Text style={styles.insightText}>
            Performance Note: {(canceledBookings / totalBookings * 100) > 15 
              ? 'High cancellation rate requires immediate attention' 
              : 'Booking stability maintained with controlled cancellations'}
          </Text>
        </View>

        {/* Partner Performance Insights */}
        <View style={styles.premiumSection}>
          <Text style={styles.sectionTitle}>Partner Ecosystem</Text>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Top Performing Partner</Text>
            <Text style={styles.metricValue}>{topPartner.name}</Text>
          </View>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Partner Revenue Share</Text>
            <Text style={styles.performanceIndicator}>
              {topPartnerPercentage}%
            </Text>
          </View>
          <Text style={styles.insightText}>
            Partner Highlight: {topPartner.name} leads with significant revenue contribution
          </Text>
        </View>

        {/* Customer Insights */}
        <View style={styles.premiumSection}>
          <Text style={styles.sectionTitle}>Customer Analytics</Text>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Top Customer Location</Text>
            <Text style={styles.metricValue}>{topCustomerLocation.location}</Text>
          </View>
          <View style={styles.metricRow}>
            <Text style={styles.metricLabel}>Average Customer Spend</Text>
            <Text style={styles.metricValue}>₹{averageCustomerSpend.toLocaleString('en-IN')}</Text>
          </View>
          <Text style={styles.insightText}>
            Customer Trend: {topCustomerLocation.location} emerges as key market with highest booking concentration
          </Text>
        </View>

        {/* Footer */}
        <View style={styles.footer}>
          <Text style={styles.footerText}>OceanVista Business</Text>
          <Text style={styles.footerText}>Confidential | Weekly Performance Report</Text>
        </View>
      </Page>
    </Document>
  );
};

export default WeeklyReportDocument;