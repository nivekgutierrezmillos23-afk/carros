package org.example;

import org.example.dao.*;
import org.example.model.*;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PersonaDAO personaDAO = new PersonaDAOimpl();
        CarroDAO carroDAO = new CarroDAOimpl();

        int opcion = 0;

        do {
            System.out.println("===== SISTEMA DE GESTIÓN DE CARROS =====");
            System.out.println("1. Registrar nueva Persona");
            System.out.println("2. Registrar nuevo Carro");
            System.out.println("3. Listar todos los registros");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) { // Validación para evitar errores si escriben letras
                System.out.print("Por favor, ingrese un número: ");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarPersona(personaDAO, sc);
                    break;
                case 2:
                    registrarCarro(carroDAO, sc);
                    break;
                case 3:
                    listarTodo(personaDAO, carroDAO);
                    break;
                case 4:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void registrarPersona(PersonaDAO dao, Scanner sc) {
        System.out.println("\n--- Registro de Persona ---");
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Primer Apellido: "); String ap1 = sc.nextLine();
        System.out.print("Segundo Apellido: "); String ap2 = sc.nextLine();
        System.out.print("DNI: "); String dni = sc.nextLine();

        dao.insertar(new Persona(nombre, ap1, ap2, dni));
    }

    private static void registrarCarro(CarroDAO dao, Scanner sc) {
        System.out.println("\n--- Registro de Carro ---");
        System.out.print("Matrícula: "); String mat = sc.nextLine();
        System.out.print("Marca: "); String marca = sc.nextLine();
        System.out.print("Modelo: "); String mod = sc.nextLine();
        System.out.print("Caballos: "); int cab = sc.nextInt();
        System.out.print("ID del Dueño (Persona): "); int idDueño = sc.nextInt();

        dao.insertar(new Carro(mat, marca, mod, cab, idDueño));
    }

    private static void listarTodo(PersonaDAO pDao, CarroDAO cDao) {
        System.out.println("\n--- Listado General (Personas y sus Carros) ---");
        List<Persona> personas = pDao.listarTodas();

        for (Persona p : personas) {
            System.out.println("\n👤 Propietario: " + p.getNombre() + " " + p.getApellido1() + " (ID: " + p.getId() + ")");
            List<Carro> carros = cDao.listarPorDuenio(p.getId());

            if (carros.isEmpty()) {
                System.out.println("   (No tiene carros registrados)");
            } else {
                for (Carro c : carros) {
                    System.out.println("   🚗 [" + c.getMatricula() + "] " + c.getMarca() + " " + c.getModelo() + " - " + c.getCaballos() + " CV");
                }
            }
        }
    }
}