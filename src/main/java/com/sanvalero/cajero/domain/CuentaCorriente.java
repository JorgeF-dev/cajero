package com.sanvalero.cajero.domain;
/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class CuentaCorriente {
    
    private int numeroCC;
    private double saldo;
    private int limiteRojo;
    
//    private Gestor gestor;
//    private Usuario usuario;

    public CuentaCorriente(int numeroCC, double saldo, int limiteRojo) {
        this.numeroCC = numeroCC;
        this.saldo = saldo;
        this.limiteRojo = limiteRojo;
    }

    
    public void ingresarDinero(double ingreso) {
        saldo = saldo + ingreso;
    }
    
    public void sacarDinero(double sacarDinero) {
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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getLimiteRojo() {
        return limiteRojo;
    }

    public void setLimiteRojo(int limiteRojo) {
        this.limiteRojo = limiteRojo;
    }
    
}
