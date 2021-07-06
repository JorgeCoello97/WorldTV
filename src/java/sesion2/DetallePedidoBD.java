package sesion2;

public class DetallePedidoBD  {
    private int codigo_pedido;
    private int codigo_producto;
    private int unidades;
    private int precio_unitario;

    public DetallePedidoBD() {
        codigo_pedido = 0;
        codigo_producto = 0;
        unidades = 0;
        precio_unitario = 0;
    }

    public int getCodigoPedido() {
        return codigo_pedido;
    }

    public void setCodigoPedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public int getCodigoProducto() {
        return codigo_producto;
    }

    public void setCodigoProducto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public int getCantidad() {
        return unidades;
    }

    public void setCantidad(int unidades) {
        this.unidades = unidades;
    }

    public int getPrecioUnitario() {
        return precio_unitario;
    }

    public void setPrecioUnitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
