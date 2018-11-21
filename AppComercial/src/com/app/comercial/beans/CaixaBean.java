/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Caixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class CaixaBean implements InterComercial{
    
    private Caixa caixa = new Caixa();
    
    public CaixaBean(Caixa caixa){
        this.caixa = caixa;
    }

    @Override
    public boolean insert() {
         
         String sql = "insert into caixa "
                 +"(cliente_id, fornecedor_id, tipo, data_caixa, movimento_id, valor) "
                 +"values (?,?,?,?,?,?)";
         try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,caixa.getClienteId());
             pst.setInt(2,caixa.getFornecedorId());
             pst.setString(3,caixa.getTipo());
             pst.setDate(4,caixa.getDataCaixa());
             pst.setInt(5,caixa.getMovimentoId());
             pst.setDouble(6,caixa.getValor());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean update() {
         String sql = "update caixa set "
                 +"cliente_id = ?, fornecedor_id = ?, "
                 +"tipo = ?, data_caixa = ?, "
                 +"movimento_id = ?, valor = ? "
                 +"Where id = ? ";
               
         try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,caixa.getClienteId());
             pst.setInt(2,caixa.getFornecedorId());
             pst.setString(3,caixa.getTipo());
             pst.setDate(4,caixa.getDataCaixa());
             pst.setInt(5,caixa.getMovimentoId());
             pst.setDouble(6,caixa.getValor());
             pst.setInt(7,caixa.getId());
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
         String sql = "DELETE FROM caixa WHERE id = ?";
         
          try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,caixa.getId());
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
        String sql = "select cliente_id, fornecedor_id, tipo, "
                +"data_caixa, movimento_id, valor "
                +"from caixa where id = "+caixa.getId(); 
        
        try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                caixa.setClienteId(rs.getInt("cliente_id"));
                caixa.setFornecedorId(rs.getInt("fornecedor_id"));
                caixa.setTipo(rs.getString("tipo"));
                caixa.setDataCaixa(rs.getDate("data_caixa"));
                caixa.setMovimentoId(rs.getInt("movimento_id"));
                caixa.setValor(rs.getDouble("valor"));
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
