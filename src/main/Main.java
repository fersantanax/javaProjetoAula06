package main;

import controllers.FuncionarioController;

public class Main {
	public static void main(String[] args) {
		FuncionarioController funcionarioController = new FuncionarioController();
		funcionarioController.cadastrarFuncionario();
	}
}