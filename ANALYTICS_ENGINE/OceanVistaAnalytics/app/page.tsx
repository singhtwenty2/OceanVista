import { Suspense } from 'react';
import DashboardHeader from '@/components/dashboard/header';
import RevenueOverview from '@/components/dashboard/revenue-overview';
import BookingStats from '@/components/dashboard/booking-stats';
import ForecastPanel from '@/components/dashboard/forecast-panel';
import WeeklyReport from '@/components/dashboard/weekly-report';
import { Skeleton } from '@/components/ui/skeleton';
import CustomerSegmentationAnalytics from '@/components/dashboard/customer_segmentation';

export default function Home() {
  return (
    <div className="min-h-screen bg-background">
      <DashboardHeader />
      <main className="container mx-auto px-4 py-8">
        <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
          <Suspense fallback={<Skeleton className="h-[400px]" />}>
            <RevenueOverview />
          </Suspense>
          <Suspense fallback={<Skeleton className="h-[400px]" />}>
            <BookingStats />
          </Suspense>
          <Suspense fallback={<Skeleton className="h-[400px]" />}>
            <ForecastPanel />
          </Suspense>
          <Suspense fallback={<Skeleton className="h-[400px]" />}>
            <CustomerSegmentationAnalytics />
          </Suspense>
        </div>
        <div className="mt-8">
          <Suspense fallback={<Skeleton className="h-[500px]" />}>
            <WeeklyReport />
          </Suspense>
        </div>
      </main>
    </div>
  );
}