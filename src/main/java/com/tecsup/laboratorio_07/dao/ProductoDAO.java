package com.tecsup.laboratorio_07.dao;

import com.tecsup.laboratorio_07.ConexionDB;
import com.tecsup.laboratorio_07.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private ConexionDB conexionDB;
    public ProductoDAO() {
        this.conexionDB = new ConexionDB();
    }
    private Connection conectar() throws SQLException {
        return conexionDB.getConnection();
    }
    // Listar productos
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                lista.add(p);}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Insertar nuevo producto
    public boolean insertar(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, id_categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getIdCategoria());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Actualizar producto
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, id_categoria = ? WHERE id_producto = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getIdCategoria());
            ps.setInt(5, producto.getIdProducto());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Eliminar producto
    public boolean eliminar(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Buscar producto por ID
    public Producto buscarPorId(int id) {
        String sql = "SELECT * FROM producto WHERE id_producto = ?";
        Producto producto = null;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }
}
