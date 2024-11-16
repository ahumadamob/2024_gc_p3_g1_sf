package com.imb4.gc.p3.gr1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateLimitsDTO {

    @NotNull(message = "El límite diario no puede ser nulo")
    @Positive(message = "El límite diario debe ser un valor positivo")
    private Float dailyLimit;

    @NotNull(message = "El límite por transacción no puede ser nulo")
    @Positive(message = "El límite por transacción debe ser un valor positivo")
    private Float transactionLimit;

    // Getters y setters
    public Float getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Float dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Float getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(Float transactionLimit) {
        this.transactionLimit = transactionLimit;
    }
}
