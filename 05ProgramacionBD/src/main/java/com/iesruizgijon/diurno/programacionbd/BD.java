/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.diurno.programacionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro
 */
public class BD {

    private Connection conexion;
    String USER = "root";
    String PASS = "123qweASD_";
    String nameDB = "northwind";
    String URL = "jdbc:mysql://localhost:3306/";

    public BD(String USER, String PASS, String nameBD) {
        this.USER = USER;
        this.PASS = PASS;
        this.nameDB = nameDB;
    }

    public void conecta() {
        try {
            conexion = DriverManager.getConnection(URL + nameDB, USER, PASS);
            System.out.println("Conexion establecida");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conexion;
    }

    public void desconecta() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}