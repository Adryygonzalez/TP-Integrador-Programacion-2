
package integrado.prog2.entities;

public class DetallePedido extends Base {
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    public DetallePedido(int cantidad, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.producto = producto;
    }
    public Double calcularSubtotal(){
    this.subtotal=cantidad*producto.getPrecio();
        return this.subtotal;
            }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {double precio_subtotal = calcularSubtotal();
    return String.format("Detalle: ID: %d %s x %d => Subtotal: $%.2f",
            getId(),producto != null? producto.getNombre():"sin producto", cantidad, precio_subtotal);
    }
}
     
