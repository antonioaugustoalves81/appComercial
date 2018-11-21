/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.modelos;

import com.app.comercial.base.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTONIO
 */
public class Categoria {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Categoria(){
        
    }
    
    public Categoria(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.id+" - "+ this.nome;
    }
    
    public static void carregaCombo(JComboBox combo, int codigo) {
        String sql = "SELECT id, nome FROM categoria ORDER BY nome";

        try {
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //config. a combo
            combo.removeAllItems();
            combo.addItem("Selecione uma categoria");
            Categoria categoria = null;

            while (rs.next()) {
                Categoria cat = new Categoria(rs.getInt("id"), rs.getString("nome"));

                if (codigo == rs.getInt("id")) {
                    categoria = cat;
                }
                combo.addItem(cat);

            }
            if (categoria != null) {
                combo.setSelectedItem(categoria);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar as categorias. "
                    + "\n" + ex.getMessage() + "\n" + ex.getCause());
        }

    }

}
