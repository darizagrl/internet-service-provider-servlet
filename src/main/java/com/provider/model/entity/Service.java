package com.provider.model.entity;

import java.util.List;

public class Service {
    private Integer id;
    private String name;
    private List<Tariff> tariffList;

    public Service(String name) {
        this.name = name;
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

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    @Override
    public String toString() {
        return "Service{" +
                ", name='" + name + '\'' +
                ", tariffList=" + tariffList +
                '}';
    }
}
