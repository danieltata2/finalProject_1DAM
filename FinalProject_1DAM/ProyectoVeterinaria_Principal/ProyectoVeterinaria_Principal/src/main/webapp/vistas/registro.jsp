<%@ page contentType="text/html; charset=UTF-8" language="java"%> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Registro de Usuario - Clínica Veterinaria</title>

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
            --error: #ff6b6b;
            --exito: #4caf50;
        }

        

        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--azul-hielo);
            color: var(--azul-oscuro);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-image: linear-gradient(rgba(230, 240, 255, 0.9), rgba(230, 240, 255, 0.9)), 
                              url('https://images.unsplash.com/photo-1534361960057-19889db9621e?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;

        }



        .register-container {
            background-color: var(--blanco);
            width: 100%;
            max-width: 450px;
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: var(--sombra);
            text-align: center;
            position: relative;
            overflow: hidden;
        }



        .register-container::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 6px;
            background: linear-gradient(90deg, var(--azul-claro), var(--azul-medio));
        }



        .logo {
            margin-bottom: 1.5rem;
            color: var(--azul-medio);
        }



        .logo i {
            font-size: 3rem;
        }



        h2 {
            color: var(--azul-oscuro);
            margin-bottom: 1.8rem;
            font-size: 1.8rem;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.8rem;
        }



        h2::after {
            content: "";
            position: absolute;
            bottom: -0.8rem;
            left: 50%;
            transform: translateX(-50%);
            width: 60px;
            height: 3px;
            background: var(--azul-pastel);
        }



        .register-form {
            text-align: left;
            margin-top: 1.5rem;
        }



        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
        }



        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: var(--azul-medio);
        }



        .form-group input {
            width: 100%;
            padding: 0.8rem 1rem 0.8rem 2.5rem;
            border: 2px solid var(--azul-pastel);
            border-radius: 8px;
            font-size: 1rem;
            transition: all 0.3s ease;
            box-sizing: border-box;
        }



        .form-group input:focus {
            border-color: var(--azul-claro);
            outline: none;
            box-shadow: 0 0 0 3px rgba(75, 134, 212, 0.2);
        }



        .input-icon {
            position: absolute;
            left: 1rem;
            top: 2.4rem;
            color: var(--azul-medio);
        }



        .btn-register {
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
            margin-top: 0.5rem;
        }



        .btn-register:hover {
            background: linear-gradient(to right, var(--azul-medio), var(--azul-oscuro));
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(26, 62, 114, 0.2);
        }



        .login-link {
            margin-top: 1.5rem;
            color: var(--azul-medio);
            text-align: center;
        }



        .login-link a {
            color: var(--azul-claro);
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s;
        }



        .login-link a:hover {
            color: var(--azul-oscuro);
            text-decoration: underline;
        }



        @media (max-width: 480px) {
            .register-container {
                padding: 1.5rem;
                margin: 1rem;
            }

            

            body {
                background-image: none;
                background-color: var(--azul-hielo);
            }

        }

    </style>

</head>

<body>

    <div class="register-container">

        <div class="logo">

            <i class="fas fa-paw"></i>

        </div>

        

        <h2><i class="fas fa-user-plus"></i> Registro de Usuario</h2>

        

        <form class="register-form" action="${pageContext.request.contextPath}/UsuarioControlador" method="POST">

            <input type="hidden" name="opcion" value="registrar">

            

            <div class="form-group">

                <label for="nombre">Nombre Completo</label>

                <i class="fas fa-user input-icon"></i>

                <input type="text" id="nombre" name="nombre" required placeholder="Ingrese su nombre completo">

            </div>

            

            <div class="form-group">

                <label for="correo">Correo Electrónico</label>

                <i class="fas fa-envelope input-icon"></i>

                <input type="email" id="correo" name="correo" required placeholder="ejemplo@clinica.com">

            </div>

            

            <div class="form-group">

                <label for="clave">Contraseña</label>

                <i class="fas fa-lock input-icon"></i>

                <input type="password" id="clave" name="clave" required placeholder="••••••••">

            </div>

            

            <button type="submit" class="btn-register">Registrarse</button>

        </form>



        <div class="login-link">

            ¿Ya tienes una cuenta? <a href="${pageContext.request.contextPath}/UsuarioControlador?opcion=login">Inicia sesión aquí</a>

        </div>

    </div>

</body>

</html>