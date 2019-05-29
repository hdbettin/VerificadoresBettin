package herenciaverificador;

import clases.Verificador;
import java.util.Random;

public class VerificadorPorCodigo extends Verificador {

    private final int limiteIntentos;
    private static int numeroDeIntentos = 0;

    public VerificadorPorCodigo(int limiteIntentos) {
        super("Introduzca el número que ha recibido por SMS");
        this.limiteIntentos = limiteIntentos;
    }

    
    @Override
    public boolean loginPaso2(String token, String respuestaUsuario) {
        if (numeroDeIntentos > limiteIntentos) {
            if (this.desafios.containsKey(token)) {
                this.desafios.remove(token);
            }
            return false;
        } else {
            numeroDeIntentos = numeroDeIntentos + 1;
            return super.loginPaso2(token, respuestaUsuario);
        }
    }

    @Override
    public String generarRespuesta(String login) {
        int n = new Random().nextInt(999);
        System.err.println("NUMERO GENERADO: " + n);
        return String.valueOf(n);
    }
    
    //Metodo Clone
    @Override
    public VerificadorPorCodigo clone() {
        VerificadorPorCodigo copia = (VerificadorPorCodigo) super.clone();
        return copia;
    }
    
    //Metodo ToString
    @Override
    public String toString() {
        return "VerificadorPorCodigo{" + "limiteIntentos=" + limiteIntentos + '}' + super.toString();
    } 
}
