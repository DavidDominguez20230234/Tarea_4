
package User_login;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;


public class User_link {
    //Instancia de la conexión
    
    Link link = new Link();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    //Enviar datos entre interfaces
    public static int id = 0;
    public static String nombre = "";
    public static String apellido = "";
    public static String telefono = "";
    public static String email = "";
    public static String user_name = "";
    public static String password1 = "";
    
    //Método login
    public User loguinQuery(String user, String password){
        String query = "SELECT * FROM users WHERE password = ?";
        User user1 = new User();
        try{
            conn = link.getConnection();
            pst = conn.prepareStatement(query);
            //Enviar parámetros
            pst.setString(1, user);
            pst.setString(2, password);
            rs = pst.executeQuery();
            
            if (rs.next()){
                user1.setId(rs.getInt("id"));
                id = user1.getId();
                user1.setNombre(rs.getString("nombre"));
                nombre = user1.getNombre();
                user1.setApellido(rs.getString("apellido"));
                apellido = user1.getApellido();
                user1.setTelefono(rs.getString("telefono"));
                telefono = user1.getTelefono();
                user1.setEmail(rs.getString("email"));
                email = user1.getEmail();
                user1.setUser_name(rs.getString("user_name"));
                user_name = user1.getUser_name();
                user1.setPassword(rs.getString("password"));
                password1 = user1.getPassword();
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al obtener el usuario" + e);
        }
        return user1;
    }  
    
    //Registrar usiario
    public boolean registerUserQuery(User user1){
        String query = "INSERT INTO user tbl_users(id, nombre, apellido, telefono, email, user_name, password)"
                + "VALUES (?,?,?,?,?,?,?)";
        try{
            conn = link.getConnection();
            pst = conn.prepareStatement(query);
            pst.setInt(1, user1.getId());
            pst.setString(2, user1.getNombre());
            pst.setString(3, user1.getApellido());
            pst.setString(4, user1.getTelefono());
            pst.setString(5, user1.getEmail());
            pst.setString(6, user1.getUser_name());
            pst.setString(7, user1.getPassword());
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar el empleado" + e);
            return false;
        }
    }
    
    //Listar usuario
    public List listUserQuery(String value){
        List<User> list_users = new ArrayList();
        String query = "SELECT * FROM tbl_users";
        String query_search = "SELECT * FROM tbl_users WHERE id LIKE '%" + value + "%'";
    
    try{
        conn = link.getConnection();
        if (value.equalsIgnoreCase("")){
            pst = conn.prepareCall(query);
            rs = pst.executeQuery();
            
        }else{
            pst = conn.prepareStatement(query_search);
            rs = pst.executeQuery();
        }
       
        while (rs.next()){
            User user1 = new User();
            user1.setId(rs.getInt("id"));
            user1.setNombre(rs.getString("nombre"));
            user1.setApellido(rs.getString("apellido"));
            user1.setTelefono(rs.getString("telefono"));
            user1.setEmail(rs.getString("email"));
            user1.setUser_name(rs.getString("user_name"));
            user1.setPassword(rs.getString("password"));
            list_users.add(user1);
        }
    
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.toString());
        
    }
    return list_users;
   
    } 
    
    //Actualizar usuario
    public boolean updateUserQuery(User user1){
        String query = "UPDATE tbl_users SET nombre = ?, apellido = ?, telefono = ?, email = ?, user_name = ? WERE id = ?";
        try{
            conn = link.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, user1.getNombre());
            pst.setString(2, user1.getApellido());
            pst.setString(3, user1.getTelefono());
            pst.setString(4, user1.getEmail());
            pst.setString(5, user1.getUser_name());
            pst.setInt(8, user1.getId());
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos del usurio" + e);
            return false;
        }
    }
    
    //Eliminar usuario
    public boolean deleteUserQuery(int id){
        String query = "DELETE FROM tbl_users WHERE id = " + id;
        
        try{
            conn = link.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al intentar eliminar el usuario" + e);
            return false;
        }
    }
    
    //Cambiar contraseña 
    public boolean updateUserPasswordQuery(User user1){
        String query = "UPDATE tbl_users SET password = ? WHERE user_name = '" + user_name + "'";
        
        try{
            conn = link.getConnection();
            pst = conn.prepareStatement(query);
            pst.setString(1, user1.getPassword());
            pst.executeUpdate();
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al intentetar cambiar la contraseña" + e);
            return false;
        }
    }
}
