/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.iesruizgijon.diurno.programacionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        
        BD bd = new BD(USER, PASS, nameDB);
        
        bd.conecta();
        
        bd.consultaPrueba();
        System.out.println("--------------------------------------------------");
        bd.consultaPrueba2();
        System.out.println("--------------------------------------------------");
        bd.consultaPrueba3();
        
        bd.desconecta();
        
        
        
    }

}

