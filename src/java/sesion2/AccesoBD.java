package sesion2;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class AccesoBD {
    private static AccesoBD instanciaUnica = null;
    private Connection conexionBD = null;
    
    public static AccesoBD getInstance(){
        if(instanciaUnica == null){
            instanciaUnica = new AccesoBD();
        }
        return instanciaUnica;
    }
    
    public AccesoBD() {
        abrirConexionBD();
    }
    
    public void abrirConexionBD(){
        if (conexionBD == null) {
            String nombreConexionBD = "jdbc:mysql://localhost/world_tv";
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexionBD = DriverManager.getConnection(nombreConexionBD, "root", "");
            }catch(Exception e){
                System.out.println("No se ha podido conectar la BBDD");
                System.out.println(e.getMessage());
            }
        }
    }
    public boolean comprobarAcceso(){
        return conexionBD != null;
    }
    
    public ResponseBD obtenerProductosBD(){
        ResponseBD responseBD;
        if(conexionBD != null){
            try {
                ArrayList<ModeloGeneralBD> productos = new ArrayList<>();
                String sql = "SELECT * FROM productos WHERE existencias>0";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                ResultSet resultados = preparedStatement.executeQuery();

                while(resultados.next()){
                    ProductoBD producto = new ProductoBD();
                    producto.setCodigo(resultados.getInt("codigo"));
                    producto.setNombre(resultados.getString("nombre"));
                    producto.setPrecio(resultados.getInt("precio"));
                    producto.setExistencia(resultados.getInt("existencias"));
                    producto.setImagen(resultados.getString("imagen"));
                    productos.add(producto);
                }
                responseBD = new ResponseBD(true);
                responseBD.setValorlista(productos);
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener información de los productos."));
            }
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD; 
    }
    
    public ResponseBD obtenerUsuariosBD(){
        ResponseBD responseBD;
        if(conexionBD != null){
            try {
                ArrayList<ModeloGeneralBD> usuarios = new ArrayList<>();
                String sql = "SELECT * FROM usuarios";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                ResultSet resultados = preparedStatement.executeQuery();

                while(resultados.next()){
                    UsuarioBD usuarioBD = new UsuarioBD();
                    usuarioBD.setCodigo(resultados.getInt("codigo"));
                    usuarioBD.setActivo(resultados.getInt("activo"));
                    usuarioBD.setUsuario(resultados.getString("usuario"));
                    usuarioBD.setClave(resultados.getString("clave"));
                    usuarioBD.setNombre(resultados.getString("nombre"));
                    usuarioBD.setApellidos(resultados.getString("apellidos"));
                    usuarioBD.setDomicilio(resultados.getString("domicilio"));
                    usuarioBD.setPoblacion(resultados.getString("poblacion"));
                    usuarioBD.setProvincia(resultados.getString("provincia"));
                    usuarioBD.setCod_postal(resultados.getString("cp"));
                    usuarioBD.setTelefono(resultados.getString("telefono"));
                    usuarioBD.setCorreo(resultados.getString("correo"));
                    usuarios.add(usuarioBD);
                }
                responseBD = new ResponseBD(true);
                responseBD.setValorlista(usuarios);
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener información de los usuarios."));
            }
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerPedidosBD(){
        ResponseBD responseBD;
        
        if (conexionBD != null) {
            try {
                ArrayList<ModeloGeneralBD> pedidos = new ArrayList<>();
                String sql =  "SELECT * FROM pedidos";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                ResultSet resultados = preparedStatement.executeQuery();

                while(resultados.next()){
                    PedidoBD pedido = new PedidoBD();
                    pedido.setCodigo(resultados.getInt("codigo"));
                    pedido.setCod_usuario(resultados.getInt("cod_usuario"));
                    pedido.setFecha(resultados.getDate("fecha"));
                    pedido.setImporte(resultados.getInt("importe"));
                    pedido.setEstado(resultados.getInt("estado"));

                    pedidos.add(pedido);
                }

                responseBD = new ResponseBD(true);
                responseBD.setValorlista(pedidos);
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener información de los pedidos."));
            }    
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD; 
    }
    
    public ResponseBD comprobarUsuarioBD(String usuario, String clave)
    {
        ResponseBD responseBD = null;
        if(conexionBD != null){
            try{
                String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, clave);
                ResultSet resultados = preparedStatement.executeQuery();
                resultados.next();
                int activo = resultados.getInt("activo");
                int admin = resultados.getInt("admin");
                
                if((activo == 1) && (admin == 0))
                {
                    responseBD = new ResponseBD(true);
                }
                else if(activo == 0)
                {
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha podido iniciar sesión. El usuario introducido está dado de baja."));
                }
                else if(admin == 1) {
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha podido iniciar sesión. El usuario introducido es un administrador."));
                }
            }
            catch(Exception e){
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("El usuario y/o contraseña es incorrecto."));
            }
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerDatosUsuario(String usuario){
        ResponseBD responseBD;
        if( conexionBD != null){
            try {
                String sql = "SELECT * FROM usuarios WHERE usuario = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, usuario);
                ResultSet resultados = preparedStatement.executeQuery();
                
                if(resultados.next()){
                    UsuarioBD usuarioBD = new UsuarioBD();
                    usuarioBD.setCodigo(resultados.getInt("codigo"));
                    usuarioBD.setActivo(resultados.getInt("activo"));
                    usuarioBD.setUsuario(resultados.getString("usuario"));
                    usuarioBD.setClave(resultados.getString("clave"));
                    usuarioBD.setNombre(resultados.getString("nombre"));
                    usuarioBD.setApellidos(resultados.getString("apellidos"));
                    usuarioBD.setDomicilio(resultados.getString("domicilio"));
                    usuarioBD.setPoblacion(resultados.getString("poblacion"));
                    usuarioBD.setProvincia(resultados.getString("provincia"));
                    usuarioBD.setCod_postal(resultados.getString("cp"));
                    usuarioBD.setTelefono(resultados.getString("telefono"));
                    usuarioBD.setCorreo(resultados.getString("correo"));
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(usuarioBD);
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se encuentra al usuario en la BBDD."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener los datos del usuario."));
            }    
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerDatosUsuarioMedianteCodigo(int codigo){
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "SELECT * FROM usuarios WHERE codigo = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setInt(1, codigo);
                ResultSet resultados = preparedStatement.executeQuery();
                
                if(resultados.next()){
                    UsuarioBD usuarioBD = new UsuarioBD();
                    usuarioBD.setCodigo(resultados.getInt("codigo"));
                    usuarioBD.setActivo(resultados.getInt("activo"));
                    usuarioBD.setUsuario(resultados.getString("usuario"));
                    usuarioBD.setClave(resultados.getString("clave"));
                    usuarioBD.setNombre(resultados.getString("nombre"));
                    usuarioBD.setApellidos(resultados.getString("apellidos"));
                    usuarioBD.setDomicilio(resultados.getString("domicilio"));
                    usuarioBD.setPoblacion(resultados.getString("poblacion"));
                    usuarioBD.setProvincia(resultados.getString("provincia"));
                    usuarioBD.setCod_postal(resultados.getString("cp"));
                    usuarioBD.setTelefono(resultados.getString("telefono"));
                    usuarioBD.setCorreo(resultados.getString("correo"));
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(usuarioBD);
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se encuentra al usuario en la BBDD."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener los datos del usuario."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD modificarCorreo_ClaveUsuario(String usuario, String correo, String clave) {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "UPDATE usuarios SET correo = ?, clave = ? WHERE usuario = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, correo);
                preparedStatement.setString(2, clave);
                preparedStatement.setString(3, usuario);
                
                int tuplas_afectadas = preparedStatement.executeUpdate();
                
                responseBD = new ResponseBD(true);
                responseBD.setValor(new Integer(tuplas_afectadas));
                
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido modificar los datos del usuario."));
            }
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD comprobarDisponibilidadUsuario(String usuario)
    {
        ResponseBD responseBD;
        if (conexionBD !=  null) {
            try{
                String sql = "SELECT * FROM usuarios WHERE usuario = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, usuario);
                ResultSet resultados = preparedStatement.executeQuery();

                if (resultados.next())
                {
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("Nombre de usuario no disponible."));
                }
                else
                {
                    responseBD = new ResponseBD(true);
                }
            }
            catch(Exception e){
               responseBD = new ResponseBD(false);
               responseBD.setValor(new String("No se ha podido comprobar la disponibilidad del usuario."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD insertarUsuario(String nombre, String apellidos, String usuario, String domicilio, String provincia, String poblacion, String codigo_postal, String telefono, String correo, String clave){
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "INSERT INTO usuarios (activo,usuario,clave,nombre,apellidos,domicilio,poblacion,provincia,cp,telefono,correo,admin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, usuario);
                preparedStatement.setString(3, clave);
                preparedStatement.setString(4, nombre);
                preparedStatement.setString(5, apellidos);
                preparedStatement.setString(6, domicilio);
                preparedStatement.setString(7, poblacion);
                preparedStatement.setString(8, provincia);
                preparedStatement.setString(9, codigo_postal);
                preparedStatement.setString(10, telefono);
                preparedStatement.setString(11, correo);
                preparedStatement.setInt(12, 0);
                
                int tuplas_afectadas = preparedStatement.executeUpdate();
                
                responseBD = new ResponseBD(true);
                responseBD.setValor(new Integer(tuplas_afectadas));
            }
            catch(SQLException e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido insertar el usuario."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD modificarDatosPersonales(UsuarioBD usuarioBD){
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?,"
                        + " domicilio = ?, poblacion = ?, provincia = ?,"
                        + " cp = ?, telefono = ? WHERE usuario = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, usuarioBD.getNombre());
                preparedStatement.setString(2, usuarioBD.getApellidos());
                preparedStatement.setString(3, usuarioBD.getDomicilio());
                preparedStatement.setString(4, usuarioBD.getPoblacion());
                preparedStatement.setString(5, usuarioBD.getProvincia());
                preparedStatement.setString(6, usuarioBD.getCod_postal());
                preparedStatement.setString(7, usuarioBD.getTelefono());
                preparedStatement.setString(8, usuarioBD.getUsuario());
                
                int tuplas_afectadas = preparedStatement.executeUpdate();
                
                responseBD = new ResponseBD(true);
                responseBD.setValor(new Integer(tuplas_afectadas));
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido modificar los datos personales del usuario."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerPedidosUsuario(String usuario) {
        ResponseBD responseBD;
        if (conexionBD != null){
            try {
                String sql_1 = "SELECT codigo FROM usuarios WHERE usuario = ?";
                PreparedStatement preparedStatement_1 = conexionBD.prepareStatement(sql_1);
                preparedStatement_1.setString(1, usuario);
                ResultSet resultados1 = preparedStatement_1.executeQuery();
                
                if(resultados1.next()){
                    int cod_usuario = resultados1.getInt("codigo");
                    ArrayList<ModeloGeneralBD> pedidos = new ArrayList<>();
                    
                    String sql_2 = "SELECT * FROM pedidos WHERE cod_usuario = ?";
                    PreparedStatement preparedStatement_2 = conexionBD.prepareStatement(sql_2);
                    preparedStatement_2.setInt(1, cod_usuario);
                    ResultSet resultados2 = preparedStatement_2.executeQuery();

                    while(resultados2.next()){
                        PedidoBD pedido = new PedidoBD();
                        pedido.setCodigo(resultados2.getInt("codigo"));
                        pedido.setCod_usuario(resultados2.getInt("cod_usuario"));
                        pedido.setFecha(resultados2.getDate("fecha"));
                        pedido.setImporte(resultados2.getInt("importe"));
                        pedido.setEstado(resultados2.getInt("estado"));

                        pedidos.add(pedido);
                    }
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValorlista(pedidos);
                }
                else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha encontrado el usuario."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener los pedidos del usuario."));
            }
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD comprobarExistenciasProducto(String nombre_producto, int cantidad_producto) {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                
                String sql = "SELECT existencias FROM productos WHERE nombre = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, nombre_producto);
                ResultSet resultados = preparedStatement.executeQuery();

                if(resultados.next()) {
                    int existencias = resultados.getInt("existencias");
                    
                    if(existencias >= cantidad_producto) {
                        responseBD = new ResponseBD(true);
                    }else{
                        responseBD = new ResponseBD(false);
                    }
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha encontrado el producto."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido comprobar la disponibilidad del producto."));
            }
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerExistenciasProducto(String nombre_producto) {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "SELECT existencias FROM productos WHERE nombre = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, nombre_producto);
                ResultSet resultados = preparedStatement.executeQuery();
                
                int existencias = 0;
                if(resultados.next()) {
                    existencias = resultados.getInt("existencias");
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(new Integer(existencias));
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha encontrado el producto."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener las existencias del producto."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerExistenciasProductoMedianteCodigo(int cod_producto) {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "SELECT existencias FROM productos WHERE codigo = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setInt(1, cod_producto);
                ResultSet resultados = preparedStatement.executeQuery();
                
                int existencias = 0;
                if(resultados.next()) {
                    existencias = resultados.getInt("existencias");
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(new Integer(existencias));
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha encontrado el producto."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener las existencias del producto."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD comprobarAdministradorBD(String usuario, String clave)
    {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try{
                String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ? AND admin = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, clave);
                preparedStatement.setInt(3, 1);
                ResultSet resultados = preparedStatement.executeQuery();
                
                if (resultados.next())
                {
                    responseBD = new ResponseBD(true);
                }
                else 
                {
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha encontrado el administrador."));
                }
            }
            catch(Exception e){
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido comprobar el usuario."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerDetallePedido(int codigo_pedido){
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "SELECT * FROM detalles WHERE codigo_pedido = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setInt(1, codigo_pedido);
                ResultSet resultados = preparedStatement.executeQuery();
                
                if(resultados.next()){
                    DetallePedidoBD detallePedidoBD = new DetallePedidoBD();
                    detallePedidoBD.setCantidad(resultados.getInt("unidades"));
                    detallePedidoBD.setCodigoPedido(resultados.getInt("codigo_pedido"));
                    detallePedidoBD.setCodigoProducto(resultados.getInt("codigo_producto"));
                    detallePedidoBD.setPrecioUnitario(resultados.getInt("precio_unitario"));
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(detallePedidoBD);
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha encontrado los detalles del pedido."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido obtener los detalles del pedido."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD insertarPedido(String nombre_usuario, int importe_pedido){
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                conexionBD.setAutoCommit(false);
                Savepoint savepoint = conexionBD.setSavepoint("insertar_pedido");
                String sql_1 = "SELECT codigo FROM usuarios WHERE usuario = ?";
                PreparedStatement preparedStatement_1 = conexionBD.prepareStatement(sql_1);
                preparedStatement_1.setString(1, nombre_usuario);
                ResultSet resultados_1 = preparedStatement_1.executeQuery();
                
                if(resultados_1.next()){
                    int cod_usuario = resultados_1.getInt("codigo");
                    
                    Date fecha_actual = new Date();
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                    String hoy = formatoFecha.format(fecha_actual);
                    
                    String sql_2 = "INSERT INTO pedidos (cod_usuario,fecha,importe,estado) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement_2 = conexionBD.prepareStatement(sql_2);
                    preparedStatement_2.setInt(1, cod_usuario);
                    preparedStatement_2.setString(2, hoy);
                    preparedStatement_2.setInt(3, importe_pedido);
                    preparedStatement_2.setInt(4, PedidoBD.ESTADO_PENDIENTE);
                    
                    int tuplas_afectadas = preparedStatement_2.executeUpdate();
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(new Integer(tuplas_afectadas));
                    responseBD.setSavepoint(savepoint);
                    
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha encontrado el usuario del pedido."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha insertado el pedido."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD insertarDetalles(Savepoint savepoint, List <ProductoCarrito> carrito) {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                ResponseBD response_ultimo_pedido = devolverCodigoUltimoPedido();
                if (response_ultimo_pedido.isSuccess()) {
                    int cod_ultimoPedido = (Integer) response_ultimo_pedido.getValor();
                    for(int i = 0; i < carrito.size(); i++) {
                        ResponseBD response_codigo_producto = devolverCodigoProductoSegunNombre(carrito.get(i).getNombre());
                        if(response_codigo_producto.isSuccess()){
                            int cod_producto = (Integer)response_codigo_producto.getValor();                        
                            int cant_producto = carrito.get(i).getCantidad();
                            int precio_unitario = carrito.get(i).getPrecioUnitario();
                            String sql = "INSERT INTO detalles (codigo_pedido,codigo_producto,unidades,precio_unitario) VALUES (?, ?, ?, ?)";
                            PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                            preparedStatement.setInt(1, cod_ultimoPedido);
                            preparedStatement.setInt(2, cod_producto);
                            preparedStatement.setInt(3, cant_producto);
                            preparedStatement.setInt(4, precio_unitario);

                            preparedStatement.executeUpdate();
                            conexionBD.setAutoCommit(true);                            
                        }else{
                            throw new Exception("El problema esta en obtener devolverCodigoProductoSegunNombre");
                        }                        
                    }
                    responseBD = new ResponseBD(true);
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha insertado los detalles del pedido."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se ha podido insertado los detalles del pedido."));
                System.out.println(e.getMessage());
                try {
                    conexionBD.rollback(savepoint);
                    conexionBD.setAutoCommit(true);
                } catch (SQLException o) {
                    System.out.println(o.getMessage());
                }
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD devolverCodigoUltimoPedido() {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "SELECT MAX(codigo) FROM pedidos";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                
                ResultSet resultados1 = preparedStatement.executeQuery();
                if(resultados1.next()){
                    int cod_ultimoPedido = resultados1.getInt("MAX(codigo)");
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(new Integer(cod_ultimoPedido));    
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha devuelto el codigo del pedido."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se podido devolver el codigo del pedido."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD devolverCodigoProductoSegunNombre(String nombre_producto) {
        ResponseBD responseBD;
        if (conexionBD != null) {
             try {
                String sql = "SELECT codigo FROM productos WHERE nombre = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setString(1, nombre_producto);
                ResultSet resultados = preparedStatement.executeQuery();

                if (resultados.next()) {
                    int cod_producto = resultados.getInt("codigo");
                    
                    responseBD = new ResponseBD(true);
                    responseBD.setValor(new Integer(cod_producto));
                }else{
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se ha devuelto el codigo del producto."));
                }
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se podido devolver el codigo del producto."));
            }
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD cancelarPedido(int cod_usuario, int codigo_pedido) {
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "UPDATE pedidos SET estado = ? WHERE cod_usuario = ? AND codigo = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setInt(1, PedidoBD.ESTADO_CANCELADO);
                preparedStatement.setInt(2, cod_usuario);
                preparedStatement.setInt(3, codigo_pedido);
                
                int tuplas_afectadas = preparedStatement.executeUpdate();
                
                responseBD = new ResponseBD(true);
                responseBD.setValor(new Integer(tuplas_afectadas));
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se podido cancelar el pedido."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD obtenerDetallePedidoCancelar(int cod_pedido){
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql_1 = "SELECT * FROM detalles WHERE codigo_pedido = ?";
                PreparedStatement preparedStatement_1 = conexionBD.prepareStatement(sql_1);
                preparedStatement_1.setInt(1, cod_pedido);
                ResultSet resultados1 = preparedStatement_1.executeQuery();
                
                ArrayList<ModeloGeneralBD> detallesPedido = new ArrayList<>();
                DetallePedidoCancelar detallePedido;
                while(resultados1.next()){
                    
                    int cod_producto = resultados1.getInt("codigo_producto");
                    int unidades = resultados1.getInt("unidades");
                    int precio_unitario = resultados1.getInt("precio_unitario");
                    int precio = unidades * precio_unitario;
                    
                    String sql_2 = "SELECT * FROM productos WHERE codigo = ?";
                    PreparedStatement preparedStatement_2 = conexionBD.prepareStatement(sql_2);
                    preparedStatement_2.setInt(1, cod_producto);
                    ResultSet resultados2 = preparedStatement_2.executeQuery();
                    
                    if(resultados2.next()){
                        String nombre_producto = resultados2.getString("nombre");
                        
                        detallePedido = new DetallePedidoCancelar();
                        detallePedido.setCodigo(cod_producto);
                        detallePedido.setNombreProducto(nombre_producto);
                        detallePedido.setUnidades(unidades);
                        detallePedido.setPrecio(precio);
                        
                        detallesPedido.add(detallePedido);
                    }else{
                        responseBD = new ResponseBD(false);
                        responseBD.setValor(new String("No se encuentra el producto en la BBDD."));
                    }
                }
                responseBD = new ResponseBD(true);
                responseBD.setValorlista(detallesPedido);
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se podido obtener los detalles del pedido."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
    
    public ResponseBD cancelarProducto(int cod_pedido, int cod_producto, int precio){
        ResponseBD responseBD = new ResponseBD(false);
        if (conexionBD != null) {
            try {
                Savepoint savepoint = conexionBD.setSavepoint("cancelar_producto");
                try {
                    String sql_1 = "SELECT importe FROM pedidos WHERE codigo = ?";
                    PreparedStatement preparedStatement_1 = conexionBD.prepareStatement(sql_1);
                    preparedStatement_1.setInt(1, cod_pedido);
                    ResultSet resultado1 = preparedStatement_1.executeQuery();
                    if(resultado1.next()){
                        int importe = resultado1.getInt("importe");

                        conexionBD.setAutoCommit(false);

                        String sql_2 = "UPDATE pedidos SET importe = ? WHERE codigo = ?";
                        PreparedStatement preparedStatement_2 = conexionBD.prepareStatement(sql_2);

                        importe = importe - precio;
                        preparedStatement_2.setInt(1, importe);
                        preparedStatement_2.setInt(2, cod_pedido);

                        int resultado2 = preparedStatement_2.executeUpdate();
                        if(resultado2 > 0){
                            String sql_3 = "DELETE FROM detalles WHERE codigo_pedido = ? AND codigo_producto = ?";
                            PreparedStatement preparedStatement_3 = conexionBD.prepareStatement(sql_3);
                            preparedStatement_3.setInt(1, cod_pedido);
                            preparedStatement_3.setInt(2, cod_producto);

                            int resultado3 = preparedStatement_3.executeUpdate();
                            if(resultado3 > 0){
                                responseBD = new ResponseBD(true);
                            }else{
                                responseBD = new ResponseBD(false);
                                responseBD.setValor(new String("No se podido el producto  del pedido."));
                            }
                        }else{
                            responseBD = new ResponseBD(false);
                            responseBD.setValor(new String("No se podido actualizar el importe del pedido."));
                        }
                    }else{
                        responseBD = new ResponseBD(false);
                        responseBD.setValor(new String("No se podido obtener el importe del pedido."));
                    }
                } catch (Exception e) {
                    responseBD = new ResponseBD(false);
                    responseBD.setValor(new String("No se podido cancelar el producto del pedido."));
                    System.out.println(e.getMessage());
                    try {
                        conexionBD.rollback(savepoint);
                        conexionBD.setAutoCommit(true);
                    } catch (SQLException o) {
                        System.out.println(o.getMessage());
                    }
                }
                
                conexionBD.setAutoCommit(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }else{
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        
        return responseBD;
    }
    
    public ResponseBD cancelarPedidoMedianteCodigo(int codigo) {
    
        ResponseBD responseBD;
        if (conexionBD != null) {
            try {
                String sql = "UPDATE pedidos SET estado = ? WHERE codigo = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(sql);
                preparedStatement.setInt(1, PedidoBD.ESTADO_CANCELADO);
                preparedStatement.setInt(2, codigo);
                
                int tuplas_afectadas = preparedStatement.executeUpdate();
                
                responseBD = new ResponseBD(true);
                responseBD.setValor(new Integer(tuplas_afectadas));
            }
            catch(Exception e) {
                responseBD = new ResponseBD(false);
                responseBD.setValor(new String("No se podido cancelar el pedido."));
            }
        } else {
            responseBD = new ResponseBD(false);
            responseBD.setValor(new String("No se ha podido conectar a la BBDD. Pruebe más tarde."));
        }
        return responseBD;
    }
}