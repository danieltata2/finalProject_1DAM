<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registrar Cliente - Clínica Veterinaria</title>
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
    input[type="number"],
    input[type="email"] {
        width: 100%;
        padding: 0.8rem 1rem;
        border: 2px solid var(--azul-pastel);
        border-radius: 8px;
        font-size: 1rem;
        transition: all 0.3s ease;
        font-family: 'Poppins', sans-serif;
        box-sizing: border-box;
    }

    input:focus {
        border-color: var(--azul-claro);
        outline: none;
        box-shadow: 0 0 0 3px rgba(75, 134, 212, 0.2);
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
        <h1><i class="fas fa-user-plus"></i> Registrar Cliente</h1>

        <form action="ClienteControlador" method="post">
            <input type="hidden" name="opcion" value="insertar">

            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required placeholder="Ej: Juan">
            </div>

            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" required placeholder="Ej: Pérez">
            </div>

            <div class="form-group">
                <label for="edad">Edad:</label>
                <input type="number" id="edad" name="edad" required placeholder="Ej: 35">
            </div>

            <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion" required placeholder="Ej: Calle Falsa 123">
            </div>

            <div class="form-group">
                <label for="telefono">Teléfono:</label>
                <input type="text" id="telefono" name="telefono" required placeholder="Ej: 5551234567">
            </div>

            <div class="form-group">
                <label for="correo">Correo electrónico:</label>
                <input type="email" id="correo" name="correo" required placeholder="Ej: juan@example.com">
            </div>

            <button type="submit" class="btn-submit">
                <i class="fas fa-save"></i> Registrar Cliente
            </button>
        </form>
    </div>
</body>
</html>
