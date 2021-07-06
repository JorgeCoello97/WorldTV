package sesion2;

public class UsuarioBD extends ModeloGeneralBD{
    private int activo;
    private String usuario;
    private String clave;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String poblacion;
    private String provincia;
    private String cod_postal;
    private String telefono;
    private String correo;

    public UsuarioBD() {
        super(0);
    }
    public UsuarioBD(int codigo) {
        super(codigo);
    }
    
    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if(usuario == null)
            this.usuario = "";
        else
            this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        if(clave == null)
            this.clave = "";
        else
            this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null)
            this.nombre = "";
        else
            this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        if(apellidos == null)
            this.apellidos = "";
        else
            this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        if(domicilio == null)
            this.domicilio = "";
        else
            this.domicilio = domicilio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        if(poblacion == null)
            this.poblacion = "";
        else
            this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        if(provincia == null)
            this.provincia = "";
        else
            this.provincia = provincia;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        if(cod_postal == null)
            this.cod_postal = "";
        else
            this.cod_postal = cod_postal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono == null)
            this.telefono = "";
        else
            this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(correo == null)
            this.correo = "";
        else
            this.correo = correo;
    }
}