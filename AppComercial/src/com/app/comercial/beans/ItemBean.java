/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ANTONIO
 */
public class ItemBean implements InterComercial{
    
    Item item = new Item();

    public ItemBean(Item item) {
        this.item = item;
    }
    
    

    @Override
    public boolean insert() {
        //To change body of generated methods, choose Tools | Templates.
        String sql = "insert into movimento "
                +"(movimento_id, produto_id, quantidade, preco)";
        
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, item.getMovimentoId());
            pst.setInt(2, item.getProdutoId());
            pst.setDouble(3,item.getQuantidade());
            pst.setDouble(4,item.getPreco());
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update() {
         //To change body of generated methods, choose Tools | Templates.
         return false;
    }

    @Override
    public boolean delete() {
       //To change body of generated methods, choose Tools | Templates.
       return false;
    }

    @Override
    public boolean findById() {
        //To change body of generated methods, choose Tools | Templates.
        return false;
    }
    
}
