package com.vereeth.misgestiones.modelos;

public class Registro {

    private int dia, mes, ano, positivos, negativos, portas, da, horas, cumplimiento, rph, efectividad;

    private long id; // El ID de la BD

    public Registro(int dia, int mes, int ano, int positivos, int negativos, int portas, int da, int horas, int cumplimiento, int rph, int efectividad) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.positivos = positivos;
        this.negativos = negativos;
        this.portas = portas;
        this.da = da;
        this.horas = horas;
        this.cumplimiento = cumplimiento;
        this.rph = rph;
        this.efectividad = efectividad;
    }

    // Constructor para cuando instanciamos desde la BD
    public Registro(int dia, int mes, int ano, int positivos, int negativos, int portas, int da, int horas, int cumplimiento, int rph, int efectividad, long id) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.positivos = positivos;
        this.negativos = negativos;
        this.portas = portas;
        this.da = da;
        this.horas = horas;
        this.cumplimiento = cumplimiento;
        this.rph = rph;
        this.efectividad = efectividad;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPositivos() {
        return positivos;
    }

    public void setPositivos(int positivos) {
        this.positivos = positivos;
    }

    public int getNegativos() {
        return negativos;
    }

    public void setNegativos(int negativos) {
        this.negativos = negativos;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    public int getDA() {
        return da;
    }

    public void setDA(int da) {
        this.da = da;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(int cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public int getRPH() {
        return rph;
    }

    public void setRPH(int rph) {
        this.rph = rph;
    }

    public int getEfectividad() {
        return efectividad;
    }

    public void setEfectividad(int efectividad) {
        this.efectividad = efectividad;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "dia='" + dia + '\'' +
                ", mes=" + mes + '\'' +
                ", ano=" + ano + '\'' +
                ", positivos=" + positivos + '\'' +
                ", negativos=" + negativos + '\'' +
                ", portas=" + portas + '\'' +
                ", da=" + da + '\'' +
                ", horas=" + horas + '\'' +
                ", cumplimiento=" + cumplimiento + '\'' +
                ", rph=" + rph + '\'' +
                ", efectividad=" + efectividad +
                '}';
    }
}
