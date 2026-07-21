package integrado.prog2.service;

import integrado.prog2.entities.*;
import integrado.prog2.exception.ValidacionException;
import integrado.prog2.config.Utilitarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductoService {
    private List<Producto> productos;
    private Scanner sc;
    private CategoriaService categoriaService;

    public ProductoService(Scanner sc, CategoriaService categoriaService) {
        this.productos = new ArrayList<>();
        this.sc = sc;
        this.categoriaService = categoriaService;
    }
    
    public void menu(){
        int op; do{
            Utilitarios.titulo("MENU PRODUCTOS");
            System.out.println("1. Listar 2. Crear 3. Editar 4. Eliminar 0. Volver");
            op = Utilitarios.leerInt(sc, "Seleccione: ");
            try{
                switch(op){
                    case 1 -> listarConsola();
                    case 2 -> crearConsola();
                    case 3 -> editarConsola();
                    case 4 -> eliminarConsola();
                }
            }catch(ValidacionException e){ System.out.println("❌ "+e.getMessage()); }
        }while(op!=0);
    }
    
    public void listarConsola(){
        List<Producto> lista = listar();
        if(lista.isEmpty()){ Utilitarios.mostrarMensaje("No hay productos"); return; }
        System.out.println("\n--- LISTA PRODUCTOS ---");
        for(Producto p : lista) System.out.println(p);
    }

    private void crearConsola(){
    categoriaService.listarConsola();
    String nombre = Utilitarios.leerString(sc, "Nombre: ");
    
    double precio;
    do {
        precio = Utilitarios.leerDouble(sc, "Precio: ");
        if(precio <= 0) Utilitarios.mostrarMensaje("Error: El precio debe ser mayor a 0");
    } while(precio <= 0);
    
    int stock;
    do {
        stock = Utilitarios.leerInt(sc, "Stock: ");
        if(stock < 0) Utilitarios.mostrarMensaje("Error: El stock no puede ser negativo");
    } while(stock < 0);
    
    String desc = Utilitarios.leerString(sc, "Descripcion: ");
    String img = Utilitarios.leerString(sc, "Imagen URL: ");
    
    Long idCat;
    Categoria cat;
    do {
        idCat = (long) Utilitarios.leerInt(sc, "ID Categoria: ");
        cat = categoriaService.buscarPorId(idCat);
        if(cat == null) Utilitarios.mostrarMensaje("Error: La categoria no existe");
    } while(cat == null);
    
    agregar(nombre, precio, desc, stock, img, idCat);
}
    
    private void editarConsola(){
    listarConsola();
    Long id = (long)Utilitarios.leerInt(sc, "ID a editar: ");
    Producto p = buscarPorId(id);
    if(p == null) {
        Utilitarios.mostrarMensaje("Producto no encontrado");
        return;
    }
    
    String nombre = Utilitarios.leerString(sc, "Nuevo Nombre: ");
    
    double precio;
    do {
        precio = Utilitarios.leerDouble(sc, "Nuevo Precio: ");
        if(precio <= 0) Utilitarios.mostrarMensaje("Error: El precio debe ser mayor a 0");
    } while(precio <= 0);
    
    String desc = Utilitarios.leerString(sc, "Nueva Descripcion: ");
    
    int stock;
    do {
        stock = Utilitarios.leerInt(sc, "Nuevo Stock: ");
        if(stock < 0) Utilitarios.mostrarMensaje("Error: El stock no puede ser negativo");
    } while(stock < 0);
    
    String img = Utilitarios.leerString(sc, "Nueva Imagen URL: ");
    editar(id, nombre, precio, desc, stock, img);
}
    
    private void eliminarConsola(){
        listarConsola();
        Long id = (long)Utilitarios.leerInt(sc, "ID a eliminar: ");
        eliminar(id);
    }

    public void agregar(String nombre, double precio, String descripcion, int stock, String imagen, Long idCategoria) {
        if (precio < 0 || stock < 0) throw new ValidacionException("Precio y stock no pueden ser negativos.");
        Categoria cat = categoriaService.buscarPorId(idCategoria);
        if(cat == null) throw new ValidacionException("La categoria no existe");
        Producto producto = new Producto(nombre, precio, descripcion, stock, imagen, cat);
        productos.add(producto);
        Utilitarios.mostrarMensaje("Producto creado con ID: " + producto.getId());
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        for (Producto p : productos) if (!p.isEliminado()) lista.add(p);
        return lista;
    }

    public Producto buscarPorId(Long id) {
        for (Producto p : productos) if (p.getId().equals(id) && !p.isEliminado()) return p;
        return null;
    }

    public void editar(Long id, String nombre, double precio, String descripcion, int stock, String imagen) {
        Producto producto = buscarPorId(id);
        if(producto == null) throw new ValidacionException("Producto no encontrado");
        if (precio < 0 || stock < 0) throw new ValidacionException("Precio y stock no pueden ser negativos.");
        producto.setNombre(nombre); producto.setPrecio(precio); producto.setDescripcion(descripcion);
        producto.setStock(stock); producto.setImagen(imagen);
        Utilitarios.mostrarMensaje("Producto editado");
    }

    public void eliminar(Long id) {
        Producto producto = buscarPorId(id);
        if(producto == null) throw new ValidacionException("Producto no encontrado");
        producto.setEliminado(true);
        Utilitarios.mostrarMensaje("Producto eliminado");
    }
}