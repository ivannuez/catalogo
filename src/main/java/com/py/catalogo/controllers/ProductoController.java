/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.catalogo.controllers;

import com.google.gson.Gson;
import com.py.catalogo.entity.Producto;
import com.py.catalogo.facade.Facade;
import com.py.catalogo.utils.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/productoController"})
public class ProductoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String operacion = request.getParameter("op");
        try (PrintWriter out = response.getWriter()) {
            Gson json = new Gson();
            switch (operacion) {
                case "all": {
                    List<Producto> list = Facade.productos("", "","");
                    out.println(json.toJson(list));
                    break;
                }
                case "search": {
                    String nombre = request.getParameter("nombre");
                    String acordes = request.getParameter("acordes");
                    String sexo = request.getParameter("sexo");
                    if (nombre != null || acordes != null || sexo != null) {
                        if (nombre == null) {
                            nombre = "";
                        }
                        if (acordes == null) {
                          acordes = "";
                        }
                        if (sexo == null) {
                          sexo = "";
                        }
                    }
                    List<Producto> list = Facade.productos(nombre, Utils.removeLastComa(acordes),Utils.listStringInSql(sexo));
                    out.println(json.toJson(list));
                    break;
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
