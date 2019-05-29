package clases;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Verificador implements Cloneable {
 
    protected final String peticiondelDesafio;
    protected HashMap<String, String> desafios;
    protected HashMap<String, Usuario> usuarios;
    
   

    protected Verificador(String peticionDesafio) {
        this.peticiondelDesafio = peticionDesafio;
        this.usuarios = new HashMap<>();
        this.desafios = new HashMap<>();
    }

  
      public String getPeticion() {
        return this.peticiondelDesafio;
    }
      
    public LinkedList<Usuario> getUsuarios() {
        return new LinkedList<>(usuarios.values());
    }
    
  
    //Metodos 
    
    public boolean borrarUsuario(Usuario usuario) {
        if (this.usuarios.containsKey(usuario.getLogin())) {
            this.usuarios.remove(usuario.getLogin());
            return true;
        }

        return false;
    }
    
    public void añadirUsuario(Usuario... usuarios) {
        for (Usuario itera : usuarios) {
            this.usuarios.put(itera.getLogin(), itera);
        }
    }

    
//metodo Abstracto
    public abstract String generarRespuesta(String login);
    
    

    public String loginPaso1(String login, String password) {
        String token = null;
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            if (usuario.getPassword().equals(password)) {
                token = UUID.randomUUID().toString();
                String respuesta = generarRespuesta(login);
                this.desafios.put(token, respuesta);
            }
        }

        return token;
    }

    public boolean loginPaso2(String token, String respuestaUsuario) {
        if (this.desafios.containsKey(token)) {
            String respuesta = desafios.get(token);
            if (respuesta.equals(respuestaUsuario)) {
                this.desafios.remove(token);
                return true;
            }
        }

        return false;
    }


    
    //Metodo ToString
    @Override
    public String toString() {
        return "Verificador{" + "usuarios=" + usuarios + ", peticionDesafio=" + peticiondelDesafio + ", desafios=" + desafios + '}';
    }

    //Metodo Clone
    @Override
    public Verificador clone() {
        try {
            Verificador copia = (Verificador) super.clone();
            copia.usuarios = new HashMap<>(copia.usuarios);
            copia.desafios = new HashMap<>();
            return copia;
        } catch (CloneNotSupportedException e) {
            System.err.println("Error en la copia realizada " + e.getMessage());
        }
        
        return null;
    }
}
