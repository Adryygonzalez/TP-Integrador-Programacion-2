
package integrado.prog2.entities;

import integrado.prog2.enums.Estado;
import integrado.prog2.enums.FormaPago;
import integrado.prog2.interfaces.Calculable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable{
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formapago;
    private List<DetallePedido> detalles;
    private Usuario usuario;
    

    public Pedido(Long id,LocalDate fecha,Estado estado,FormaPago formapago, Usuario usuario) {
        super();
        this.fecha=fecha;
        this.estado=estado;
        this.formapago = formapago;
        this.usuario = usuario;
        this.detalles=new ArrayList<>();
        this.total = 0.0;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public FormaPago getFormapago() {
        return formapago;
    }

    public void setFormapago(FormaPago formapago) {
        this.formapago = formapago;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    @Override
    public void calcularTotal(){
    this.total=0.0;
    for(DetallePedido d: detalles){
        this.total+= d.calcularSubtotal();
    }
    
   }
    
    public void addDetallePedido(int cantidad,Producto producto){
        DetallePedido detalle= new DetallePedido(cantidad,producto);
            this.detalles.add(detalle);
            calcularTotal();
    }
    
    public DetallePedido findDetallePedidoByProducto(Producto producto){
    for(DetallePedido d:this.detalles){
        if (d.getProducto().getId().equals(producto.getId())){
            return d;
        }
    }
      return null;  
    }
    public void deleteDetallePedidoByProducto(Producto producto){
    DetallePedido detalle=findDetallePedidoByProducto(producto);
        if(detalle !=null){
            this.detalles.remove(detalle);
            calcularTotal();
        }
    }
    @Override
    public String toString() {
        return String.format("Pedido: ID: %d | Fecha:%s Estado: %s Forma de pago: %s"
                ,getId(),fecha,estado,formapago);
    }
    
}
