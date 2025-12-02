<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Generar Reportes - Clínica Veterinaria</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --azul-oscuro: #1a3e72;
            --azul-medio: #2a56a5;
            --azul-claro: #4b86d4;
            --azul-pastel: #a8c6f7;
            --azul-hielo: #e6f0ff;
            --blanco: #ffffff;
            --sombra: 0 4px 12px rgba(26, 62, 114, 0.15);
            --border-radius: 12px;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--azul-hielo);
            margin: 0;
            padding: 2rem;
            color: var(--azul-oscuro);
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: var(--blanco);
            border-radius: var(--border-radius);
            box-shadow: var(--sombra);
            padding: 2rem;
        }

        .header {
            text-align: center;
            margin-bottom: 2rem;
            position: relative;
        }

        .header h1 {
            color: var(--azul-oscuro);
            font-size: 2em;
            display: inline-block;
            margin-bottom: 0.5rem;
        }

        .header::after {
            content: "";
            display: block;
            width: 100px;
            height: 4px;
            background: var(--azul-pastel);
            margin: 10px auto 0;
            border-radius: 2px;
        }

        .filter-section {
            background-color: var(--azul-hielo);
            padding: 1.5rem;
            border-radius: var(--border-radius);
            margin-bottom: 2rem;
            box-shadow: var(--sombra);
        }

        .filter-group {
            display: flex;
            gap: 1rem;
            flex-wrap: wrap;
        }

        .input-field {
            flex: 1;
            padding: 0.8rem 1rem;
            border: 1px solid var(--azul-claro);
            border-radius: var(--border-radius);
        }

        .btn {
            padding: 0.8rem 1.5rem;
            border-radius: var(--border-radius);
            font-weight: 500;
            font-size: 0.9rem;
            border: none;
            cursor: pointer;
            transition: all 0.3s ease;
            color: var(--azul-oscuro);
            background-color: var(--azul-pastel);
        }

        .btn:hover {
            background-color: var(--azul-claro);
            color: var(--blanco);
            transform: translateY(-2px);
        }

        .links-section {
            display: grid;
            grid-template-columns: 1fr;
            gap: 1.5rem;
        }

        .link-card {
            background-color: var(--blanco);
            border-radius: var(--border-radius);
            padding: 1.5rem;
            box-shadow: var(--sombra);
            border-top: 4px solid var(--azul-medio);
            transition: all 0.3s ease;
        }

        .link-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 6px 16px rgba(26, 62, 114, 0.2);
        }

        .link-card h3 {
            margin: 0 0 1rem;
            color: var(--azul-oscuro);
            font-size: 1.2em;
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .alert {
            padding: 1rem;
            border-radius: var(--border-radius);
            margin-bottom: 1rem;
            display: none;
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        @media (max-width: 768px) {
            .filter-group {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1><i class="fas fa-file-pdf"></i> Generar Reportes PDF</h1>
        </div>

        <div class="filter-section">
            <div id="modalSinDatos" class="alert">
                <strong><i class="fas fa-exclamation-circle"></i> Aviso:</strong> No se encontraron datos para el filtro indicado.
            </div>

            <div class="filter-group">
                <input type="text" id="campoFiltro1" class="input-field" placeholder="Ingrese su criterio de búsqueda...">
                <button onclick="generarPDF1()" class="btn">
                    <i class="fas fa-search"></i> Buscar
                </button>
            </div>
        </div>

        <div class="links-section">
            <div class="link-card">
                <h3><i class="fas fa-paw"></i> Animales</h3>
                <a href="http://localhost:8089/obtenerAnimal" target="_blank" class="btn">
                    <i class="fas fa-list"></i> Consultar Listado
                </a>
            </div>

            <div class="link-card">
                <h3><i class="fas fa-users"></i> Clientes</h3>
                <a href="http://localhost:8089/obtenerCliente" target="_blank" class="btn">
                    <i class="fas fa-list"></i> Consultar Listado
                </a>
            </div>

            <div class="link-card">
                <h3><i class="fas fa-user-md"></i> Veterinarios</h3>
                <a href="http://localhost:8089/obtenerVeterinario" target="_blank" class="btn">
                    <i class="fas fa-list"></i> Consultar Listado
                </a>
            </div>
        </div>
    </div>

    <script>
        function generarPDF1() {
            const filtro = document.getElementById("campoFiltro1").value;
            const alerta = document.getElementById("modalSinDatos");
            alerta.style.display = "none";

            if (filtro.trim() === "") {
                alert("Por favor, introduce un criterio de búsqueda válido");
                return;
            }

            fetch("http://localhost:8089/obtenerVeterinarioNombres/" + encodeURIComponent(filtro))
                .then(response => {
                    if (response.status === 204) {
                        alerta.style.display = "block";
                        return;
                    } else if (!response.ok) {
                        alert("Error al generar el informe");
                        return;
                    }
                    return response.blob();
                })
                .then(blob => {
                    if (blob) {
                        const url = window.URL.createObjectURL(blob);
                        window.open(url, '_blank');
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("No se pudo conectar con el servidor.");
                });
        }
    </script>
</body>
</html>
