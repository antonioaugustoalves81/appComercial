/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.beans;

import com.app.comercial.base.Conexao;
import com.app.comercial.base.InterComercial;
import com.app.comercial.modelos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class UsuarioBean implements InterComercial{
    
    Usuario usuario = new Usuario();

    public UsuarioBean(Usuario user) {
        this.usuario = user;
    }
    
    
    @Override
    public boolean insert() {
        String sql = "insert into usuario "
                +"(email, senha, foto) values (?,?,?)";
        try{
            Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,usuario.getEmail());
             pst.setString(2,usuario.getSenha());
             pst.setString(3,usuario.getFoto());
             pst.executeUpdate();
             return true;

        }catch(SQLException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean update() {
         String sql = "update usuario set "
                 +"email = ?, senha = ?, foto = ? "
                 +"where id = ?";
         
          try{
            Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1,usuario.getEmail());
             pst.setString(2,usuario.getSenha());
             pst.setString(3,usuario.getFoto());
             pst.setInt(4,usuario.getId());
             pst.executeUpdate();
             return true;

        }catch(SQLException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean delete() {
         String sql = "delete from usuario where id = ?";
          try{
             Connection con = Conexao.getConexao();
             PreparedStatement pst = con.prepareStatement(sql);
             pst.setInt(1,usuario.getId());
             pst.executeUpdate();
             return true;
         }catch(SQLException e){
             e.getMessage();
             return false;
         }
    }

    @Override
    public boolean findById() {
        String sql = "select email, senha, foto "
                +"from usuario where id = "+usuario.getId();
        
         try{
            Connection con = Conexao.getConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            
            if(rs.getRow() > 0){
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setFoto(rs.getString("foto"));
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
