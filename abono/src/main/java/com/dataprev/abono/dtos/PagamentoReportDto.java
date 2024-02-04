package com.dataprev.abono.dtos;

import java.math.BigDecimal;

public class PagamentoReportDto {
    private String identificacaoRegistro;
    private String codigoPagamento;
    private String exercicioFinanceiro = "01012022";
    private String anoBase = "2022";
    private String numeroParcela = "01";
    private String valorPagamento;
    private String mesesTrabalhados;
    private String dataInicialPagamento = "05022024";
    private String dataFinalPagamento = "31122024";
    private String numeroSentenca = "0000000-00.0000.000.0000";
    private String banco;
    private String agencia;
    private String digitoVerificador;
    private String tipoConta;
    private String conta;
    private String indicadorPagamento;
    private String cpf;
    private String nome;
    private String nomeMae;
    private String nascimento;
    private String pisPasep;
    private String zeros;

    public PagamentoReportDto(String identificacaoRegistro, String codigoPagamento, String exercicioFinanceiro, String anoBase, String numeroParcela, String valorPagamento, String mesesTrabalhados, String dataInicialPagamento, String dataFinalPagamento, String numeroSentenca, String banco, String agencia, String digitoVerificador, String tipoConta, String conta, String indicadorPagamento, String cpf, String nome, String nomeMae, String nascimento, String pisPasep, String zeros) {
        this.identificacaoRegistro = identificacaoRegistro;
        this.codigoPagamento = codigoPagamento;
        this.exercicioFinanceiro = exercicioFinanceiro;
        this.anoBase = anoBase;
        this.numeroParcela = numeroParcela;
        this.valorPagamento = valorPagamento;
        this.mesesTrabalhados = mesesTrabalhados;
        this.dataInicialPagamento = dataInicialPagamento;
        this.dataFinalPagamento = dataFinalPagamento;
        this.numeroSentenca = numeroSentenca;
        this.banco = banco;
        this.agencia = agencia;
        this.digitoVerificador = digitoVerificador;
        this.tipoConta = tipoConta;
        this.conta = conta;
        this.indicadorPagamento = indicadorPagamento;
        this.cpf = cpf;
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.nascimento = nascimento;
        this.pisPasep = pisPasep;
        this.zeros = zeros;
    }

    public PagamentoReportDto(){

    }

    public String getIdentificacaoRegistro() {
        return identificacaoRegistro;
    }

    public void setIdentificacaoRegistro(String identificacaoRegistro) {
        this.identificacaoRegistro = identificacaoRegistro;
    }

    public String getCodigoPagamento() {
        return codigoPagamento;
    }

    public void setCodigoPagamento(String codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

    public String getExercicioFinanceiro() {
        return exercicioFinanceiro;
    }

    public void setExercicioFinanceiro(String exercicioFinanceiro) {
        this.exercicioFinanceiro = exercicioFinanceiro;
    }

    public String getAnoBase() {
        return anoBase;
    }

    public void setAnoBase(String anoBase) {
        this.anoBase = anoBase;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(String valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public String getMesesTrabalhados() {
        return mesesTrabalhados;
    }

    public void setMesesTrabalhados(String mesesTrabalhados) {
        this.mesesTrabalhados = mesesTrabalhados;
    }

    public String getDataInicialPagamento() {
        return dataInicialPagamento;
    }

    public void setDataInicialPagamento(String dataInicialPagamento) {
        this.dataInicialPagamento = dataInicialPagamento;
    }

    public String getDataFinalPagamento() {
        return dataFinalPagamento;
    }

    public void setDataFinalPagamento(String dataFinalPagamento) {
        this.dataFinalPagamento = dataFinalPagamento;
    }

    public String getNumeroSentenca() {
        return numeroSentenca;
    }

    public void setNumeroSentenca(String numeroSentenca) {
        this.numeroSentenca = numeroSentenca;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
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

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getIndicadorPagamento() {
        return indicadorPagamento;
    }

    public void setIndicadorPagamento(String indicadorPagamento) {
        this.indicadorPagamento = indicadorPagamento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(String pisPasep) {
        this.pisPasep = pisPasep;
    }

    public String getZeros() {
        return zeros;
    }

    public void setZeros(String zeros) {
        this.zeros = zeros;
    }
}
