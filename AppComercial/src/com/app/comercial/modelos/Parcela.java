/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.comercial.modelos;

import java.sql.Date;

/**
 *
 * @author ANTONIO
 */
public class Parcela {
    private int id;
    private String documento;
    private String tipoParcela;
    private double valor;
    private int clienteId;
    private int fornecedorId;
    private int movimentoId;
    private Date pagamento;
    private Date vencimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoParcela() {
        return tipoParcela;
    }

    public void setTipoParcela(String tipoParcela) {
        this.tipoParcela = tipoParcela;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public int getMovimentoId() {
        return movimentoId;
    }

    public void setMovimentoId(int movimentoId) {
        this.movimentoId = movimentoId;
    }

    public Date getPagamento() {
        return pagamento;
    }

    public void setPagamento(Date pagamento) {
        this.pagamento = pagamento;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }
    
    
    
}
