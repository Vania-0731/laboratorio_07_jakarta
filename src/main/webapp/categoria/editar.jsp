<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 9/05/2025
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.tecsup.laboratorio_07.model.Categoria" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Editar Categoría</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2 class="text-center mb-4 text-primary">
    <i class="bi bi-pencil-fill me-2"></i>Editar Categoría
  </h2>
  <form action="CategoriaServlet" method="post" class="mt-3">
    <input type="hidden" name="accion" value="actualizar">
    <input type="hidden" name="id_categoria" value="${categoria.idCategoria}">
    <div class="mb-3">
      <label class="form-label">Nombre:</label>
      <input type="text" name="nombre_categoria" value="${categoria.nombreCategoria}" class="form-control" required placeholder="Nombre de la categoría">
    </div>
    <div class="d-flex justify-content-between">
      <button type="submit" class="btn btn-warning">
        <i class="bi bi-save"></i> Actualizar
      </button>
      <a href="CategoriaServlet?accion=listar" class="btn btn-secondary">
        <i class="bi bi-arrow-left-circle"></i> Volver
      </a>
    </div>
  </form>
</div>
</body>
</html>

