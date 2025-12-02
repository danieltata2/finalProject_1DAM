<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Cita - Clínica Veterinaria</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        :root {
            --azul-oscuro: #1a3e72;
            --azul-medio: #2a56a5;
            --azul-claro: #4b86d4;
            --azul-pastel: #a8c6f7;
            --azul-hielo: #e6f0ff;
            --blanco: #ffffff;
            --sombra: 0 4px 20px rgba(26, 62, 114, 0.1);
            --error: #ff6b6b;
        }
        
        body {
            font-family: 'Poppins', sans-serif;
            background-color: var(--azul-hielo);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: var(--azul-oscuro);
        }

        .container {
            background-color: var(--blanco);
            width: 100%;
            max-width: 600px;
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: var(--sombra);
            position: relative;
            overflow: hidden;
        }

        .container::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 8px;
            background: linear-gradient(90deg, var(--azul-claro), var(--azul-medio));
        }

        h1 {
            color: var(--azul-oscuro);
            text-align: center;
            margin-bottom: 2rem;
            font-size: 1.8rem;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.8rem;
        }

        h1::after {
            content: "";
            position: absolute;
            bottom: -0.8rem;
            left: 50%;
            transform: translateX(-50%);
            width: 60px;
            height: 3px;
            background: var(--azul-pastel);
        }

        form {
            margin-top: 1.5rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: var(--azul-medio);
        }

        input[type="text"],
        input[type="date"],
        input[type="time"],
        select {
            width: 100%;
            padding: 0.8rem 1rem;
            border: 2px solid var(--azul-pastel);
            border-radius: 8px;
            font-size: 1rem;
            transition: all 0.3s ease;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        input[type="text"]:focus,
        input[type="date"]:focus,
        input[type="time"]:focus,
        select:focus {
            border-color: var(--azul-claro);
            outline: none;
            box-shadow: 0 0 0 3px rgba(75, 134, 212, 0.2);
        }

        select {
            appearance: none;
            background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%232a56a5' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
            background-repeat: no-repeat;
            background-position: right 1rem center;
            background-size: 1em;
        }

        .btn-submit {
            background: linear-gradient(to right, var(--azul-claro), var(--azul-medio));
            color: var(--blanco);
            border: none;
            padding: 0.8rem;
            width: 100%;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 1rem;
            font-family: 'Poppins', sans-serif;
        }

        .btn-submit:hover {
            background: linear-gradient(to right, var(--azul-medio), var(--azul-oscuro));
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(26, 62, 114, 0.2);
        }

        @media (max-width: 768px) {
            .container {
                padding: 1.5rem;
                margin: 1rem;
            }
            
            h1 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1><i class="fas fa-calendar-plus"></i> Registrar Nueva Cita</h1>
        
        <form action="CitaControlador" method="post">
            <input type="hidden" name="opcion" value="insertar">
            
            <div class="form-group">
                <label for="idCliente">Cliente:</label>
                <select id="idCliente" name="idCliente" required>
                    <option value="">-- Selecciona un cliente --</option>
                    <c:forEach var="cliente" items="${clientes}">
                        <option value="${cliente.idCliente}">
                            ${cliente.nombre} ${cliente.apellido}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="idAnimal">Animal:</label>
                <select id="idAnimal" name="idAnimal" required>
                    <option value="">-- Selecciona un animal --</option>
                    <c:forEach var="animal" items="${animales}">
                        <option value="${animal.idAnimal}">
                            ${animal.nombre} (${animal.especie})
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="idVeterinario">Veterinario:</label>
                <select id="idVeterinario" name="idVeterinario" required>
                    <option value="">-- Selecciona un veterinario --</option>
                    <c:forEach var="veterinario" items="${veterinarios}">
                        <option value="${veterinario.idVeterinario}">
                            Dr. ${veterinario.nombre} ${veterinario.apellido}
                        </option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label for="fecha">Fecha de la cita:</label>
                <input type="date" id="fecha" name="fecha" required>
            </div>
            
            <div class="form-group">
                <label for="hora">Hora de la cita:</label>
                <input type="time" id="hora" name="hora" required>
            </div>
            
            <button type="submit" class="btn-submit">
                <i class="fas fa-calendar-check"></i> Programar Cita
            </button>
        </form>
    </div>

    <script>
        // Establecer la fecha mínima como hoy
        document.getElementById('fecha').min = new Date().toISOString().split('T')[0];
        
        // Establecer horas laborales (ejemplo: 8am a 6pm)
        document.getElementById('hora').min = '08:00';
        document.getElementById('hora').max = '18:00';
    </script>
</body>
</html>
