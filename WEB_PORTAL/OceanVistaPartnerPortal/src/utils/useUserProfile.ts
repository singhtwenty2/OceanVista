import { useEffect, useState } from 'react';
import { fetchUserProfile } from '../services/authService';

export interface UserProfileData {
  userId: string;
  name: string;
  email: string;
  phoneNumber: string;
  partnerType: string;
  partnerStatus: string;
  maxAllowedBeachCount: number;
  createdAt: string;
  updatedAt: string;
}

export const useUserProfile = () => {
  const [user, setUser] = useState<UserProfileData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const data = await fetchUserProfile();
        setUser(data);
      } catch (err: any) {
        setError(err.message || 'Failed to fetch user details');
      } finally {
        setLoading(false);
      }
    };

    fetchProfile();
  }, []);

  return { user, loading, error };
};
