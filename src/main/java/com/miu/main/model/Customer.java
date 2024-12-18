package com.miu.main.model;

public class Customer {
    private int customerId;
    private String name;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                '}';
    }
}
