<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Citas - Veterinaria</title>
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
        }

        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--azul-hielo);
            color: var(--azul-oscuro);
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
            margin: 40px auto;
            background-color: var(--blanco);
            border-radius: 15px;
            box-shadow: var(--sombra);
            overflow: hidden;
        }

        .header {
            background: linear-gradient(135deg, var(--azul-oscuro), var(--azul-medio));
            color: var(--blanco);
            padding: 1.5rem;
            text-align: center;
            position: relative;
        }

        .header h1 {
            margin: 0;
            font-size: 1.8rem;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.8rem;
        }

        .table-container {
            padding: 2rem;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 0 auto;
        }

        th, td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid var(--azul-hielo);
        }

        th {
            background-color: var(--azul-medio);
            color: var(--blanco);
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.85rem;
            letter-spacing: 0.5px;
        }

        tr:hover {
            background-color: rgba(74, 134, 212, 0.05);
        }

        .actions {
            display: flex;
            gap: 0.5rem;
        }

        .btn {
            padding: 0.5rem 1rem;
            border-radius: 8px;
            font-weight: 500;
            font-size: 0.85rem;
            transition: all 0.3s ease;
            display: inline-flex;
            align-items: center;
            gap: 0.5rem;
            text-decoration: none;
            border: none;
            cursor: pointer;
        }

        .btn-edit {
            background-color: var(--azul-pastel);
            color: var(--azul-oscuro);
        }

        .btn-edit:hover {
            background-color: var(--azul-claro);
            color: var(--blanco);
            transform: translateY(-2px);
        }

        .btn-delete {
            background-color: #f8d7da;
            color: #721c24;
        }

        .btn-delete:hover {
            background-color: #f1b0b7;
            color: #721c24;
            transform: translateY(-2px);
        }

        .btn-add {
            display: inline-flex;
            margin: 1rem 0;
            background: linear-gradient(135deg, var(--azul-medio), var(--azul-oscuro));
            color: var(--blanco);
            padding: 0.8rem 1.5rem;
            border-radius: 8px;
            font-weight: 600;
            transition: all 0.3s ease;
            text-decoration: none;
            box-shadow: var(--sombra);
        }

        .btn-add:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(26, 62, 114, 0.2);
        }

        footer {
            text-align: center;
            padding: 25px;
            background: linear-gradient(135deg, var(--azul-medio), var(--azul-oscuro));
            color: var(--blanco);
            margin-top: 30px;
            font-size: 0.9em;
        }

        @media (max-width: 768px) {
            .table-container {
                padding: 1rem;
            }
            
            th, td {
                padding: 0.8rem;
            }
            
            .actions {
                flex-direction: column;
                gap: 0.3rem;
            }
            
            .btn {
                width: 100%;
                justify-content: center;
            }
            
            .header h1 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
    	<div style="text-align: right; margin-bottom: 20px;">

    		<a href="${pageContext.request.contextPath}/index.jsp" style="

        		display: inline-block;

        		padding: 10px 20px;

        		background-color: #34495e;

        		color: white;

        		text-decoration: none;

        		border-radius: 5px;

        		font-weight: bold;

    		">Volver</a>

		</div>
            
            <table>
                <thead>
                    <tr>
                        <th>ID Cita</th>
                        <th>Cliente</th>
                        <th>Animal</th>
                        <th>Veterinario</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cita" items="${lista}">
                        <tr>
                            <td>${cita.idCita}</td>
                            <td>
                                <c:forEach var="cliente" items="${clientes}">
                                    <c:if test="${cliente.idCliente == cita.idCliente}">
                                        ${cliente.nombre} ${cliente.apellido}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="animal" items="${animales}">
                                    <c:if test="${animal.idAnimal == cita.idAnimal}">
                                        ${animal.nombre} (${animal.especie})
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="veterinario" items="${veterinarios}">
                                    <c:if test="${veterinario.idVeterinario == cita.idVeterinario}">
                                        Dr. ${veterinario.nombre} ${veterinario.apellido}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${cita.fecha}</td>
                            <td>${cita.hora}</td>
                            <td>
                                <div class="actions">
                                    <a href="CitaControlador?opcion=editar&idCita=${cita.idCita}" class="btn btn-edit">
                                        <i class="fas fa-edit"></i> Editar
                                    </a>
                                    <a href="CitaControlador?opcion=eliminar&idCita=${cita.idCita}" class="btn btn-delete">
                                        <i class="fas fa-trash-alt"></i> Eliminar
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
</body>
</html>