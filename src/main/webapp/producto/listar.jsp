<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 10/05/2025
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.tecsup.laboratorio_07.model.Producto" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Categorías y Productos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <!-- Sección de Productos -->
    <h2 class="text-center mb-4 text-primary">
        <i class="bi bi-boxes me-2"></i> Lista de Productos
    </h2>

    <div class="d-flex justify-content-between mb-3">
        <a href="ProductoServlet?accion=agregar" class="btn btn-success">
            <i class="bi bi-plus-circle"></i> Agregar Nuevo Producto
        </a>
        <a href="CategoriaServlet?accion=listar" class="btn btn-success">
            <i class="bi bi-plus-circle"></i> Categorías
        </a>
    </div>

    <table class="table table-bordered table-hover table-striped">
        <thead class="table-dark">
        <tr>
            <th>Código</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Categoría</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <!-- Usando JSTL para iterar sobre la lista de productos -->
        <c:choose>
            <c:when test="${not empty listaProducto}">
                <c:forEach var="producto" items="${listaProducto}">
                    <tr>
                        <td>${producto.idProducto}</td>
                        <td>${producto.nombre}</td>
                        <td>${producto.descripcion}</td>
                        <td>${producto.precio}</td>
                        <!-- Mostrar el nombre de la categoría usando el mapa -->
                        <td>${categoriasMap[producto.idCategoria]}</td>
                        <td>
                            <a href="ProductoServlet?accion=editar&id=${producto.idProducto}" class="btn btn-warning btn-sm">
                                <i class="bi bi-pencil-fill"></i> Editar
                            </a>
                            <a href="ProductoServlet?accion=eliminar&id=${producto.idProducto}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro?')">
                                <i class="bi bi-trash"></i> Eliminar
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6" class="text-center">No hay productos disponibles.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
