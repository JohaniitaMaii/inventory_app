import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import './index.css';

// Función para verificar si el usuario está logueado
const ProtectedRoute = ({ children }) => {
  const token = localStorage.getItem('token');
  return token ? children : <Navigate to="/login" />;
};

function App() {
  return (
    <Router>
      <Routes>
        {/* Ruta Pública: Lo primero que ve el mundo */}
        <Route path="/" element={<Home />} />

        {/* Ruta de Login */}
        <Route path="/login" element={<Login />} />

        {/* Ruta Protegida: Solo para empleados con Token */}
        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;