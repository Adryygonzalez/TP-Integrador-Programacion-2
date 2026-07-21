package integrado.prog2.service;

import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;
import integrado.prog2.exception.ValidacionException;
import integrado.prog2.config.Utilitarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {
    private List<Usuario> usuarios;
    private Scanner sc;

    public UsuarioService(Scanner sc) {
        this.usuarios = new ArrayList<>();
        this.sc=sc;
    }
    
    public void menu(){
        int op; do{
            Utilitarios.titulo("MENU USUARIOS");
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
        List<Usuario> lista = listar();
        if(lista.isEmpty()){ Utilitarios.mostrarMensaje("No hay usuarios"); return; }
        System.out.println("\n--- LISTA USUARIOS ---");
        for(Usuario u : lista) System.out.println(u);
    }

    private void crearConsola(){
        String nombre = Utilitarios.leerString(sc, "Nombre: ");
        String apellido = Utilitarios.leerString(sc, "Apellido: ");
        String mail = Utilitarios.leerString(sc, "Email: ");
        String celular = Utilitarios.leerString(sc, "Celular: ");
        String pass = Utilitarios.leerString(sc, "Password: ");
        agregar(nombre, apellido, mail, celular, pass, Rol.USUARIO);
    }
    
    private void editarConsola(){
        listarConsola();
        Long id = (long)Utilitarios.leerInt(sc, "ID a editar: ");
        String nombre = Utilitarios.leerString(sc, "Nuevo Nombre: ");
        String apellido = Utilitarios.leerString(sc, "Nuevo Apellido: ");
        String mail = Utilitarios.leerString(sc, "Nuevo Email: ");
        String celular = Utilitarios.leerString(sc, "Nuevo Celular: ");
        String pass = Utilitarios.leerString(sc, "Nueva Password: ");
        editar(id, nombre, apellido, mail, celular, pass);
    }
    
    private void eliminarConsola(){
        listarConsola();
        Long id = (long)Utilitarios.leerInt(sc, "ID a eliminar: ");
        eliminar(id);
    }

    public void agregar(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        for (Usuario u : usuarios) {
            if (!u.isEliminado() && u.getMail().equalsIgnoreCase(mail)) {
                throw new ValidacionException("El mail ya está registrado."); 
            }
        }
        Usuario usuario = new Usuario(nombre, apellido, mail, celular, contrasenia, rol);
        usuarios.add(usuario);
        Utilitarios.mostrarMensaje("Usuario creado con ID: " + usuario.getId());
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        for (Usuario u : usuarios) if (!u.isEliminado()) lista.add(u);
        return lista;
    }

    public Usuario buscarPorId(Long id) {
        for (Usuario u : usuarios) if (u.getId().equals(id) && !u.isEliminado()) return u;
        return null;
    }

    public void editar(Long id, String nombre, String apellido, String mail, String celular, String contrasenia) {
        Usuario usuario = buscarPorId(id);
        if(usuario == null) throw new ValidacionException("Usuario no encontrado");
        usuario.setNombre(nombre); usuario.setApellido(apellido); usuario.setMail(mail);
        usuario.setCelular(celular); usuario.setContrasenia(contrasenia);
        Utilitarios.mostrarMensaje("Usuario editado");
    }

    public void eliminar(Long id) {
        Usuario usuario = buscarPorId(id);
        if(usuario == null) throw new ValidacionException("Usuario no encontrado");
        usuario.setEliminado(true);
        Utilitarios.mostrarMensaje("Usuario eliminado");
    }
}