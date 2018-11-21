/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class CategoriaBean implements InterComercial{
    Categoria categoria = new Categoria();

    public CategoriaBean(Categoria categoria) {
        this.categoria = categoria;
    }
    
    

    @Override
    public boolean insert() {
         
         String sql = "insert into categoria (nome) values(?)";
         
         try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,categoria.getNome());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean update() {
       String sql = "update categoria set nome = ? "
               +"where id = ?";
       
        try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,categoria.getNome());
             pst.setInt(2,categoria.getId());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.printStackTrace();
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean delete() {
      String sql = "delete from categoria where id = ?";
      
       try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,categoria.getId());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
        
    }

    @Override
    public boolean findById() {
         //To change body of generated methods, choose Tools | Templates.
         String sql = "select nome from categoria where id = "+categoria.getId();
         
          try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                categoria.setNome(rs.getString("nome"));
                return true;
            }else{
                System.out.println("Nenhum registro encontrado");
                return false;
            }
        }catch(SQLException e){
            e.getMessage();
            return false;
        }
    }
    
}
