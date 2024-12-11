"use client";

import { useState } from 'react';
import { Calendar, ChevronDown, Hotel, Settings, FileText } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { ThemeToggle } from '@/components/ui/theme-toggle';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';

export default function DashboardHeader() {
  const [dateRange, setDateRange] = useState('Last 7 days');

  return (
    <header className="sticky top-0 z-50 backdrop-blur-lg bg-background/80 border-b border-border/50 transition-all duration-300">
      <div className="container mx-auto px-4 py-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-4">
            <div className="relative group">
              <div className="absolute -inset-1 bg-gradient-to-r from-primary to-primary/50 rounded-full blur opacity-25 group-hover:opacity-75 transition duration-200"></div>
              <Hotel className="h-8 w-8 text-primary relative" />
            </div>
            <h1 className="text-2xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-primary to-primary/50">
              Analytics Engine
            </h1>
          </div>
          
          <div className="flex items-center space-x-4">
            <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button variant="outline" className="glass-effect hover:bg-primary/10 transition-all duration-300">
                  <Calendar className="h-4 w-4 mr-2" />
                  <span>{dateRange}</span>
                  <ChevronDown className="h-4 w-4 ml-2" />
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent className="glass-effect">
                <DropdownMenuItem onClick={() => setDateRange('Today')}>
                  Today
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => setDateRange('Last 7 days')}>
                  Last 7 days
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => setDateRange('Last 30 days')}>
                  Last 30 days
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => setDateRange('Last 90 days')}>
                  Last 90 days
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>

            <Button variant="ghost" className="hover:bg-primary/10 transition-all duration-300">
              <FileText className="h-4 w-4 mr-2" />
              Reports
            </Button>
            
            <Button variant="ghost" className="hover:bg-primary/10 transition-all duration-300">
              <Settings className="h-4 w-4 mr-2" />
              Settings
            </Button>

            <ThemeToggle />
          </div>
        </div>
      </div>
    </header>
  );
}