/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.catalogo.facade;

import com.py.catalogo.connection.Conexion;
import com.py.catalogo.entity.Acorde;
import com.py.catalogo.entity.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Facade {

    public static List<Producto> productos(String nombre, String acordes) {
        String selectSQL = "select a.artcodigo,a.artnom,a.artdescl,a.artprevent, a.artimagen from articulos a "
                + "inner join articulos2 b "
                + "on (a.artcodigo = b.artcodigo) "
                + "inner join acordes c "
                + "on (b.codacorde = c.codacorde) where a.artcodigo <> ''  ";
        if (!nombre.isEmpty()) {
            selectSQL += " and UPPER(a.artnom) LIKE '%" + nombre.toUpperCase() + "%'";
        }
        if (!acordes.isEmpty()) {
            selectSQL += " and b.codacorde in(" + acordes + ")";
        }
        selectSQL += " group by a.artcodigo,a.artnom,a.artdescl,a.artprevent, a.artimagen order by a.artcodigo desc";
        System.out.println(selectSQL);
        List<Producto> resultado = new ArrayList<Producto>();
        Connection con = null;
        try {
            con = Conexion.connect();
            ResultSet rs = null;
            PreparedStatement ps = con.prepareStatement(selectSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto data = new Producto();
                data.setIdProducto(Integer.parseInt(rs.getString("artcodigo").trim()));
                data.setNombre(rs.getString("artnom").trim());
                data.setDescripcion(rs.getString("artdescl").trim());
                data.setPrecio(rs.getInt("artprevent"));
                resultado.add(data);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return resultado;
    }

    public static Producto productoWithAcorde(int id) {
        Producto resultado = null;
        Connection con = null;
        try {
            con = Conexion.connect();
            ResultSet rs = null;
            String selectSQL = "select a.artcodigo,a.artnom,a.artdescl,a.artprevent,b.codacorde,c.acordesc, a.artimagen from articulos a "
                    + "inner join articulos2 b "
                    + "on (a.artcodigo = b.artcodigo) "
                    + "inner join acordes c "
                    + "on (b.codacorde = c.codacorde) "
                    + "where CAST(a.artcodigo AS DECIMAL ) = " + id + " "
                    + "order by a.artcodigo desc";
            PreparedStatement ps = con.prepareStatement(selectSQL);
            System.out.println(selectSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (resultado == null) {
                    resultado = new Producto();
                    resultado.setIdProducto(Integer.parseInt(rs.getString("artcodigo").trim()));
                    resultado.setNombre(rs.getString("artnom").trim());
                    resultado.setDescripcion(rs.getString("artdescl").trim());
                    resultado.setPrecio(rs.getInt("artprevent"));
                    Acorde ac = new Acorde(rs.getInt("codacorde"), rs.getString("acordesc").trim());
                    resultado.getAcordes().add(ac);
                } else {
                    Acorde ac = new Acorde(rs.getInt("codacorde"), rs.getString("acordesc"));
                    resultado.getAcordes().add(ac);
                }
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return resultado;
    }

    public static List<Acorde> acordes() {
        List<Acorde> resultado = new ArrayList<Acorde>();
        Connection con = null;
        try {
            con = Conexion.connect();
            ResultSet rs = null;
            String selectSQL = "select * from acordes";
            System.out.println(selectSQL);
            PreparedStatement ps = con.prepareStatement(selectSQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Acorde data = new Acorde();
                data.setIdAcorde(rs.getInt("codacorde"));
                data.setNombre(rs.getString("acordesc"));
                resultado.add(data);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return resultado;
    }
}
