import axiosInstance from '../api/axiosInstance';
import { getToken } from '../utils/authToken';

export interface SignupPayload {
  name: string;
  email: string;
  phoneNumber: string;
  password: string;
  partnerType: string;
}

export const registerPartner = async (payload: SignupPayload) => {
  try {
    const response = await axiosInstance.post('auth/register', payload);
    return response.data;
  } catch (error: any) {
    throw error.response ? error.response.data : new Error('Something went wrong');
  }
};

export const login = async (email: string, password: string) => {
  try {
    const response = await axiosInstance.post('auth/login', { email, password });
    return response.data;
  } catch (error: any) {
    throw error.response
      ? error.response.data
      : new Error('Login failed. Please try again.');
  }
};

export const fetchUserProfile = async () => {
  const token = getToken();
  console.log("Fetching user profile with token:", token);

  if (!token) {
    throw new Error("Token not available. Please log in again.");
  }

  try {
    const response = await axiosInstance.get('auth/about', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    console.log("User profile response:", response.data);
    return response.data;
  } catch (error: any) {
    console.error("Error fetching user profile:", error);
    throw error.response
      ? error.response.data
      : new Error('Failed to fetch user profile. Please try again.');
  }
};
