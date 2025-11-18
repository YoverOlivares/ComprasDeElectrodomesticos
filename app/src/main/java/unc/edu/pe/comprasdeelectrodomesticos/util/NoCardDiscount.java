package unc.edu.pe.comprasdeelectrodomesticos.util;

public class NoCardDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * 0.05;
    }

    @Override
    public String getDescription() {
        return Constants.DISCOUNT_TYPE_NO_CARD;
    }
}