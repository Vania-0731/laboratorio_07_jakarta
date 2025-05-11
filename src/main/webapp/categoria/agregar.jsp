<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 9/05/2025
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Categoría</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body class="bg-light">
<div class="container mt-5 d-flex justify-content-center">
    <div class="card shadow p-4" style="width: 100%; max-width: 500px;">
        <h3 class="text-center mb-4 text-primary">
            <i class="bi bi-folder-plus me-2"></i>Agregar Nueva Categoría
        </h3>
        <form action="CategoriaServlet" method="post">
            <input type="hidden" name="accion" value="guardar">
            <div class="mb-3">
                <label for="nombre_categoria" class="form-label">Nombre</label>
                <input type="text" name="nombre_categoria" id="nombre_categoria" class="form-control" placeholder="Ej. Electrónica" required>
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <a href="CategoriaServlet?accion=listar" class="btn btn-secondary">
                    <i class="bi bi-arrow-left"></i> Volver
                </a>
                <button type="submit" class="btn btn-success">
                    <i class="bi bi-check-circle"></i> Guardar
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
