package lucasheber.core;

public enum Cargo {

    Secretario(7000, 1000, 0.2),

    Vendedor(12000, 1800, 0.3),

    Gerente(20000, 3000, 0);

    private double salario;
    private double valorAnoServico;
    private double beneficio;

    Cargo(double salario, double valorAnoServico, double beneficio) {
        this.beneficio = beneficio;
        this.salario = salario;
        this.valorAnoServico = valorAnoServico;
    }

    public double getSalario() {
        return salario;
    }

    public double getValorAnoServico() {
        return valorAnoServico;
    }

    public double getBeneficio() {
        return beneficio;
    }
}
