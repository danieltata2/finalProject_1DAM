<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EDITAR VETERINARIO</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #ecf0f1;
        padding: 30px;
    }
    h1 {
        text-align: center;
        color: #2c3e50;
    }
    form {
        max-width: 500px;
        margin: auto;
        background-color: #ffffff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }
    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
    }
    input[type="text"],
    input[type="number"],
    input[type="email"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    input[type="submit"] {
        background-color: #3498db;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background-color: #2980b9;
    }
</style>
</head>
<body>

<h1>EDITAR VETERINARIO</h1>

<form class="register-form" action="${pageContext.request.contextPath}/VeterinarioControlador" method="POST">
    <input type="hidden" name="opcion" value="editar">
    <input type="hidden" name="idVeterinario" value="${veterinario.idVeterinario}">

    <label>Nombre:</label>
    <input type="text" name="nombre" value="${veterinario.nombre}" required>

    <label>Apellido:</label>
    <input type="text" name="apellido" value="${veterinario.apellido}" required>

    <label>Teléfono:</label>
    <input type="text" name="telefono" value="${veterinario.telefono}" required>
    
    <label>Edad:</label>
    <input type="number" name="edad" value="${veterinario.edad}" required>

    <label>Especialidad:</label>
    <input type="text" name="especialidad" value="${veterinario.especialidad}" required>

    <input type="submit" value="Guardar Cambios">
</form>


</body>
</html>

