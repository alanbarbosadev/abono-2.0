package com.dataprev.abono.models;

import jakarta.persistence.*;

@Entity
@Table(name="TB_BANCO")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="banco_id")
    private Integer bancoId;
    @Column(name="banco")
    private Long banco;
    @Column(name="agencia")
    private Long agencia;
    @Column(name="digito_verificador")
    private String digitoVerificador;
    @Column(name="tipo_conta")
    private String tipoConta;
    @Column(name="conta")
    private Long conta;
    @Column(name="indicador_pagamento")
    private Integer indicadorPagamento;

    public Banco(Integer bancoId, Long banco, Long agencia, String digitoVerificador, String tipoConta, Long conta, Integer indicadorPagamento) {
        this.bancoId = bancoId;
        this.banco = banco;
        this.agencia = agencia;
        this.digitoVerificador = digitoVerificador;
        this.tipoConta = tipoConta;
        this.conta = conta;
        this.indicadorPagamento = indicadorPagamento;
    }

    public Banco() {
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public Long getBanco() {
        return banco;
    }

    public void setBanco(Long banco) {
        this.banco = banco;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public Integer getIndicadorPagamento() {
        return indicadorPagamento;
    }

    public void setIndicadorPagamento(Integer indicadorPagamento) {
        this.indicadorPagamento = indicadorPagamento;
    }
}
