package integrado.prog2.menu;

import integrado.prog2.config.Utilitarios;
import integrado.prog2.service.*;
import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private CategoriaService categoriaService;
    private ProductoService productoService;
    private UsuarioService usuarioService;
    private PedidoService pedidoService;

    public Menu() {
        sc = new Scanner(System.in);
        categoriaService = new CategoriaService(sc);
        productoService = new ProductoService(sc, categoriaService);
        usuarioService = new UsuarioService(sc);
        pedidoService = new PedidoService(sc, usuarioService, productoService);
    }

    public void iniciar() {
        int op;
        do {
            Utilitarios.titulo("=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("5. Salir");
            op = Utilitarios.leerInt(sc, "Seleccione: ");
            
            switch (op) {
                case 1 -> categoriaService.menu();
                case 2 -> productoService.menu();
                case 3 -> usuarioService.menu();
                case 4 -> pedidoService.menu();
                case 5 -> Utilitarios.mostrarMensaje("Saliendo del sistema... Gracias!");
                default -> Utilitarios.mostrarMensaje("Opcion Invalida!. Elija entre 1 y 5");
            }
        } while (op != 5);
        sc.close();
    }
}