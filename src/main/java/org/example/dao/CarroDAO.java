package org.example.dao;

import org.example.model.Carro;
import java.util.List;

public interface CarroDAO {
    void insertar(Carro carro);
    List<Carro> listarPorDuenio(int personaId);
}
