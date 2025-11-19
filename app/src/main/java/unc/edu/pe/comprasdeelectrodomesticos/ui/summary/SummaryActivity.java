package unc.edu.pe.comprasdeelectrodomesticos.ui.summary;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import unc.edu.pe.comprasdeelectrodomesticos.databinding.ActivitySummaryBinding;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.PurchaseSummary;
import unc.edu.pe.comprasdeelectrodomesticos.ui.productlist.MainActivity;
import unc.edu.pe.comprasdeelectrodomesticos.util.Constants;

public class SummaryActivity extends AppCompatActivity {

    private ActivitySummaryBinding binding;
    private SummaryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SummaryViewModel.class);

        // Recuperar objeto serializable
        PurchaseSummary summary = (PurchaseSummary) getIntent().getSerializableExtra(Constants.EXTRA_SUMMARY);

        if (summary != null) {
            viewModel.setSummary(summary);
            displayData(summary);
        }

        // IMPORTANTE: Llamar al método para configurar el botón
        setupBackButton();
    }

    private void displayData(PurchaseSummary data) {
        binding.tvSummaryProductName.setText(data.getProductName());
        binding.tvSummaryPrice.setText("Precio Unit: S/. " + data.getProductPrice());
        binding.tvSummaryQuantity.setText("Cant: " + data.getQuantity());

        binding.tvSummarySubtotal.setText("Subtotal: S/. " + String.format("%.2f", data.getSubtotal()));

        binding.tvAddonsList.setText(data.getAddOnsList());
        binding.tvAddonsTotal.setText("+ S/. " + String.format("%.2f", data.getAddOnsTotal()));

        binding.tvDiscountType.setText(data.getDiscountType());
        binding.tvDiscountAmount.setText("- S/. " + String.format("%.2f", data.getDiscountAmount()));

        binding.tvGiftName.setText(data.getGiftName());

        binding.tvTotalAmount.setText("S/. " + String.format("%.2f", data.getTotal()));
    }

    private void setupBackButton() {
        binding.btnBack.setOnClickListener(v -> {
            // Cerrar todas las actividades y volver al inicio
            Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
            startActivity(intent);
            finishAffinity(); // Cierra todas las actividades del stack
        });
    }
}