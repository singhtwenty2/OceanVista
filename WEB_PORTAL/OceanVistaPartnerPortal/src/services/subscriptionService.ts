import axiosInstance from "../api/axiosInstance";

export const fetchSubscriptionPlans = async () => {
  try {
    const response = await axiosInstance.get('subscription-plan/all');
    return response.data;
  } catch (error: any) {
    console.error("Error fetching subscription plans:", error);
    throw error.response
      ? error.response.data
      : new Error('Failed to fetch subscription plans. Please try again.');
  }
};
