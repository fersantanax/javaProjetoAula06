package entities;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enums.TipoFuncionario;

public class Funcionario {
	private UUID id;
	private String nome;
	private String cpf;
	private String matricula;
	private TipoFuncionario tipo;
	private Double salario;
	private FolhaDePagamento folhaDePagamento;

	public Funcionario() {
// TODO Auto-generated constructor stub
	}

	public Funcionario(UUID id, String nome, String cpf, String matricula,

			TipoFuncionario tipo, Double salario, FolhaDePagamento folhaDePagamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.tipo = tipo;
		this.salario = salario;
		this.folhaDePagamento = folhaDePagamento;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		Pattern pattern = Pattern.compile("^[A-Za-zÀ-Üà-ü\\s]{8,100}$");
		Matcher matcher = pattern.matcher(nome);
		if (!matcher.matches())
			throw new IllegalArgumentException("Informe um nome válido de 8 a 100 caracteres.");

		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		Pattern pattern = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
		Matcher matcher = pattern.matcher(cpf);
		if (!matcher.matches())
			throw new IllegalArgumentException("Informe um CPF no formato '999.999.999-99'.");

		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		Pattern pattern = Pattern.compile("^[0-9]{6,10}$");
		Matcher matcher = pattern.matcher(matricula);
		if (!matcher.matches())
			throw new IllegalArgumentException("Informe somente números de 6 a 10 dígitos.");

		this.matricula = matricula;
	}

	public TipoFuncionario getTipo() {
		return tipo;
	}

	public void setTipo(TipoFuncionario tipo) {
		this.tipo = tipo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		if (salario <= 0)
			throw new IllegalArgumentException("Informe um salário com valor maior do que zero.");

		this.salario = salario;
	}

	public FolhaDePagamento getFolhaDePagamento() {
		return folhaDePagamento;
	}

	public void setFolhaDePagamento(FolhaDePagamento folhaDePagamento) {
		this.folhaDePagamento = folhaDePagamento;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", matricula=" + matricula + ", tipo="
				+ tipo + ", salario=" + salario + ", folhaDePagamento=" + folhaDePagamento + "]";

	}
}