package unc.edu.pe.comprasdeelectrodomesticos.data.repository;

import androidx.lifecycle.LiveData;
import java.util.List;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;

public interface ProductRepository {
    LiveData<List<Product>> getProducts();
}