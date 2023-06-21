package ru.sber.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Информация для перевода
 */
public class DataForTransfer {
    private String numberPhone;
    private BigDecimal sum;
    private LocalDate date;

    public DataForTransfer(String numberPhone, BigDecimal sum, LocalDate date) {
        this.numberPhone = numberPhone;
        this.sum = sum;
        this.date = date;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumber(String number) {
        this.numberPhone = number;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
