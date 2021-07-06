package sesion2;

public class ProductoCarrito {
    private int cantidad;
    private String nombre;
    private int precio_unitario;
    private int precio;
    private String imagen;

    public ProductoCarrito() {
        cantidad = 0;
        nombre = "";
        precio_unitario = 0;
        precio = 0;
        imagen = "";
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioUnitario() {
        return precio_unitario;
    }

    public void setPrecioUnitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "ProductoCarrito{" + "cantidad=" + cantidad + ", nombre=" + nombre + ", precio_unitario=" + precio_unitario + ", precio=" + precio + ", imagen=" + imagen + '}';
    }
    
    
}
