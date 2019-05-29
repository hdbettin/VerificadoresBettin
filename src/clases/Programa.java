package clases;

import herenciaverificador.VerificadorPorCodigo;
import herenciaverificador.VerificadorBlacklist;
import clases.Usuario;
import clases.Verificador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       
        Scanner obte = new Scanner(System.in);
        String usuario, contraseña, respuesta;

        /*Crea un usuario con login “fperez” con password “lechemerengada”. Modifica el
        password del usuario anterior estableciendo “cr7comeback”*/
        Usuario fperezOb = new Usuario("fperez", "lechemerengada");
        fperezOb.modificarPassword("lechemerengada", "cr7comeback");

        //Crea un usuario con login “mlama” con password “tururu”.
        Usuario mlamaOb = new Usuario("mlama", "tururu");

        /*
        - Crea un verificador blacklist y un verificador por código.
        - Añade los usuarios a los verificadores*/
        VerificadorBlacklist verblackOb = new VerificadorBlacklist();
        verblackOb.añadirUsuario(fperezOb, mlamaOb);
        VerificadorPorCodigo verCodOb = new VerificadorPorCodigo(3);
        verCodOb.añadirUsuario(fperezOb, mlamaOb);

        //Declara y construye una lista de verificadores. Añade los verificadores anteriores
        List<Verificador> verificadores = new ArrayList<>(2);
        Collections.addAll(verificadores, verblackOb, verCodOb);

        /*Recorre la lista de verificadores: si el verificador es de tipo blacklist, añade el usuario
        “mlama” como usuario bloqueado.*/
        for (Verificador iter : verificadores) {
            if (iter instanceof VerificadorBlacklist) {
                ((VerificadorBlacklist) iter).añadirLoginAListaNegra(mlamaOb.getLogin());
            }
        }

        /*Recorre la lista de verificadores:
            o Solicita por la consola el login y password del usuario.
            Nota: utiliza la clase java.util.Scanner para leer información de la consola.
            o Con las credenciales anteriores, realiza el primer paso de verificación.
            o Si el primer paso ha sido superado:
                ▪ Obtén del verificador la petición del desafío y muéstrala por la consola.
                ▪ Solicita por la consola la respuesta al desafío.
                ▪ Con el token obtenido en el paso 1 y la respuesta, realiza el segundo paso
                del desafío. Muestra por la consola el resultado.*/
        for (Verificador iter : verificadores) {
            System.out.print(" Por favor introduzca su Usuario: ");
            usuario = obte.next();
            System.out.print("Por favor introduzca su Contraseña: ");
            contraseña = obte.next();
            String token = iter.loginPaso1(usuario, contraseña);
            if (token != null) {
                System.out.println("Peticion del Desafio!! : " + iter.getPeticion());
                System.out.print("Por favor introduzca la Respuesta Correcta: ");
                respuesta = obte.next();
                if (iter.loginPaso2(token, respuesta)) {
                    System.out.println("ACCESO LOGRADO!");
                } else {
                    System.out.println("RESPUESTA INCORRECTA. ACCESO RECHAZADO");
                }
            } else {
                System.out.println("USUARIO Y CONTRASEÑA INCORRECTA. ACCESO RECHAZADO!");
            }
        }

        //Declara y construye una nueva lista de verificadores llamada copias.
        List<Verificador> copias = new ArrayList<>(2);
        
        /*Recorre la primera lista de verificadores y para cada elemento obtén una copia y
        guárdala en la lista copias.*/
        for (Verificador ver : verificadores) {
            copias.add(ver.clone());
        }

        /*Recorre la lista con las copias y para cada verificador muestra su información por la
        consola (toString).*/
        for (Verificador ver : copias) {
            System.out.println(ver.toString());
        }
    }
}
