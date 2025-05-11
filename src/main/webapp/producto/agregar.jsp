<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 10/05/2025
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.tecsup.laboratorio_07.model.Producto" %>
<%@ page import="com.tecsup.laboratorio_07.model.Categoria" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <!-- Sección de Agregar Producto -->
    <h2 class="text-center mb-4 text-primary">
        <i class="bi bi-boxes me-2"></i> Agregar Nuevo Producto
    </h2>

    <form action="ProductoServlet" method="post">
        <input type="hidden" name="accion" value="guardar">

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre del Producto</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>

        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea class="form-control" id="descripcion" name="descripcion" rows="4" required></textarea>
        </div>

        <div class="mb-3">
            <label for="precio" class="form-label">Precio</label>
            <input type="number" class="form-control" id="precio" name="precio" step="0.01" required>
        </div>

        <div class="mb-3">
            <label for="categoria" class="form-label">Categoría</label>
            <select class="form-select" id="categoria" name="id_categoria" required>
                <c:forEach var="categoria" items="${listaCategoria}">
                    <!-- Compara si la categoria.idCategoria es igual a producto.idCategoria en el caso de editar -->
                    <option value="${categoria.idCategoria}" ${categoria.idCategoria == producto.idCategoria ? 'selected' : ''}>
                            ${categoria.nombreCategoria}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="d-flex justify-content-between mb-3">
            <button type="submit" class="btn btn-success">
                <i class="bi bi-save"></i> Guardar Producto
            </button>
            <a href="ProductoServlet?accion=listar" class="btn btn-secondary">
                <i class="bi bi-arrow-left"></i> Volver a la Lista
            </a>
        </div>
    </form>
</div>
</body>
</html>
