<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 9/05/2025
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.tecsup.laboratorio_07.model.Categoria" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Lista de Categorías</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2 class="text-center mb-4 text-primary">
    <i class="bi bi-journal-bookmark-fill me-2"></i> Lista de Categorías
  </h2>

  <div class="d-flex justify-content-between mb-3">
    <a href="CategoriaServlet?accion=agregar" class="btn btn-primary">
      <i class="bi bi-plus-circle"></i> Agregar Nueva Categoría
    </a>
    <a href="ProductoServlet?accion=listar" class="btn btn-primary">
      <i class="bi bi-plus-circle"></i> Producto
    </a>
  </div>

  <table class="table table-bordered table-hover table-striped">
    <thead class="table-dark">
    <tr>
      <th>Código</th>
      <th>Nombre</th>
      <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <!-- Usando JSTL para iterar sobre la lista de categorías -->
    <c:choose>
      <c:when test="${not empty listaCategoria}">
        <c:forEach var="categoria" items="${listaCategoria}">
          <tr>
            <td>${categoria.idCategoria}</td>
            <td>${categoria.nombreCategoria}</td>
            <td>
              <a href="CategoriaServlet?accion=editar&id=${categoria.idCategoria}" class="btn btn-warning btn-sm">
                <i class="bi bi-pencil-fill"></i> Editar
              </a>
              <a href="CategoriaServlet?accion=eliminar&id=${categoria.idCategoria}" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro?')">
                <i class="bi bi-trash"></i> Eliminar
              </a>
            </td>
          </tr>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <tr>
          <td colspan="3" class="text-center">No hay categorías disponibles.</td>
        </tr>
      </c:otherwise>
    </c:choose>
    </tbody>
  </table>
</div>
</body>
</html>
