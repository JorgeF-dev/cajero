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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cajero {

    private boolean salir;
    private final Scanner teclado;
    private final Conexion connection;
    private final UsuarioDAO usuarioDAO;
    private final CuentaCorrienteDAO ccDAO;

    public Cajero() {
        salir = false;
        teclado = new Scanner(System.in);
        connection = new Conexion();
        connection.connect();
        ccDAO = new CuentaCorrienteDAO(connection);
        usuarioDAO = new UsuarioDAO(connection);
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
            String menu = teclado.nextLine();

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

    private void salir() {
        salir = true;
    }

    private void registroUsuario() {
        System.out.println("Introduce tu nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Introduce tus apellidos: ");
        String apellidos = teclado.nextLine();
        System.out.println("Introduce tu contrasena:");
        String contrasena = teclado.nextLine();
        System.out.println("Introduce tu dni:");
        String dni = teclado.nextLine();
        System.out.println("Introduce tu email:");
        String email = teclado.nextLine();
        System.out.println("Introduce tu número de teléfono:");
        int telefono = Integer.parseInt(teclado.nextLine());
        System.out.println("Creando usuario...");
        Usuario usuario = new Usuario(nombre, apellidos, dni, email, telefono, contrasena);
        /*
        Usuario usuario = new Usuario();
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
        System.out.println("Introduce tu dni:");
        String dni = teclado.nextLine();
        System.out.println("Introduce tu contraseña:");
        String contrasena = teclado.nextLine();
        try {
            int id_usuario = usuarioDAO.verId(dni, contrasena);
            //Mostramos ID_USUARIO
            System.out.println("Tu id de usuario es: " + id_usuario);
            System.out.println("Bienvenido");

            System.out.println("Introduce el saldo inicial: ");
            float saldo = Float.parseFloat(teclado.nextLine());
            System.out.println("Introduce el límite de descubierto que quieres permitirte: ");
            float limiteRojo = Float.parseFloat(teclado.nextLine());
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
        String dni = teclado.nextLine();
        System.out.println("Introduce tu contraseña:");
        String contrasena = teclado.nextLine();
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

// Para usar el mismo id en todas las operaciones.
// sentencia.setInt(2, cc.getId_usuario());
    private void operar() {
        System.out.println("LOG-IN");
        System.out.println("Introduce tu DNI");
        String dni = teclado.nextLine();
        System.out.println("Introduce tu contraseña");
        String contrasena = teclado.nextLine();
        try {
            int id_usuario = usuarioDAO.verId(dni, contrasena);

            do {
                System.out.println("Log-In aceptado: ID USUARIO: " + id_usuario);
                System.out.println("OPERACIONES");
                System.out.println("1. Ingresar dinero en cuenta corriente");
                System.out.println("2. Sacar dinero de cuenta corriente");
                System.out.println("3. Borrar cuenta corriente");
                System.out.println("4. Dar de baja usuario (Se eliminará cuenta corriente)");
                System.out.println("x. Salir");
                System.out.println("Selecciona: ");
                String menu = teclado.nextLine();

                switch (menu.toUpperCase()) {
                    case "1":
                        ingresarDinero:
                        {
                            float saldo = ccDAO.verSaldo(id_usuario);
                            System.out.println("Saldo actual: " + saldo);
                            System.out.println("Cuanto vas a ingresar: ");
                            float ingreso = Float.parseFloat(teclado.nextLine());
                            try {
                                float nuevosaldo = ingreso + saldo;
                                CuentaCorriente cc = new CuentaCorriente(id_usuario, nuevosaldo);
                                ccDAO.meteSaca(cc);
                                System.out.println("Tu saldo actual es: " + nuevosaldo);
                            } catch (SQLException sqle) {
                                System.out.println("Se ha producido un problema leyendo los datos");
                                sqle.printStackTrace();
                            }
                        }
                        break;
                    case "2":
                        sacarDinero:
                        {
                            float saldo = ccDAO.verSaldo(id_usuario);
                            System.out.println("Saldo actual: " + saldo);
                            System.out.println("Cuanto vas a sacar: ");
                            float sacar = Float.parseFloat(teclado.nextLine());
                            try {
                                float nuevosaldo = saldo - sacar;
                                CuentaCorriente cc = new CuentaCorriente(id_usuario, nuevosaldo);
                                ccDAO.meteSaca(cc);
                                System.out.println("Tu saldo actual es: " + nuevosaldo);
                            } catch (SQLException sqle) {
                                System.out.println("Se ha producido un problema leyendo los datos");
                                sqle.printStackTrace();
                            }
                        }
                        break;
                    case "3":
                        borrarCC:
                        {
                            try {
                                ccDAO.borrarCC(id_usuario);
                                System.out.println("Cuenta borrada");
                            } catch (SQLException sqle) {
                                System.out.println("Se ha producido un problema leyendo los datos");
                                sqle.printStackTrace();
                            }
                        }
                        break;
                case "4":
                    borrarUsuario:
                        {
                            try {
                                usuarioDAO.eliminarUsuario(id_usuario);
                                System.out.println("Usuario borrado");
                            } catch (SQLException sqle) {
                                System.out.println("Se ha producido un problema leyendo los datos");
                                sqle.printStackTrace();
                            }
                        }
                    break;
                    case "X":
                        salir2();
                        break;
                    default:
                        System.out.println("Opción incorrecta");
                }
            } while (!salir);
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }

    private void salir2() {
        salir = true;
    }

}
