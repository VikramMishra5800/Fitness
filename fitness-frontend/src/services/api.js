import axios from "axios";
import store from "../store/store";
import { logout } from "../store/authSlice";

const API_URL = `${import.meta.env.VITE_API_URL}`;

const api = axios.create({
    baseURL:API_URL
});

api.interceptors.request.use((config) => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }

    if (userId) {
        config.headers['X-User-ID'] = userId;
    }
    return config;
}
);

// Response interceptor to handle 401 Unauthorized responses
api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && error.response.status === 401) {
            // Token is invalid or expired, dispatch logout action
            store.dispatch(logout());
        }
        return Promise.reject(error);
    }
);


export const getActivities = () => api.get('/activity');
export const addActivity = (activity) => api.post('/activity', activity);
export const getActivityDetail = (id) => api.get(`/recommendations/activity/${id}`);