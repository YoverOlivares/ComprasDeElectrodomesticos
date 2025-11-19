package unc.edu.pe.comprasdeelectrodomesticos.domain.usecase;

import java.util.List;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.AddOn;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.PurchaseSummary;
import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;

public class CalculatePurchaseUseCase {

    public PurchaseSummary execute(String productName, double productPrice, int quantity,
                                   List<AddOn> addOns, String discountType, String giftName) {

        // 1. Calcular Subtotal Base
        double subtotal = productPrice * quantity;

        // 2. Calcular Adicionales (Basado en Porcentaje del Subtotal)
        double totalAddOns = 0;
        StringBuilder addOnsNames = new StringBuilder();

        for (AddOn addOn : addOns) {
            if (addOn.isSelected()) {
                // AQUÍ ESTÁ EL CAMBIO CLAVE:
                // El valor del addOn ahora es un porcentaje (ej. 0.05), no un precio fijo.
                // Calculamos cuánto dinero representa ese porcentaje sobre el subtotal.
                double addOnCost = subtotal * addOn.getPrice();

                totalAddOns += addOnCost;

                // Agregamos al texto del resumen
                if (addOnsNames.length() > 0) addOnsNames.append(", ");
                addOnsNames.append(addOn.getName());
            }
        }

        if (totalAddOns == 0) addOnsNames.append("Ninguno");

        // 3. Calcular Descuento
        double discountPercentage = 0;
        if (discountType.equals(Constants.DISCOUNT_TYPE_STORE_CARD)) {
            discountPercentage = 0.10; // 10%
        } else {
            discountPercentage = 0.05; // 5%
        }

        // El descuento suele aplicar a la suma del subtotal (algunas tiendas incluyen adicionales, otras no).
        // Asumiremos que el descuento aplica solo al producto para no complicar.
        double discountAmount = subtotal * discountPercentage;

        // 4. Calcular Total Final
        double total = subtotal + totalAddOns - discountAmount;

        // 5. Retornar el objeto resumen
        return new PurchaseSummary(
                productName,
                productPrice,
                quantity,
                subtotal,
                addOnsNames.toString(),
                totalAddOns,
                discountType,
                discountAmount,
                giftName,
                total
        );
    }
}