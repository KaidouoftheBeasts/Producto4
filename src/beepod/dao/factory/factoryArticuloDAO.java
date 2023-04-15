package beepod.dao.factory;

import beepod.dao.ArticuloDao;
import beepod.dao.DAOException;
import beepod.modelo.Articulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class factoryArticuloDAO implements ArticuloDao {

    final String INSERT = "INSERT INTO articulos(codigo, descripcion, pvp, envio, tiempoPreparacion) VALUES (?,?,?,?,?)";
    final String UPDATE = "UPDATE articulos SET codigo = ?,descripcion = ?, pvp = ?, envio = ?, tiempoPreparacion= ? WHERE codigo = ? ";
    final String DELETE = "DELETE FROM articulos WHERE codigo = ?";
    final String GETALL = "SELECT codigo, descripcion, pvp, envio, tiempoPreparacion FROM articulos";
    final String GETONE = "SELECT codigo, descripcion, pvp, envio, tiempoPreparacion FROM articulos  WHERE codigo = ?";
    private Connection con;
    public factoryArticuloDAO(Connection con){
        this.con = con;
    }
    @Override
    public void insertar(Articulo a) throws DAOException{
        PreparedStatement stat = null;
        try{

            stat = con.prepareStatement(INSERT);
            stat.setString(1, a.getCodigo());
            stat.setString(2, a.getDescripcion());
            stat.setFloat(3,a.getPrecioVenta());
            stat.setFloat(4, a.getGastosEnvio());
            stat.setLong(5, a.getTiempoPreparacion());
            if (stat.executeUpdate() == 0){
                throw  new DAOException("Error en grabado SQL");
            };
        }catch (SQLException exception){
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

    @Override
    public void modificar(Articulo a) {

    }

    @Override
    public void eliminar(Articulo a) {

    }

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
    public static void main (String [] args) throws SQLException{
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/beepod", "root","Windows1234");
            ArticuloDao dao = new factoryArticuloDAO(con);
            /*List<Articulo> articulos = dao.obtenerTodos();
            for (Articulo a: articulos){
                System.out.println(a.toString());
            }*/
            Articulo articulo = new Articulo("1F", "es una prueba DOS", 125, 20, 5);
            dao.insertar(articulo);
            System.out.println("Articulo generado correctamente "+ articulo.getCodigo());


            con.close();

        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }
}
