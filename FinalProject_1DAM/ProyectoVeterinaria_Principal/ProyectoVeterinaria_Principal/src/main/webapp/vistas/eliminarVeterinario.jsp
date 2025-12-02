<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar Veterinario</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            padding: 40px;
            margin: 0;
        }

        h1 {
            color: #1565c0;
            text-align: center;
            margin-bottom: 30px;
        }

        .form-container {
            max-width: 400px;
            margin: auto;
            background-color: #e3f2fd;
            padding: 30px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            border-radius: 15px;
            text-align: center;
        }

        .form-container button {
            background-color: #4a90e2;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            margin: 10px;
        }

        .form-container .cancel {
            background-color: #bbb;
        }

        .form-container p {
            font-size: 1.1em;
            margin-bottom: 20px;
            color: #333;
        }
    </style>
</head>
<body>

    <h1>Confirmar Eliminación</h1>

    <div class="form-container">
        <p>¿Estás seguro que deseas eliminar el veterinario con ID <strong>${idVeterinario}</strong>?</p>

        <form action="VeterinarioControlador" method="get">
            <input type="hidden" name="opcion" value="eliminar">
            <input type="hidden" name="idVeterinario" value="${idVeterinario}">
            <input type="hidden" name="confirmacion" value="si">
            <button type="submit">Sí, eliminar</button>
            <a href="VeterinarioControlador?opcion=consultar">
                <button type="button" class="cancel">Cancelar</button>
            </a>
        </form>
    </div>

</body>
</html>

