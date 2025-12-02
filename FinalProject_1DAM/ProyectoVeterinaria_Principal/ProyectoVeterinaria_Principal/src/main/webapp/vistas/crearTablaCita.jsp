<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pantalla Principal - Veterinaria</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f2f2;
            padding: 30px;
            color: #333;
        }
        h1 {
            text-align: center;
            color: #2c3e50;
        }
        .menu {
            width: 400px;
            margin: auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .menu input, .menu button {
            display: block;
            margin: 10px 0;
            padding: 10px;
            text-align: center;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 6px;
            transition: background-color 0.3s;
            cursor: pointer;
        }
        .menu button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

<h1>Gestión de Veterinaria</h1>
<div class="menu">
   
    <form action="CitaControlador" method="GET">
        <input type="hidden" name="opcion" value="crearTabla">
        <button type="submit">Crear tabla CITA</button>
    </form>
    
   
    
</div>

</body>
</html>
