/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class CidadeBean implements InterComercial{
    Cidade cidade = new Cidade();

    public CidadeBean(Cidade cidade) {
        this.cidade = cidade;
    }
    
    

    @Override
    public boolean insert() {
        String sql = "insert into cidade (nome, estado, cep) "
                +"values(?,?,?)";
        
        try{
            Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1, cidade.getNome());
             pst.setString(2, cidade.getEstado());
             pst.setInt(3, cidade.getCep());
             pst.executeUpdate();
             return true;
        }catch(SQLException e){
            e.getMessage();
            return false;
        }
        
    }

    @Override
    public boolean update() {
         String sql = "update cidade set "
                +"nome = ?, estado = ?, cep = ? "
                 +"where id = ?";
        
        try{
            Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1, cidade.getNome());
             pst.setString(2, cidade.getEstado());
             pst.setInt(3, cidade.getCep());
             pst.setInt(4, cidade.getId());
             pst.executeUpdate();
             return true;
        }catch(SQLException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean delete() {
         String sql = "delete from cidade where id = ?";
         
          try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,cidade.getId());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
         
    }

    @Override
    public boolean findById() {
         String sql = "select nome, estado, cep from cidade where id = "+cidade.getId();
         
          try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                cidade.setNome(rs.getString("nome"));
                cidade.setEstado(rs.getString("estado"));
                cidade.setCep(rs.getInt("cep"));
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
