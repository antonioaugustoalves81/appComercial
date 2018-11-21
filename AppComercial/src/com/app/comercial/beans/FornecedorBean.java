/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class FornecedorBean implements InterComercial{
    
    Fornecedor fornecedor  = new Fornecedor();

    public FornecedorBean(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
    @Override
    public boolean insert() {
         String sql = "insert into fornecedor "
                 +"(nome, email, telefone, cidade_id) "
                 +"values(?,?,?,?)";
         try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,fornecedor.getNome());
            pst.setString(2,fornecedor.getEmail());
            pst.setString(3,fornecedor.getTelefone());
            pst.setInt(4,fornecedor.getCidadeId());
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
         String sql = "update fornecedor set "
                 +"nome = ?, email = ?, "
                 +"telefone = ?, cidade_id = ? "
                 +"where id = ?";
         
         try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,fornecedor.getNome());
            pst.setString(2,fornecedor.getEmail());
            pst.setString(3,fornecedor.getTelefone());
            pst.setInt(4,fornecedor.getCidadeId());
            pst.setInt(5,fornecedor.getId());
            pst.executeUpdate();
            return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean delete() {
        String sql = "delete from fornecedor where id=?";
        
        try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,fornecedor.getId());
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
                 +"from fornecedor where id = ?"+fornecedor.getId();
         
          try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setCidadeId(rs.getInt("cidade_id"));
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
