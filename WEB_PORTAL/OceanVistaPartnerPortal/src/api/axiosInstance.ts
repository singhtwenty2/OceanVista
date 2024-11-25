import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://192.168.0.155:7035/partner.engine.api/v1/',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default axiosInstance;
