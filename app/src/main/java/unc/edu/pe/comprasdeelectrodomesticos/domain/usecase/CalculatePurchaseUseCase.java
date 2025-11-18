package unc.edu.pe.comprasdeelectrodomesticos.domain.usecase;

import java.util.List;

import unc.edu.pe.comprasdeelectrodomesticos.domain.model.AddOn;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.PurchaseSummary;
import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;
import unc.edu.pe.comprasdeelectrodomesticos.util.DiscountStrategy;
import unc.edu.pe.comprasdeelectrodomesticos.util.NoCardDiscount;
import unc.edu.pe.comprasdeelectrodomesticos.util.StoreCardDiscount;

public class CalculatePurchaseUseCase {

    public PurchaseSummary execute(String productName, double price, int quantity,
                                   List<AddOn> addOns, String discountType, String giftName) {

        // 1. Calcular Subtotal [cite: 35]
        double subtotal = price * quantity;

        // 2. Calcular Adicionales [cite: 36]
        double addOnsTotal = 0;
        StringBuilder addOnsNames = new StringBuilder();

        for (AddOn addOn : addOns) {
            if (addOn.isSelected()) {
                addOnsTotal += addOn.getPrice();
                if (addOnsNames.length() > 0) addOnsNames.append(", ");
                addOnsNames.append(addOn.getName());
            }
        }

        if (addOnsNames.length() == 0) addOnsNames.append("Ninguno");

        // 3. Calcular Descuento (Strategy Pattern) [cite: 37]
        DiscountStrategy strategy;
        if (Constants.DISCOUNT_TYPE_STORE_CARD.equals(discountType)) {
            strategy = new StoreCardDiscount();
        } else {
            strategy = new NoCardDiscount();
        }

        double discountAmount = strategy.calculateDiscount(subtotal);

        // 4. Calcular Total Final [cite: 38]
        double total = subtotal + addOnsTotal - discountAmount;

        // Retornar objeto resumen
        return new PurchaseSummary(
                productName,
                price,
                quantity,
                subtotal,
                addOnsNames.toString(),
                addOnsTotal,
                discountType,
                discountAmount,
                giftName,
                total
        );
    }
}