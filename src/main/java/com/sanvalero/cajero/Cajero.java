package com.sanvalero.cajero;

/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
//librerias
import java.util.Random;
import com.sanvalero.cajero.domain.CuentaCorriente;
import com.sanvalero.cajero.dao.CuentaCorrienteDAO;
import com.sanvalero.cajero.dao.Conexion;
import com.sanvalero.cajero.domain.Usuario;
import com.sanvalero.cajero.dao.UsuarioDAO;
import com.sanvalero.cajero.domain.Util;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cajero {

    private boolean salir;
    private final Scanner teclado;
    private final Conexion connection;
    private final UsuarioDAO usuarioDAO;
    private final CuentaCorrienteDAO ccDAO;
    private final CuentaCorriente cc;

    public Cajero() {
        salir = false;
        teclado = new Scanner(System.in);
        connection = new Conexion();
        connection.connect();
        ccDAO = new CuentaCorrienteDAO(connection);
        usuarioDAO = new UsuarioDAO(connection);
        cc = new CuentaCorriente();
    }

    public void ejecutar() {

        System.out.println("¡Hola! Te damos la bienvenida a nuestro sistema de banca privada.");
        do {
            System.out.println("¿qué deseas hacer?");
            System.out.println("1. Darte de alta como usuario");
            System.out.println("2. Crear una cuenta corriente(Solo usuarios)");
            System.out.println("3. Revisar datos de tu cuenta");
            System.out.println("4. Operar y dar de baja");
            System.out.println("x. Salir");
            System.out.println("Selecciona: ");
            String menu = teclado.next();

            switch (menu.toUpperCase()) {
                case "1":
                    registroUsuario();
                    break;
                case "2":
                    crearCC();
                    break;
                case "3":
                    verDatos();
                    break;
                case "4":
                    operar();
                    break;
                case "X":
                    salir();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!salir);

    }

    private void registroUsuario() {

        System.out.println("Introduce tu nombre: ");
        String nombre = teclado.next();
        System.out.println("Introduce tus apellidos: ");
        String apellidos = teclado.next();
        System.out.println("Introduce tu contrasena:");
        String contrasena = teclado.next();
        System.out.println("Introduce tu dni:");
        String dni = teclado.next();
        System.out.println("Introduce tu email:");
        String email = teclado.next();
        System.out.println("Introduce tu número de teléfono:");
        int telefono = teclado.nextInt();
        System.out.println("Creando usuario...");

        Usuario usuario = new Usuario(nombre, apellidos, dni, email, telefono, contrasena);
        /*
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setDni(dni);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setContrasena(contrasena);
         */

        try {
            usuarioDAO.registroUsuario(usuario);
            System.out.println("Usuario registrado correctamente");
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema. Inténtelo de nuevo");
            sqle.printStackTrace();
        }
    }

    private void crearCC() {
        int id_usuario = 0;
        System.out.println("Introduce tu dni:");
        String dni = teclado.next();
        System.out.println("Introduce tu contraseña:");
        String contrasena = teclado.next();
        try {
            id_usuario = usuarioDAO.verId(dni, contrasena);
            //Mostramos ID_USUARIO
            System.out.println("Tu id de usuario es: " + id_usuario);
            System.out.println("Bienvenido");

            System.out.println("Introduce el saldo inicial: ");
            float saldo = teclado.nextFloat();
            System.out.println("Introduce el límite de descubierto que quieres permitirte: ");
            float limiteRojo = teclado.nextFloat();
            // int limiteRojo = Integer.parseInt(teclado.nextLine());
            System.out.println("Creando tu cuenta corriente con los datos aportados... ");
            // Para generar el numero de cuenta con un número aleatorio
            Random random = new Random();
            int numeroCC = (random.nextInt(999999999));

            CuentaCorriente cc = new CuentaCorriente(id_usuario, numeroCC, saldo, limiteRojo);
            try {
                ccDAO.crearCC(cc);
                System.out.println("La cuenta se ha registrado correctamente");
                System.out.println("Tu número de cuenta es: " + numeroCC);
            } catch (SQLException sqle) {
                System.out.println("Se ha producido un problema. Inténtelo de nuevo");
                sqle.printStackTrace();
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema. Inténtelo de nuevo");
            sqle.printStackTrace();
        }

    }

    /*
    Frog frog1 = new Frog(); => creates object frog1 of type Frog.
System.out.println(frog1); => outputs the String representation
of the object frog1.
     */
    private void verDatos() {
        System.out.println("Introduce tu dni:");
        String dni = teclado.next();
        System.out.println("Introduce tu contraseña:");
        String contrasena = teclado.next();
        try {
            int id_usuario = usuarioDAO.verId(dni, contrasena);
            ArrayList<Usuario> usuario1;
            usuario1 = usuarioDAO.obtenerUsuario(id_usuario);
            for (Usuario usuario : usuario1) {
                System.out.println(usuario);
            }
            ArrayList<CuentaCorriente> cc1;
            cc1 = ccDAO.obtenerCC(id_usuario);
            for (CuentaCorriente cc : cc1) {
                System.out.println(cc);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }
//Usaremos el 
//    sentencia.setInt(2, cc.getId_usuario()); para asignar el id pasado en cada operacion

    private void operar() {
        System.out.println("LOG-IN");
        System.out.println("Introduce tu DNI");
        String dni = teclado.next();
        System.out.println("Introduce tu contraseña");
        String contrasena = teclado.next();
        try {
        int id_usuario = usuarioDAO.verId(dni, contrasena);
            System.out.println("Log-In aceptado");
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }

        do {
            System.out.println("OPERACIONES");
            System.out.println("1. Ingresar dinero en cuenta corriente");
            System.out.println("2. Sacar dinero de cuenta corriente");
            System.out.println("3. Borrar cuenta corriente");
            System.out.println("4. Dar de baja usuario (Se eliminará cuenta corriente)");
            System.out.println("x. Salir");
            System.out.println("Selecciona: ");
            String menu = teclado.next();

            switch (menu.toUpperCase()) {
                case "1":
                    ingresarDinero();
                    break;
//                case "2":
//                    sacarDinero();
//                    break;
//                case "3":
//                    borrarCC();
//                    break;
//                case "4":
//                    borrarUsuario();
//                    break;
                case "X":
                    salir();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (!salir);

    }

    private void ingresarDinero() {
        float saldo = 0;
        System.out.println("Cuanto vas a ingresar: ");
            float ingreso = teclado.nextFloat();
            Util util = new Util(ingreso);
         try {
            CuentaCorriente cc = new CuentaCorriente (saldo);
//            float saldo = cc.getSaldo();
//saldo id ingreso
            saldo = cc.getSaldo();
            System.out.println("Ingreso correcto");
            System.out.println("Saldo actual: " + saldo);
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }

    }

    private void salir() {
        salir = true;
    }

}
