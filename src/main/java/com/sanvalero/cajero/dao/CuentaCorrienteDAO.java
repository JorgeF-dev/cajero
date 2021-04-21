package com.sanvalero.cajero.dao;

import com.sanvalero.cajero.domain.CuentaCorriente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class CuentaCorrienteDAO {
    
    private Conexion connection;    
    public CuentaCorrienteDAO(Conexion connection) {
        this.connection = connection;
    }
    
       
    public void crearCC(CuentaCorriente cc) throws SQLException {
        String sql = "INSERT INTO CUENTACORRIENTE (numeroCC, saldo, limiteRojo, id_usuario) VALUES (?, ?, ?, ?)";
        
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setInt(1, cc.getNumeroCC());
        sentencia.setFloat(2, cc.getSaldo());
        sentencia.setFloat(3, cc.getLimiteRojo());
        sentencia.setInt(4, cc.getId_usuario());
        
        sentencia.executeUpdate();
    }
}

/*
    public void registrarCoche(Coche coche) throws SQLException {
        String sql = "INSERT INTO coches (matricula, modelo) VALUES (?, ?)";
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, coche.getMatricula());
        sentencia.setString(2, coche.getModelo());
        sentencia.executeUpdate();
    }
    
    // No se permite modificar la matricula y se usa como criterio para la busqueda
    // en la operaci�n de modificaci�n
    // TODO Falta terminarlo
    public void modificarCoche(Coche coche) throws SQLException {
        String sql = "UPDATE coches SET modelo = ? WHERE matricula = ?";
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, coche.getModelo());
        sentencia.setString(2, coche.getMatricula());
        sentencia.executeUpdate();
    }
*/
/*
    public void eliminarCoche(String matricula) throws SQLException {
        String sql = "DELETE FROM coches WHERE matricula = ?";
       
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, matricula);
        sentencia.executeUpdate();
    }
    
    public ArrayList<Coche> obtenerCoches() throws SQLException {
        String sql = "SELECT id, matricula, modelo FROM coches";
        ArrayList<Coche> coches = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Coche coche = new Coche();
            coche.setId(resultado.getInt(1));
            coche.setMatricula(resultado.getString(2));
            coche.setModelo(resultado.getString(3));
            
            coches.add(coche);
        }
        
        return coches;
    }
    
    // Sin usar en Taller
    public ArrayList<Coche> obtenerCoches(String cadenaBusqueda) throws SQLException {
        String sql = "SELECT * FROM coches WHERE modelo = ?";
        ArrayList<Coche> coches = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Coche coche = new Coche();
            coche.setId(resultado.getInt(1));
            coche.setMatricula(resultado.getString(2));
            coche.setModelo(resultado.getString(3));
            
            coches.add(coche);
        }
        
        return coches;
    }
    
    public int obtenerCantidadCoches() {
        String sql = "SELECT COUNT(*) FROM coches";
        int cantidad = 0;
        
        // TODO 
        
        return cantidad;
    }
    
    public void arreglarCoche(String matricula) {
        String sql = "UPDATE coches SET arreglado = TRUE WHERE matricula = ?";
    }

}*/
