package lucasheber.models;

import lucasheber.core.Cargo;
import lucasheber.core.Funcionario;
import lucasheber.core.FuncionarioBeneficiado;
import lucasheber.core.RegistroVenda;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

public class Vendedor extends Funcionario implements FuncionarioBeneficiado {

    private ArrayList<RegistroVenda> registroVendas;

    public Vendedor(String nome, LocalDate contratacao) {
        super(nome, Cargo.Vendedor, contratacao);
        this.registroVendas = new ArrayList<>();
    }

    public void adicionaRegistroVenda(RegistroVenda registroVenda) {
        this.registroVendas.add(registroVenda);
    }

    /**
     * @param date data de realizaca
     * @param soma se true irá somar todos os beneficios ate a data passada, se false irá retorna o valor correnpondente a data
     * @return
     */
    public Double totalBeneficio(LocalDate date, double percentual, boolean soma) {

        double total = 0;
        double totalAcumulado = 0;
        for (RegistroVenda registroVenda : registroVendas) {

            if (registroVenda.getDate().getYear() == date.getYear() && registroVenda.getDate().getMonthValue() == date.getMonthValue()) {
                total = registroVenda.getValorVenda() * percentual;
            }

            if (registroVenda.getDate().compareTo(date) <= 0) {
                totalAcumulado += registroVenda.getValorVenda() * percentual;
            }
        }

        return soma ? totalAcumulado : total;
    }
}
