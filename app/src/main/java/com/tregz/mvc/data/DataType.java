package com.tregz.mvc.data;

public enum DataType {
    APPLE("Pomme"),
    STRAWBERRY("Fraise"),
    USER("Utilisateur");

    private String type;

    public String getType() {
        return type;
    }

    DataType(String type) {
        this.type = type;
    }
}
