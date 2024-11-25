import { useNavigate } from 'react-router-dom';

export const saveToken = (token: string) => {
  localStorage.setItem('jwtToken', token);
};

export const getToken = () => {
  return localStorage.getItem('jwtToken');
};

export const isAuthenticated = () => {
  const token = getToken();
  if (!token) return false;

  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const tokenExpiry = payload.exp * 1000;
    const now = Date.now();
    return tokenExpiry > now;
  } catch (e) {
    console.error('Error decoding token:', e);
    return false;
  }
};

export const useAuth = () => {
  const navigate = useNavigate();

  const clearToken = () => {
    localStorage.removeItem('jwtToken');
    navigate('/login');
  };

  return { saveToken, getToken, clearToken, isAuthenticated };
};
