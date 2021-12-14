/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.catalogo.entity;

/**
 *
 * @author Usuario
 */
public class Acorde {
    private int idAcorde;
    private String nombre;

    public Acorde() {
    }

    public Acorde(int idAcorde, String nombre) {
        this.idAcorde = idAcorde;
        this.nombre = nombre;
    }

    /**
     * @return the idAcorde
     */
    public int getIdAcorde() {
        return idAcorde;
    }

    /**
     * @param idAcorde the idAcorde to set
     */
    public void setIdAcorde(int idAcorde) {
        this.idAcorde = idAcorde;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
