
package integrado.prog2.entities;

public class Producto extends Base{
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private boolean disponible;
    private Categoria categoria;
    

    public Producto(String nombre, double precio, String descripcion, int stock, String imagen, Categoria categoria) {
        super();
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        setStock (stock);
        this.imagen = imagen;
        this.categoria = categoria;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        this.disponible = (stock > 0);
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
public String toString() {
    String nombreCategoria = (categoria != null) ? categoria.getNombre() : "Sin categoria";
    
    return String.format("Producto: | ID:%d | Nombre:%s | Precio:$%.2f | Descripcion: %s | Stock:%d | Imagen: %s | Categoria: %s | Disponible:%s",
            getId(), nombre, precio, descripcion, stock, imagen, nombreCategoria, disponible ? "SI" : "NO");
}
}
