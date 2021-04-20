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
     * @param user
     * @throws SQLException 
     */
    public void registroUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIO (NOMBRE, APELLIDOS, DNI, EMAIL, TELEFONO, CONTRASENA) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement query = connection.getConexion().prepareStatement(sql);

        query.setString(1, usuario.getNombre());
        query.setString(2, usuario.getApellidos());
        query.setString(3, usuario.getDni());
        query.setString(4, usuario.getEmail());
        query.setInt(5, usuario.getTelefono());
        query.setString(6, usuario.getContrasena());
        query.executeUpdate();
    }
    //TODO para juntar usuario con cuenta
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
        String sql = "DELETE FROM USUARIO WHERE DNI = ?";
       
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, matricula);
        sentencia.executeUpdate();
    }
}


