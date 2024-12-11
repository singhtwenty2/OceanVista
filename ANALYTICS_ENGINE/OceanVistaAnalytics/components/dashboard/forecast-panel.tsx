"use client";

import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { TrendingUp, TrendingDown, AlertCircle } from 'lucide-react';

const insights = [
  {
    title: "Weekend Booking Surge",
    description: "Expected 25% increase in bookings",
    trend: "up",
  },
  {
    title: "Revenue Forecast",
    description: "₹30,00,000 expected next week",
    trend: "up",
  },
  {
    title: "Off-Peak Season",
    description: "July bookings may decrease",
    trend: "down",
  },
];

export default function ForecastPanel() {
  return (
    <Card className="w-full">
      <CardHeader>
        <CardTitle>Forecasting & Insights</CardTitle>
      </CardHeader>
      <CardContent>
        <div className="space-y-4">
          {insights.map((insight, index) => (
            <div
              key={index}
              className="flex items-center space-x-4 p-4 rounded-lg bg-muted/50"
            >
              <div className="flex-shrink-0">
                {insight.trend === "up" ? (
                  <TrendingUp className="h-6 w-6 text-green-500" />
                ) : insight.trend === "down" ? (
                  <TrendingDown className="h-6 w-6 text-red-500" />
                ) : (
                  <AlertCircle className="h-6 w-6 text-yellow-500" />
                )}
              </div>
              <div className="flex-grow">
                <h3 className="font-semibold text-sm mb-1">{insight.title}</h3>
                <p className="text-xs text-muted-foreground">
                  {insight.description}
                </p>
              </div>
            </div>
          ))}
        </div>
      </CardContent>
    </Card>
  );
}