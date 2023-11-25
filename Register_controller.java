
package User_login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Register_controller implements ActionListener {
    private User user;
    private User_link user_link;
    private Register register;

    public Register_controller(User user, User_link user_link, Register register) {
        this.user = user;
        this.user_link = user_link;
        this.register = register;
        //Boton de registrar
        this.register.btn_registrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register.btn_registrar)
            //Verificar si los campos estan vacios
            if(register.txt_register_name.getText().equals(""))
                || register.t
    }
    
}
