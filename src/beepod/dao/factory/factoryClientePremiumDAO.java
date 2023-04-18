package beepod.dao.factory;

import beepod.dao.ClientePremiumDao;
import beepod.dao.DAOException;
import beepod.modelo.Articulo;
import beepod.modelo.Cliente;
import beepod.modelo.ClienteNormal;
import beepod.modelo.ClientePremium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class factoryClientePremiumDAO implements ClientePremiumDao {
    /**
     * Atributos para usarlos en los métodos sucesivos.
     */
    final String INSERT = "INSERT INTO clientes(email, nombre, domicilio, nif, tipoCliente) VALUES (?,?,?,?,?)";
    final String UPDATE = "UPDATE clientes SET email = ?, nombre = ?, domicilio = ?, nif = ?, tipoCliente = ? WHERE email = ? ";
    final String DELETE = "DELETE FROM clientes WHERE email = ?";
    final String GETALL = "SELECT email, nombre, domicilio, nif, tipoCliente, descuento, cuota FROM clientes";
    final String GETONE = "SELECT email, nombre, domicilio, nif, tipoCliente, descuento, cuota FROM clientes  WHERE email = ?";
    private Connection con;

    /**
     * Constructor que necesita una clase connection.
     * @param con
     */
    public factoryClientePremiumDAO(Connection con){
        this.con = con;
    }

    /**
     * metodo insercion de los clientes
     * @param a
     * @throws DAOException
     */
    @Override
    public void insertar(ClientePremium a) throws DAOException {
        PreparedStatement stat = null;
        CallableStatement cs = null;//para los procedimientos almacenados
        try{

            stat = con.prepareStatement(INSERT);
            stat.setString(1, a.getEmail());
            stat.setString(2, a.getNombre());
            stat.setString(3,a.getDomicilio());
            stat.setString(4, a.getNif());
            stat.setString(5, "Premium");
            if (stat.executeUpdate() == 0){
                throw  new DAOException("Error en grabado SQL");
            };
            cs = con.prepareCall("{CALL insertarDatosPremium()}");//llamamos al procedimiento almacenado
            cs.execute();//ejecutamos el procedimiento almacenado
            cs.close();//cerramos la llamada
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

    /**
     * metodo no usado
     * @param a
     * @throws DAOException
     */
    @Override
    public void modificar(ClientePremium a) throws DAOException {

    }

    /**
     * metodo no usuado
     * @param a
     * @throws DAOException
     */
    @Override
    public void eliminar(ClientePremium a) throws DAOException {

    }

    /**
     * metodo para listar todos los clientes
     * @return
     * @throws DAOException
     */
    @Override
    public List<ClientePremium> obtenerTodos() throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        List<ClientePremium> clientePremiums = new ArrayList<>();
        try {
            stat = con.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                clientePremiums.add(convertir(rs));
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
        return clientePremiums;

    }

    /**
     * metodo para obtener un cliente premium en función del id, en este caso el email
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public ClientePremium obtener(String id) throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        ClientePremium cliente = null;
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
     * metodo para saber si un cliente premium existe
     *
     * @param clientePremium
     * @return
     * @throws DAOException
     */
    @Override
    public boolean existe(ClientePremium clientePremium) throws DAOException {
        PreparedStatement stat= null;
        ResultSet rs = null;
        ClientePremium cliente = null;
        try {
            stat = con.prepareStatement(GETONE);
            stat.setString(1,clientePremium.getEmail());
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

    /**
     * metodo para comvertir el resultset en un cliente premium
     * @param rs
     * @return
     * @throws SQLException
     */
    private ClientePremium convertir(ResultSet rs) throws SQLException{
        String nombre = rs.getString("nombre");
        String domicilio = rs.getString("domicilio");
        String nif = rs.getString("nif");
        String email = rs.getString("email");
        ClientePremium cliente = new ClientePremium (nombre, domicilio, nif, email);
        cliente.setEmail(rs.getString("email"));
        return cliente;
    }
}
