package beepod.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    /**
     * Atributos de la conexion
     */
    private Connection conn = null;
    private Properties prop = new Properties();
    private FileInputStream input;

    /**
     * Constructor de la conexion, leemos el archivo DatosConexion.txt que previamente hemso configurado, seguidamente unimos
     * todo y realizamos la conexion.
     * @throws IOException
     */
    public Conexion() throws IOException {
        input = new FileInputStream("Recursos/DatosConexion.txt");
        prop.load(input);
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("password");

        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion a BBDD Ok.");

        } catch (SQLException ex){
            System.out.println("Error conexion BD: "+ ex.getMessage());
        }
    }

    /**
     * nos retorna la conexion generada
     * @return
     */
    public Connection getConnection(){
        return this.conn;
    }

    /**
     * nos desconecta de la base de datos.
     */
    public void desconectarBD(){
        System.out.println("Cerrar conexión a BBDD");
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("No se realizó la desconexión: " + ex.getMessage());
            }
        }
    }
}
