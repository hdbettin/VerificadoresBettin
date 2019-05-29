package clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private final String login;
    private LocalDate fechaUltimoAcceso;
    private String password;
    private final HashSet<String> historialPassword;
    
    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
        this.historialPassword = new HashSet<>();
        this.historialPassword.add(password);
        this.fechaUltimoAcceso = LocalDate.now();
    }
    
  public String getPassword() {
        return password; 
    }
    public Set<String> getHistorialPassword() {
        return historialPassword;
    }
    public String getLogin() { 
        return login; 
    }
    public LocalDate getFechaUltimoAcceso() {
        return fechaUltimoAcceso; 
    }
   
    
    
    //Metodos
    
    public boolean modificarPassword(String passactual, String passnueva) {
        if(this.password.equals(passactual) && !this.historialPassword.contains(passnueva)) {
            this.password = passnueva;
            return this.historialPassword.add(passnueva);
        }
        
        return false;
    }
    
    public boolean validar(String password) {
        return (this.password.equals(password));
    }
    
    public void establecerFechaUltimoAcceso() {
        this.fechaUltimoAcceso = LocalDate.now();
    }
    
    

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", fechaUltimoAcceso=" + fechaUltimoAcceso + ", password=" + password + ", historialPassword=" + historialPassword + '}';
    }
}
