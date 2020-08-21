package com.example.pabellonlh;

public class Horario {
    private int idhoirario;
    private String momento;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;
    private String sabado;
    private String domingo;

    public Horario() {
    }

    public Horario(int idhoirario, String momento, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo) {
        this.idhoirario = idhoirario;
        this.momento = momento;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    public int getIdhoirario() {
        return idhoirario;
    }

    public void setIdhoirario(int idhoirario) {
        this.idhoirario = idhoirario;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public String getLunes() {
        return lunes;
    }

    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    public String getMartes() {
        return martes;
    }

    public void setMartes(String martes) {
        this.martes = martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    public String getViernes() {
        return viernes;
    }

    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "momento='" + momento + '\'' +
                ", lunes='" + lunes + '\'' +
                ", martes='" + martes + '\'' +
                ", miercoles='" + miercoles + '\'' +
                ", jueves='" + jueves + '\'' +
                ", viernes='" + viernes + '\'' +
                ", sabado='" + sabado + '\'' +
                ", domingo='" + domingo + '\'' +
                '}';
    }
}
