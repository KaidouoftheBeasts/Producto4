package beepod.dao.factory;
import beepod.dao.ArticuloDao;
import beepod.dao.DAOException;
import beepod.modelo.Articulo;
import beepod.modelo.ClienteNormal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class factoryArticuloDAO implements ArticuloDao {
    /**
     * Listado de String para utilizar en los diferentes métodos, no son necesarios todos.
     */
    final String INSERT = "INSERT INTO articulos(codigo, descripcion, pvp, envio, tiempoPreparacion) VALUES (?,?,?,?,?)";

//    final String UPDATE = "UPDATE articulos SET codigo = ?,descripcion = ?, pvp = ?, envio = ?, tiempoPreparacion= ? WHERE codigo = ? ";
//    final String DELETE = "DELETE FROM articulos WHERE codigo = ?";
    final String GETALL = "SELECT codigo, descripcion, pvp, envio, tiempoPreparacion FROM articulos";
    final String GETONE = "SELECT codigo, descripcion, pvp, envio, tiempoPreparacion FROM articulos  WHERE codigo = ?";
    private Connection con;

    /**
     * Constructor de la clase que recibe por parametros una clase Connection.
     * @param con
     */
    public factoryArticuloDAO(Connection con){
        this.con = con;
    }

    /**
     * Metodo insertar articulos,
     * @param a
     * @throws DAOException
     */
    @Override
    public void insertar(Articulo a) throws DAOException{
        PreparedStatement stat = null;
        //CallableStatement cs = null;//para los procedimientos almacenados
        try{
            con.setAutoCommit(false);
            stat = con.prepareStatement(INSERT);
            stat.setString(1, a.getCodigo());
            stat.setString(2, a.getDescripcion());
            stat.setFloat(3,a.getPrecioVenta());
            stat.setFloat(4, a.getGastosEnvio());
            stat.setLong(5, a.getTiempoPreparacion());
            if (stat.executeUpdate() == 0){
                throw  new DAOException("Error en grabado SQL");
            };
            con.commit();///confirmamos
            /*cs = con.prepareCall("{CALL totalPedido()}");//llamamos al procedimiento almacenado
            cs.execute();//ejecutamos el procedimiento almacenado
            cs.close();//cerramos la llamada*/
        }catch (SQLException exception){
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex2) {
                System.out.println("Error al deshacer la transacción: " + ex2.getMessage());
            }
            throw  new DAOException("Error en SQL"+exception);

        }
        finally {
            if (stat != null){
                try{
                    stat.close();

                }catch (SQLException exception){
                    throw  new DAOException("Error en SQL"+exception);

                }
            }

        }
    }

    /**
     * no utilizados
     * @param a
     */
    @Override
    public void modificar(Articulo a) {

    }

    /**
     * no utilizados
     * @param a
     */
    @Override
    public void eliminar(Articulo a) {

    }

    /**
     * metodo para listar los articulos, retorna el listado de articulos
     * @return
     * @throws DAOException
     */
    @Override
    public List<Articulo> obtenerTodos()throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        List<Articulo> articulos = new ArrayList<>();
        try {
            stat = con.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                articulos.add(convertir(rs));
            }


        }catch (SQLException ex){
            throw new DAOException("Error en SQL");
        }
        finally {
            if (rs!= null){
                try{

                    rs.close();
                }catch (Exception ex){
                    new DAOException("ERROR sql "+ex);
                }

            }
            if (stat != null){
                try{
                    stat.close();

                }catch (SQLException ex){
                    new DAOException("Error en la extraccion");
                }
            }

        }
        return articulos;

    }

    /**
     * metodo para convertir el resultset en un articulo y que nos retorne el articulo
     * @param rs
     * @return
     * @throws SQLException
     */
    private Articulo convertir(ResultSet rs) throws SQLException{
        String codigo = rs.getString("codigo");
        String descripcion = rs.getString("descripcion");
        float pvp = rs.getFloat("pvp");
        float envio = rs.getFloat("envio");
        long tiempoPreparacion = rs.getLong("tiempoPreparacion");
        Articulo articulo = new Articulo(codigo, descripcion, pvp, envio, tiempoPreparacion);
        articulo.setCodigo(rs.getString("codigo"));
        return articulo;

    }

    /**
     * metodo para obtener el articulo que deseemos recibiendo el id del producto.
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Articulo obtener(String id) throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        Articulo articulo = null;
        try {
            stat = con.prepareStatement(GETONE);
            stat.setString(1,id);
            rs = stat.executeQuery();

            if (rs.next()){
                articulo = convertir(rs);
                System.out.println(articulo);//imprimo el articulo

            }else{
                throw new DAOException("No se ha encontrado el registro");
            }
        }catch (SQLException ex){
            throw new DAOException("Error en SQL");
        }
        finally {
            if (rs!= null){
                try{

                    rs.close();
                }catch (Exception ex){
                    new DAOException("ERROR sql "+ex);
                }

            }
            if (stat != null){
                try{
                    stat.close();

                }catch (SQLException ex){
                    new DAOException("Error en la extraccion");
                }
            }

        }
        return articulo;
    }

    /**
     * metodo para controlar que el articulo exista.
     * @param a
     * @return
     * @throws DAOException
     */
    @Override
    public boolean existe(Articulo a) throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        Articulo articulo = null;
        try {
            stat = con.prepareStatement(GETONE);
            stat.setString(1,articulo.getCodigo());
            rs = stat.executeQuery();

            if (rs.next()){
                articulo = convertir(rs);
                return true;
            }else{
                throw new DAOException("No se ha encontrado el registro");
            }
        }catch (SQLException ex){
            throw new DAOException("Error en SQL");
        }
        finally {
            if (rs!= null){
                try{
                    rs.close();
                }catch (Exception ex){
                    new DAOException("ERROR sql "+ex);
                }
            }
            if (stat != null){
                try{
                    stat.close();
                }catch (SQLException ex){
                    new DAOException("Error en la extraccion");
                }
            }
        }
    }
}

    /*public static void main (String [] args) throws SQLException{//para probar métodos
        Connection con = null;
        Scanner s = new Scanner(System.in);
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/beepod", "root","Windows1234");
            ArticuloDao dao = new factoryArticuloDAO(con);

            //Articulo articulo = new Articulo("4F", "es una prueba DOS", 125, 20, 5);
            //dao.insertar(articulo);
            //System.out.println("Articulo generado correctamente "+ articulo.getCodigo());

            List<Articulo> articulos = dao.obtenerTodos();
            for (Articulo a: articulos){
                System.out.println(a.toString());
            }
            System.out.println("Introduce el código del articulo: ");
            String codigo = s.nextLine();
            dao.obtener(codigo);
            con.close();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }*/

