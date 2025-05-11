package com.tecsup.laboratorio_07.dao;

import com.tecsup.laboratorio_07.ConexionDB;
import com.tecsup.laboratorio_07.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private ConexionDB conexionDB;
    public CategoriaDAO() {
        this.conexionDB = new ConexionDB();
    }
    private Connection conectar() throws SQLException {
        return conexionDB.getConnection();
    }
    // Listar categorías
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombreCategoria(rs.getString("nombre_categoria"));
                lista.add(c);  }
        } catch (Exception e) {
            System.out.println("Error al listar las categorías.");
            e.printStackTrace();
        }  return lista; }

    // Insertar nueva categoría
    public boolean insertar(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre_categoria) VALUES (?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombreCategoria());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar la categoría.");
            e.printStackTrace();
        }

        return false;
    }

    // Actualizar categoría
    public boolean actualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre_categoria = ? WHERE id_categoria = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getIdCategoria());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar la categoría.");
            e.printStackTrace();
        }

        return false;
    }

    // Eliminar categoría
    public boolean eliminar(int id) {
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar la categoría.");
            e.printStackTrace();
        }

        return false;
    }

    // Buscar categoría por ID
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        Categoria categoria = null;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombreCategoria(rs.getString("nombre_categoria"));
            }

        } catch (Exception e) {
            System.out.println("Error al buscar la categoría.");
            e.printStackTrace();
        }

        return categoria;
    }

    public static void main(String[] args) {
        CategoriaDAO dao = new CategoriaDAO();

        try (Connection conn = dao.conectar()) {
            if (conn != null) {
                System.out.println("Conexión a la base de datos exitosa.");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo conectar con la base de datos.");
            return;
        }

        // Insertar categoría
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombreCategoria("Tecnología");

        if (dao.insertar(nuevaCategoria)) {
            System.out.println("Categoría insertada correctamente.");
        } else {
            System.out.println("No se pudo insertar la categoría.");
        }

        // Listar categorías
        List<Categoria> categorias = dao.listar();
        System.out.println("Lista de categorías:");
        for (Categoria c : categorias) {
            System.out.println("ID: " + c.getIdCategoria() + " | Nombre: " + c.getNombreCategoria());
        }

        // Actualizar categoría
        Categoria categoriaActualizar = dao.buscarPorId(1);
        if (categoriaActualizar != null) {
            categoriaActualizar.setNombreCategoria("Tecnología Avanzada");

            boolean actualizacionExitosa = dao.actualizar(categoriaActualizar);
            if (actualizacionExitosa) {
                System.out.println("Categoría actualizada correctamente.");
            } else {
                System.out.println("No se pudo actualizar la categoría.");
            }
        } else {
            System.out.println("Categoría no encontrada para actualizar.");
        }

        // Eliminar categoría
        boolean eliminacionExitosa = dao.eliminar(1);
        if (eliminacionExitosa) {
            System.out.println("Categoría eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la categoría.");
        }

        // Buscar categoría por ID
        Categoria categoriaBuscada = dao.buscarPorId(2);
        if (categoriaBuscada != null) {
            System.out.println("Categoría encontrada:");
            System.out.println("ID: " + categoriaBuscada.getIdCategoria() +
                    ", Nombre: " + categoriaBuscada.getNombreCategoria());
        } else {
            System.out.println("No se encontró la categoría con ID 2.");
        }
    }
}
