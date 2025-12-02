<!DOCTYPE html>

<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Página Principal</title>
  <style>

    body {
      margin: 0;
      padding: 0;
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #74ebd5, #ACB6E5);
      height: 100vh;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      position: relative;
      text-align: center;
    }

    .logo {
      position: absolute;
      top: 20px;
      right: 20px;
      width: 100px;
    }

    h1 {
      font-size: 48px;
      color: #ffffff;
      margin-bottom: 40px;
      text-shadow: 2px 2px 5px rgba(0,0,0,0.3);
    }

    button {
      padding: 15px 30px;
      font-size: 20px;
      background-color: #ffffff;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      color: #333;
      box-shadow: 0 4px 6px rgba(0,0,0,0.2);
      transition: background-color 0.3s ease;
    }
	
    button:hover {
      background-color: #f0f0f0;
    }
  </style>
  
</head>
<body>
	  <img src="imagenes/logo.png" alt="Logo" class="logo">
	  <h1>Bienvenido</h1>
	  <button onclick="location.href='vistas/registro.jsp'">Iniciar</button>
</body>
</html>