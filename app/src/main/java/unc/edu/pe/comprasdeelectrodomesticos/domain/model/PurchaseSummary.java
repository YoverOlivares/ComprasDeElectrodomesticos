package unc.edu.pe.comprasdeelectrodomesticos.domain.model;

import java.io.Serializable;

public class PurchaseSummary implements Serializable {
    private String productName;
    private double productPrice;
    private int quantity;
    private double subtotal;
    private String addOnsList; // Texto concatenado ej: "Instalación, Mantenimiento"
    private double addOnsTotal;
    private String discountType;
    private double discountAmount;
    private String giftName;
    private double total;

    public PurchaseSummary(String productName, double productPrice, int quantity, double subtotal,
                           String addOnsList, double addOnsTotal, String discountType,
                           double discountAmount, String giftName, double total) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.addOnsList = addOnsList;
        this.addOnsTotal = addOnsTotal;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.giftName = giftName;
        this.total = total;
    }

    // Getters
    public String getProductName() { return productName; }
    public double getProductPrice() { return productPrice; }
    public int getQuantity() { return quantity; }
    public double getSubtotal() { return subtotal; }
    public String getAddOnsList() { return addOnsList; }
    public double getAddOnsTotal() { return addOnsTotal; }
    public String getDiscountType() { return discountType; }
    public double getDiscountAmount() { return discountAmount; }
    public String getGiftName() { return giftName; }
    public double getTotal() { return total; }
}