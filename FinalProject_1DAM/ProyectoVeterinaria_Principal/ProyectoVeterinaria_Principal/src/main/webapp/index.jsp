<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clinica Veterinaria</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<style>
:root {
	--azul-oscuro: #1a3e72;
	--azul-medio: #2a56a5;
	--azul-claro: #4b86d4;
	--azul-pastel: #a8c6f7;
	--azul-hielo: #e6f0ff;
	--blanco: #ffffff;
	--sombra: 0 4px 12px rgba(26, 62, 114, 0.15);
}

body {
	margin: 0;
	padding: 0;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: var(--azul-hielo);
	color: var(--azul-oscuro);
	line-height: 1.6;
}

header {
	background: linear-gradient(135deg, var(--azul-oscuro),
		var(--azul-medio));
	color: var(--blanco);
	padding: 30px 0;
	text-align: center;
	font-size: 2.5em;
	font-weight: 600;
	letter-spacing: 1px;
	box-shadow: var(--sombra);
	position: relative;
	text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
}

header::after {
	content: "";
	display: block;
	width: 100px;
	height: 4px;
	background: var(--azul-pastel);
	margin: 15px auto 0;
	border-radius: 2px;
}

.logout {
	position: absolute;
	top: 20px;
	right: 30px;
}

.logout a {
	background-color: var(--azul-pastel);
	color: var(--azul-oscuro);
	padding: 2px 4px;
	border-radius: 3px;
	text-decoration: none;
	font-weight: bold;
	font-size: 0.5em;
	transition: background-color 0.3s ease, color 0.3s ease;
}

.logout a:hover {
	background-color: var(--azul-claro);
	color: var(--blanco);
}

.contenedor {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	gap: 30px;
	padding: 40px;
	max-width: 1200px;
	margin: 0 auto;
}

.card {
	background-color: var(--blanco);
	width: 280px;
	padding: 30px 25px;
	border-radius: 15px;
	box-shadow: var(--sombra);
	text-align: center;
	transition: all 0.3s ease;
	position: relative;
	overflow: hidden;
}

.card:hover {
	transform: translateY(-8px);
	box-shadow: 0 8px 20px rgba(26, 62, 114, 0.2);
}

.card::before {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 5px;
	background: linear-gradient(90deg, var(--azul-claro), var(--azul-medio));
}

.card i {
	font-size: 2.5em;
	color: var(--azul-medio);
	margin-bottom: 15px;
	display: block;
}

.card h2 {
	margin: 0 0 20px 0;
	font-size: 1.5em;
	color: var(--azul-oscuro);
	position: relative;
	padding-bottom: 10px;
}

.card h2::after {
	content: "";
	position: absolute;
	bottom: 0;
	left: 50%;
	transform: translateX(-50%);
	width: 50px;
	height: 2px;
	background: var(--azul-pastel);
}

.card a {
	display: block;
	margin: 12px 0;
	padding: 12px 0;
	background-color: var(--azul-pastel);
	color: var(--azul-oscuro);
	text-decoration: none;
	border-radius: 8px;
	transition: all 0.3s ease;
	font-weight: 500;
}

.card a:hover {
	background-color: var(--azul-claro);
	color: var(--blanco);
	transform: translateY(-2px);
}

footer {
	text-align: center;
	padding: 25px;
	background: linear-gradient(135deg, var(--azul-medio),
		var(--azul-oscuro));
	color: var(--blanco);
	margin-top: 30px;
	font-size: 0.9em;
}

@media ( max-width : 768px) {
	.contenedor {
		padding: 20px;
		gap: 20px;
	}
	.card {
		width: 100%;
		max-width: 350px;
	}
	header {
		font-size: 1.8em;
		padding: 20px 0;
	}
	.logout {
		top: 15px;
		right: 20px;
	}
}
</style>
</head>
<body>

	<header>
		Clínica Veterinaria
		<div class="logout">
			<a href="UsuarioControlador?opcion=cerrarSesion"> <i
				class="fas fa-sign-out-alt"></i> Cerrar Sesión
			</a>
		</div>
	</header>

	<%
	String mensaje = (String) request.getAttribute("mensaje");
	if (mensaje != null) {
	%>
	<div
		style="background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; padding: 15px; margin: 20px auto; width: 60%; border-radius: 8px; text-align: center;">
		<strong><i class="fas fa-check-circle"></i></strong>
		<%=mensaje%>
	</div>
	<%
	}
	%>


	<div class="contenedor">

		<div class="card">
			<i class="fas fa-user"></i>
			<h2>Clientes</h2>
			<a href="ClienteControlador?opcion=crearTabla">Crear Tabla</a> <a
				href="ClienteControlador?opcion=insertar">Nuevo Cliente</a> <a
				href="ClienteControlador?opcion=consultar">Listar Clientes</a>
		</div>

		<div class="card">
			<i class="fas fa-dog"></i>
			<h2>Animales</h2>
			<a href="AnimalControlador?opcion=crearTabla">Crear Tabla</a> <a
				href="AnimalControlador?opcion=insertar">Nuevo Animal</a> <a
				href="AnimalControlador?opcion=consultar">Listar Animales</a>
		</div>

		<div class="card">
			<i class="fas fa-user-md"></i>
			<h2>Veterinarios</h2>        
			<a href="VeterinarioControlador?opcion=crearTabla">Crear Tabla</a> <a
				href="VeterinarioControlador?opcion=insertar">Nuevo Veterinario</a>
			<a href="VeterinarioControlador?opcion=consultar">Listar
				Veterinarios</a>
		</div>

		<div class="card">
			<i class="fas fa-calendar-check"></i>
			<h2>Citas</h2>
			<a href="CitaControlador?opcion=crearTabla">Crear Tabla</a> <a
				href="CitaControlador?opcion=insertar">Nueva Cita</a> <a
				href="CitaControlador?opcion=consultar">Listar Citas</a>
		</div>
		
		<div class="card">
			<i class="fas fa-calendar-check"></i>
			<h2>Generar PDF</h2>
			<a href="pagina_pdf.jsp" target="_blank">Ver Reporte PDF</a>

			
		</div>

	</div>

</body>
</html>
