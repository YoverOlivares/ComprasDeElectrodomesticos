package unc.edu.pe.comprasdeelectrodomesticos.util;

public interface DiscountStrategy {
    double calculateDiscount(double subtotal);
    String getDescription();
}