package sesion2;

import java.sql.Date;

public class PedidoBD extends ModeloGeneralBD{
    public static final int ESTADO_PENDIENTE = 0;
    public static final int ESTADO_ENVIADO = 1;
    public static final int ESTADO_ENTREGADO = 2;
    public static final int ESTADO_CANCELADO = 3;
    
    private int cod_usuario;
    private Date fecha;
    private int importe;
    private String estado;

    public PedidoBD() {
        super(0);
    }
   
    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        if(estado == ESTADO_PENDIENTE)
            this.estado = "Pendiente";
        else if(estado == ESTADO_ENVIADO)
            this.estado = "Enviado";
        else if(estado == ESTADO_ENTREGADO)
            this.estado = "Entregado";
        else if(estado == ESTADO_CANCELADO)
            this.estado = "Cancelado";
    }
}
