package com.api.busqueda.model;

public class ErrorResponse {
    private String codigo;
    private String error;

    public ErrorResponse(String codigo, String error) {
        this.codigo = codigo;
        this.error = error;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public String toJson() {
        return String.format("{\"codigo\": \"%s\", \"error\": \"%s\"}", codigo, error);
    }
}
