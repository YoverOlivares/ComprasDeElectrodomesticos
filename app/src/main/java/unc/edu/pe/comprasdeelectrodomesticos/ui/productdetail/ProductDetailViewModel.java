package unc.edu.pe.comprasdeelectrodomesticos.ui.productdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

import unc.edu.pe.comprasdeelectrodomesticos.domain.model.AddOn;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Gift;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.PurchaseSummary;
import unc.edu.pe.comprasdeelectrodomesticos.domain.usecase.CalculatePurchaseUseCase;
import unc.edu.pe.comprasdeelectrodomesticos.domain.usecase.GenerateGiftUseCase;
import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;

public class ProductDetailViewModel extends ViewModel {

    // Estados observables
    private final MutableLiveData<Integer> quantity = new MutableLiveData<>(1);
    private final MutableLiveData<Gift> gift = new MutableLiveData<>();
    private final MutableLiveData<PurchaseSummary> purchaseSummary = new MutableLiveData<>();

    // Datos internos
    private Product currentProduct;
    private final List<AddOn> selectedAddOns = new ArrayList<>();
    private String selectedDiscountType = Constants.DISCOUNT_TYPE_NO_CARD; // Default

    // Use Cases
    private final GenerateGiftUseCase generateGiftUseCase;
    private final CalculatePurchaseUseCase calculatePurchaseUseCase;

    public ProductDetailViewModel() {
        generateGiftUseCase = new GenerateGiftUseCase();
        calculatePurchaseUseCase = new CalculatePurchaseUseCase();

        // Inicializar AddOns disponibles
        selectedAddOns.add(new AddOn("Instalación", Constants.PRICE_INSTALLATION));
        selectedAddOns.add(new AddOn("Mantenimiento", Constants.PRICE_MAINTENANCE));
    }

    public void setProduct(Product product) {
        this.currentProduct = product;
    }

    // --- Lógica de Cantidad ---
    public LiveData<Integer> getQuantity() { return quantity; }

    public void increaseQuantity() {
        if (quantity.getValue() != null) {
            quantity.setValue(quantity.getValue() + 1);
        }
    }

    public void decreaseQuantity() {
        if (quantity.getValue() != null && quantity.getValue() > 1) {
            quantity.setValue(quantity.getValue() - 1);
        }
    }

    // --- Lógica de Adicionales ---
    public void setInstallationSelected(boolean isSelected) {
        // Asumimos índice 0 es instalación
        selectedAddOns.get(0).setSelected(isSelected);
    }

    public void setMaintenanceSelected(boolean isSelected) {
        // Asumimos índice 1 es mantenimiento
        selectedAddOns.get(1).setSelected(isSelected);
    }

    // --- Lógica de Descuento ---
    public void setDiscountType(String type) {
        this.selectedDiscountType = type;
    }

    // --- Lógica de Regalo ---
    public LiveData<Gift> getGift() { return gift; }

    public void generateGift() {
        Gift newGift = generateGiftUseCase.execute();
        gift.setValue(newGift);
    }

    // --- Lógica de Compra ---
    public LiveData<PurchaseSummary> getPurchaseSummary() { return purchaseSummary; }

    public void processPurchase() {
        if (currentProduct == null) return;

        String giftName = (gift.getValue() != null) ? gift.getValue().getName() : "Ninguno";

        PurchaseSummary summary = calculatePurchaseUseCase.execute(
                currentProduct.getName(),
                currentProduct.getPrice(),
                quantity.getValue(),
                selectedAddOns,
                selectedDiscountType,
                giftName
        );

        purchaseSummary.setValue(summary);
    }
}