package integrado.prog2.config;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilitarios {
    
    public static void mostrarMensaje(String mensaje) {
        System.out.println(">>> " + mensaje);
    }    
// Lee un int y no se rompe nunca. Si pones letra te vuelve a pedir
    public static int leerInt(Scanner sc, String mensaje) {
        while(true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error! Debe ingresar un número");
                sc.nextLine(); // limpiar lo que escribió mal
            }
        }
    }
    
    // Lee un double con validación
    public static double leerDouble(Scanner sc, String mensaje) {
        while(true) {
            System.out.print(mensaje);
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error! Debe ingresar un número decimal");
                sc.nextLine();
            }
        }
    }
    
    // Lee un String que no esté vacío
    public static String leerString(Scanner sc, String mensaje) {
        while(true) {
            System.out.print(mensaje);
            String texto = sc.nextLine();
            if(!texto.isBlank()) {
                return texto;
            }
            System.out.println("Error! No puede estar vacío");
        }
    }
    
    // Para mostrar títulos lindos
    public static void titulo(String texto) {
        System.out.println("\n**** " + texto + " ****");
    }
}