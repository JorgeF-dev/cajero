package com.sanvalero.cajero.dao;

import com.sanvalero.cajero.Cajero;
import com.sanvalero.cajero.domain.CuentaCorriente;
import com.sanvalero.cajero.dao.UsuarioDAO;
import com.sanvalero.cajero.domain.Util;
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
    private UsuarioDAO usuarioDAO;

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

    public ArrayList<CuentaCorriente> obtenerCC(int id_usuario) throws SQLException {
        String sql = "SELECT numeroCC, saldo, limiteRojo FROM cuentacorriente WHERE id_usuario = ?";

        PreparedStatement sentencia;
        sentencia = connection.getConexion().prepareStatement(sql);
        ArrayList<CuentaCorriente> cc1 = new ArrayList<>();
        sentencia.setInt(1, id_usuario);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            CuentaCorriente cc = new CuentaCorriente();
            cc.setNumeroCC(resultado.getInt(1));
            cc.setSaldo(resultado.getFloat(2));
            cc.setLimiteRojo(resultado.getFloat(3));
            cc1.add(cc);
        }
        return cc1;
    }
    //Para update executeUpdate(); 
    public void ingresarDinero(CuentaCorriente cc, Util util) throws SQLException {
        String sql = "UPDATE cuentacorriente SET saldo = ?  WHERE id_usuario = ?";
        int id_usuario = usuarioDAO.verId(sql, sql);
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        
        float saldo = cc.getSaldo() + util.getIngreso();
        sentencia.setInt(2, id_usuario);
        sentencia.setFloat(1, saldo);
        sentencia.executeUpdate();
    }
        
}
