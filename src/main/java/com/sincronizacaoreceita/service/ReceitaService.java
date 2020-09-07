package com.sincronizacaoreceita.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sincronizacaoreceita.entity.Receita;
import org.springframework.util.StringUtils;

public class ReceitaService {

    public String atualizarConta(Receita receita)
            throws RuntimeException, InterruptedException {

        String retorno = "";
        retorno += StringUtils.isEmpty(receita.getAgencia()) ? "Agencia não informada! | " : "";
        retorno += !StringUtils.isEmpty(receita.getAgencia()) && receita.getAgencia().length() != 4 ? "Agencia com formato incorreto! | " : "";
        retorno += StringUtils.isEmpty(receita.getConta()) ? "Conta não informada! | " : "";
        retorno += !StringUtils.isEmpty(receita.getConta()) && receita.getConta().length() != 7 ? "Conta com formato incorreto! | " : "";

        // Tipos de status validos:
        List<String> tipos = new ArrayList<String>();
        tipos.add("A");
        tipos.add("I");
        tipos.add("B");
        tipos.add("P");

        retorno += StringUtils.isEmpty(receita.getStatus()) ? "Status não informado! | " : "";
        retorno += !StringUtils.isEmpty(receita.getStatus()) && !tipos.contains(receita.getStatus()) ? "Status invalido! | " : "";
        retorno += !Objects.nonNull(receita.getSaldo()) ? "Saldo não informado! | " : "";

        retorno += retorno.isEmpty() ? "Envio realizado com sucesso!" : "";

        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
        long wait = Math.round(Math.random() * 4000) + 1000;
        Thread.sleep(wait);

        // Simula cenario de erro no serviço (0,1% de erro)
        long randomError = Math.round(Math.random() * 1000);
        if (randomError == 500)
            throw new RuntimeException("Error");

        return retorno;
    }
}
