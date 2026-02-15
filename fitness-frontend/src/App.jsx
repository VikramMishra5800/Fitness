import { useContext, useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import { Box, Button, Typography } from "@mui/material";
import { BrowserRouter as Router, Navigate, Route, Routes, useLocation } from "react-router";
import { AuthContext } from 'react-oauth2-code-pkce';
import { useDispatch } from 'react-redux';
import { setCredentials, logout } from "./store/authSlice";
import ActivityForm from './components/ActivityForm';
import ActivityList from './components/ActivityList';
import ActivityDetail from './components/ActivityDetail';

const ActvitiesPage = () => {
  return (<Box sx={{ p: 2, border: '1px dashed grey' }}>
    <ActivityForm onActivityAdded = {() => window.location.reload()} />
    <ActivityList />
  </Box>);
}

function App() {

  const { token, tokenData, logIn, logOut, isAuthenticated } = useContext(AuthContext);
  const dispatch = useDispatch();
  const [authReady, setAuthReady] = useState(false);

  useEffect(() => {
    if (token) {
      dispatch(setCredentials({token, user: tokenData}));
      setAuthReady(true);
    }
  }, [token, tokenData, dispatch]);

  const handleLogout = () => {
    // Dispatch Redux logout action to clear local state and localStorage
    dispatch(logout());
    
    logOut();

    // Call OAuth logout to logout from Keycloak
    const logoutUrl = `${import.meta.env.VITE_KEYCLOAK_LOGOUT_URL}`;
    window.location.href = logoutUrl;
  };

  return (
      <Router>
        {!token ? (
          <Button variant="contained" onClick={() => {
                logIn();
            }}>
              LOGIN
          </Button>
        ) : (
          <Box sx={{ p: 2, border: '1px dashed grey' }}>
            <Button variant="contained" color="secondary" onClick={handleLogout}>
              Logout
            </Button>
            <Routes>
              <Route path="/activities" element={<ActvitiesPage />} />
              <Route path="/activities/:id" element={<ActivityDetail />} />

              <Route path="/" element={token ? <Navigate to="/activities" replace /> : <div>Welcome! Please Login.</div>} />
            </Routes>
          </Box>
        )}
        
      </Router>
  )
}

export default App
