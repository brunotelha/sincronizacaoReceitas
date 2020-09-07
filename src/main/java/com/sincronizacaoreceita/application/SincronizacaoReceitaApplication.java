package com.sincronizacaoreceita.application;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sincronizacaoreceita.entity.Receita;
import com.sincronizacaoreceita.service.ReceitaService;

@SpringBootApplication
public class SincronizacaoReceitaApplication {

	public static void main(String[] args) throws InterruptedException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);

		System.out.println("Envio em processamento...");

		//obtem o diretório atual
		String dir = System.getProperty("user.dir");

		//Lendo arquivo informado
		Reader reader = Files.newBufferedReader(Paths.get(dir + "\\" + args[0]));

		//Transformando arquivo em CscToBean
		CsvToBean<Receita> listaReceitas = new CsvToBeanBuilder(reader)
				.withType(Receita.class)
				.build();

		// Transformando CsvToBean em lista do objeto Receita
		List<Receita> receitas = listaReceitas.parse();

		Writer writer = Files.newBufferedWriter(Paths.get(dir+"\\Resultado_envio.csv"));
		StatefulBeanToCsv<Receita> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

		//Definindo variável de retorno
		String statusRetorno = "";

		//Instanciando service
		ReceitaService receitaService = new ReceitaService();

		//Percorrendo objeto para escrita em novo CSV após a chamada do service de receita

		for (Receita receita : receitas) {

			// atualizando variavel de retorno
			statusRetorno = receitaService.atualizarConta(receita);

			// setando retorno
			receita.setStatusRetorno(statusRetorno);

			// escrevendo csv
			beanToCsv.write(receita);
		}
		//fechando buffer de escrita
		writer.close();
		System.out.println("Processamento finalizado. \n Resultado disponivel em: "+ dir+"\\Resultado_envio.csv");

	}

}