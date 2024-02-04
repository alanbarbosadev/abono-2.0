package com.dataprev.abono.mappers;

import com.dataprev.abono.models.Banco;
import com.dataprev.abono.models.Pagamento;
import com.dataprev.abono.models.Trabalhador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PagamentoDatabaseRowMapper implements RowMapper<Pagamento> {

    @Override
    public Pagamento mapRow(ResultSet rs, int rowNum) throws SQLException {
        var pagamento = new Pagamento();
        var banco = new Banco();
        var trabalhador = new Trabalhador();


        pagamento.setId(rs.getInt("pagamento_id"));
        pagamento.setCodigoPagamento(rs.getLong("codigo_pagamento"));
        pagamento.setExercicioFinanceiro(rs.getString("exercicio_financeiro"));
        pagamento.setAnoBase(rs.getString("ano_base"));
        pagamento.setNumeroParcela(rs.getString("numero_parcela"));
        pagamento.setValorPagamento(rs.getBigDecimal("valor_pagamento"));
        pagamento.setMesesTrabalhados(rs.getInt("meses_trabalhados"));
        pagamento.setDataInicialPagamento(rs.getString("data_inicial_pagamento"));
        pagamento.setDataFinalPagamento(rs.getString("data_final_pagamento"));
        pagamento.setNumeroSentenca(rs.getString("numero_sentenca"));
        banco.setBancoId(rs.getInt("banco_id"));
        banco.setBanco(rs.getLong("banco"));
        banco.setAgencia(rs.getLong("agencia"));
        banco.setDigitoVerificador(rs.getString("digito_verificador"));
        banco.setTipoConta(rs.getString("tipo_conta"));
        banco.setConta(rs.getLong("conta"));
        banco.setIndicadorPagamento(rs.getInt("indicador_pagamento"));
        trabalhador.setCpf(rs.getLong("trabalhador_id"));
        trabalhador.setNome(rs.getString("nome"));
        trabalhador.setNomeMae(rs.getString("nome_mae"));
        trabalhador.setNascimento(rs.getDate("data_nascimento"));
        trabalhador.setPisPasep(rs.getLong("pis_pasep"));

        pagamento.setBanco(banco);
        pagamento.setTrabalhador(trabalhador);

        return pagamento;
    }
}
