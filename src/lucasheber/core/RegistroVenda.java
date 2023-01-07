package lucasheber.core;

import java.time.LocalDate;
import java.util.Date;

public class RegistroVenda {
    private LocalDate date;
    private Double valorVenda;

    public RegistroVenda(LocalDate date, Double valorVenda) {
        this.date = date;
        this.valorVenda = valorVenda;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getValorVenda() {
        return valorVenda;
    }
}
