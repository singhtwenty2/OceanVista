import './globals.css';
import '../styles/animations.css';
import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import { ThemeProvider } from '@/components/theme-provider';
import { Toaster } from "@/components/ui/toaster";

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'Premium Analytics Engine',
  description: 'Enterprise-grade analytics dashboard',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body className={inter.className}>
        <ThemeProvider attribute="class" defaultTheme="system" enableSystem>
          <div className="min-h-screen bg-gradient-to-br from-background to-background/95 dark:from-background dark:to-background/90 transition-colors duration-500">
            {children}
            <Toaster />
          </div>
        </ThemeProvider>
      </body>
    </html>
  );
}