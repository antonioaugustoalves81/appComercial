/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class ProdutoBean implements InterComercial{
    Produto produto = new Produto();

    public ProdutoBean(Produto prod) {
        this.produto = prod;
    }
    
    

    @Override
    public boolean insert() {
        String sql = "insert into produto "
                + "(nome,descricao,preco,quantidade, fornecedor_id, categoria_id) values (?,?,?,?,?,?)";
        try {
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setString(2, produto.getDescricao());
            pst.setDouble(3, produto.getPreco());
            pst.setDouble(4, produto.getQuantidade());
            pst.setInt(5, produto.getFornecedorId());
            pst.setInt(6, produto.getCategoriaId());
            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean update() {
        String sql = "update produto set "
                +"nome = ?, descricao = ?, "
                +"preco = ?, quantidade = ?, "
                +"fornecedor_id = ?, categoria_id = ?"
                +"where id = ?";
        
        try{
            Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,produto.getNome());
             pst.setString(2,produto.getDescricao());
             pst.setDouble(3,produto.getPreco());
             pst.setDouble(4,produto.getQuantidade());
             pst.setInt(5,produto.getFornecedorId());
             pst.setInt(6,produto.getCategoriaId());
             pst.executeUpdate();
             return true;

        }catch(SQLException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean delete() {
        String sql = "delete from produto where id = ?";
        try {
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, produto.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean findById() {
          String sql = "select email, senha, foto "
                +"from usuario where id = "+produto.getId();
        
         try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setFornecedorId(rs.getInt("fornecedor_id"));
                produto.setCategoriaId(rs.getInt("categoria_id"));
                
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
