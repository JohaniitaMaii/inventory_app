import axios from 'axios';

// Esta URL debe coincidir con tu API de Spring Boot
const API_URL = "http://localhost:8080/api/productos";
const token = localStorage.getItem('token'); // Donde sea que guardes el JWT
const response = await fetch('http://localhost:8080/api/productos', {
    headers: {
        'Authorization': `Bearer ${token}`
    }
export const getProductos = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data; // Aquí vienen tus productos desde Java
    } catch (error) {
        console.error("Error al obtener productos:", error);
        return [];
    }
};