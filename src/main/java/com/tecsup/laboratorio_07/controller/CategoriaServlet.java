package com.tecsup.laboratorio_07.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.tecsup.laboratorio_07.model.Categoria;
import com.tecsup.laboratorio_07.dao.CategoriaDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
    private CategoriaDAO dao = new CategoriaDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";
        switch (accion) {
            case "listar":
                List<Categoria> categorias = dao.listar();
                if (categorias != null && !categorias.isEmpty()) {
                    request.setAttribute("listaCategoria", categorias);
                } else {request.setAttribute("mensaje", "No se encontraron categorías.");}
                request.getRequestDispatcher("categoria/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("categoria/agregar.jsp").forward(request, response);
                break;
            case "editar":
                String idEditar = request.getParameter("id");
                if (idEditar != null) {
                    Categoria categoriaEditar = dao.buscarPorId(Integer.parseInt(idEditar));
                    request.setAttribute("categoria", categoriaEditar);
                    request.getRequestDispatcher("categoria/editar.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "ID no encontrado para editar.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                break;
            case "eliminar":
                String idEliminar = request.getParameter("id");
                if (dao.eliminar(Integer.parseInt(idEliminar))) {
                    request.setAttribute("mensaje", "Categoría eliminada correctamente.");
                } else {
                    request.setAttribute("mensaje", "No se pudo eliminar la categoría.");
                }
                response.sendRedirect("CategoriaServlet?accion=listar");
                break;

            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            String nombre = request.getParameter("nombre_categoria");
            Categoria categoria = new Categoria();
            categoria.setNombreCategoria(nombre);

            dao.insertar(categoria);
            response.sendRedirect("CategoriaServlet?accion=listar");

        } else if ("actualizar".equals(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id_categoria"));
                String nombre = request.getParameter("nombre_categoria");

                Categoria categoria = new Categoria();
                categoria.setIdCategoria(id);
                categoria.setNombreCategoria(nombre);

                dao.actualizar(categoria);
                response.sendRedirect("CategoriaServlet?accion=listar");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID inválido.");
                request.getRequestDispatcher("categoria/formulario.jsp").forward(request, response);
            }
        }
    }
}