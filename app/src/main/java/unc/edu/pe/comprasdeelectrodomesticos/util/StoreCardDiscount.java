package unc.edu.pe.comprasdeelectrodomesticos.util;

public class StoreCardDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * 0.10;
    }

    @Override
    public String getDescription() {
        return Constants.DISCOUNT_TYPE_STORE_CARD;
    }
}