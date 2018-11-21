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
public class Cidade {
    private int id;
    private String nome;
    private String estado;
    private int cep;

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }
    
    public Cidade(){
        
    }
    
    public Cidade(int id, String nome, String estado){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }
    
    
    @Override
    public String toString(){
        return this.id+" - "+ this.nome+" - "+ this.estado;
    }
    
    
     public static void carregaCombo(JComboBox combo, int codigo) {
        String sql = "SELECT id, nome, estado FROM cidade ORDER BY nome";

        try {
            Connection con = Conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //config. a combo
            combo.removeAllItems();
            combo.addItem("Selecione uma cidade");
            Cidade cidade = null;

            while (rs.next()) {
                Cidade cid = new Cidade(rs.getInt("id"), rs.getString("nome"), rs.getString("estado"));

                if (codigo == rs.getInt("id")) {
                    cidade = cid;
                }
                combo.addItem(cid);

            }
            if (cidade != null) {
                combo.setSelectedItem(cidade);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar as cidades. "
                    + "\n" + ex.getMessage() + "\n" + ex.getCause());
        }

    }
    
}
