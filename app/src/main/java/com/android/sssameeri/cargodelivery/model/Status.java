package com.android.sssameeri.cargodelivery.model;

public enum Status {

    ACTIVE("Активно"),
    INPROCESS("В процецсі"),
    END("Завершено");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
