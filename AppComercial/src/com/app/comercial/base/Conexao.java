/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ANTONIO
 */
public class Conexao {
    public static Connection getConexao() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/appcomercial", //endereço e nome do banco
                    "root", // usuário do banco
                    "r#5vj@t6%"); // senha do banco
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
        
    }
    
    public static void main(String[] args) {
        try {
            Connection con = Conexao.getConexao();
            System.out.println("Conectou");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
