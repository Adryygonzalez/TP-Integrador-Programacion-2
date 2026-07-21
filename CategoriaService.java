package integrado.prog2.service;
import integrado.prog2.exception.ValidacionException;
import integrado.prog2.entities.Categoria;
import integrado.prog2.config.Utilitarios;
import java.util.*;

public class CategoriaService {
    private ArrayList<Categoria> lista = new ArrayList<>();
    private Scanner sc;
    public CategoriaService(Scanner sc){ this.sc = sc; }

    public void menu(){
        int op; do{
            Utilitarios.titulo("MENU CATEGORIAS");
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
        System.out.println("\n--- LISTA CATEGORIAS ---");
        boolean hay = false;
        for(Categoria c : lista) if(!c.isEliminado()){ System.out.println(c); hay=true; }
        if(!hay) System.out.println("No hay categorias cargadas");
    }

    private void crearConsola(){
        String nombre = Utilitarios.leerString(sc, "Nombre: ");
        if(existeNombre(nombre)) throw new ValidacionException("Ya existe una categoria con ese nombre");
        String descripcion = Utilitarios.leerString(sc, "Descripcion: ");
        Categoria c = new Categoria(nombre, descripcion);
        lista.add(c);
        Utilitarios.mostrarMensaje("Categoria creada con ID: " + c.getId());
    }

    private void editarConsola(){
        listarConsola();
        Long id = (long)Utilitarios.leerInt(sc, "ID a editar: ");
        Categoria c = buscarPorId(id);
        if(c == null) throw new ValidacionException("ID no encontrado");
        c.setNombre(Utilitarios.leerString(sc, "Nuevo nombre: "));
        c.setDescripcion(Utilitarios.leerString(sc, "Nueva descripcion: "));
        Utilitarios.mostrarMensaje("Categoria actualizada");
    }

    private void eliminarConsola(){
        listarConsola();
        Long id = (long)Utilitarios.leerInt(sc, "ID a eliminar: ");
        Categoria c = buscarPorId(id);
        if(c == null) throw new ValidacionException("ID no encontrado");
        c.setEliminado(true); // SOFT DELETE
        Utilitarios.mostrarMensaje("Eliminado logicamente");
    }

    public Categoria buscarPorId(Long id){
        for(Categoria c : lista) if(c.getId().equals(id) && !c.isEliminado()) return c;
        return null;
    }
    private boolean existeNombre(String nombre){
        for(Categoria c : lista) if(c.getNombre().equalsIgnoreCase(nombre) &&!c.isEliminado()) return true;
        return false;
    }
}