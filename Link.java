
package User_login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Link {
    private String database_name = "registro";
    
    private String user = "root";
    private String password = "root";
    public String url = "jdbc:mysql://localhost:3306/" + database_name;
    Connection conn = null;
    
    public Connection getConnection() throws SQLException{
        try{
            //Obtener valor del driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Obtener la conexión
            conn = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e){
            System.err.println("Error ClassNotFoundException" + e.getMessage());
        }catch(SQLException e){
            System.err.println("SQLException" + e.getMessage());
        }
        return conn;
    }
}
