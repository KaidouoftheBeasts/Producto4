package beepod.dao.factory;

import beepod.dao.ClienteNormalDao;
import beepod.dao.DAOException;
import beepod.modelo.Articulo;
import beepod.modelo.Cliente;
import beepod.modelo.ClienteNormal;
import beepod.modelo.ClientePremium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class factoryClienteEstandarDAO implements ClienteNormalDao {
    /**
     * Strings para utilizar en los diferentes metodos,
     *
     */
    final String INSERT = "INSERT INTO clientes(email, nombre, domicilio, nif, tipoCliente) VALUES (?,?,?,?,?)";
//    final String UPDATE = "UPDATE clientes SET email = ?, nombre = ?, domicilio = ?, nif = ?, tipoCliente = ? WHERE email = ? ";
//    final String DELETE = "DELETE FROM clientes WHERE email = ?";
    final String GETALL = "SELECT email, nombre, domicilio, nif, tipoCliente, descuento, cuota FROM clientes";
    final String GETONE = "SELECT email, nombre, domicilio, nif, tipoCliente, descuento, cuota FROM clientes  WHERE email = ?";
    private Connection con;

    /**
     * Constructor que necesita clase Connection
     * @param con
     */
    public factoryClienteEstandarDAO(Connection con){
        this.con = con;
    }

    /**
     * metodo insercion de lcientes normales o Estandar, recibe un cliente por parte del controlador
     * @param a
     * @throws DAOException
     */
    @Override
    public void insertar(ClienteNormal a) throws DAOException {
        PreparedStatement stat = null;
        try{
            con.setAutoCommit(false);
            stat = con.prepareStatement(INSERT);
            stat.setString(1, a.getEmail());
            stat.setString(2, a.getNombre());
            stat.setString(3,a.getDomicilio());
            stat.setString(4, a.getNif());
            stat.setString(5, "Estandard");

            if (stat.executeUpdate() == 0){
                throw  new DAOException("Error en grabado SQL");
            };
            con.commit();//confirmamos
        }catch (SQLException exception){
            try {
                if (con != null) {
                    con.rollback();//si falla volvemos atras
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
     * no usado
     * @param a
     * @throws DAOException
     */
    @Override
    public void modificar(ClienteNormal a) throws DAOException {

    }

    /**
     * no usado
     * @param a
     * @throws DAOException
     */
    @Override
    public void eliminar(ClienteNormal a) throws DAOException {

    }

    /**
     * metodo para convertir el resulset en un cliente estandard
     * @param rs
     * @return
     * @throws SQLException
     */
    private ClienteNormal convertir(ResultSet rs) throws SQLException{
        String nombre = rs.getString("nombre");
        String domicilio = rs.getString("domicilio");
        String nif = rs.getString("nif");
        String email = rs.getString("email");
        ClienteNormal cliente = new ClienteNormal (nombre, domicilio, nif, email);
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }


    /**
     * metodo listar clientes Estandar
     * @return
     * @throws DAOException
     */
    @Override
    public List<ClienteNormal> obtenerTodos() throws DAOException {

        PreparedStatement stat= null;
        ResultSet rs = null;
        List<ClienteNormal> clienteNormals = new ArrayList<>();
        try {
            stat = con.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                clienteNormals.add(convertir(rs));
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
        return clienteNormals;
    }

    /**
     * Metodo para obtener los datos de un cliente Estandar recibiendo del controlador el id, en este caso el mail
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public ClienteNormal obtener(String id) throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        ClienteNormal cliente = null;
        try {
            stat = con.prepareStatement(GETONE);
            stat.setString(1,id);
            rs = stat.executeQuery();

            if (rs.next()){
                cliente = convertir(rs);
                //System.out.println(cliente.toString());//imprimo el cliente

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
        return cliente;
    }

    /**
     * metodo para saber si un cliente existe, recibe por parametros un cliente estandard
     * @param clienteNormal
     * @return
     * @throws DAOException
     */
    @Override
    public boolean existe(ClienteNormal clienteNormal) throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        ClienteNormal cliente = null;
        try {
            stat = con.prepareStatement(GETONE);
            stat.setString(1,clienteNormal.getEmail());
            rs = stat.executeQuery();

            if (rs.next()){
                cliente = convertir(rs);
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
