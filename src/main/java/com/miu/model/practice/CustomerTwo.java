package com.miu.model.practice;

import java.util.List;

public class CustomerTwo {
    private Long customerId;
    private String name;
    private  String email;
    private String phone;
    private  String address;

    List<OrderTwo> orders;

    public List<OrderTwo> getOrders() {
        return orders;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrders(List<OrderTwo> orders) {
        this.orders = orders;
    }
}
