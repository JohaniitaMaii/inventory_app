import React from 'react'

function App() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 to-slate-800 flex items-center justify-center">
      <div className="p-10 bg-white/5 backdrop-blur-lg border border-white/10 rounded-2xl shadow-2xl text-center">
        <h1 className="text-4xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-emerald-400 to-cyan-400 mb-4">
          ¡ENTORNO CONFIGURADO! 🚀
        </h1>
        <p className="text-slate-400 text-lg">
          React + Tailwind funcionando sin errores.
        </p>
        <div className="mt-6 inline-block px-6 py-2 bg-emerald-500 text-slate-900 font-bold rounded-full shadow-lg shadow-emerald-500/20">
          Listo para conectar el Backend
        </div>
      </div>
    </div>
  )
}

export default App