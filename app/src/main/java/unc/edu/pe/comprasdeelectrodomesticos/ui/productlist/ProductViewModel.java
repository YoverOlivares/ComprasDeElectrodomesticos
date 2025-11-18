package unc.edu.pe.comprasdeelectrodomesticos.ui.productlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import unc.edu.pe.comprasdeelectrodomesticos.data.repository.ProductRepository;
import unc.edu.pe.comprasdeelectrodomesticos.data.repository.ProductRepositoryImpl;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;

public class ProductViewModel extends ViewModel {

    private final ProductRepository repository;
    private LiveData<List<Product>> products;

    public ProductViewModel() {
        // En una app real usaríamos Inyección de Dependencias (Hilt/Dagger)
        repository = new ProductRepositoryImpl();
    }

    public LiveData<List<Product>> getProducts() {
        if (products == null) {
            products = repository.getProducts();
        }
        return products;
    }
}