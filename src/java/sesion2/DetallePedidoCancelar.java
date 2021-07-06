package sesion2;

public class DetallePedidoCancelar extends ModeloGeneralBD{
    String nombre_producto;
    int unidades;
    int precio;

    public DetallePedidoCancelar(String nombre_producto, int unidades, int precio) {
        super(0);
        this.nombre_producto = nombre_producto;
        this.unidades = unidades;
        this.precio = precio;
    }

    public DetallePedidoCancelar() {
        super(0);
        this.nombre_producto = "";
        this.unidades = 0;
        this.precio = 0;
    }
    
    public String getNombreProducto() {
        return nombre_producto;
    }

    public void setNombreProducto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}
