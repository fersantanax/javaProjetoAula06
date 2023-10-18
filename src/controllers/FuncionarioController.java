package controllers;

import java.util.UUID;

import javax.swing.JOptionPane;

import entities.FolhaDePagamento;
import entities.Funcionario;
import enums.TipoFuncionario;
import interfaces.FolhaDePagamentoService;
import repositories.FuncionarioRepository;
import services.FolhaDePagamentoCLTService;
import services.FolhaDePagamentoEstagioService;

public class FuncionarioController {
	public void cadastrarFuncionario() {
		try {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(UUID.randomUUID());
			funcionario.setNome(JOptionPane.showInputDialog("INFORME O NOME:"));
			funcionario.setCpf(JOptionPane.showInputDialog("INFORME O CPF"));
			funcionario.setMatricula(JOptionPane.showInputDialog("INFORME A MATRICULA:"));
			funcionario.setSalario(Double.parseDouble(JOptionPane.showInputDialog("INFORME O SALÁRIO:")));
			Integer tipo = Integer.parseInt(JOptionPane.showInputDialog("INFORME (1) ESTÁGIO OU (2) CLT:"));
//POLIMORFISMO
			FolhaDePagamentoService folhaDePagamentoService = null;
			switch (tipo) {
			case 1:
				funcionario.setTipo(TipoFuncionario.Estagio);
				folhaDePagamentoService = new FolhaDePagamentoEstagioService();
//POLIMORFISMO
				break;
			case 2:
				funcionario.setTipo(TipoFuncionario.CLT);
				folhaDePagamentoService = new FolhaDePagamentoCLTService();
//POLIMORFISMO
				break;
			default:
				throw new IllegalArgumentException("Tipo de funcionário inválido");

			}
//realizando os calculos
			FolhaDePagamento folha = new FolhaDePagamento();
			folha.setId(UUID.randomUUID());
			folha.setDescValeTransporte(folhaDePagamentoService.calcularDescValeTransporte(funcionario.getSalario()));
			folha.setDescINSS(folhaDePagamentoService.calcularDescINSS(funcionario.getSalario()));
			folha.setSalarioLiquido(folhaDePagamentoService.calcularSalarioLiquido(funcionario.getSalario(),
					folha.getDescValeTransporte(), folha.getDescINSS()));
			funcionario.setFolhaDePagamento(folha);
//exportar para txt
			FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
			funcionarioRepository.exportar(funcionario);
//imprimindo os dados do funcionário
			System.out.println("\nDADOS DO FUNCIONÁRIO:");
			System.out.println(funcionario.toString());
		} catch (IllegalArgumentException e) {
			System.out.println("\nErro de validação:");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("\nFalha ao cadastrar funcionário:");
			System.out.println(e.getMessage());
		}
	}
}