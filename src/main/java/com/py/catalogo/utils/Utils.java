/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.catalogo.utils;

/**
 *
 * @author Usuario
 */
public class Utils {

    public static String listStringInSql(String d) {
        String r = "";
        String[] data = d.split(",");
        for(String di : data){
            r = r + "'"+di+"',";
        }
        r = removeLastComa(r);
        r = r.equals("''") ? "" : r;
        return r;
    }

    public static String removeLastComa(String d) {
        return d.replaceAll(",$", "");
    }

}
