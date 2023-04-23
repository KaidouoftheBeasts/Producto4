package beepod.dao.factory;
import beepod.dao.DAOException;
import beepod.modelo.Pedido;
import java.sql.*;
public class factoryPedido {
    /**
     * Atributos para utilizar en los diferentes métodos.
     */
    private Connection con;
    final String GETALLPENDIENTES = "SELECT idPedido, codigo, cantidad, email, fecha, enviado, total FROM pedidos WHERE enviado = 0";
    final String GETALLENVIADOS = "SELECT idPedido, codigo, cantidad, email, fecha, enviado, total FROM pedidos WHERE enviado = 1";
    final String GETALLPENDIENTESCLIENTE = "SELECT idPedido, codigo, cantidad, email, fecha, enviado, total FROM pedidos WHERE enviado = 0 AND email = ?";
    final String GETALLENVIADOSCLIENTE = "SELECT idPedido, codigo, cantidad, email, fecha, enviado, total FROM pedidos WHERE enviado = 1 AND email = ?";
    final String INSERT = "INSERT INTO pedidos(codigo, cantidad, email) VALUES (?,?,?)";
    final String DELETE = "DELETE FROM pedidos WHERE idPedido = ? AND enviado = 0";

    /**
     * Constructor, necesita una clase connection.
     * @param con
     */
    public factoryPedido(Connection con){
        this.con = con;
    }

    /**
     * metodo para la inserción de un pedido, recibe el pedido del controlador
     * @param a
     * @throws DAOException
     */
    public void insertar(Pedido a) throws DAOException {
        PreparedStatement stat = null;
        CallableStatement cs = null;//para los procedimientos almacenados
        try{
            con.setAutoCommit(false);
            stat = con.prepareStatement(INSERT);
            stat.setString(1, a.getArticulo().getCodigo());
            stat.setInt(2, a.getCantidad());
            stat.setString(3,a.getCliente().getEmail());

            if (stat.executeUpdate() == 0){
                throw  new DAOException("Error en grabado SQL");
            };
            cs = con.prepareCall("{CALL totalPedido()}");//llamamos al procedimiento almacenado para poner los datos totales del pedido hora y total
            cs.execute();//ejecutamos el procedimiento almacenado
            cs.close();//cerramos la llamada
            con.commit();//confirmamos
        }catch (SQLException exception){
            try {
                if (con != null) {
                    con.rollback();//volvemos si hay error
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
     * metodo para convertir el resultset en algo visible por pantalla.
     * @param rs
     * @throws SQLException
     */
    private void convertir(ResultSet rs) throws SQLException{
        int idPedido = rs.getInt("idPedido");
        String codigo = rs.getString("codigo");
        int cantidad = rs.getInt("cantidad");
        String email = String.valueOf(rs.getString("email"));
        Timestamp fecha = rs.getTimestamp("fecha");
        boolean enviado = rs.getBoolean("enviado");
        float total = rs.getFloat("total");
        System.out.println("Pedido num: "+ idPedido+ " codigo: "+codigo+" cantidad: "+ cantidad+ " email: "+email+" fecha: "+ fecha+ " enviado: "+ enviado+" total: "+total);
    }

    /**
     * Metodo para obtener los pedidos que tengan pendientes el cliente que viene definido por el mail.
     * @param email
     * @throws DAOException
     */

    public void obtenerPendientesCliente(String email) throws DAOException {

        PreparedStatement stat = null;
        ResultSet rs = null;
        CallableStatement cs = null;//para los procedimientos almacenados

        try {
            actualizarPedidos();//actualiza el estado de los pedidos
            stat = con.prepareStatement(GETALLPENDIENTESCLIENTE);
            stat.setString(1, email);
            rs = stat.executeQuery();
            while (rs.next()) {
                convertir(rs); //Ver como listar los pedidos
            }


        } catch (SQLException | DAOException ex) {
            throw new DAOException("Error en SQL");
        } finally {
            if (rs != null) {
                try {

                    rs.close();
                } catch (Exception ex) {
                    new DAOException("ERROR sql " + ex);
                }

            }
            if (stat != null) {
                try {
                    stat.close();

                } catch (SQLException ex) {
                    new DAOException("Error en la extraccion");
                }
            }

        }
    }

    /**
     * metodo para obtener todos los pedidos pendientes sin tener en cuenta nada más.
     * @throws DAOException
     */
    public void obtenerTodosPendienes() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        CallableStatement cs = null;//para los procedimientos almacenados

        try {
            actualizarPedidos();
            stat = con.prepareStatement(GETALLPENDIENTES);
            rs = stat.executeQuery();

            while (rs.next()) {
                convertir(rs); //Ver como listar los pedidos
            }


        } catch (SQLException ex) {
            throw new DAOException("Error en SQL");
        } finally {
            if (rs != null) {
                try {

                    rs.close();
                } catch (Exception ex) {
                    new DAOException("ERROR sql " + ex);
                }

            }
            if (stat != null) {
                try {
                    stat.close();

                } catch (SQLException ex) {
                    new DAOException("Error en la extraccion");
                }
            }

        }
       //return pedidos;
    }

    /**
     * metodo para eliminar un pedido siempre y cuando no esté enviado
     * @param id
     * @throws DAOException
     */
    public void eliminar(int id) throws DAOException {
        PreparedStatement stat = null;
        try{
            actualizarPedidos();//actualizo los pedidos
            stat = con.prepareStatement(DELETE);
            stat.setInt(1, id);

            if (stat.executeUpdate() == 0){
                throw  new DAOException("Error en la eliminacion SQL");
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

    /**
     * metodo para obtener todos los pedidos enviados
     * @throws DAOException
     */
    public void obtenerTodosEnviados() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        CallableStatement cs = null;//para los procedimientos almacenados

        try {
            actualizarPedidos();//actualizo los pedidos
            stat = con.prepareStatement(GETALLENVIADOS);
            rs = stat.executeQuery();

            while (rs.next()) {
                convertir(rs); //Ver como listar los pedidos
            }


        } catch (SQLException ex) {
            throw new DAOException("Error en SQL");
        } finally {
            if (rs != null) {
                try {

                    rs.close();
                } catch (Exception ex) {
                    new DAOException("ERROR sql " + ex);
                }

            }
            if (stat != null) {
                try {
                    stat.close();

                } catch (SQLException ex) {
                    new DAOException("Error en la extraccion");
                }
            }
        }
    }

    /**
     * metodo que recibe un mail de un cliente y nos lista los pedidos de este cliente
     * @param email
     * @throws DAOException
     */
    public void obtenerEnviadosCliente(String email) throws DAOException {

        PreparedStatement stat = null;
        ResultSet rs = null;
        CallableStatement cs = null;//para los procedimientos almacenados

        try {
            actualizarPedidos();
            stat = con.prepareStatement(GETALLENVIADOSCLIENTE);
            stat.setString(1, email);
            rs = stat.executeQuery();
            while (rs.next()) {
                convertir(rs); //Ver como listar los pedidos
            }


        } catch (SQLException | DAOException ex) {
            throw new DAOException("Error en SQL");
        } finally {
            if (rs != null) {
                try {

                    rs.close();
                } catch (Exception ex) {
                    new DAOException("ERROR sql " + ex);
                }

            }
            if (stat != null) {
                try {
                    stat.close();

                } catch (SQLException ex) {
                    new DAOException("Error en la extraccion");
                }
            }

        }
    }

    /**
     * metodo para actulizar el estado del pedido, para ello ejecuta el procedimiento almacenado de la BBDD cambiarEnvio.
     * @throws DAOException
     */
    public void actualizarPedidos() throws DAOException {

        CallableStatement cs = null;//para los procedimientos almacenados

        try{
            cs = con.prepareCall("{CALL cambiarEnvio()}");//llamamos al procedimiento almacenado para poner los datos totales del pedido hora y total
            cs.execute();//ejecutamos el procedimiento almacenado
            cs.close();//cerramos la llamada
        }catch (SQLException exception){
            throw  new DAOException("Error en SQL 1"+exception);
        }
    }

}
