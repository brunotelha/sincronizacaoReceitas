package com.sincronizacaoreceita.application.service;

import com.sincronizacaoreceita.entity.Receita;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sincronizacaoreceita.service.ReceitaService;

@SpringBootTest
public class ReceitaServiceTest {

	@Test
	void testarSincronizacaoReceitaSucesso() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("0059");
		receita.setConta("0000100");
		receita.setSaldo(100.00);
		receita.setStatus("A");

		String erro =  service.atualizarConta(receita);
		
		Assert.assertTrue("Envio realizado com sucesso!".equals(erro));
	}
	
	@Test
	void testarSincronizacaReceitaErroContaInvalida() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();
		
		receita.setAgencia("0059");
		receita.setConta("000010");
		receita.setSaldo(100.00);
		receita.setStatus("A");

		String erro =  service.atualizarConta(receita);
		
		Assert.assertTrue("Conta com formato incorreto! | ".equals(erro));
	}

	@Test
	void testarSincronizacaReceitaAgenciaFormatoIncorreto() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("12");
		receita.setConta("12226-8");
		receita.setSaldo(100.00);
		receita.setStatus("A");

		String erro =  service.atualizarConta(receita);

		Assert.assertTrue("Agencia com formato incorreto! | ".equals(erro));
	}

	@Test
	void testarSincronizacaReceitaAgenciaNaoInformada() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("");
		receita.setConta("12226-8");
		receita.setSaldo(100.00);
		receita.setStatus("A");

		String erro =  service.atualizarConta(receita);

		Assert.assertTrue("Agencia não informada! | ".equals(erro));
	}

	@Test
	void testarSincronizacaReceitaContaNaoInformada() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("1234");
		receita.setConta("");
		receita.setSaldo(100.00);
		receita.setStatus("A");

		String erro =  service.atualizarConta(receita);

		Assert.assertTrue("Conta não informada! | ".equals(erro));
	}

	@Test
	void testarSincronizacaReceitaStatusInvalido() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("1234");
		receita.setConta("12226-8");
		receita.setSaldo(100.00);
		receita.setStatus("X");

		String erro =  service.atualizarConta(receita);

		Assert.assertTrue("Status invalido! | ".equals(erro));
	}

	@Test
	void testarSincronizacaReceitaStatusNaoInformado() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("1234");
		receita.setConta("12226-8");
		receita.setSaldo(100.00);
		receita.setStatus("");

		String erro =  service.atualizarConta(receita);

		Assert.assertTrue("Status não informado! | ".equals(erro));
	}

	@Test
	void testarSincronizacaReceitaSaldoNaoInformado() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("0059");
		receita.setConta("0000100");
		receita.setSaldo(null);
		receita.setStatus("A");

		String erro =  service.atualizarConta(receita);

		Assert.assertTrue("Saldo não informado! | ".equals(erro));
	}

	@Test
	void testarSincronizacaReceitaMultiplosErros() throws RuntimeException, InterruptedException {

		ReceitaService service = new ReceitaService();
		Receita receita = new Receita();

		receita.setAgencia("");
		receita.setConta("123455677");
		receita.setSaldo(null);
		receita.setStatus("F");

		String erro =  service.atualizarConta(receita);

		Assert.assertTrue("Agencia não informada! | Conta com formato incorreto! | Status invalido! | Saldo não informado! | ".equals(erro));
	}



}
