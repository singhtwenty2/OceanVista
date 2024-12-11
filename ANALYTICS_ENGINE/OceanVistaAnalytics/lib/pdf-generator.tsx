import { Document, Page, Text, View, StyleSheet, PDFDownloadLink } from '@react-pdf/renderer';
import { generateBookingData, generateRevenueData } from './data';

const styles = StyleSheet.create({
  page: {
    flexDirection: 'column',
    backgroundColor: '#FFFFFF',
    padding: 30,
  },
  section: {
    margin: 10,
    padding: 10,
  },
  title: {
    fontSize: 24,
    marginBottom: 20,
  },
  subtitle: {
    fontSize: 18,
    marginBottom: 10,
  },
  text: {
    fontSize: 12,
    marginBottom: 5,
  },
});

export const WeeklyReportPDF = () => {
  const weeklyRevenue = generateRevenueData(7);
  const weeklyBookings = generateBookingData(7);

  const totalRevenue = weeklyRevenue.reduce((sum, rev) => sum + rev.amount, 0);
  const totalBookings = weeklyBookings.length;
  const canceledBookings = weeklyBookings.filter(b => b.status === 'canceled').length;

  return (
    <Document>
      <Page size="A4" style={styles.page}>
        <View style={styles.section}>
          <Text style={styles.title}>Weekly Performance Report</Text>
          
          <Text style={styles.subtitle}>Revenue Overview</Text>
          <Text style={styles.text}>
            Total Revenue: ₹{totalRevenue.toLocaleString('en-IN')}
          </Text>
          
          <Text style={styles.subtitle}>Booking Statistics</Text>
          <Text style={styles.text}>Total Bookings: {totalBookings}</Text>
          <Text style={styles.text}>Canceled Bookings: {canceledBookings}</Text>
          
          <Text style={styles.subtitle}>Key Highlights</Text>
          <Text style={styles.text}>• Highest revenue day: Friday (₹8,50,000)</Text>
          <Text style={styles.text}>• Most active partner: Taj Hotels (45% of bookings)</Text>
          <Text style={styles.text}>• Average booking value increased by 12%</Text>
          <Text style={styles.text}>• Customer satisfaction rate: 4.8/5</Text>
        </View>
      </Page>
    </Document>
  );
};