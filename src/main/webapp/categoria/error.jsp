<%--
  Created by IntelliJ IDEA.
  User: Sonaly
  Date: 9/05/2025
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Error</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
<c:choose>
  <c:when test="${not empty mensaje}">
    <div class="alert alert-danger" role="alert">
      <h4 class="alert-heading">¡Error!</h4>
      <p>${mensaje}</p>
    </div>
  </c:when>
  <c:otherwise>
    <div class="alert alert-danger" role="alert">
      <h4 class="alert-heading">¡Error desconocido!</h4>
      <p>No se pudieron cargar las categorías desde la base de datos.</p>
      <p>Por favor, intente nuevamente más tarde o contacte con el administrador.</p>
    </div>
  </c:otherwise>
</c:choose>

<a href="CategoriaServlet?accion=listar" class="btn btn-primary">Volver a intentar</a>
</body>
</html>
