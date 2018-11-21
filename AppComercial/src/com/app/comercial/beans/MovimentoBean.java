/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Movimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class MovimentoBean implements InterComercial{

    Movimento movimento = new Movimento();
    
    public MovimentoBean(Movimento mov) {
        this.movimento = mov;
    }
    
    

    @Override
    public boolean insert() {
         String sql = "insert into movimento("
                 +"data_movimento, tipo_movimento, "
                 +"tipo_pagamento, cliente_id, fornecedor_id, "
                 +"vendedor_id, total) values(?,?,?,?,?,?,?)";
         try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1,movimento.getDataMovimento());
            pst.setString(2,movimento.getTipoMovimento());
            pst.setString(3,movimento.getTipoPagamento());
            pst.setInt(4,movimento.getClienteId());
            pst.setInt(5,movimento.getFornecedorId());
            pst.setInt(6,movimento.getVendedorId());
            pst.setDouble(7,movimento.getTotal());
            pst.executeUpdate();
            return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
                 
    }

    @Override
    public boolean update() {
        String sql = "update movimento set "
                +"data_movimento = ?, "
                +"tipo_movimento = ?, "
                +"tipo_pagamento = ?, "
                +"cliente_id = ?, "
                +"fornecedor_id = ?, "
                +"vendedor_id = ?, "
                +"total = ? "
                +"where id = ?";
        
         try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1,movimento.getDataMovimento());
            pst.setString(2,movimento.getTipoMovimento());
            pst.setString(3,movimento.getTipoPagamento());
            pst.setInt(4,movimento.getClienteId());
            pst.setInt(5,movimento.getFornecedorId());
            pst.setInt(6,movimento.getVendedorId());
            pst.setDouble(7,movimento.getTotal());
            pst.setInt(8,movimento.getId());
            pst.executeUpdate();
            return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean delete() {
         String sql = "delete from movimento where id = ?";
         
          try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,movimento.getId());
            pst.executeUpdate();
            return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean findById() {
         String sql = "select data_movimento, "
                 +"tipo_movimento, tipo_pagamento, "
                 +"cliente_id, fornecedor_id, "
                 +"vendedor_id, total from movimento "
                 +"where id = "+movimento.getId();
         
         try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                movimento.setDataMovimento(rs.getDate("data_movimento"));
                movimento.setTipoMovimento(rs.getString("tipo_movimento"));
                movimento.setTipoPagamento(rs.getString("tipo_pagamento"));
                movimento.setClienteId(rs.getInt("cliente_id"));
                movimento.setFornecedorId(rs.getInt("fornecedor_id"));
                movimento.setVendedorId(rs.getInt("vendedor_id"));
                movimento.setTotal(rs.getDouble("total"));
                return true;
            }else{
                System.out.println("Nenhum registro encontrado");
                return false;
            }    
         }catch(SQLException e){
             e.printStackTrace();
             return false;
         }
    }
    
}
