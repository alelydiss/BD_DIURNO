/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.diurno.programacionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void consultaPrueba() {
        String company;
        String apellidos;
        String nombre;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select company, last_name, first_name from customers limit 10");
            while (resultado.next()) {
                company = resultado.getString("company");
                apellidos = resultado.getString("last_name");
                nombre = resultado.getString("first_name");

                System.out.println("Company " + company + " Apellidos " + apellidos + " Nombre " + nombre);

            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*Crea el método consultaPrueba2 en la que aparezcan los nombres de los clientes 
    concatenados apellidos-nombre con los pedidos realizados (2 campos)*/
    public void consultaPrueba2() {
        String nombre;
        String numeroPedido;

        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT DISTINCT CONCAT(c.last_name, '-', c.first_name) AS nombre,o.id AS numeroPedido\n"
                    + "FROM customers c\n"
                    + "JOIN orders o ON c.id = o.customer_id limit 10");
            while (resultado.next()) {
                nombre = resultado.getString("nombre");
                numeroPedido = resultado.getString("NumeroPedido");

                System.out.println("Nombre " + nombre + " -- Numero de pedido: " + numeroPedido);

            }
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] describe(String nombre) {

        String[] columnas = null;
        int n_columnas = 0;
        int i = 0;

        try {

            Statement statement = conexion.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT * FROM " + nombre);
            ResultSetMetaData metadatos = resultset.getMetaData();
            n_columnas = metadatos.getColumnCount();
            columnas = new String[n_columnas];

            for (i = 1; i <= n_columnas; i++) {

                columnas[i - 1] = metadatos.getColumnName(i);

            }

        } catch (SQLException ex) {

            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);

        }

        return columnas;

    }
}
