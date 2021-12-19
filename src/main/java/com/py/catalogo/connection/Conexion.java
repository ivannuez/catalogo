/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.catalogo.connection;

import com.py.catalogo.services.PropertiesServices;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Usuario
 */
public class Conexion {
    
    /**
     * Obtiene una conexion del DataSource.
     */
    public static java.sql.Connection connect() {
        Properties prop = PropertiesServices.getInstance().getProperties();
        java.sql.Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.user"),prop.getProperty("db.pass"));
            conn.setAutoCommit(true);
        } catch (SQLException Se) {
            System.out.println("Error de SQL. " + Se);
            Se.printStackTrace();
        } catch (Exception E) {
            System.out.println("Exception." + E.getMessage());
            E.printStackTrace();
        }
        return conn;
    }

}
