package org.example.dao;

import org.example.util.ConexionBD;
import org.example.model.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAOimpl implements PersonaDAO {

    @Override
    public void insertar(Persona persona) {
        String sql = "INSERT INTO Persona (Nombre, Apellido1, Apellido2, DNI) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getApellido1());
            ps.setString(3, persona.getApellido2());
            ps.setString(4, persona.getDni());
            ps.executeUpdate();
            System.out.println("✅ Persona guardada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Persona> listarTodas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM Persona";
        try (Connection con = ConexionBD.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Persona(
                        rs.getInt("id"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido1"),
                        rs.getString("Apellido2"),
                        rs.getString("DNI")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Persona buscarPorId(int id) {
        String sql = "SELECT * FROM Persona WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Persona(rs.getInt("id"), rs.getString("Nombre"),
                            rs.getString("Apellido1"), rs.getString("Apellido2"),
                            rs.getString("DNI"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}