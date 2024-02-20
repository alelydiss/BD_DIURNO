/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
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
public class Principal {

    public static void main(String[] args) {
        
        final String USER = "root";
        final String PASS = "123qweASD_";
        final String nameDB = "northwind";
        final String URL = "jdbc:mysql://localhost:3306/";
        
        BD bd = new BD(nameDB, USER, PASS);
        
        bd.conecta();
        bd.desconecta();
        
    }
}
