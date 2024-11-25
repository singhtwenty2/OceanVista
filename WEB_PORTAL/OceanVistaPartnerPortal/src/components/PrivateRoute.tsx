import React from 'react';
import { Navigate } from 'react-router-dom';
import { isAuthenticated } from '../utils/authToken';

const ProtectedRoute: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  return isAuthenticated() ? <>{children}</> : <Navigate to="/login" />;
};

export default ProtectedRoute;