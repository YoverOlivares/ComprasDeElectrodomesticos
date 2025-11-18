package unc.edu.pe.comprasdeelectrodomesticos.ui.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import unc.edu.pe.comprasdeelectrodomesticos.databinding.ActivityMainBinding;
import unc.edu.pe.comprasdeelectrodomesticos.ui.productdetail.ProductDetailActivity;
import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProductViewModel viewModel;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Configurar ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Configurar ViewModel
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        // 3. Configurar RecyclerView
        setupRecyclerView();

        // 4. Observar datos
        observeProducts();
    }

    private void setupRecyclerView() {
        adapter = new ProductAdapter(product -> {
            // Navegación al hacer clic en un producto
            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
            // Pasamos datos básicos para evitar volver a consultar la API en la siguiente pantalla
            intent.putExtra("p_id", product.getId());
            intent.putExtra("p_name", product.getName());
            intent.putExtra("p_price", product.getPrice());
            intent.putExtra("p_image", product.getImageUrl());
            startActivity(intent);
        });

        binding.rvProductList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProductList.setAdapter(adapter);
    }

    private void observeProducts() {
        binding.progressBar.setVisibility(View.VISIBLE);

        viewModel.getProducts().observe(this, products -> {
            binding.progressBar.setVisibility(View.GONE);
            if (products != null) {
                adapter.setProducts(products);
            }
        });
    }
}