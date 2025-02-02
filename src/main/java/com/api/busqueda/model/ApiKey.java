package com.api.busqueda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ApiKey {

    @Id
    private Long id;
    private String key;
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
// Getters y Setters
}
