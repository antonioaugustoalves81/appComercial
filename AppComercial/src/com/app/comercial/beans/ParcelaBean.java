/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Parcela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class ParcelaBean implements InterComercial{
    
    Parcela parcela = new Parcela();

    public ParcelaBean(Parcela parc) {
        this.parcela = parc;
    }
     

    @Override
    public boolean insert() {
       String sql = "insert into parcela("
               +"documento, fornecedor_id, cliente_id, "
               +"movimento_id, data_vencimento, valor, "
               +"tipo_parcela) values (?,?,?,?,?,?,?)";
       
       try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,parcela.getDocumento());
            pst.setInt(2,parcela.getFornecedorId());
            pst.setInt(3,parcela.getClienteId());
            pst.setInt(4,parcela.getMovimentoId());
            pst.setDate(5,parcela.getVencimento());
            pst.setDouble(6,parcela.getValor());
            pst.setString(7,parcela.getTipoParcela());
            pst.executeUpdate();
            return true;
       }catch(SQLException e){
           e.getMessage();
           return false;
       }
    }

    @Override
    public boolean update() {
        String sql = "update parcela set "
                +"documento = ?, fornecedor_id = ?, "
                +"cliente_id = ?, movimento_id = ?, "
                +"data_vencimento = ?, valor = ?, "
                + "tipo_parcela = ? "
                +"where id = ?";
        
        try{
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,parcela.getDocumento());
            pst.setInt(2,parcela.getFornecedorId());
            pst.setInt(3,parcela.getClienteId());
            pst.setInt(4,parcela.getMovimentoId());
            pst.setDate(5,parcela.getVencimento());
            pst.setDouble(6,parcela.getValor());
            pst.setString(7,parcela.getTipoParcela());
            pst.setInt(8,parcela.getId());
            pst.executeUpdate();
            return true;
       }catch(SQLException e){
           e.getMessage();
           return false;
       }
        
    }

    @Override
    public boolean delete() {
        //To change body of generated methods, choose Tools | Templates.
        String sql = "delete from parcela where id = ?";
        
         try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,parcela.getId());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
        
    }

    @Override
    public boolean findById() {
        String sql = "select documento, fornecedor_id, cliente_id, "
                +"movimento_id, data_vencimento, valor, tipo_parcela "
                +"from parcela where id = ?";
        
        try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                parcela.setDocumento(rs.getString("documento"));
                parcela.setFornecedorId(rs.getInt("fornecedor_id"));
                parcela.setClienteId(rs.getInt("cliente_id"));
                parcela.setMovimentoId(rs.getInt("movimento_id"));
                parcela.setVencimento(rs.getDate("data_vencimento"));
                parcela.setValor(rs.getDouble("valor"));
                parcela.setTipoParcela(rs.getString("tipo_valor"));
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
