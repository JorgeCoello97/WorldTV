package sesion2;

import java.sql.Savepoint;
import java.util.List;

public class ResponseBD {
    private boolean success;
    private List<ModeloGeneralBD> valorlista;
    private Object valor;
    private Savepoint savepoint;
    
    public ResponseBD(boolean success) {
        this.success = success;
    }
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ModeloGeneralBD> getValorlista() {
        return valorlista;
    }

    public void setValorlista(List<ModeloGeneralBD> valorlista) {
        this.valorlista = valorlista;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public void setSavepoint(Savepoint savepoint) {
        this.savepoint = savepoint;
    }

    public Savepoint getSavepoint() {
        return savepoint;
    }
    
}
