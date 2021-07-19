/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author diego
 */
public class HProduct {
    private String nombre;
    private int cantidad;
    private double purchaseValue;
    private double saleValue;
    private String date;
    private String skuCode;
    private String week;

    public HProduct(String nombre, int cantidad, double purchaseValue, double saleValue, String date, String skuCode, String week ) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.purchaseValue = purchaseValue;
        this.saleValue = saleValue;
        this.date = date;
        this.skuCode = skuCode;
        this.week = week;
       
    }

    public HProduct() {
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the purchaseValue
     */
    public double getPurchaseValue() {
        return purchaseValue;
    }

    /**
     * @param purchaseValue the purchaseValue to set
     */
    public void setPurchaseValue(double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    /**
     * @return the saleValue
     */
    public double getSaleValue() {
        return saleValue;
    }

    /**
     * @param saleValue the saleValue to set
     */
    public void setSaleValue(double saleValue) {
        this.saleValue = saleValue;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the skuCode
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * @param skuCode the skuCode to set
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    /**
     * @return the week
     */
    public String getWeek() {
        return week;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(String week) {
        this.week = week;
    }
    
    
}

