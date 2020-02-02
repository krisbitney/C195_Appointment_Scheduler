package Resources;

import java.util.ListResourceBundle;

public class Login_es extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][] {
                {"username", "Nombre de Usuario"},
                {"password", "Contraseña"} ,
                {"error_invalid", "Nombre de usuario o contraseña no válidos"},
                {"close", "Cierra"}
        };
    }
}

