package org.example.dao;

import org.example.util.ConexionBD;
import org.example.model.Carro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAOimpl implements CarroDAO {

    @Override
    public void insertar(Carro carro) {
        String sql = "INSERT INTO Coche (Matricula, Marca, Modelo, Caballos, Persona_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, carro.getMatricula());
            ps.setString(2, carro.getMarca());
            ps.setString(3, carro.getModelo());
            ps.setInt(4, carro.getCaballos());
            ps.setInt(5, carro.getPersonaId());
            ps.executeUpdate();
            System.out.println("🚗 Carro registrado exitosamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error: Posiblemente el ID de la persona no existe o la matrícula está duplicada.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Carro> listarPorDuenio(int personaId) {
        List<Carro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Coche WHERE Persona_id = ?";
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, personaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Carro(
                            rs.getString("Matricula"),
                            rs.getString("Marca"),
                            rs.getString("Modelo"),
                            rs.getInt("Caballos"),
                            rs.getInt("Persona_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}