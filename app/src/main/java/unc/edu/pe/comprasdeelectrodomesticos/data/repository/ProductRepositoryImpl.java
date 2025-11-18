package unc.edu.pe.comprasdeelectrodomesticos.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;
// Retrofit y API Service son innecesarios mientras se use el mock, pero los mantenemos para el futuro.
// import retrofit2.Call;
// import retrofit2.Callback;
// import retrofit2.Response;
// import retrofit2.Retrofit;
// import retrofit2.converter.gson.GsonConverterFactory;
// import unc.edu.pe.comprasdeelectrodomesticos.data.remote.ApiService;
// import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;

public class ProductRepositoryImpl implements ProductRepository {

    // private final ApiService apiService; // Descomentar para API real
    private final MutableLiveData<List<Product>> productsLiveData;

    public ProductRepositoryImpl() {
        productsLiveData = new MutableLiveData<>();

        // La configuración de Retrofit se puede comentar o eliminar temporalmente
        // Retrofit retrofit = new Retrofit.Builder()...
        // apiService = retrofit.create(ApiService.class);
    }

    @Override
    public LiveData<List<Product>> getProducts() {

        // --- INYECCIÓN DE DATOS MOCK TEMPORALES ---
        List<Product> mockProducts = createMockProducts();
        productsLiveData.setValue(mockProducts);
        // ------------------------------------------

        /* // Descomentar el siguiente bloque para usar la API real con Retrofit
        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productsLiveData.setValue(response.body());
                } else {
                    Log.e("Repo", "Error en la respuesta: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Repo", "Fallo en la conexión: " + t.getMessage());
                productsLiveData.setValue(null);
            }
        });
        */

        return productsLiveData;
    }

    /**
     * Función que crea la lista de productos simulada.
     */
    private List<Product> createMockProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("lavadora", "Lavadora LG 18kg", 1299.00, "url_lavadora"));
        products.add(new Product("refrigeradora", "Refrigeradora Samsung 500L", 2499.00, "url_refrigeradora"));
        products.add(new Product("cocina", "Cocina Indurama 6 hornillas", 899.00, "url_cocina"));
        products.add(new Product("rapdoucha", "Rap Ducha Eléctrica", 199.00, "url_rapdoucha"));
        products.add(new Product("microondas", "Microondas LG 42L", 549.00, "url_microondas"));

        return products;
    }
}