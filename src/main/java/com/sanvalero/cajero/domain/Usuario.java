package com.sanvalero.cajero.domain;
/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class Usuario {
    
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private int telefono;
    private String contrasena;   

    public Usuario(String nombre, String apellidos, String dni, String email, int telefono, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }
    
    public Usuario () {
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", email=" + email + ", telefono=" + telefono + ", contraseña=" + contrasena + '}';
    }
    

}
