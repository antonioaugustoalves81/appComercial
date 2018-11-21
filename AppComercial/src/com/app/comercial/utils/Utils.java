/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANTONIO
 */
public class Utils {
     public static Date strToDate(String aData){
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Date rdata = null;
        try {
            rdata = new Date(fmt.parse(aData).getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, aData + "não é uma data válida");
        }
         return rdata;
    }
    
    public static String dateToStr(Date aData){
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(aData);
    }
    
    public static void limpaTabela(DefaultTableModel dtm){
    //retorna quantidade de registros
        int q = dtm.getRowCount();
        for (int x = 1; x <= q; x++){
            dtm.removeRow(0);
  
             //valor zero para remover
            //sempre a primeira linha
        }
    }
}
