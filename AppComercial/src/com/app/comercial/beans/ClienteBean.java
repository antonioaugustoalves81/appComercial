/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class ClienteBean implements InterComercial{
    
    Cliente cliente  = new Cliente();

    public ClienteBean(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    @Override
    public boolean insert() {
         String sql = "insert into cliente "
                 +"(nome, email, telefone, cidade_id) "
                 +"values(?,?,?,?)";
         try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,cliente.getNome());
            pst.setString(2,cliente.getEmail());
            pst.setString(3,cliente.getTelefone());
            pst.setInt(4,cliente.getCidadeId());
            pst.executeUpdate();
            return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean update() {
         //To change body of generated methods, choose Tools | Templates.
         String sql = "update cliente set "
                 +"nome = ?, email = ?, "
                 +"telefone = ?, cidade_id = ? "
                 +"where id = ?";
         
         try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,cliente.getNome());
            pst.setString(2,cliente.getEmail());
            pst.setString(3,cliente.getTelefone());
            pst.setInt(4,cliente.getCidadeId());
            pst.setInt(5,cliente.getId());
            pst.executeUpdate();
            return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean delete() {
        String sql = "delete from cliente where id=?";
        
        try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,cliente.getId());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean findById() {
         String sql = "select nome, email, telefone, cidade_id "
                 +"from cliente where id = "+cliente.getId();
         
          try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCidadeId(rs.getInt("cidade_id"));
                return true;
            }else{
                System.out.println("Nenhum registro encontrado");
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            e.getMessage();
            return false;
        }
    }
    
}
