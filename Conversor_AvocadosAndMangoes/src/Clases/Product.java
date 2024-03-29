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
public class Product {
    private String nombre;
    private int cantidad;
    private double purchaseValue;
    private String skuCode;
    

    public Product(String nombre, int cantidad, double purchaseValue, String skuCode) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.purchaseValue = purchaseValue;
        this.skuCode = skuCode;
    }

    public Product() {
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
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * @param skuCode the saleValue to set
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    
}
