package User_login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {

    private User user1;
    private User_link user_link;
    private Login login;

    public LoginController(User user1, User_link user_link, Login login) {
        this.user1 = user1;
        this.user_link = user_link;
        this.login = login;
        this.login.btn_iniciar_sesion.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Obtener datos de la vista
        String user = login.txt_user_name.getText().trim();
        String pass = String.valueOf(login.txt_password.getPassword());

        if (e.getSource() == login.btn_iniciar_sesion) {
            //Verificar que los campos de textos no esten vacios

            if (!user.equals("") || !pass.equals("")) {
                //Pasar parametros al metodo login
                user1 = user_link.loguinQuery(user, pass);
                //Verificar la existencia del usuario en la base de datos
                if (user1.getUser_name() != null) {
                    Registered_users registered = new Registered_users();
                    registered.setVisible(true);
                } else {
                    Register register = new Register();
                    register.setVisible(true);
                }
                this.login.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }

}
