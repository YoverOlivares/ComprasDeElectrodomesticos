package unc.edu.pe.comprasdeelectrodomesticos.data.repository;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import unc.edu.pe.comprasdeelectrodomesticos.data.remote.ApiService;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;
import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;

public class ProductRepositoryImpl implements ProductRepository {

    private final ApiService apiService;
    private final MutableLiveData<List<Product>> productsLiveData;

    public ProductRepositoryImpl() {
        productsLiveData = new MutableLiveData<>();

        // Configuración básica de Retrofit
        // Nota: En una app real, esto se inyectaría con Dagger/Hilt
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public LiveData<List<Product>> getProducts() {
        // Llamada asíncrona a la API
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
                // Aquí podrías manejar errores, por ejemplo enviando una lista vacía o null
                productsLiveData.setValue(null);
            }
        });

        return productsLiveData;
    }
}