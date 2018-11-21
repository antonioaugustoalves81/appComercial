/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class VendedorBean implements InterComercial{
    Vendedor vendedor = new Vendedor();

    public VendedorBean(Vendedor ven) {
        this.vendedor = ven;
    }
    
    
    @Override
    public boolean insert() {
       String sql = "insert into vendedor ("
               +"nome, email, telefone, salario, data_adm) "
               +"values (?,?,?,?,?)";
       try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, vendedor.getNome());
            pst.setString(2, vendedor.getEmail());
            pst.setString(3, vendedor.getTelefone());
            pst.setDouble(4, vendedor.getSalario());
            pst.setDate(5,vendedor.getDataAdmissao());
            pst.executeUpdate();
            return true;
       }catch(SQLException e){
           e.getMessage();
           return false;
       }
    }

    @Override
    public boolean update() {
        String sql = "update vendedor set "
                 +"nome = ?, email = ?, telefone = ?, "
                 +"salario = ?, data_adm = ? "
                 +"where id = ?";
        
         try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, vendedor.getNome());
            pst.setString(2, vendedor.getEmail());
            pst.setString(3, vendedor.getTelefone());
            pst.setDouble(4, vendedor.getSalario());
            pst.setDate(5,vendedor.getDataAdmissao());
            pst.setInt(6,vendedor.getId());
            pst.executeUpdate();
            return true;
       }catch(SQLException e){
           e.getMessage();
           return false;
       }
        
    }

    @Override
    public boolean delete() {
          String sql = "delete from vendedor where id = ?";
        try {
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, vendedor.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
         
    }

    @Override
    public boolean findById() {
         String sql = "select nome, email, telefone, salario, data_adm "
                +"from vendedor where id = "+vendedor.getId();
        
         try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                vendedor.setNome(rs.getString("nome"));
                vendedor.setEmail(rs.getString("email"));
                vendedor.setTelefone(rs.getString("telefone"));
                vendedor.setSalario(rs.getDouble("salario"));
                vendedor.setDataAdmissao(rs.getDate("data_adm"));
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
