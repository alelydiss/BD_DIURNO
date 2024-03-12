/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iesruizgijon.diurno.client_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

     public boolean Conecta() {

        boolean conectado = false;
        
        try {
            conexion = DriverManager.getConnection(URL + nameDB, USER, PASS);
            conectado = true;
           
        } catch (SQLException ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conectado;
    }

    public void Desconecta() {
        try {
            if (conexion != null) {
                conexion.close();
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

    /**
     *
     */
    public void consultaPrueba2() {

        String nombre;
        int pedido;
        final String consulta
                = "select concat_ws(\"-\", last_name, first_name) as\n" + "nombre, orders.id as numeroPedido from customers\n" + "inner join orders on customers.id =\n" + "orders.customer_id order by nombre,\n" + "numeroPedido;\n";
        try {

            //ResultSet resultado = conexion.createStatement().executeQuery(consulta);
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            while (resultado.next()) {
                nombre = resultado.getString("nombre");
                pedido = resultado.getInt("numeroPedido");
                System.out.println("Nombre: " + nombre + " numero pedido: " + pedido);
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
    
    public void getDataBaseNames(){
        
        try {

                  Statement stmt = conexion.createStatement();

                //Retrieving the data

                ResultSet rs = stmt.executeQuery("Show Databases");

                System.out.println("List of databases: ");

                while(rs.next()) {

                   System.out.print(rs.getString(1));

                   System.out.println();

                }

        } catch (SQLException ex) {

            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public ArrayList<String> getQuery(String consulta){
        
        ArrayList<String> tabla = new ArrayList<>();
        String fila = new String();
        
        
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(consulta);
            ResultSetMetaData rsmd = resultado.getMetaData();
            int numeroColumnas = rsmd.getColumnCount();
            
            while (resultado.next()) {
                for (int i = 0; i <numeroColumnas;i++){
                    fila = fila + " | " + resultado.getString(i+1);
                }
               tabla.add(fila);
               fila = new String();

            }
            
            resultado.close();
            sentencia.close();

        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabla;
    }
    
    
}