package com.sincronizacaoreceita.entity;

public class Receita {

    private String agencia;
    private String conta;
    private Double saldo;
    private String status;
    private String statusRetorno;

    public String getStatusRetorno() {
        return statusRetorno;
    }

    public void setStatusRetorno(String statusRetorno) {
        this.statusRetorno = statusRetorno;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
