package org.example.dao;

import org.example.model.Persona;
import java.util.List;

public interface PersonaDAO {
    void insertar(Persona persona);
    List<Persona> listarTodas();
    Persona buscarPorId(int id);
}
