package com.dataprev.abono.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static final int CODIGO_PAGAMENTO_SIZE = 12;
    public static final int PIS_PASEP_SIZE = 11;
    public static final int NOME_SIZE = 70;
    public static final int NOME_MAE_SIZE = 70;
    public static final int CPF_SIZE = 11;
    public static final int VALOR_PAGAMENTO_SIZE = 9;
    public static final int MESES_TRABALHADOS_SIZE = 2;
    public static final int BANCO_SIZE = 4;
    public static final int AGENCIA_SIZE = 5;
    public static final int DIGITO_VERIFICADOR_SIZE = 1;
    public static final int TIPO_CONTA_SIZE = 2;
    public static final int CONTA_SIZE = 15;
    public static final int INDICADOR_PAGAMENTO_SIZE = 1;
    public static final int QUANTIDADE_PAGAMENTOS_SIZE = 9;
    public static final int VALOR_TOTAL_PAGAMENTOS_SIZE = 13;
    public static final int NOME_ARQUIVO_SIZE = 50;
    public static final String IDENTIFICACAO_REGISTRO_CABECALHO = "11";
    public static final String CODIGO_ARQUIVO = "446954311";
    public static final String NOME_ARQUIVO = "ARQUIVOCAIXA";
    public static final String NUMERO_LOTE = "12345";
    public static final String IDENTIFICACAO_REGISTO_PAGAMENTO = "21";
    public static final String EXERCICIO_FINANCEIRO = "01012022";
    public static final String ANO_BASE = "2022";
    public static final String NUMERO_PARCELA = "01";
    public static final String DATA_INICIAL_PAGAMENTO = "05022024";
    public static final String DATA_FINAL_PAGAMENTO = "31122024";
    public static final String NUMERO_SENTENCA_JUDICIAL = "00000000000000000000";
    public static final String IDENTIFICACAO_REGISTO_RODAPE = "31";

    public static <T> String formatField(T field, int size) {
        String fieldStr = String.valueOf(field);

        if (fieldStr.length() < size) {
            int paddingSize = size - fieldStr.length();
            StringBuilder str = new StringBuilder(fieldStr);

            if (field instanceof String) {
                for (int i = 0; i < paddingSize; i++) {
                    str.insert(0, ' ');
                }
            } else {
                for (int i = 0; i < paddingSize; i++) {
                    str.insert(0, '0');
                }
            }
            return str.toString();
        }
        return fieldStr;
    }

    public static String formatNomeMae(String nomeMae, int size) {
        StringBuilder str = new StringBuilder(nomeMae);

        if (nomeMae.isEmpty() || nomeMae.isBlank()) {
            str = new StringBuilder("Não Informado");
        }

        int paddingSize = size - str.length();

        if (str.length() < size) {
            for (int i = 0; i < paddingSize; i++) {
                str.insert(0, ' ');
            }
            return str.toString();
        }

        return nomeMae;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(date);
    }
}

