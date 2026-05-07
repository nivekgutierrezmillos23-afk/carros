package org.example.model;

public class Carro {
    private String matricula;
    private String marca;
    private String modelo;
    private int caballos;
    private int personaId; // Esta es la llave foránea


    public Carro() {
    }


    public Carro(String matricula, String marca, String modelo, int caballos, int personaId) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.caballos = caballos;
        this.personaId = personaId;
    }


    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getCaballos() { return caballos; }
    public void setCaballos(int caballos) { this.caballos = caballos; }

    public int getPersonaId() { return personaId; }
    public void setPersonaId(int personaId) { this.personaId = personaId; }

    @Override
    public String toString() {
        return "Carro [" +
                "Matricula='" + matricula + '\'' +
                ", Marca='" + marca + '\'' +
                ", Modelo='" + modelo + '\'' +
                ", Caballos=" + caballos +
                ", Dueño(ID)=" + personaId +
                ']';
    }
}
