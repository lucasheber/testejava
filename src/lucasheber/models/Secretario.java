package lucasheber.models;

import lucasheber.core.Cargo;
import lucasheber.core.Funcionario;
import lucasheber.core.FuncionarioBeneficiado;

import java.time.LocalDate;
import java.util.Date;

public class Secretario extends Funcionario implements FuncionarioBeneficiado {
    public Secretario(String nome, LocalDate contratacao) {
        super(nome, Cargo.Secretario, contratacao);
    }

}
