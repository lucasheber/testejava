package lucasheber.core;

import java.time.LocalDate;
import java.util.Date;

public class Funcionario {
    private String nome;
    private Cargo cargo;
    private LocalDate contratacao;

    public Funcionario(String nome, Cargo cargo, LocalDate contratacao) {
        this.nome = nome;
        this.cargo = cargo;
        this.contratacao = contratacao;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome +
                ", Cargo: " + cargo.name() +
                ", Salario: R$ " + cargo.getSalario() +
                ", Beneficio: R$ " + cargo.getBeneficio() * 100 + "%";
    }

    public double salario() {
        return this.cargo.getSalario();
    }

    public double valorAnoServico() {
        return this.cargo.getValorAnoServico();
    }

    public double beneficio() {
        return this.cargo.getBeneficio();
    }

    public LocalDate getContratacao() {
        return this.contratacao;
    }

    public String getNome() {
        return nome;
    }
}
