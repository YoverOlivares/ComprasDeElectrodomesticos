package unc.edu.pe.comprasdeelectrodomesticos.ui.productdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import unc.edu.pe.comprasdeelectrodomesticos.databinding.ActivityProductDetailBinding;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Product;
import unc.edu.pe.comprasdeelectrodomesticos.ui.summary.SummaryActivity;
import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;

public class ProductDetailActivity extends AppCompatActivity {

    private ActivityProductDetailBinding binding;
    private ProductDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);

        setupInitialData();
        setupListeners();
        observeViewModel();
    }

    private void setupInitialData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString("p_id");
            String name = extras.getString("p_name");
            double price = extras.getDouble("p_price");
            String img = extras.getString("p_image");

            Product product = new Product(id, name, price, img);
            viewModel.setProduct(product);

            // Setear UI inicial
            binding.tvProductName.setText(name);
            binding.tvProductPrice.setText("S/. " + price);

            // Cargar imagen
            int imageResId = getResources().getIdentifier(img, "drawable", getPackageName());
            if (imageResId != 0) {
                binding.ivProductDetailImage.setImageResource(imageResId);
            }
        }
    }

    private void setupListeners() {
        // Botones de cantidad
        binding.btnIncrease.setOnClickListener(v -> viewModel.increaseQuantity());
        binding.btnDecrease.setOnClickListener(v -> viewModel.decreaseQuantity());

        // Checkbox Adicionales
        binding.cbInstallation.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.setInstallationSelected(isChecked));

        binding.cbMaintenance.setOnCheckedChangeListener((buttonView, isChecked) ->
                viewModel.setMaintenanceSelected(isChecked));

        // RadioGroup Descuento
        binding.rgDiscount.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == binding.rbStoreCard.getId()) {
                viewModel.setDiscountType(Constants.DISCOUNT_TYPE_STORE_CARD);
            } else {
                viewModel.setDiscountType(Constants.DISCOUNT_TYPE_NO_CARD);
            }
        });

        // Botón Obsequio
        binding.btnGenerateGift.setOnClickListener(v -> viewModel.generateGift());

        // Botón Comprar
        binding.btnBuy.setOnClickListener(v -> viewModel.processPurchase());
    }

    private void observeViewModel() {
        // Observar Cantidad
        viewModel.getQuantity().observe(this, qty ->
                binding.tvQuantity.setText(String.valueOf(qty)));

        // Observar Regalo Generado
        viewModel.getGift().observe(this, gift -> {
            if (gift != null) {
                // Mostrar el TextView con el nombre
                binding.tvGiftName.setVisibility(View.VISIBLE);
                binding.tvGiftName.setText("¡Ganaste: " + gift.getName() + "!");

                // Mostrar el ImageView con la imagen del regalo
                binding.ivGiftImage.setVisibility(View.VISIBLE);
                binding.ivGiftImage.setImageResource(gift.getImageResourceId());
            } else {
                // Ocultar si no hay regalo
                binding.tvGiftName.setVisibility(View.GONE);
                binding.ivGiftImage.setVisibility(View.GONE);
            }
        });

        // Observar Resultado de Compra (Navegación)
        viewModel.getPurchaseSummary().observe(this, summary -> {
            if (summary != null) {
                Intent intent = new Intent(ProductDetailActivity.this, SummaryActivity.class);
                intent.putExtra(Constants.EXTRA_SUMMARY, summary);
                startActivity(intent);
            }
        });
    }
}