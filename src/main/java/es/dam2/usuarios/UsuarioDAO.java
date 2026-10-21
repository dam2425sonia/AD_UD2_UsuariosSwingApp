package es.dam2.usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Clase que maneja las operaciones de acceso a la base de datos relacionadas con los usuarios
public class UsuarioDAO {
    // Método que valida las credenciales de un usuario en la base de datos
    public boolean validarCredenciales(String username, String password) throws SQLException {
        String sql = "SELECT 1 FROM usuarios WHERE username = ? AND password = ? LIMIT 1";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    // Método que lista todos los usuarios de la base de datos
    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT id, username, password, fecha_creacion FROM usuarios ORDER BY id";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getTimestamp("fecha_creacion")
                ));
            }
        }
        return usuarios;
    }

    // Método que elimina un usuario de la base de datos por su ID
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }   

    // Método que actualiza los datos de un usuario en la base de datos
    public void actualizar(int id, String username, String password) throws SQLException {
        String sql = "UPDATE usuarios SET username = ?, password = ? WHERE id = ?";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }
}
