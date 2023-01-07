import lucasheber.core.Funcionario;
import lucasheber.core.FuncionarioBeneficiado;
import lucasheber.core.RegistroVenda;
import lucasheber.models.Gerente;
import lucasheber.models.Secretario;
import lucasheber.models.Vendedor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        inicia();
    }


    public static void inicia() {
        //FORMATANDO NO PADRAO BRL
        NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        ArrayList<FuncionarioBeneficiado> funcionarioBeneficiado = new ArrayList<>();
        ArrayList<Vendedor> vendedores = new ArrayList<>();

        //ADICIONANDO OS REGISTROS DE VENDAS
        Vendedor anaSilva = new Vendedor("Ana Silva", LocalDate.of(2021, 12, 1));
        anaSilva.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2021, 12, 1), 5200.0));
        anaSilva.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 1, 1), 4000.0));
        anaSilva.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 2, 1), 4200.0));
        anaSilva.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 3, 1), 5850.0));
        anaSilva.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 4, 1), 7000.0));

        //ADICIONANDO OS REGISTROS DE VENDAS
        Vendedor joaoMendes = new Vendedor("João Mendes", LocalDate.of(2021, 12, 1));
        joaoMendes.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2021, 12, 1), 3400.0));
        joaoMendes.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 1, 1), 7700.0));
        joaoMendes.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 2, 1), 5000.0));
        joaoMendes.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 3, 1), 5900.0));
        joaoMendes.adicionaRegistroVenda(new RegistroVenda(LocalDate.of(2022, 4, 1), 6500.0));

        funcionarios.add(anaSilva);
        funcionarios.add(joaoMendes);
        funcionarios.add(new Secretario("Jorge Carvalho", LocalDate.of(2018, 1, 1)));
        funcionarios.add(new Secretario("Maria Souza", LocalDate.of(2015, 12, 1)));

        funcionarios.add(new Gerente("Juliana Alves", LocalDate.of(2017, 7, 1)));
        funcionarios.add(new Gerente("Bento Albino", LocalDate.of(2014, 3, 1)));

        funcionarioBeneficiado.add(joaoMendes);
        funcionarioBeneficiado.add(anaSilva);

        vendedores.add(anaSilva);
        vendedores.add(joaoMendes);
        /* ######################################################################################################*/

        System.out.println("\nTotal salario + beneficios");
        System.out.println("\t R$ " + nf.format(totalPagoSalarioEBeneficios(funcionarios, LocalDate.of(2019, 12, 1))));

        System.out.println("\nTotal salario");
        System.out.println("\t R$ " + nf.format(totalPago(funcionarios, LocalDate.of(2019, 12, 1))));

        System.out.println("\nTotal beneficios");
        System.out.println("\t R$ " + nf.format(totalPagoEmBeneficio(funcionarioBeneficiado, LocalDate.of(2022, 01, 1))));

        System.out.println("\nMaior Valor total pago");
        System.out.println("\t" + maiorValortotalPago(funcionarios, LocalDate.of(2019, 12, 1)));

        System.out.println("\nMaior Valor total pago em beneficios");
        System.out.println("\t" + maiorValortotalBeneficio(funcionarioBeneficiado, LocalDate.of(2022, 01, 1)));

        System.out.println("\nMaior Vendedor");
        System.out.println("\t" + maiorVendedor(vendedores, LocalDate.of(2022, 01, 1)));

    }

    /**
     * Um método que receba uma lista de funcionários, mês e ano e retorne o valor total
     * pago (salário e benefício) a esses funcionários no mês.
     */
    private static double totalPagoSalarioEBeneficios(ArrayList<Funcionario> funcionarios, LocalDate date) {
        ArrayList<FuncionarioBeneficiado> funcionarioBeneficiado = new ArrayList<>();

        for (Funcionario f : funcionarios) {
            if (f instanceof FuncionarioBeneficiado) {
                funcionarioBeneficiado.add((FuncionarioBeneficiado) f);
            }
        }

        double totalPagoEmBeneficio = totalPagoEmBeneficio(funcionarioBeneficiado, date);
        double totalPago = totalPago(funcionarios, date);

        return totalPago + totalPagoEmBeneficio;
    }

    /**
     * Um método que receba uma lista de funcionários, mês e ano e retorne o total pago
     * somente em salários no mês.
     */
    private static double totalPago(ArrayList<Funcionario> funcionarios, LocalDate date) {
        double total = 0;

        for (Funcionario funcionario : funcionarios) {

            total += _totalPago(funcionario, date, false);
        }

        return total;
    }

    /**
     * Um método que receba uma lista somente com os funcionários que recebem
     * benefícios, mês e ano e retorne o total pago em benefícios no mês.
     */
    private static double totalPagoEmBeneficio(ArrayList<FuncionarioBeneficiado> funcionariosBeneficiado, LocalDate date) {

        double total = 0;

        for (FuncionarioBeneficiado funcionario : funcionariosBeneficiado) {

            total += _totalBeneficio(funcionario, date, false);

        }

        return total;
    }

    /**
     * Um método que receba uma lista de funcionários, mês e ano e retorne o que
     * recebeu o valor mais alto no mês.
     */
    private static Funcionario maiorValortotalPago(ArrayList<Funcionario> funcionarios, LocalDate date) {
        Funcionario f = null;

        double anterior = 0;
        for (Funcionario funcionario : funcionarios) {
            double valor = _totalPago(funcionario, date, false);

            if (funcionario instanceof FuncionarioBeneficiado) {
                valor += _totalBeneficio((FuncionarioBeneficiado) funcionario, date, false);
            }

            if (valor > anterior) {
                f = funcionario;
                anterior = valor;
            }
        }

        return f;
    }

    /**
     * Um método que receba uma lista somente com os funcionários que recebem
     * benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais
     * alto em benefícios no mês
     */
    private static Funcionario maiorValortotalBeneficio(ArrayList<FuncionarioBeneficiado> funcionarios, LocalDate date) {
        Funcionario f = null;

        double anterior = 0;
        for (FuncionarioBeneficiado funcionario : funcionarios) {
            double valor = _totalBeneficio(funcionario, date, false);

            if (valor > anterior) {
                f = (Funcionario) funcionario;
                anterior = valor;
            }
        }

        return f;
    }

    /**
     * Um método que receba uma lista de vendedores, mês e ano e retorne o que mais
     * vendeu no mês.
     */
    private static Vendedor maiorVendedor(ArrayList<Vendedor> vendedores, LocalDate date) {
        double anterior = 0;
        Vendedor v = null;

        for (Vendedor vendedor : vendedores) {
            double t = vendedor.totalBeneficio(date, vendedor.beneficio(), false);
            if (t > anterior) {
                v = vendedor;
                anterior = t;
            }
        }

        return v;
    }

    private static double _totalPago(Funcionario funcionario, LocalDate date, Boolean showLog) {
        NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        Period interval = Period.between(funcionario.getContratacao(), date);
        int anosTrabalhos = Math.max(0, interval.getYears());
        double totalSalario = funcionario.salario() + (funcionario.valorAnoServico() * anosTrabalhos);

        if (showLog) {
            _logB(funcionario, anosTrabalhos, totalSalario);
        }

        return totalSalario;
    }

    private static double _totalBeneficio(FuncionarioBeneficiado funcionario, LocalDate date, Boolean showLog) {
        double beneficio = 0;
        double t = 0;

        if (funcionario instanceof Vendedor) {
            Vendedor v = (Vendedor) funcionario;
            t = _totalPago(v, date, false);
            beneficio = v.totalBeneficio(date, v.beneficio(), false);

            if (showLog)
                _logA(v, t, beneficio);

        } else if (funcionario instanceof Secretario) {
            Secretario s = (Secretario) funcionario;
            t = _totalPago(s, date, false);
            beneficio = (s.beneficio() * t);

            if (showLog)
                _logA(s, t, beneficio);
        }

        return beneficio;
    }

    private static void _logA(Funcionario f, double salario, double beneficio) {
        NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        System.out.println(f.getNome() +
                ": \n\tSalario atual: R$ " + nf.format(salario) +
                " \n\tBeneficio: R$ " + nf.format(beneficio));
    }

    private static void _logB(Funcionario funcionario, int anosTrabalhos, double totalSalario) {
        NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        System.out.println(funcionario.getNome() +
                ": \n\tSalario: R$ " + nf.format(funcionario.salario()) +
                " \n\tVl. Ano Serviço: R$ " + nf.format(funcionario.valorAnoServico()) +
                " \n\tAnos de Serviço: " + anosTrabalhos +
                " \n\tTotal Salario: R$ " + nf.format(totalSalario) + "\n");
    }
}