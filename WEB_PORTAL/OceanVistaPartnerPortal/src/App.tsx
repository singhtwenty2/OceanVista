import React, { ReactNode } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { ThemeProvider } from './context/ThemeContext';
import { ThemeToggle } from './components/ThemeToggle';
import { Navbar } from './components/Navbar';
import { Home } from './pages/Home';
import { Pricing } from './pages/Pricing';
import { About } from './pages/About';
import { Contact } from './pages/Contact';
import { Login } from './pages/Login';
import { NotFound } from './pages/NotFound';
import Footer from './pages/Footer';
import { Blog } from './pages/Blog';
import { Documentation } from './pages/Documentation';
import VerifyEmail from './pages/VerifyEmail';
import Signup from './pages/Signup';
import { UserProfile } from './components/layout/UserProfile';
import PrivateRoute from './components/PrivateRoute';
import { UserAccountDetails } from './components/UserAccountDetails';
import DashboardScreen from './pages/dashboard/DashboardScreen';
import { isAuthenticated } from './utils/authToken';
import { Business } from './pages/dashboard/Business';

// Layout wrapper component for public pages
const PublicLayout = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="min-h-screen flex flex-col">
      <Navbar />
      <ThemeToggle />
      <div className="flex-grow">
        {children}
      </div>
      <Footer />
    </div>
  );
};

// Layout wrapper component for dashboard pages
const DashboardLayout = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="min-h-screen">
      {children}
    </div>
  );
};

// Component to determine which layout to use
const LayoutWrapper = ({ children }: { children: ReactNode }) => {
  const location = useLocation();
  const isDashboardRoute = location.pathname.startsWith('/dashboard');

  if (isDashboardRoute) {
    return <DashboardLayout>{children}</DashboardLayout>;
  }

  return <PublicLayout>{children}</PublicLayout>;
};

function App() {
  return (
    <Router>
      <ThemeProvider>
        <div className="bg-white dark:bg-slate-900 transition-colors duration-300">
          <LayoutWrapper>
            <Routes>
              {/* Root Route */}
              <Route
                path="/"
                element={
                  isAuthenticated() ? <Navigate to="/dashboard" /> : <Home />
                }
              />

              {/* Public Routes */}
              <Route
                path="/login"
                element={
                  isAuthenticated() ? <Navigate to="/dashboard" /> : <Login />
                }
              />
              <Route
                path="/signup"
                element={
                  isAuthenticated() ? <Navigate to="/dashboard" /> : <Signup />
                }
              />
              <Route path="/pricing" element={<Pricing />} />
              <Route path="/about" element={<About />} />
              <Route path="/contact" element={<Contact />} />
              <Route path="/verify-email" element={<VerifyEmail />} />
              <Route path="/blog" element={<Blog />} />
              <Route path="/documentation" element={<Documentation />} />

              {/* Private Routes */}
              <Route
                path="/dashboard"
                element={
                  <PrivateRoute>
                    <DashboardScreen />
                  </PrivateRoute>
                }
              />
              <Route
                path="/dashboard/account/details"
                element={
                  <PrivateRoute>
                    <UserAccountDetails />
                  </PrivateRoute>
                }
              />
              
              {/* User Profile Route */}
              <Route
                path="/dashboard/profile"
                element={
                  <PrivateRoute>
                    <UserProfile />
                  </PrivateRoute>
                }
              />
              <Route
                path="/dashboard/bookings"
                element={
                  <PrivateRoute>
                    <Business />
                  </PrivateRoute>
                }
              />
              {/* 404 Page */}
              <Route path="*" element={<NotFound />} />
            </Routes>
          </LayoutWrapper>
        </div>
      </ThemeProvider>
    </Router>
  );
}

export default App;