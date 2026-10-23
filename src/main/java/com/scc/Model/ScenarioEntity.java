package com.scc.model;

public record ScenarioEntity(
        int id,
        String customerName,
        String drinkItem,
        double price,
        String status
) {}