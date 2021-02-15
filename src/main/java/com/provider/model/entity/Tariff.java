package com.provider.model.entity;

import java.util.List;

public class Tariff {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private Service service;
    private List<User> users;
    public Tariff() {
    }
    public Tariff(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public boolean isConnectedToUser(User user) {
        for(Tariff userTariff : user.getTariffs()) {
            if(userTariff.getId().equals(this.id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", service=" + service +
                ", users=" + users +
                '}';
    }
}
