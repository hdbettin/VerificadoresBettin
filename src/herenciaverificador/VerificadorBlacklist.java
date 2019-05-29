package herenciaverificador;

import clases.Usuario;
import clases.Verificador;
import java.util.LinkedList;
import java.util.List;

public class VerificadorBlacklist extends Verificador {

    private LinkedList<String> listaNegra;

    public VerificadorBlacklist() {
        super(" Por favor introduzca el número del día de su último acceso");
        this.listaNegra = new LinkedList<>();
    }

    //Metodos Lista Negra
   
    
     public void eliminarLoginDeListaNegra(String login) {
        this.listaNegra.remove(login);
    }
    
    public void añadirLoginAListaNegra(String login) {
        this.listaNegra.add(login);
    }

   

    @Override
    public String loginPaso1(String login, String password) {
        if (this.listaNegra.contains(login)) {
            return null;
        }
        return super.loginPaso1(login, password);
    }

    
    @Override
    public String generarRespuesta(String login) {
        Usuario usuario = this.usuarios.get(login);
        String str = String.valueOf(usuario.getFechaUltimoAcceso().getDayOfMonth());
        return str;
    }
    
    //Metodo Clone
    @Override
    public VerificadorBlacklist clone() {
        VerificadorBlacklist copia = (VerificadorBlacklist) super.clone();
        copia.listaNegra = new LinkedList<>();
        return copia;
    }
    
    //Metodo ToString
    @Override
    public String toString() {
        return "VerificadorBlacklist {" + "listaNegra=" + listaNegra + '}' + super.toString();
    }
}
