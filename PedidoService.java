package integrado.prog2.service;

import integrado.prog2.entities.*;
import integrado.prog2.enums.Estado;
import integrado.prog2.exception.ValidacionException;
import integrado.prog2.config.Utilitarios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {
    private List<Pedido> pedidos;
    private UsuarioService usuarioService;
    private ProductoService productoService;
    private Scanner sc;
    
    public PedidoService(Scanner sc,UsuarioService usuarioService, ProductoService productoService) {
        this.sc=sc;
        this.pedidos = new ArrayList<>();
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }
  
    public void menu(){
        int op; do{
            Utilitarios.titulo("MENU PEDIDOS");
            System.out.println("1. Crear Pedido");
            System.out.println("2. Agregar Producto al Pedido");
            System.out.println("3. Listar Pedidos");
            System.out.println("4. Cambiar Estado");
            System.out.println("0. Volver");
            op = Utilitarios.leerInt(sc, "Seleccione: ");
            try{
                switch(op){
                    case 1 -> crearPedidoConsola();
                    case 2 -> agregarProductoConsola();
                    case 3 -> listarPedidos();
                    case 4 -> cambiarEstadoConsola();
                }
            }catch(ValidacionException e){
                System.out.println(" " + e.getMessage());
            }catch(Exception e){
                System.out.println(" Error! " + e.getMessage());
            }
        }while(op!=0);
    }

    // METODOS PARA PEDIR POR CONSOLA
    private void crearPedidoConsola() {
        usuarioService.listar(); // para ver que usuarios hay
        Long idUsuario = (long) Utilitarios.leerInt(sc, "ID Usuario: ");
        crearPedido(idUsuario);
    }

    private void agregarProductoConsola() {
        listarPedidos();
        Long idPedido = (long) Utilitarios.leerInt(sc, "ID Pedido: ");
        productoService.listar(); // para ver que productos hay
        Long idProducto = (long) Utilitarios.leerInt(sc, "ID Producto: ");
        int cantidad = Utilitarios.leerInt(sc, "Cantidad: ");
        agregarProductoAPedido(idPedido, idProducto, cantidad);
    }

    private void cambiarEstadoConsola() {
        listarPedidos();
        Long idPedido = (long) Utilitarios.leerInt(sc, "ID Pedido: ");
        System.out.println("Estados: PENDIENTE, ENVIADO, ENTREGADO, CANCELADO");
        String est = Utilitarios.leerString(sc, "Nuevo Estado: ").toUpperCase();
        cambiarEstadoPedido(idPedido, Estado.valueOf(est));
    }

    public void crearPedido(Long idUsuario) {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);
        if (usuario == null) {
            throw new ValidacionException("El usuario no existe");
        }

        Pedido pedido = new Pedido(null, LocalDate.now(), Estado.PENDIENTE, null, usuario);
        pedidos.add(pedido);
        Utilitarios.mostrarMensaje("Pedido creado con ID: " + pedido.getId());
    }

    public void agregarProductoAPedido(Long idPedido, Long idProducto, int cantidad) {
        Pedido pedido = buscarPorId(idPedido);
        if (pedido == null) {
            throw new ValidacionException("El pedido no existe");
        }
        if (pedido.getEstado() != Estado.PENDIENTE) {
            throw new ValidacionException("No se pueden modificar pedidos que no estén pendientes");
        }
        if (cantidad <= 0) { 
            throw new ValidacionException("La cantidad debe ser mayor a 0");
        }

        Producto producto = productoService.buscarPorId(idProducto);
        if (producto == null) {
            throw new ValidacionException("El producto no existe");
        }
        if (producto.getStock() < cantidad) {
            throw new ValidacionException("Stock insuficiente. Stock actual: " + producto.getStock());
        }

        pedido.addDetallePedido(cantidad, producto);
        producto.setStock(producto.getStock() - cantidad);
        
        Utilitarios.mostrarMensaje("Producto agregado. Total: $" + pedido.getTotal()); // era getId()
    }

    public void cambiarEstadoPedido(Long idPedido, Estado nuevoEstado) {
        Pedido pedido = buscarPorId(idPedido);
        if (pedido == null) {
            throw new ValidacionException("El pedido no existe");
        }
        pedido.setEstado(nuevoEstado);
        Utilitarios.mostrarMensaje("Estado cambiado a: " + nuevoEstado);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            Utilitarios.mostrarMensaje("No hay pedidos cargados");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println("---------------------------------");
            System.out.println("ID: " + p.getId() + " | Usuario: " + p.getUsuario().getNombre());
            System.out.println("Fecha: " + p.getFecha() + " | Estado: " + p.getEstado());
            System.out.println("Total: $" + p.getTotal());
            System.out.println("Detalles:");
            for (DetallePedido d : p.getDetalles()) {
                System.out.println("  - " + d.getProducto().getNombre() + " x" + d.getCantidad() + " = $" + d.calcularSubtotal());
            }
        }
    }

    public Pedido buscarPorId(Long id) {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}