package lucasheber.models;

import lucasheber.core.Cargo;
import lucasheber.core.Funcionario;

import java.time.LocalDate;

public class Gerente extends Funcionario {
    public Gerente(String nome, LocalDate contratacao) {
        super(nome, Cargo.Gerente, contratacao);
    }
}
