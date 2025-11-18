package unc.edu.pe.comprasdeelectrodomesticos.data.remote;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;

public interface ApiService {
    // Endpoint para obtener el JSON de productos
    // Asegúrate de que la URL Base en Constants termine en '/' y esta parte complete la ruta
    @GET("ruta/a/tu/archivo.json")
    Call<List<Product>> getProducts();
}