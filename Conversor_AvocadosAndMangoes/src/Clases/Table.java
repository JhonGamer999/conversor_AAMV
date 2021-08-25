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
public class Table {
            
    private String product;
    private int quantity;
    private double saleValue;
    private double purchaseValue;
    private double saleTotal;
    private double purchaseTotal;
    private double valorPrecioCosto;
    private String date;

    public Table() {
    }

    public Table(String product, int quantity, double saleValue, double purchaseValue, double saleTotal, double purchaseTotal, double valorPrecioCosto, String date ) {
        this.product = product;
        this.quantity = quantity;
        this.saleValue = saleValue;
        this.purchaseValue = purchaseValue;
        this.saleTotal = saleTotal;
        this.purchaseTotal = purchaseTotal;
        this.valorPrecioCosto = valorPrecioCosto;
        this.date = date;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
     * @return the saleTotal
     */
    public double getSaleTotal() {
        return saleTotal;
    }

    /**
     * @param saleTotal the saleTotal to set
     */
    public void setSaleTotal(double saleTotal) {
        this.saleTotal = saleTotal;
    }

    /**
     * @return the purchaseTotal
     */
    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    /**
     * @param purchaseTotal the purchaseTotal to set
     */
    public void setPurchaseTotal(double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
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
     * @return the valorPrecioCosto
     */
    public double getValorPrecioCosto() {
        return valorPrecioCosto;
    }

    /**
     * @param valorPrecioCosto the valorPrecioCosto to set
     */
    public void setValorPrecioCosto(double valorPrecioCosto) {
        this.valorPrecioCosto = valorPrecioCosto;
    }

    

}
