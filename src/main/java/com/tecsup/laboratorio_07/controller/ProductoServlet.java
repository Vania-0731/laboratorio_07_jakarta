package com.tecsup.laboratorio_07.controller;

import com.tecsup.laboratorio_07.dao.ProductoDAO;
import com.tecsup.laboratorio_07.dao.CategoriaDAO;  // Asegúrate de importar el CategoriaDAO
import com.tecsup.laboratorio_07.model.Categoria;
import com.tecsup.laboratorio_07.model.Producto;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {
    private ProductoDAO dao = new ProductoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";
        switch (accion) {
            case "listar":
                List<Producto> productos = dao.listar();
                request.setAttribute("listaProducto", productos);
                List<Categoria> categorias = categoriaDAO.listar();
                Map<Integer, String> categoriasMap = new HashMap<>();
                for (Categoria categoria : categorias) {
                    categoriasMap.put(categoria.getIdCategoria(), categoria.getNombreCategoria());}
                request.setAttribute("categoriasMap", categoriasMap);
                request.getRequestDispatcher("producto/listar.jsp").forward(request, response);
                break;

            case "agregar":
                // Recuperar todas las categorías para el formulario de agregar
                List<Categoria> listaCategorias = categoriaDAO.listar();  // Cambié el nombre de la variable aquí
                request.setAttribute("listaCategoria", listaCategorias);  // Pasa las categorías al JSP
                request.getRequestDispatcher("producto/agregar.jsp").forward(request, response);
                break;

            case "editar":
                String idEditar = request.getParameter("id");
                if (idEditar != null) {
                    Producto productoEditar = dao.buscarPorId(Integer.parseInt(idEditar));
                    request.setAttribute("producto", productoEditar);

                    // Recuperar todas las categorías para el formulario de editar
                    List<Categoria> categoriasEditar = categoriaDAO.listar();
                    request.setAttribute("listaCategoria", categoriasEditar);  // Pasa las categorías al JSP
                    request.getRequestDispatcher("producto/editar.jsp").forward(request, response);
                }
                break;

            case "eliminar":
                String idEliminar = request.getParameter("id");
                if (dao.eliminar(Integer.parseInt(idEliminar))) {
                    request.setAttribute("mensaje", "Producto eliminado correctamente.");
                } else {
                    request.setAttribute("mensaje", "No se pudo eliminar el producto.");
                }
                response.sendRedirect("ProductoServlet?accion=listar");
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
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));

            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setIdCategoria(idCategoria);

            dao.insertar(producto);
            response.sendRedirect("ProductoServlet?accion=listar");

        } else if ("actualizar".equals(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id_producto"));
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                double precio = Double.parseDouble(request.getParameter("precio"));
                int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));

                Producto producto = new Producto();
                producto.setIdProducto(id);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setPrecio(precio);
                producto.setIdCategoria(idCategoria);

                dao.actualizar(producto);
                response.sendRedirect("ProductoServlet?accion=listar");
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID inválido.");
                request.getRequestDispatcher("producto/formulario.jsp").forward(request, response);
            }
        }
    }
}
