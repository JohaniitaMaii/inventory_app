import { useNavigate } from 'react-router-dom';

function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/');
  };

  return (
    <div className="min-h-screen bg-slate-950 text-white flex">
      {/* Sidebar Simple */}
      <div className="w-64 bg-slate-900 border-r border-white/5 p-6">
        <h2 className="text-xl font-bold mb-10 text-blue-400">Panel de Control</h2>
        <nav className="space-y-4">
          <div className="text-slate-400 hover:text-white cursor-pointer transition-colors">📦 Productos</div>
          <div className="text-slate-400 hover:text-white cursor-pointer transition-colors">💰 Ventas</div>
          <div className="text-slate-400 hover:text-white cursor-pointer transition-colors">🗂️ Empleados</div>
          <div className="text-slate-400 hover:text-white cursor-pointer transition-colors">🤝 Proveedores</div>
          <div className="text-slate-400 hover:text-white cursor-pointer transition-colors">🖥️ Sistema</div>
        </nav>
      </div>

      {/* Contenido Principal */}
      <div className="flex-1 p-10">
        <header className="flex justify-between items-center mb-10">
          <h1 className="text-3xl font-bold">Bienvenida, Líder</h1>
          <button
            onClick={handleLogout}
            className="bg-red-500/10 hover:bg-red-500/20 text-red-500 px-4 py-2 rounded-lg border border-red-500/20 transition-all"
          >
            Cerrar Sesión
          </button>
        </header>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="bg-slate-900 border border-white/5 p-6 rounded-2xl">
            <p className="text-slate-400 text-sm">Total Productos</p>
            <p className="text-2xl font-bold">124</p>
          </div>
          <div className="bg-slate-900 border border-white/5 p-6 rounded-2xl">
            <p className="text-slate-400 text-sm">Proveedores Activos</p>
            <p className="text-2xl font-bold">12</p>
          </div>
          <div className="bg-slate-900 border border-white/5 p-6 rounded-2xl">
            <p className="text-slate-400 text-sm">Ventas del Mes</p>
            <p className="text-2xl font-bold">$45.200</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;