package com.sanvalero.cajero.dao;

import com.sanvalero.cajero.domain.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class UsuarioDAO {

    private Conexion connection;

    public UsuarioDAO(Conexion connection) {
        this.connection = connection;
    }

    /**
     * Registra nuevo usuario y lo añade a la bbdd.
     *
     * @param usuario
     * @throws SQLException
     */
    public void registroUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (NOMBRE, APELLIDOS, DNI, EMAIL, TELEFONO, CONTRASENA) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement query = connection.getConexion().prepareStatement(sql);

        query.setString(1, usuario.getNombre());
        query.setString(2, usuario.getApellidos());
        query.setString(3, usuario.getDni());
        query.setString(4, usuario.getEmail());
        query.setInt(5, usuario.getTelefono());
        query.setString(6, usuario.getContrasena());
        query.executeUpdate();
    }
    //Para envíar una sentencia SQL del tipo Consulta se utliza el método executeQuery(sql);
    //Para update executeUpdate(); 

    /*
     * Determina el id_usuario pidiendo al usuario su nombre y apellidos
     */
    public void verId(String dni,String contrasena) throws SQLException {
        String sql = "SELECT id_usuario FROM USUARIO WHERE dni = ? AND  contrasena = ?)";
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, dni);
        sentencia.setString(2, contrasena);
        ResultSet resultado = sentencia.executeQuery(sql);
        resultado.getInt("id_usuario");
    }
        
  
        //    public Usuario showId(String nombre, String apellidos) throws SQLException {
        //        String sql = "SELECT id_usuario FROM USUARIO WHERE nombre = ? AND  apellidos = ?)";
        //        
        //        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        //        sentencia.setString(1, nombre);
        //        sentencia.setString(2, apellidos);
        //        ResultSet resultado = sentencia.executeQuery(sql);  //executeQuery
        //        resultado.getInt("id_usuario");
        //        return getString(resultado.parseInt.toInt());
        ////        while (resultado.next()) {
        ////        int id_usuario = resultado.getInt("id_usuario");
        ////        }
        //        
        //        //return Usuario.sentencia.getInt("id_usuario");
        //        //return showId(nombre, apellidos);
        //    }
        
        
    public ArrayList<Usuario> obtenerUsuario(String cadenaBusqueda) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE DNI = ?";
        ArrayList<Usuario> usuario1 = new ArrayList<>();

        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Usuario usuario = new Usuario();
            usuario.setDni(resultado.getString(3));

            usuario1.add(usuario);
        }
        return usuario1;
    }
//TODO

    public void eliminarUsuario(String dni, String contrasena) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE DNI = ? AND  contrasena = ?)";

        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, dni);
        sentencia.setString(2, contrasena);
        sentencia.executeUpdate();
    }
}
