package ru.petr.entity;

public class Status {
    private int id;
    private boolean saleStatus;

    public Status() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(boolean saleStatus) {
        this.saleStatus = saleStatus;
    }
}
