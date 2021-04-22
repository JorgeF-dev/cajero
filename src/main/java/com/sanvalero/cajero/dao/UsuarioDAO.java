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

    /*
     * Registra nuevo usuario y lo añade a la bbdd.
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
    public int verId(String dni,String contrasena) throws SQLException {
        String sql = "SELECT id_usuario FROM USUARIO WHERE dni = ? AND  contrasena = ?";
        int id_usuario = 0;
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, dni);
        sentencia.setString(2, contrasena);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()){
        id_usuario = resultado.getInt("id_usuario");
        }       
        return id_usuario;
    } 
    
    public ArrayList<Usuario> obtenerUsuario(int id_usuario) throws SQLException {
        String sql = "SELECT nombre, apellidos, dni, email, telefono, contrasena FROM usuario WHERE id_usuario = ?";
//        verId(sql,sql);
        PreparedStatement sentencia;
        sentencia = connection.getConexion().prepareStatement(sql);
        ArrayList<Usuario> usuario1 = new ArrayList<>();
        sentencia.setInt(1, id_usuario);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Usuario usuario = new Usuario();
            usuario.setNombre(resultado.getString(1));
            usuario.setApellidos(resultado.getString(2));
            usuario.setDni(resultado.getString(3));
            usuario.setEmail(resultado.getString(4));
            usuario.setTelefono(resultado.getInt(5));
            usuario.setContrasena(resultado.getString(6));            
            usuario1.add(usuario);
        }return usuario1;
}            

    public void eliminarUsuario(String dni, String contrasena) throws SQLException {
        String sql = "DELETE FROM USUARIO WHERE DNI = ? AND  contrasena = ?)";

        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, dni);
        sentencia.setString(2, contrasena);
        sentencia.executeUpdate();
    }
}
