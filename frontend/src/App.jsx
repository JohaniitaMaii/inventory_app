import React, { useEffect, useState } from 'react';
import { getProductos } from './services/ProductService';

function App() {
  // 1. Estado para guardar los productos que vienen del Backend
  const [productos, setProductos] = useState([]);

  // 2. useEffect ejecuta la carga de datos apenas se abre la página
  useEffect(() => {
    const cargarDatos = async () => {
      const data = await getProductos();
      setProductos(data);
    };
    cargarDatos();
  }, []);

  return (
    <div className="min-h-screen bg-slate-900 text-white p-8">
      <div className="max-w-5xl mx-auto">
        <header className="mb-10 text-center">
          <h1 className="text-4xl font-black text-transparent bg-clip-text bg-gradient-to-r from-cyan-400 to-emerald-400">
            INVENTARIO PRO 2026
          </h1>
          <p className="text-slate-400 mt-2">Panel de control de stock en tiempo real</p>
        </header>

        <div className="overflow-hidden rounded-2xl border border-slate-700 bg-slate-800/50 backdrop-blur-md shadow-2xl">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-slate-700/50 text-cyan-400 uppercase text-xs tracking-widest">
                <th className="p-5 border-b border-slate-700">ID</th>
                <th className="p-5 border-b border-slate-700">Producto</th>
                <th className="p-5 border-b border-slate-700">Precio</th>
                <th className="p-5 border-b border-slate-700">Stock</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-700/50">
              {productos.length > 0 ? (
                productos.map((p) => (
                  <tr key={p.id} className="hover:bg-slate-700/20 transition-all group">
                    <td className="p-5 text-slate-500 font-mono text-sm">#{p.id}</td>
                    <td className="p-5 font-semibold text-slate-200">{p.nombre}</td>
                    <td className="p-5 text-emerald-400 font-bold">${p.precio}</td>
                    <td className="p-5">
                      <span className={`px-3 py-1 rounded-full text-xs font-bold ${
                        p.stock_actual > 5 ? 'bg-emerald-500/10 text-emerald-500' : 'bg-rose-500/10 text-rose-500'
                      }`}>
                        {p.stock_actual} unidades
                      </span>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="4" className="p-20 text-center text-slate-500 italic">
                    <div className="flex flex-col items-center gap-3">
                      <span className="text-4xl animate-pulse">🔌</span>
                      <p>Esperando conexión con el servidor Spring Boot...</p>
                    </div>
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default App;