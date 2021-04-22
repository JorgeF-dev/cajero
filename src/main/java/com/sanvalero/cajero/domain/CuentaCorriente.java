package com.sanvalero.cajero.domain;
/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class CuentaCorriente {
    
    private int numeroCC;
    private float saldo;
    private float limiteRojo;
    private int id_usuario;
    
//    private Gestor gestor;
//    private Usuario usuario;


    public CuentaCorriente(int id_usuario, int numeroCC, float saldo, float limiteRojo) {
        this.id_usuario =id_usuario;
        this.numeroCC = numeroCC;
        this.saldo = saldo;
        this.limiteRojo = limiteRojo;
    }

    public CuentaCorriente() {
    }
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    
    public void ingresarDinero(float ingreso) {
        saldo = saldo + ingreso;
    }
    
    public void sacarDinero(float sacarDinero) {
        if(saldo - sacarDinero >= limiteRojo) {
        saldo = saldo - sacarDinero;
            System.out.println("Has sacado el dinero. Te quedan " + saldo + "euros.");    
        } else {
            System.out.println("Excede el límite de descubierto");
        }
    }
    
    public int getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(int numeroCC) {
        this.numeroCC = numeroCC;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimiteRojo() {
        return limiteRojo;
    }

    public void setLimiteRojo(float limiteRojo) {
        this.limiteRojo = limiteRojo;
    }

    @Override
    public String toString() {
        return "CuentaCorriente{" + " numeroCC=" + numeroCC + ",saldo=" + saldo + ", limiteRojo=" + limiteRojo + '}';
    }
    
}
